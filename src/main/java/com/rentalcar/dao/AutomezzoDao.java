package com.rentalcar.dao;

import com.rentalcar.entity.Automezzo;
import com.rentalcar.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class AutomezzoDao {
    public List<Automezzo> getAllAutomezzi() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Automezzo", Automezzo.class).list();
        }
    }
}
