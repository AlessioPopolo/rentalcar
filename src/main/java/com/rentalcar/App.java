package com.rentalcar;

import com.rentalcar.dao.TipologiaUtenteDao;
import com.rentalcar.entity.TipologiaUtente;

import java.util.List;

public class App {
    public static void main(String[] args) {
        TipologiaUtenteDao tipologiaUtenteDao = new TipologiaUtenteDao();
        List< TipologiaUtente > tipologiaUtenteList = tipologiaUtenteDao.getAllTipologie();
        tipologiaUtenteList.forEach(s -> System.out.println(s.getRuolo()));
    }
}
