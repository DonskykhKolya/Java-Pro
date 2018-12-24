package com.gmail.ndonskih63.DAO;

import com.gmail.ndonskih63.FlatsInfo;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlatsDAOImpl implements FlatsDAO {

    private EntityManager em;

    public FlatsDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(FlatsInfo fi) {
        try {
            em.getTransaction().begin();
            em.persist(fi);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public void delFlat(long id) {
        FlatsInfo fi = em.find(FlatsInfo.class, id);
        if (fi == null) {
            System.out.println("Flat not found!");
            return;
        }
        em.getTransaction().begin();
        try {
            em.remove(fi);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public List<FlatsInfo> searchByMoney(Double from, Double to) {
        List<FlatsInfo> flatsInfos = new ArrayList<>();
        try {
            Query query = em.createQuery("select d from FlatsInfo d where d.money between :from and :to");
            query.setParameter("from", from);
            query.setParameter("to", to);
            flatsInfos.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flatsInfos;
    }

    @Override
    public List<FlatsInfo> searchByDistrict(String district) {
        List<FlatsInfo> flatsInfos = new ArrayList<>();
        try {
            Query query = em.createQuery("select d from FlatsInfo d where d.district = :district");
            query.setParameter("district", district);
            flatsInfos.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flatsInfos;
    }

    @Override
    public List<FlatsInfo> getAll() {
        List<FlatsInfo> flatsInfos = new ArrayList<>();
        flatsInfos.addAll(em.createQuery("select d from FlatsInfo d").getResultList());
        return flatsInfos;
    }

}
