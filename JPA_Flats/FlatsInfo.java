package com.gmail.ndonskih63;

import javax.persistence.*;

@Entity
@Table(name = "Flats", schema = "newdb")
public class FlatsInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    @Column(name="district", nullable = false)
    private String district;
    @Column(name="address", nullable = false)
    private String address;
    @Column(name="area", nullable = false)
    private double area;
    @Column(name="rooms", nullable = false)
    private int rooms;
    @Column(name="price", nullable = false)
    private Double money;

    public FlatsInfo(String district, String address, double area, int rooms, double money) {
        this.district = district;
        this.address = address;
        this.area = area;
        this.rooms = rooms;
        this.money = money;
    }

    public FlatsInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "FlatsInfo{" +
                "id=" + id +
                ", district='" + district + '\'' +
                ", address='" + address + '\'' +
                ", area=" + area +
                ", rooms=" + rooms +
                ", money=" + money +
                '}';
    }
}
