/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import modelo.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import conexion.*;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "combox", urlPatterns = {"/combox"})
public class combox extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Gerencias ger = new Gerencias();
        ArrayList<Gerencias> lista = new ArrayList<Gerencias>();
        lista = ger.consultarGerencias();
        request.setAttribute("lista", lista);

        Departamentos dep = new Departamentos();
        ArrayList<Departamentos> list = new ArrayList<Departamentos>();
        list = dep.consultarDepartamentos();
        request.setAttribute("list", list);

        Asignaciones asi = new Asignaciones();
        ArrayList<Asignaciones> lis = new ArrayList<Asignaciones>();
        lis = asi.consultarAsignaciones();
        request.setAttribute("lis", lis);

        Encargados enc = new Encargados();
        ArrayList<Encargados> li = new ArrayList<Encargados>();
        li = enc.consultarEncargados();
        request.setAttribute("li", li);
        
        
        String ge = request.getParameter("gerencias");
        String de = request.getParameter("departamentos");
        String as = request.getParameter("asignaciones");
        String en = request.getParameter("encargados");
        String re = request.getParameter("requerimiento");
        Requerimientos req;
        if (ge != null && de != null && as != null && en != null && re != null) {
            
            req = new Requerimientos(ge, de, as, en, re);
            req.ingresarRequerimiento();
        } else {
            req = new Requerimientos();
        }

        request.getRequestDispatcher("/ingresar.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
