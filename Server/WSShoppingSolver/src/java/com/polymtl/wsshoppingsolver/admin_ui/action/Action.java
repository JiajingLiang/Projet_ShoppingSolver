/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.admin_ui.action;

import com.google.android.gcm.server.Sender;
import com.polymtl.wsshoppingsolver.util.Constant;
//import com.polymtl.wsshoppingsolver.webservice_client.ShopProductAdminWS;
import shopproduct_admin_webservice.ShopProductAdminWS;
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jiajing
 */
public abstract class Action {
    protected ShopProductAdminWS servicePort;
    protected Sender notificationSender;
    
    protected final Logger logger = Logger.getLogger(getClass().getName());
    
    public void setServicePort(ShopProductAdminWS servicePort)
    {
        this.servicePort = servicePort;
    }
    
    public void setNotificationSender()
    {
        this.notificationSender = new Sender(Constant.SERVER_API_KEY);
    }
    
    public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
