/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.admin_ui.action;

import com.polymtl.wsshoppingsolver.model.Product;
import com.polymtl.wsshoppingsolver.model.ProductCategory;
import com.polymtl.wsshoppingsolver.model.ProductPriceInShop;
import com.polymtl.wsshoppingsolver.model.RegistedDevice;
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
public class ChangeProductPriceInShop extends Action{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strShopBranchId = request.getParameter("hiddenBranchId");
        Long branchId = Long.parseLong(strShopBranchId);
        String productBarCode = request.getParameter("hiddenProductBarCode");
        String strXmlProductPrice = servicePort.getProductPriceFromShop(productBarCode, branchId);
        XStream xstream = new XStream();
        xstream.processAnnotations(ProductPriceInShop.class);
        xstream.processAnnotations(Product.class);
        xstream.processAnnotations(ProductCategory.class);
        xstream.processAnnotations(ShopBranch.class);
        xstream.processAnnotations(ShopBrand.class);
        ProductPriceInShop productPriceInShop = (ProductPriceInShop)xstream.fromXML(strXmlProductPrice);
        Double oldPrice = productPriceInShop.getPrice();
        
        String strNewPrice = request.getParameter("textNewPrice");
        Double newPrice = Double.parseDouble(strNewPrice);
        servicePort.setProductPriceInShop(productBarCode, branchId, newPrice);
        
        if(newPrice<oldPrice){
            String strDevicesToNotificate = servicePort.getClientDevicesByFavoriteProducts(productBarCode);
            xstream.processAnnotations(RegistedDevice.class);
            List<RegistedDevice> devicesToNotificate = (List<RegistedDevice>)xstream.fromXML(strDevicesToNotificate);
            request.setAttribute("devicesToNotificate", devicesToNotificate);
        }
        
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
