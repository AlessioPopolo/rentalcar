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
