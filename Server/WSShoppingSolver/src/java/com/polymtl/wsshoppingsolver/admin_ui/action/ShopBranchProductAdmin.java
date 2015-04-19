/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.admin_ui.action;

import com.polymtl.wsshoppingsolver.model.Product;
import com.polymtl.wsshoppingsolver.model.ProductCategory;
import com.polymtl.wsshoppingsolver.model.ProductPriceInShop;
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
public class ShopBranchProductAdmin extends Action{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        XStream xstream = new XStream();
        String strShopBranchId = request.getParameter("hiddenBranchId");
        Long branchId = Long.parseLong(strShopBranchId);
        String strListProducts = servicePort.getAllProductsInShop(branchId);
        xstream.processAnnotations(Product.class);
        xstream.processAnnotations(ProductCategory.class);
        xstream.processAnnotations(ShopBranch.class);
        xstream.processAnnotations(ShopBrand.class);
        List<ProductPriceInShop> allProductsInShop = (List<ProductPriceInShop>)xstream.fromXML(strListProducts);
        request.setAttribute("allProductsInShop", allProductsInShop);
        request.setAttribute("shopBranchId", strShopBranchId);
        
        String strListAllProducts = servicePort.findAllProduct();
        List<Product> allProducts = (List<Product>)xstream.fromXML(strListAllProducts);
        request.setAttribute("allProducts", allProducts);
        
        return "ViewBranchProductAdmin";
    }
    
}
