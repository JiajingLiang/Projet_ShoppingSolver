/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.admin_ui.action;

import com.polymtl.wsshoppingsolver.model.ShopBranch;
import com.polymtl.wsshoppingsolver.model.ShopBrand;
import com.thoughtworks.xstream.XStream;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jiajing
 */
public class AddBranch extends Action{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String street = request.getParameter("textBranchStreet");
        String city = request.getParameter("textBranchCity");
        String postCode = request.getParameter("textBranchPostCode");
        String country = request.getParameter("textBranchCountry");
        String bId = request.getParameter("textBrandId");
        Long brandId = Long.parseLong(bId);
        servicePort.createShopBranch(brandId, street, city, postCode, country);
        
        XStream xstream = new XStream();
        String strListBrands = servicePort.findAllShopBrand();
        xstream.processAnnotations(ShopBrand.class);
        List<ShopBrand> allBrands = (List<ShopBrand>)xstream.fromXML(strListBrands);
        request.setAttribute("allBrands", allBrands);
        
        String strListBranches = servicePort.findAllShopBranch();
        xstream.processAnnotations(ShopBranch.class);
        List<ShopBranch> allBranches = (List<ShopBranch>)xstream.fromXML(strListBranches);
        request.setAttribute("allBranches", allBranches);
        
        return "ViewShopAdmin";
    }
    
}
