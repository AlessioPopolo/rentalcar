package com.rentalcar.dao;

import com.rentalcar.entity.Prenotazioni;
import com.rentalcar.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.*;
import java.util.Date;
import java.util.List;

public class PrenotazioniDao {
    public static List <Prenotazioni> getPrenotazioni(Long customerId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query query = session.createQuery("from Prenotazioni where utente = :id order by startdate").setLong("id", customerId);
            return query.list();
        }
    }

    public void deletePrenotazione(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // delete the customer
            Prenotazioni prenotazione=session.load(Prenotazioni.class, id);

            Date prenotazioneStartdate = prenotazione.getStartdate();
            LocalDateTime ldt = LocalDateTime.ofInstant(prenotazioneStartdate.toInstant(), ZoneId.systemDefault()).minusDays(2);


            if(prenotazione!=null && ldt.isAfter(LocalDateTime.now())){
                session.delete(prenotazione);
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

    public Boolean checkDateUpdateOrDelete(Long id) {
        return null;
    }
}
