package com.rentalcar.dao;

import com.rentalcar.entity.Utente;
import com.rentalcar.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class UtenteDao {
    public List<Utente> getAllUtenti() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Utente", Utente.class).list();
        }
    }

    public List<Utente> getAllCustomers(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Utente WHERE ruolo='customer'", Utente.class).list();
        }
    }
}
