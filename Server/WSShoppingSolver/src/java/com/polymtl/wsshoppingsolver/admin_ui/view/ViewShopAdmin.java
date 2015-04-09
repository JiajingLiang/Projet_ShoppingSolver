/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.admin_ui.view;

import com.polymtl.wsshoppingsolver.model.ShopBranch;
import com.polymtl.wsshoppingsolver.model.ShopBrand;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jiajing
 */
@WebServlet(name = "ViewShopAdmin", urlPatterns = {"/ViewShopAdmin"})
public class ViewShopAdmin extends HttpServlet {

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
        List<ShopBrand> allBrands = (List<ShopBrand>)request.getAttribute("allBrands");
        List<ShopBranch> allBranches = (List<ShopBranch>)request.getAttribute("allBranches");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ViewShopAdmin</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<table border=\"1\" style=\"width:20%\">");
            out.println("<tr>");
            out.println(" <th colspan=\"2\">Shop Brands</th>");
            out.println("</tr>");
            for(int i=0; i<allBrands.size(); i++)
            {
                ShopBrand aBrand = allBrands.get(i);
                out.println("<tr>");
                out.println("<td>"+aBrand.getId().toString()+"</td>");
                out.println("<td>"+aBrand.getBrandName()+"</td>");
                out.println("</tr>");
            }
            out.println("<tr>");
            out.println("<td colspan=\"2\">");
            out.println("<form name=\"pageEmploye\" action=\"ControllerServlet?action=AddBrand\" method=\"POST\">");
            out.println("<input type=\"text\" name=\"textBrandName\" value=\"\" />");
            out.println("<input type=\"submit\" value=\"Add A Brand\" name=\"btnAddBrand\" />");
            out.println("</form>");
            out.println("</td>");
            out.println("</tr>");
            out.println("</table>");
            
            out.println("<table border=\"1\" style=\"width:80%\">");
            out.println("<tr>");
            out.println(" <th colspan=\"6\">Shop Branches</th>");
            out.println("</tr>");
            for(int i=0; i<allBranches.size(); i++)
            {
                ShopBranch aBranch = allBranches.get(i);
                out.println("<tr>");
                out.println("<td>"+aBranch.getId().toString()+"</td>");
                out.println("<td>"+aBranch.getStreet()+"</td>");
                out.println("<td>"+aBranch.getCity()+"</td>");
                out.println("<td>"+aBranch.getPostCode()+"</td>");
                out.println("<td>"+aBranch.getCountry()+"</td>");
                out.println("<td>"+aBranch.getBrand().getBrandName()+"</td>");
                out.println("</tr>");
            }
            out.println("<tr>");
            out.println("<td colspan=\"6\">");
            out.println("<form name=\"pageEmploye\" action=\"ControllerServlet?action=AddBranch\" method=\"POST\">");
            out.println("<input type=\"text\" name=\"textBranchStreet\" value=\"\" />");
            out.println("<input type=\"text\" name=\"textBranchCity\" value=\"\" />");
            out.println("<input type=\"text\" name=\"textBranchPostCode\" value=\"\" />");
            out.println("<input type=\"text\" name=\"textBranchCountry\" value=\"\" />");
            out.println("<input type=\"text\" name=\"textBrandId\" value=\"\" />");
            out.println("<input type=\"submit\" value=\"Add A Branch\" name=\"btnAddBranch\" />");
            out.println("</form>");
            out.println("</td>");
            out.println("</tr>");
            out.println("</table>");
            
            out.println("</body>");
            out.println("</html>");
        }
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
