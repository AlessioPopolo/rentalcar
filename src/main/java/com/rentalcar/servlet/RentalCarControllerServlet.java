package com.rentalcar.servlet;

import com.rentalcar.dao.AutomezzoDao;
import com.rentalcar.dao.TipologiaAutomezzoDao;
import com.rentalcar.dao.TipologiaUtenteDao;
import com.rentalcar.dao.UtenteDao;
import com.rentalcar.entity.TipologiaUtente;
import com.rentalcar.entity.Utente;

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

    @Override
    public void init() throws ServletException {
        super.init();
        utenteDao = new UtenteDao();
        automezzoDao = new AutomezzoDao();
        tipologiaUtenteDao = new TipologiaUtenteDao();
        tipologiaAutomezzoDao = new TipologiaAutomezzoDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // read the "command" parameter
        String theCommand = request.getParameter("command");
        // if command missing then default
        if (theCommand == null){
            theCommand = "LIST";
        }
        //route the appropriate method
        switch (theCommand){

            case "ADD":
                try {
                    addCustomer(request, response);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;

            case "LOAD":
                loadCustomer(request, response);
                break;

            case "UPDATE":
                try {
                    updateCustomer(request, response);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;

            case "DELETE":
                deleteCustomer(request, response);
                break;

            default:
                listaCustomers(request, response);
        }
    }

    private void addCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String datadinascita = request.getParameter("datadinascita");
        Date date=new SimpleDateFormat("yyyy-MM-dd").parse(datadinascita);
        String ruolo = request.getParameter("ruolo");
        TipologiaUtente tipologiaUtente = new TipologiaUtente(ruolo);
        Utente nuovoUtente = new Utente(nome, cognome, date, tipologiaUtente);
        utenteDao.aggiungiUtente(nuovoUtente);
        listaCustomers(request, response);
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("customerId"));
        utenteDao.deleteCustomer(id);
        listaCustomers(request, response);
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws ParseException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("customerId"));
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String datadinascita = request.getParameter("datadinascita");
        Date date=new SimpleDateFormat("yyyy-MM-dd").parse(datadinascita);
        String ruolo = request.getParameter("ruolo");
        TipologiaUtente tipologiaUtente = new TipologiaUtente(ruolo);
        Utente theCustomer = new Utente(id, nome, cognome, date, tipologiaUtente);

        utenteDao.updateCustomer(theCustomer);
        listaCustomers(request, response);
    }

    private void loadCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //read id from form data
        String theCustomerId = request.getParameter("customerId");
        //get customer from database
        Utente utente = UtenteDao.getCustomer(theCustomerId);
        //place customer in the request attribute
        request.setAttribute("THE_CUSTOMER", utente);
        //Send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/update-customer-form.jsp");
        dispatcher.forward(request, response);
    }

    private void listaCustomers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get customers from Dao
        List<Utente> listaCustomers = utenteDao.getAllCustomers();
        // add to the request
        request.setAttribute("LISTA_CUSTOMERS", listaCustomers);
        // send to view
        RequestDispatcher dispatcher =request.getRequestDispatcher("/lista-customers.jsp");
        dispatcher.forward(request, response);
    }

}
