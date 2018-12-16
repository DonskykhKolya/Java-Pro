package com.gmail.ndonskih63.dao;

import com.gmail.ndonskih63.details.Clients;

import java.sql.*;

import static com.gmail.ndonskih63.dataconn.Connector.getConnection;

public class ClientsDAO extends AbstractDAO<Integer, Clients> {
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS Clients";
    private static final String CREATE_TABLE = "CREATE TABLE Clients(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "login VARCHAR(10) NOT NULL, " +
                "password VARCHAR(20) NOT NULL, " +
                "name VARCHAR(15) NOT NULL, " +
                "address VARCHAR(50) NOT NULL, " +
                "phone VARCHAR(15) NOT NULL)";

    public ClientsDAO(Connection cn) {
        super(cn, "Clients");
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
    public Clients catchOut(String login, String password) {
        Clients client = null;
        try(PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM Clients WHERE login = ? AND password = ?")){
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                client = new Clients(login, password);
                client.setId(rs.getInt("id"));
                client.setName(rs.getString("name"));
                client.setAddress(rs.getString("address"));
                client.setPhone(rs.getString("phone"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }
    public boolean exist(String login, String password) {
        Clients client = catchOut(login, password);
        return client != null;
    }
}
