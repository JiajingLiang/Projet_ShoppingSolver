/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.admin_ui.controller;

import com.polymtl.wsshoppingsolver.admin_ui.action.Action;
import com.polymtl.wsshoppingsolver.admin_ui.action.AddBrand;
import com.polymtl.wsshoppingsolver.admin_ui.action.ShopAdmin;
//import com.polymtl.wsshoppingsolver.webservice_client.ShopProductAdminWS;
import shopproduct_admin_webservice.ShopProductAdminWS;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Jiajing
 */
@WebServlet(name = "ControllerServlet", urlPatterns = {"/ControllerServlet"})
public class ControllerServlet extends HttpServlet {
    private ShopProductAdminWS servicePort;

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ControllerServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ControllerServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
            service(request,response);
        }
    }

    @Override
    protected void service (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String actionToDo = request.getParameter("action");
        Action action = getAction(actionToDo);
        action.setServicePort(getPort());
        
        String view = action.execute(request, response);
        RequestDispatcher reqDisp = request.getRequestDispatcher(view);
        reqDisp.forward(request, response);
    }
    
    private Action getAction(String actionName)
    {
        Action action = null;
        if("ShopAdmin".equals(actionName)){
            action = new ShopAdmin();
        }
        if("AddBrand".equals(actionName)){
            action = new AddBrand();
        }
        return action;
    }
    
    private ShopProductAdminWS getPort() {
        try {
            // Call Web Service Operation
            shopproduct_admin_webservice.ShopProductAdminWS_Service service = new shopproduct_admin_webservice.ShopProductAdminWS_Service();
//            com.polymtl.wsshoppingsolver.webservice_client1.ShopProductAdminWS p = service.getShopProductAdminWSPort();
//            return p;
            servicePort = service.getShopProductAdminWSPort();
            return servicePort;
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

}
