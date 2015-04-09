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
public class ShopAdmin extends Action{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
