package realization;

import data.DataHat;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        RealizDAO rdao = new RealizDAO(DriverManager.getConnection(DataHat.DB_CONN, DataHat.DB_USERNAME, DataHat.DB_PASSWORD));

        Scanner scan = new Scanner(System.in);
        rdao.init();
        while (true){
            System.out.println("1. Add flat.");
            System.out.println("2. View flats");
            System.out.println("3. Delete flat");
            System.out.println("4. View by money");
            System.out.println("5. View by district and rooms");
            System.out.println("Any key for Exit");
            System.out.println("↧↧↧↧↧");
            String str = scan.nextLine();

            switch (str){
                default: return;
                case "1": {
                    System.out.println("Enter district");
                    String district = scan.nextLine();
                    System.out.println("Enter address");
                    String address = scan.nextLine();
                    System.out.println("Enter area");
                    double area = Double.parseDouble(scan.nextLine());
                    System.out.println("Enter rooms");
                    int rooms = Integer.parseInt(scan.nextLine());
                    System.out.println("Enter money");
                    double money = Double.parseDouble(scan.nextLine());
                    rdao.addFlat(district, address, area, rooms, money);
                    break;
                }
                case "2": {
                    System.out.println(rdao.getAll());
                    break;
                }
                case "3": {
                    System.out.println("Enter address");
                    String address = scan.nextLine();
                    rdao.deleteFlat(address);
                    break;
                }
                case "4":{
                    double minMoney, maxMoney;
                    System.out.println("Enter min money");
                    minMoney = Double.parseDouble(scan.nextLine());
                    System.out.println("Enter max money");
                    maxMoney = Double.parseDouble(scan.nextLine());
                    System.out.println(rdao.getByMoney(minMoney,maxMoney));
                    break;
                }
                case "5":{
                    System.out.println("Enter district");
                    String district = scan.nextLine();
                    System.out.println("Enter rooms");
                    int rooms = Integer.parseInt(scan.nextLine());
                    System.out.println(rdao.getByDistAndRooms(district,rooms));
                    break;
                }
            }
        }
    }
}
