package com.gmail.ndonskih63.DAO;

import com.gmail.ndonskih63.FlatsInfo;

import java.util.List;

public abstract interface FlatsDAO {

    public void save(FlatsInfo fi);
    public void delFlat(long id);
    public List<FlatsInfo> searchByMoney(Double from, Double to);
    public List<FlatsInfo> searchByDistrict(String district);
    List<FlatsInfo> getAll();

}
