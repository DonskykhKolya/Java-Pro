package realization;

public class Flats {
    private String district;
    private String address;
    private double area;
    private int rooms;
    private double money;

    public Flats(String district, String address, double area, int rooms, double money) {
        this.district = district;
        this.address = address;
        this.area = area;
        this.rooms = rooms;
        this.money = money;
    }

    public Flats() {
    }

    @Override
    public String toString() {
        return "Flats{" +
                "district='" + district + '\'' +
                ", address='" + address + '\'' +
                ", area=" + area +
                ", rooms=" + rooms +
                ", money=" + money +
                '}';
    }
}
