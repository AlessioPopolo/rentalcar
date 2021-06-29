package com.rentalcar.dao;

import com.rentalcar.entity.Utente;
import com.rentalcar.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UtenteDao {
    public static Utente getCustomer(String theCustomerId) {
        int customerId;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            customerId = Integer.parseInt(theCustomerId);
            Query query = session.createQuery("from Utente where id = :customerId");
            query.setParameter("customerId", customerId);
            return (Utente) query.uniqueResult();
        }
    }

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

    public void deleteCustomer(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // delete the customer
            Utente customer=session.load(Utente.class, id);
            if(customer!=null){
                session.delete(customer);
                System.out.println("Venditore cancellato");
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

}
