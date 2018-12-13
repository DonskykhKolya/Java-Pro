package data;

import realization.Flats;

import java.util.List;

public abstract interface ParamsDAO {
        public void init();
        public void addFlat(String district, String address, double area, int rooms, double money);
        public void deleteFlat(String addreses);
        public List<Flats> getAll();
}
