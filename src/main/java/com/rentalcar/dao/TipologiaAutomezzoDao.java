package com.rentalcar.dao;

import com.rentalcar.entity.TipologiaAutomezzo;
import com.rentalcar.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class TipologiaAutomezzoDao {
    public List<TipologiaAutomezzo> getAllTipologie() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM TipologiaAutomezzo", TipologiaAutomezzo.class).list();
        }
    }
}
