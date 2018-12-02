package com.gmail.ndonskih63.details;

import java.time.LocalDate;

public class ThisPost {

    private final String id;
    private String text;
    private final Users user;
    private final LocalDate date;

    public ThisPost(String id, String text, Users Users, LocalDate date) {
        this.id = id;
        this.text = text;
        this.user = Users;
        this.date = date;
    }

    public String getId() {

        return id;
    }
    public String getText() {

        return text;
    }
    public void setText(String text) {

        this.text = text;
    }
    public Users getUser() {

        return user;
    }

    public String getDate() {

        return date.toString();
    }

    @Override
    public String toString() {
        return "ThisPost{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", user='" + user.getUsername() + '\'' +
                ", date=" + date.toString() +
                '}';
    }
}
