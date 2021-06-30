package com.rentalcar.dao;

import com.rentalcar.entity.TipologiaAutomezzo;
import com.rentalcar.entity.TipologiaUtente;
import com.rentalcar.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class TipologiaAutomezzoDao {
    public List<TipologiaAutomezzo> getAllTipologie() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM TipologiaAutomezzo").list();
        }
    }

    public TipologiaAutomezzo getCategoria(String categoria) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM TipologiaAutomezzo WHERE categoria = :miaCategoria ").setParameter("miaCategoria", categoria);
            return (TipologiaAutomezzo) query.uniqueResult();
        }
    }
}
