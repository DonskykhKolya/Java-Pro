package com.gmail.ndonskih63.dao;

import com.gmail.ndonskih63.details.Goods;
import com.gmail.ndonskih63.details.Orders;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static com.gmail.ndonskih63.dataconn.Connector.getConnection;

public class OrdersDAO extends AbstractDAO<Integer, Orders>{
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS Orders";
    private static final String CREATE_TABLE = "CREATE TABLE Orders (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
            "date DATETIME NOT NULL, " + "client_id INT NOT NULL)";
    private static final String DROP_COMPOUND_TABLE = "DROP TABLE IF EXISTS DetailsOfOrders";
    private static final String CREATE_COMPOUND_TABLE = "CREATE TABLE DetailsOfOrders(order_id INT NOT NULL, good_id INT NOT NULL, " +
            "PRIMARY KEY(order_id, good_id))";

    public OrdersDAO(Connection cn) {
        super(cn, "Orders");
    }

    @Override
    public void init() {
        try(Statement st = getConnection().createStatement()){
            st.execute(DROP_TABLE);
            st.execute(CREATE_TABLE);
            st.execute(DROP_COMPOUND_TABLE);
            st.execute(CREATE_COMPOUND_TABLE);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public int add(Orders order) {
        int res = 0;
        try {
            getConnection().setAutoCommit(false);
            try(PreparedStatement ps = getConnection().prepareStatement("INSERT INTO Orders(date, client_id) VALUES(? , ?)")){
                ps.setDate(1, Date.valueOf(order.getDate()));
                ps.setInt(2, order.getClients().getId());
                res = ps.executeUpdate();
            }
            try(Statement st = getConnection().createStatement();
                ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID()")) {
                if(rs.next()){
                    order.setId(rs.getInt(1));
                }
            }
            for (Goods goods : order.getGoods()){
                try(PreparedStatement ps = getConnection().prepareStatement("INSERT INTO DetailsOfOrder(order_id, good_id) VALUES(?, ?)")) {
                    ps.setInt(1, order.getId());
                    ps.setInt(2, goods.getId());
                    ps.executeUpdate();
                }
            }
            getConnection().commit();
            getConnection().setAutoCommit(true);
        } catch (SQLException e) {
            try {
                getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return res;
    }

    public List<Orders> getOrderList(int clientId) {
        List<Orders> oL = new ArrayList<>();
        String str = "SELECT o.id, o.date, g.id, g.name, g.description, g.price FROM Orders as o " +
                "LEFT JOIN DetailsOfOrders as doo ON o.id = doo.order_id "+
                "LEFT JOIN Goods as g ON doo.good_id = g.id WHERE o.client_id=?";
        try(PreparedStatement ps = getConnection().prepareStatement(str)){
            ps.setInt(1, clientId);
            int ids = 0;
            Orders ords = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt(1);
                if(id != ids) {
                    ords = new Orders();
                    ords.setId(id);
                    ords.setDate(rs.getDate(2).toLocalDate());
                    ids = id;
                    oL.add(ords);
                }
                Goods gds = new Goods();
                gds.setId(rs.getInt(3));
                gds.setName(rs.getString(4));
                gds.setDescription(rs.getString(5));
                gds.setPrice(rs.getDouble(6));
                ords.addGood(gds);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oL;
    }

    @Override
    public int change(Orders order) {
        System.out.println("Method not supported.");
        return 0;
    }

    @Override
    public void delete(Integer id) {
        System.out.println("Method not supported.");
    }

    @Override
    public List<Orders> getAll(Class<Orders> cls) {
        System.out.println("Method not supported.");
        return null;
    }
}
