package com.rentalcar.dao;

import com.rentalcar.entity.TipologiaUtente;
import com.rentalcar.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class TipologiaUtenteDao {
    public List<TipologiaUtente> getAllTipologie() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM TipologiaUtente", TipologiaUtente.class).list();
        }
    }

    public TipologiaUtente getRuolo(String mioRuolo) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM TipologiaUtente WHERE ruolo = :mioRuolo ").setParameter("mioRuolo", mioRuolo);
            return (TipologiaUtente) query.uniqueResult();
        }
    }
}
