/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.webservice;

import com.polymtl.wsshoppingsolver.dao.ClientDAOLocal;
import com.polymtl.wsshoppingsolver.model.Client;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Jiajing
 */
@WebService(serviceName = "ShoppingWS", targetNamespace = "http://shopping_webservice/")
@Stateless()
public class ShoppingWS {
    @EJB
    private ClientDAOLocal clientDao;

    @WebMethod(operationName = "createClient")
    public Boolean createClient(@WebParam(name = "email") String email, @WebParam(name = "name") String name, @WebParam(name = "password") String password, @WebParam(name = "telephone") String telephone, @WebParam(name = "address") String address ){
        if(clientDao.findByEmail(email).isEmpty()){
            Client aClient = new Client(email,name,password,telephone,address);
            clientDao.create(aClient);
            return true;
        }else{
            return false;
        }
    }
    
    @WebMethod(operationName = "findClientById")
    public String findClientById(@WebParam(name = "clientId") long clientId) {
        Client client = clientDao.findByKey(clientId);
        return client.toXmlString();
    }
}
