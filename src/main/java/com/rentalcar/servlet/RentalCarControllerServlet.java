package com.rentalcar.servlet;

import com.rentalcar.dao.AutomezzoDao;
import com.rentalcar.dao.TipologiaAutomezzoDao;
import com.rentalcar.dao.TipologiaUtenteDao;
import com.rentalcar.dao.UtenteDao;
import com.rentalcar.entity.Utente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
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
        listaCustomers(request, response);
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
