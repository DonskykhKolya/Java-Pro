package com.gmail.ndonskih63.details;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Orders {
    private int id;
    private LocalDate date;
    private Clients clients;
    private List<Goods> goods = new ArrayList<>();

    public Orders() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Clients getClients() {
        return clients;
    }

    public void setClients(Clients clients) {
        this.clients = clients;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public void addGood(Goods good) {
        goods.add(good);
    }
    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", date=" + date +
                ", clients=" + clients +
                ", goods=" + goods +
                '}';
    }
}
