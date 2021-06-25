package com.rentalcar;

import com.rentalcar.dao.TipologiaUtenteDao;
import com.rentalcar.dao.UtenteDao;
import com.rentalcar.entity.TipologiaUtente;
import com.rentalcar.entity.Utente;

import java.util.List;

public class App {
    public static void main(String[] args) {
        TipologiaUtenteDao tipologiaUtenteDao = new TipologiaUtenteDao();
        List< TipologiaUtente > tipologiaUtenteList = tipologiaUtenteDao.getAllTipologie();
        tipologiaUtenteList.forEach(s -> System.out.println("tipo utente: " + s.getRuolo()));

        UtenteDao utenteDao = new UtenteDao();
        List<Utente> listaUtenti = utenteDao.getAllUtenti();
        listaUtenti.forEach(u -> System.out.println(u.toString()));
    }
}
