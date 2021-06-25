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
}
