/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.admin_ui.view;

import com.polymtl.wsshoppingsolver.model.Product;
import com.polymtl.wsshoppingsolver.model.ProductPriceInShop;
import com.polymtl.wsshoppingsolver.model.RegistedDevice;
import com.polymtl.wsshoppingsolver.model.ShopBranch;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jiajing
 */
public class ViewBranchProductAdmin extends HttpServlet {

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
        List<ProductPriceInShop> allProductsInShop = (List<ProductPriceInShop>)request.getAttribute("allProductsInShop");
        String strShopBranchId = (String)request.getAttribute("shopBranchId");
        List<Product> allProducts = (List<Product>)request.getAttribute("allProducts");
        List<RegistedDevice> devicesToNotificate = (List<RegistedDevice>)request.getAttribute("devicesToNotificate");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ViewBranchProductAdmin</title>");            
            out.println("</head>");
            out.println("<body>");
//            if(devicesToNotificate!=null){
//                for (RegistedDevice deviceToNotificate : devicesToNotificate) {
//                    out.println("<h1>"+deviceToNotificate.getDeviceId()+"</h1>");
//                }
//            }
            out.println("<table border=\"1\" style=\"width:50%\">");
            out.println("<tr>");
            if(allProductsInShop.size()>0){
                ShopBranch theShopBranch = allProductsInShop.get(0).getShopBranch();
                out.println(" <th colspan=\"7\">All Products In The Shop:"+theShopBranch.getBrand().getBrandName()+"("+theShopBranch.getStreet()+" "+theShopBranch.getCity()+" "+theShopBranch.getPostCode()+")</th>");
            }else{
                out.println(" <th colspan=\"7\">No Products In The Shop currently</th>");
            }
            out.println("</tr>");
            for(int i=0; i<allProductsInShop.size(); i++)
            {
                ProductPriceInShop aProduct = allProductsInShop.get(i);
                out.println("<tr>");
                out.println("<td>"+aProduct.getProduct().getBarCode()+"</td>");
                out.println("<td>"+aProduct.getProduct().getDescription()+"</td>");
                out.println("<td>"+aProduct.getProduct().getCategory().getCategoryName()+"</td>");
                out.println("<td>"+aProduct.getPrice().toString()+"</td>");
                out.println("<td>"+aProduct.getRatioTaxFederal().toString()+"</td>");
                out.println("<td>"+aProduct.getRatioTaxProvincial().toString()+"</td>");
                out.println("<td><form action=\"ControllerServlet?action=ChangeProductPriceInShop\" method=\"POST\">");
                out.println("<input type=\"hidden\" value=\""+aProduct.getShopBranch().getId().toString()+"\" name=\"hiddenBranchId\" />");
                out.println("<input type=\"hidden\" value=\""+aProduct.getProduct().getBarCode()+"\" name=\"hiddenProductBarCode\" />");
                out.println("<input type=\"text\" value=\"\" name=\"textNewPrice\" />");
                out.println("<input type=\"submit\" value=\"Change Price\" name=\"btnChangeProductPriceInShop\" />");
                out.println("</form></td>");
                out.println("</tr>");
            }
            out.println("<tr>");
            out.println("<td colspan=\"7\">");
            out.println("<form name=\"formAddProductToShop\" action=\"ControllerServlet?action=AddProductToShop\" method=\"POST\">");
            out.println("<input type=\"text\" name=\"textProductBarCode\" value=\"\" />");
            out.println("<input type=\"hidden\" value=\""+strShopBranchId+"\" name=\"hiddenBranchId\" />");
            out.println("<input type=\"text\" name=\"textPrice\" value=\"\" />");
            out.println("<input type=\"text\" name=\"textRatioTaxFederal\" value=\"\" />");
            out.println("<input type=\"text\" name=\"textRatioTaxProvincial\" value=\"\" />");
            out.println("<input type=\"submit\" value=\"Add Product To Shop\" name=\"btnAddProductToShop\" />");
            out.println("</form>");
            out.println("</td>");
            out.println("</tr>");
            out.println("</table>");
            
            out.println("<table border=\"1\" style=\"width:50%\">");
            out.println("<tr>");
            out.println(" <th colspan=\"3\">All Products</th>");
            out.println("</tr>");
            for(int i=0; i<allProducts.size(); i++)
            {
                Product aProduct = allProducts.get(i);
                out.println("<tr>");
                out.println("<td>"+aProduct.getBarCode()+"</td>");
                out.println("<td>"+aProduct.getDescription()+"</td>");
                out.println("<td>"+aProduct.getCategory().getCategoryName()+"</td>");
                out.println("</tr>");
            }
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
