/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.admin_ui.action;

//import com.polymtl.wsshoppingsolver.webservice_client.ShopProductAdminWS;
import shopproduct_admin_webservice.ShopProductAdminWS;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jiajing
 */
public abstract class Action {
    protected ShopProductAdminWS servicePort;
    
    public void setServicePort(ShopProductAdminWS servicePort)
    {
        this.servicePort = servicePort;
    }
    
    public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
