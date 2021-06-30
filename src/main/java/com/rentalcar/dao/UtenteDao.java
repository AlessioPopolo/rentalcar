package com.rentalcar.dao;

import com.rentalcar.entity.Utente;
import com.rentalcar.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UtenteDao {
    public static Utente getCustomer(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query query = session.createQuery("from Utente where id = :customerId").setParameter("customerId", id);
            return (Utente) query.uniqueResult();
        }
    }

    public List<Utente> getAllUtenti() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Utente ORDER BY id");
            return query.list();
        }
    }

    public List<Utente> getAllCustomers(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Utente where ruolo = (from TipologiaUtente tu where tu.ruolo = 'customer') order by id");
            return query.list();
        }
    }

    public void deleteCustomer(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // delete the customer
            Utente customer=session.load(Utente.class, id);
            if(customer!=null){
                session.delete(customer);
            }
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void upsertCustomer(Utente theCustomer) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // update the customer object
            session.saveOrUpdate(theCustomer);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Utente> searchCustomers(String theSearchName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query;
            if (theSearchName != null && theSearchName.trim().length() > 0) {
                query = session.createQuery("from Utente where (nome like :nome OR cognome like :cognome) AND ruolo = (from TipologiaUtente tu where tu.ruolo = 'customer') order by id")
                        .setParameter("nome", "%" + theSearchName + "%")
                        .setParameter("cognome", "%" + theSearchName + "%");
            }
            else {
                query = session.createQuery("from Utente where ruolo = (from TipologiaUtente tu where tu.ruolo = 'customer') order by id");
            }
            return query.list();
        }
    }
}
