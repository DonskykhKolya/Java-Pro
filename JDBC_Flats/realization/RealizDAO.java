package realization;

import data.ParamsDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RealizDAO implements ParamsDAO {
    private final Connection cn;

    public RealizDAO(Connection cn) {
        this.cn = cn;
    }

    @Override
    public void init() {
        try (Statement statement = cn.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS FlatsTable");
            statement.execute("CREATE TABLE FlatsTable(district VARCHAR(25) NOT NULL," + "address VARCHAR(40) NOT NULL," +
                    "area DOUBLE NOT NULL, " + "rooms INT NOT NULL," + "money DOUBLE NOT NULL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addFlat(String district, String address, double area, int rooms, double money) {
        try (PreparedStatement ps = cn.prepareStatement("INSERT INTO FlatsTable VALUES (?,?,?,?,?)")) {
            ps.setString(1, district);
            ps.setString(2, address);
            ps.setDouble(3, area);
            ps.setInt(4, rooms);
            ps.setDouble(5, money);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFlat(String addreses){
        try (PreparedStatement ps = cn.prepareStatement("DELETE FROM FlatsTable WHERE address = ?")) {
            ps.setString(1, addreses);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Flats> getAll() {
        List<Flats> flatssList = new ArrayList<>();
        try (Statement st = cn.createStatement()) {
            try (ResultSet rs = st.executeQuery("SELECT * FROM FlatsTable")) {
                while (rs.next()) {
                    String district;
                    String address;
                    double area;
                    int rooms;
                    double money;
                    district = rs.getString(1);
                    address = rs.getString(2);
                    area = rs.getDouble(3);
                    rooms = rs.getInt(4);
                    money = rs.getDouble(5);
                    flatssList.add(new Flats(district, address, area, rooms, money));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flatssList;
    }

    public List<Flats> getByMoney(double minMoney, double maxMoney){
        List<Flats> flatsList = new ArrayList<>();
        try (PreparedStatement ps = cn.prepareStatement("SELECT * FROM FlatsTable " +
                "WHERE MONEY >= ? AND MONEY <= ?")) {
            ps.setDouble(1, minMoney);
            ps.setDouble(2, maxMoney);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    double money;
                    String district;
                    String address;
                    double area;
                    int rooms;
                    district = rs.getString(1);
                    address = rs.getString(2);
                    area = rs.getDouble(3);
                    rooms = rs.getInt(4);
                    money = rs.getDouble(5);
                    flatsList.add(new Flats(district, address, area, rooms, money));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flatsList;
    }

    public List<Flats> getByDistAndRooms (String enDistrict, int numRooms){
        List<Flats> flatsList = new ArrayList<>();
        try (PreparedStatement ps = cn.prepareStatement("SELECT * FROM FlatsTable " +
                "WHERE district=? AND rooms=?")) {
            ps.setString(1, enDistrict);
            ps.setInt(2, numRooms);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String district;
                    String address;
                    double area;
                    int rooms;
                    double money;
                    district = rs.getString(1);
                    address = rs.getString(2);
                    area = rs.getDouble(3);
                    rooms = rs.getInt(4);
                    money = rs.getDouble(5);
                    flatsList.add(new Flats(district, address, area, rooms, money));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flatsList;
    }

}


