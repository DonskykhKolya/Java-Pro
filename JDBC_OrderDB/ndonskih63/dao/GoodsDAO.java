package com.gmail.ndonskih63.dao;

import com.gmail.ndonskih63.details.Goods;

import java.sql.*;

import static com.gmail.ndonskih63.dataconn.Connector.getConnection;

public class GoodsDAO extends AbstractDAO<Integer, Goods> {

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS Goods";
    private static final String CREATE_TABLE = "CREATE TABLE Goods(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
            "name VARCHAR(30) NOT NULL, " +
            "description VARCHAR(200) NOT NULL, " +
            "price DOUBLE NOT NULL)";

    public GoodsDAO(Connection cn) {
        super(cn, "Goods");
    }

    @Override
    public void init() {
        try(Statement st = getConnection().createStatement()){
            st.execute(DROP_TABLE);
            st.execute(CREATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Goods searchByName(String name){
        Goods good = null;
        try (PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM Goods WHERE name=?")){
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                good = new Goods();
                good.setId(rs.getInt("id"));
                good.setName(name);
                good.setDescription(rs.getString("description"));
                good.setPrice(rs.getDouble("price"));
            }
            rs.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return good;
    }
}
