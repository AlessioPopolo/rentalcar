package com.rentalcar.servlet;

import com.rentalcar.dao.*;
import com.rentalcar.entity.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "RentalCarControllerServlet", value = "/RentalCarControllerServlet")
public class RentalCarControllerServlet extends HttpServlet {

    private UtenteDao utenteDao;
    private AutomezzoDao automezzoDao;
    private TipologiaUtenteDao tipologiaUtenteDao;
    private TipologiaAutomezzoDao tipologiaAutomezzoDao;
    private PrenotazioniDao prenotazioniDao;

    @Override
    public void init() throws ServletException {
        super.init();
        utenteDao = new UtenteDao();
        automezzoDao = new AutomezzoDao();
        tipologiaUtenteDao = new TipologiaUtenteDao();
        tipologiaAutomezzoDao = new TipologiaAutomezzoDao();
        prenotazioniDao = new PrenotazioniDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String theCommand = getTheCommand(request);
        //route the appropriate method
        switchRequest(request, response, theCommand);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String theCommand = getTheCommand(request);
        //route the appropriate method
        switchRequest(request, response, theCommand);
    }

    private void switchRequest(HttpServletRequest request, HttpServletResponse response, String theCommand) throws ServletException, IOException {
        switch (theCommand){

            case "ADD":

            case "UPDATE":
                try {
                    upsertCustomer(request, response);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;

            case "LOAD":
                loadCustomer(request, response);
                break;

            case "DELETE":
                deleteCustomer(request, response);
                break;

            case "BOOK":
                loadPrenotazioni(request, response);
                break;

            case "SEARCH":
                searchCustomers(request, response);
                break;

            case "ADDAUTO":

            case "UPDATEAUTO":
                try {
                    upsertAuto(request, response);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;

            case "LOADAUTO":
                loadAuto(request, response);
                break;

            case "DELETEAUTO":
                deleteAuto(request, response);
                break;

            case "SEARCHAUTO":
                searchAuto(request, response);
                break;

            default:
                listaCustomers(request, response);
        }
    }

    private String getTheCommand(HttpServletRequest request) {
        // read the "command" parameter
        String theCommand = request.getParameter("command");
        // if command missing then default
        if (theCommand == null){
            theCommand = "LIST";
        }
        return theCommand;
    }

    private void searchAuto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoria = request.getParameter("searchAuto");

        List<Automezzo> automezzi = automezzoDao.getAllAutoFromCategoria(categoria);

        // add to the request
        request.setAttribute("listaAuto", automezzi);
        // send to view
        RequestDispatcher dispatcher =request.getRequestDispatcher("/lista-auto.jsp");
        dispatcher.forward(request, response);
    }

    private void searchCustomers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String theSearchName = request.getParameter("theSearchName");

        List<Utente> utenti = utenteDao.searchCustomers(theSearchName);

        // add to the request
        request.setAttribute("listaCustomers", utenti);
        // send to view
        RequestDispatcher dispatcher =request.getRequestDispatcher("/lista-customers.jsp");
        dispatcher.forward(request, response);
    }

    private void loadAuto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //read id from form data
        Long id = Long.parseLong(request.getParameter("autoId"));
        //get auto from database
        Automezzo automezzo = automezzoDao.getAutomezzo(id);
        //place auto in the request attribute
        request.setAttribute("auto", automezzo);
        //Send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/update-auto-form.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteAuto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("autoId"));
        automezzoDao.deleteAuto(id);
        listaCustomers(request, response);
    }

    private void upsertAuto(HttpServletRequest request, HttpServletResponse response) throws ParseException, ServletException, IOException {
        Boolean control = false;
        Long id = Long.parseLong("0");
        if (request.getParameter("autoId") != null){
            id = Long.parseLong(request.getParameter("autoId"));
            control = true;
        }

        String marca = request.getParameter("marca");
        String modello = request.getParameter("modello");
        String immatricolazione = request.getParameter("immatricolazione");
        Date date=new SimpleDateFormat("yyyy-MM").parse(immatricolazione);
        String targa = request.getParameter("targa");
        String categoria = request.getParameter("categoria");
        TipologiaAutomezzo miaCategoria = tipologiaAutomezzoDao.getCategoria(categoria);

        Automezzo automezzo;
        if (control){
            automezzo = new Automezzo(id, targa, marca, modello, date, miaCategoria);
        }
        else {
            automezzo = new Automezzo(targa, marca, modello, date, miaCategoria);
        }

        automezzoDao.upsertAutomezzo(automezzo);
        listaCustomers(request, response);
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("customerId"));
        utenteDao.deleteCustomer(id);
        listaCustomers(request, response);
    }

    private void upsertCustomer(HttpServletRequest request, HttpServletResponse response) throws ParseException, ServletException, IOException {
        Boolean control = false;
        Long id = Long.parseLong("0");
        if (request.getParameter("customerId") != null){
            id = Long.parseLong(request.getParameter("customerId"));
            control = true;
        }

        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String datadinascita = request.getParameter("datadinascita");
        Date date=new SimpleDateFormat("yyyy-MM-dd").parse(datadinascita);
        String ruolo = request.getParameter("ruolo");
        TipologiaUtente mioRuolo = tipologiaUtenteDao.getRuolo(ruolo);

        Utente theCustomer;
        if (control){
            theCustomer = new Utente(id, nome, cognome, date, mioRuolo);
        }
        else {
            theCustomer = new Utente(nome, cognome, date, mioRuolo);
        }

        utenteDao.upsertCustomer(theCustomer);
        listaCustomers(request, response);
    }

    private void loadCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //read id from form data
        Long id = Long.parseLong(request.getParameter("customerId"));
        //get customer from database
        Utente utente = utenteDao.getCustomer(id);
        //place customer in the request attribute
        request.setAttribute("customer", utente);
        //Send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/update-customer-form.jsp");
        dispatcher.forward(request, response);
    }

    private void loadPrenotazioni(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("customerId"));
        Utente utente = utenteDao.getCustomer(id);
        List <Prenotazioni> prenotazioni = prenotazioniDao.getPrenotazioni(id);
        request.setAttribute("customer", utente);
        request.setAttribute("listaPrenotazioni", prenotazioni);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view-booking.jsp");
        dispatcher.forward(request, response);
    }

    private void listaCustomers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get customers from Dao
        List<Utente> listaCustomers = utenteDao.getAllCustomers();
        // add to the request
        request.setAttribute("listaCustomers", listaCustomers);
        // send to view
        RequestDispatcher dispatcher =request.getRequestDispatcher("/lista-customers.jsp");
        dispatcher.forward(request, response);
    }

}
