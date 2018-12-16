package com.gmail.ndonskih63;

import com.gmail.ndonskih63.dao.ClientsDAO;
import com.gmail.ndonskih63.dao.GoodsDAO;
import com.gmail.ndonskih63.dao.OrdersDAO;
import com.gmail.ndonskih63.dataconn.Connector;
import com.gmail.ndonskih63.details.Clients;
import com.gmail.ndonskih63.details.Goods;
import com.gmail.ndonskih63.details.Orders;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class OrderTable {

    private Connection cn;
    private ClientsDAO clientsDAO;
    private OrdersDAO ordersDAO;
    private GoodsDAO goodsDAO;
    private Scanner sc = new Scanner(System.in);
    private Clients clnts;

    public OrderTable() {
        initDAO();
    }

    private void initDAO() {
        cn = Connector.getConnection();
        clientsDAO = new ClientsDAO(cn);
        clientsDAO.init();
        ordersDAO = new OrdersDAO(cn);
        ordersDAO.init();
        goodsDAO = new GoodsDAO(cn);
        goodsDAO.init();
    }

    public void loginMenu() {
        System.out.println("1: registration");
        System.out.println("2: sign in");
        System.out.println("↓↓↓");

        String str = sc.nextLine();
        switch (str) {
            case "1":
                registration();
                break;
            case "2":
                signIn();
                break;
            default:
                close();
                return;
        }
    }

    private void registration() {
        System.out.println("Enter your login: ");
        String login = sc.nextLine();
        System.out.println("Enter your password: ");
        String password = sc.nextLine();
        System.out.println("Enter your name: ");
        String name = sc.nextLine();
        System.out.println("Enter your address: ");
        String address = sc.nextLine();
        System.out.println("Enter your phone: ");
        String phone = sc.nextLine();

        Clients client = new Clients(login, password);
        client.setName(name);
        client.setAddress(address);
        client.setPhone(phone);

        if (!clientsDAO.exist(login, password)) {
            int res = clientsDAO.add(client);
            if (res != 0) {
                clnts = client;
                mainMenu();
            } else {
                System.out.println("Fail!");
                loginMenu();
            }
        } else {
            System.out.println("This client's login already used!!!");
        }
    }

    private void signIn() {
        System.out.println("Enter your login: ");
        String login = sc.nextLine();
        System.out.println("Enter your password: ");
        String password = sc.nextLine();
        clnts = clientsDAO.catchOut(login, password);
        if (clnts != null) {
            mainMenu();
        } else {
            System.out.println("Wrong data!");
            loginMenu();
        }
    }

    private void mainMenu() {
        System.out.println("1: add order.");
        System.out.println("2: add good.");
        System.out.println("3: show order list");
        System.out.println("4: show goods list");
        System.out.println("5: show client's info");
        System.out.println("↓↓↓");

        String str = sc.nextLine();
        switch (str) {
            case "1":
                addOrder();
                break;
            case "2":
                addGood();
                break;
            case "3":
                showOrderList();
                break;
            case "4":
                showGoodsList();
                break;
            case "5":
                showClientsInfo();
                break;
            default:
                close();
                return;
        }
    }

    private void addGood() {
        System.out.println("Enter good name: ");
        String name = sc.nextLine();
        System.out.println("Enter good description: ");
        String desc = sc.nextLine();
        System.out.println("Enter good price: ");
        String priceStr = sc.nextLine();
        double price = Double.parseDouble(priceStr);

        Goods good = new Goods(name, price);
        good.setDescription(desc);
        int res = goodsDAO.add(good);
        if (res == 0) {
            System.out.println("Can't add new product.");
        }
    }

    private void showGoodsList() {
        List<Goods> productList = goodsDAO.getAll(Goods.class);
        productList.forEach(System.out::println);
    }

    private void addOrder() {
        System.out.println("Enter 's' to complete your order.");
        Orders order = new Orders();
        order.setClients(clnts);
        order.setDate(LocalDate.now());
        String str = "";
        do {
            System.out.println("Enter good name: ");
            str = sc.nextLine();
            Goods good = goodsDAO.searchByName(str);
            if (good != null) {
                order.addGood(good);
            } else {
                System.out.println("Good with name " + str + " doesn't exist.");
            }
        } while (!str.equals("s"));
        int res = ordersDAO.add(order);
        if (res == 0) {
            System.out.println("Can't save your order. Please, try again later.");
        }
    }

    private void showOrderList() {
        List<Orders> orders = ordersDAO.getOrderList(clnts.getId());
        orders.forEach(System.out::println);
    }

    private void showClientsInfo() {
        System.out.println(clnts.toString());
    }

    private void close() {
        sc.close();
        try {
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}