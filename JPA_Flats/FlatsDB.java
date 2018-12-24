package com.gmail.ndonskih63;

import com.gmail.ndonskih63.DAO.FlatsDAO;
import com.gmail.ndonskih63.DAO.FlatsDAOImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

import static javax.swing.UIManager.get;

public class FlatsDB {

    private EntityManagerFactory emf;
    private EntityManager em;
    private FlatsDAO dao;
    private Scanner sc;

    public void printFlatsMenu() {
        try {
            init();
            try {
                while (true) {
                    System.out.println("1. Add flat.");
                    System.out.println("2. View flats");
                    System.out.println("3. Delete flat");
                    System.out.println("4. View by money");
                    System.out.println("5. View by district");
                    System.out.println("Any key for Exit");
                    System.out.println("↧↧↧↧↧");
                    String str = sc.nextLine();
                    switch (str) {
                        case "1":
                            addFlat();
                            break;
                        case "2":
                            getAllFlats();
                            break;
                        case "3":
                            deleteFlat();
                            break;
                        case "4":
                            getByMoney();
                            break;
                        case "5":
                            getByDistrict();
                            break;
                        default:
                            return;
                    }
                }
            } finally {
                sc.close();
                em.close();
                emf.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
    }

    private void init() {
        emf = Persistence.createEntityManagerFactory("JPAFlats");
        em = emf.createEntityManager();
        dao = new FlatsDAOImpl(em);
        sc = new Scanner(System.in);
    }

    private void addFlat() {
        System.out.println("Enter district");
        String district = sc.nextLine();
        System.out.println("Enter address");
        String address = sc.nextLine();
        System.out.println("Enter area");
        double area = Double.parseDouble(sc.nextLine());
        System.out.println("Enter rooms");
        int rooms = Integer.parseInt(sc.nextLine());
        System.out.println("Enter money");
        double money = Double.parseDouble(sc.nextLine());
        dao.save(new FlatsInfo(district, address, area, rooms, money));
    }

    private void deleteFlat() {
        System.out.println("Enter id");
        String strId = sc.nextLine();
        long id = Long.parseLong(strId);
        dao.delFlat(id);
    }


    private void getAllFlats() {
        dao.getAll().forEach(System.out::println);
    }

    private void getByMoney() {
        System.out.print("Enter price from: ");
        String fromString = sc.nextLine();
        Double from = Double.parseDouble(fromString);
        System.out.print("Enter price to: ");
        String toString = sc.nextLine();
        Double to = Double.parseDouble(toString);
        dao.searchByMoney(from, to).forEach(System.out::println);
    }

    private void getByDistrict() {
        System.out.println("Enter district");
        String district = sc.nextLine();
        dao.searchByDistrict(district).forEach(System.out::println);
    }
}

