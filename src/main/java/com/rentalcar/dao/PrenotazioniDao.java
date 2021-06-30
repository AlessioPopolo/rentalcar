package com.rentalcar.dao;

import com.rentalcar.entity.Prenotazioni;
import com.rentalcar.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class PrenotazioniDao {
    public static List <Prenotazioni> getPrenotazioni(Long customerId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query query = session.createQuery("from Prenotazioni where utente = :id order by startdate").setLong("id", customerId);
            return query.list();
        }
    }
}
