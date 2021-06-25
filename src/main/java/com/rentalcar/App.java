package com.rentalcar;

import com.rentalcar.dao.AutomezzoDao;
import com.rentalcar.dao.TipologiaAutomezzoDao;
import com.rentalcar.dao.TipologiaUtenteDao;
import com.rentalcar.dao.UtenteDao;
import com.rentalcar.entity.Automezzo;
import com.rentalcar.entity.TipologiaAutomezzo;
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

        TipologiaAutomezzoDao tipologiaAutomezzoDao = new TipologiaAutomezzoDao();
        List<TipologiaAutomezzo> tipologiaAutomezzoList = tipologiaAutomezzoDao.getAllTipologie();
        tipologiaAutomezzoList.forEach(s -> System.out.println("Categoria automezzi: " + s.getCategoria()));

        AutomezzoDao automezzoDao = new AutomezzoDao();
        List<Automezzo> listaAutomezzo = automezzoDao.getAllAutomezzi();
        listaAutomezzo.forEach(u -> System.out.println(u.toString()));


    }
}
