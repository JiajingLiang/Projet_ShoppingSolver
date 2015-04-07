/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.webservice;

import com.polymtl.wsshoppingsolver.dao.ClientDAOLocal;
import com.polymtl.wsshoppingsolver.dao.RegistedDeviceDAOLocal;
import com.polymtl.wsshoppingsolver.model.Client;
import com.polymtl.wsshoppingsolver.model.RegistedDevice;
import java.util.List;
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
    @EJB
    private RegistedDeviceDAOLocal registedDeviceDao;

    @WebMethod(operationName = "createClient")
    public String createClient(@WebParam(name = "email") String email, @WebParam(name = "name") String name, @WebParam(name = "password") String password, @WebParam(name = "telephone") String telephone, @WebParam(name="street")String street, @WebParam(name="city")String city, @WebParam(name="postcode")String postcode, @WebParam(name="country")String country){
        if(clientDao.findByEmail(email).isEmpty()){
            Client aClient = new Client(email,name,password,telephone,street,city,postcode,country);
            Client theClient = clientDao.create(aClient);
            return "<ClientId>"+theClient.getId()+"</ClientId>";
        }else{
            return "null";
        }
    }
    
    @WebMethod(operationName = "createClientWithDevice")
    public String createClientWithDevice(@WebParam(name = "email") String email, @WebParam(name = "name") String name, @WebParam(name = "password") String password, @WebParam(name = "telephone") String telephone, @WebParam(name="street")String street, @WebParam(name="city")String city, @WebParam(name="postcode")String postcode, @WebParam(name="country")String country, @WebParam(name="deviceKey")String deviceKey){
        if(clientDao.findByEmail(email).isEmpty()){
            Client aClient = new Client(email,name,password,telephone,street,city,postcode,country);
            Client theClient = clientDao.create(aClient);
            RegistedDevice aDevice = new RegistedDevice(deviceKey,theClient);
            registedDeviceDao.create(aDevice);
            return "<ClientId>"+theClient.getId()+"</ClientId>";
        }else{
            return "null";
        }
    }
    
    @WebMethod(operationName = "findClientById")
    public String findClientById(@WebParam(name = "clientId") long clientId) {
        Client client = clientDao.findByKey(clientId);
        if(client!=null){
            return client.toXmlString();
        }else{
            return "null";
        }        
    }
    
    @WebMethod(operationName="findClientByEmail")
    public String findClientByEmail(@WebParam(name = "clientEmail") String clientEmail) {
        List<Client> clients = clientDao.findByEmail(clientEmail);
        if(!clients.isEmpty()){
            return clients.get(0).toXmlString();
        }else{
            return "null";
        }        
    }
    
    @WebMethod(operationName="addDeviceToClient")
    public boolean addDeviceToClient(@WebParam(name = "deviceKey")String deviceKey, @WebParam(name = "clientId")long clientId){
        Client client = clientDao.findByKey(clientId);
        if(client != null){
            RegistedDevice device = registedDeviceDao.findByKey(deviceKey);
            if(device==null){
                device = new RegistedDevice(deviceKey,client);
                registedDeviceDao.create(device);
            }else{
                if(!device.getClient().equals(client)){
                    device.setClient(client);
                    registedDeviceDao.update(device);
                }
            }
            return true;
        }else{
            return false;
        }
    }
    
    @WebMethod(operationName="deleteRegistedDevice")
    public boolean deleteRegistedDevice(@WebParam(name = "deviceKey")String deviceKey){
        RegistedDevice device = registedDeviceDao.findByKey(deviceKey);
        if(device==null){
            return false;
        }else{
            registedDeviceDao.delete(deviceKey);
            return true;
        }
    }
    
    
}
