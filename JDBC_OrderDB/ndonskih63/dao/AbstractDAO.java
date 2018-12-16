package com.gmail.ndonskih63.dao;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractDAO <K, T>{
    private final Connection cn;
    private final String table;

    public AbstractDAO(Connection cn, String table) {
        this.cn = cn;
        this.table = table;
    }

    public Connection getCn() {
        return cn;
    }

    public abstract void init();

    public int add(T instance) {
        return addOrUpdate(instance, false);
    }

    public int change(T instance) {
        return addOrUpdate(instance, true);
    }

    private int addOrUpdate(T instance, boolean update) {
        int result = 0;
        Class clazz = instance.getClass();
        Field[] fields = clazz.getDeclaredFields();

        String str = update ? createUpdateQuery(fields) : createInsertQuery(fields);
        int i = 1;
        try (PreparedStatement st = cn.prepareStatement(str)) {
            for(Field field : fields) {
                if(!field.getName().equals("id")) {
                    field.setAccessible(true);
                    st.setObject(i, field.get(instance));
                    i++;
                }
            }
            if(update) {
                Field fieldId = clazz.getDeclaredField("id");
                fieldId.setAccessible(true);
                st.setObject(i, fieldId.get(instance));
            }
            result = st.executeUpdate();
        } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String createUpdateQuery(Field[] fields) {

        StringBuilder queryBuilder = new StringBuilder();

        queryBuilder.append("UPDATE ").append(table).append(" SET ");
        for (Iterator<Field> i = Arrays.asList(fields).iterator(); i.hasNext();) {
            String fieldName = i.next().getName();
            if(!fieldName.equals("id")) {
                queryBuilder.append(fieldName).append("=?");
                if(i.hasNext()) {
                    queryBuilder.append(",");
                }
            }
        }
        queryBuilder.append(" WHERE id=?");
        return queryBuilder.toString();
    }

    private String createInsertQuery(Field[] fields) {

        StringBuilder queryBuilder = new StringBuilder();
        StringBuilder params = new StringBuilder();

        queryBuilder.append("INSERT INTO ").append(table).append(" (");
        for (Iterator<Field> i = Arrays.asList(fields).iterator(); i.hasNext();) {
            String fieldName = i.next().getName();
            if(!fieldName.equals("id")) {
                queryBuilder.append(fieldName);
                params.append("?");
                if(i.hasNext()) {
                    queryBuilder.append(",");
                    params.append(",");
                }
            }
        }
        queryBuilder.append(") VALUES (").append(params.toString()).append(")");

        return queryBuilder.toString();
    }

    public void delete(K id) {
        try (PreparedStatement st = cn.prepareStatement("DELETE FROM " + table + " WHERE id=?")) {
            st.setObject(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<T> getAll(Class<T> cls) {
        List<T> result = new ArrayList<>();
        try (Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM " + table)) {
            ResultSetMetaData md = rs.getMetaData();

            while (rs.next()) {
                T instance = cls.newInstance();
                for (int i = 1; i < md.getColumnCount() + 1; i++) {
                    String columnName = md.getColumnName(i);
                    Field field = cls.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(instance, rs.getObject(columnName));
                }
                result.add(instance);
            }
        } catch (SQLException | IllegalAccessException | InstantiationException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return result;
    }
}

