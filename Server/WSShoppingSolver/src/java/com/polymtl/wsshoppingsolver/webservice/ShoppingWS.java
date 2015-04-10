/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.webservice;

import com.polymtl.wsshoppingsolver.dao.ClientDAOLocal;
import com.polymtl.wsshoppingsolver.dao.ProductDAOLocal;
import com.polymtl.wsshoppingsolver.dao.ProductPriceInShopDAOLocal;
import com.polymtl.wsshoppingsolver.dao.ProductTransactRecordDAOLocal;
import com.polymtl.wsshoppingsolver.dao.RegistedDeviceDAOLocal;
import com.polymtl.wsshoppingsolver.dao.ShopBranchDAOLocal;
import com.polymtl.wsshoppingsolver.dao.TransactDAOLocal;
import com.polymtl.wsshoppingsolver.model.Client;
import com.polymtl.wsshoppingsolver.model.Product;
import com.polymtl.wsshoppingsolver.model.ProductPriceInShop;
import com.polymtl.wsshoppingsolver.model.ProductTransactRecord;
import com.polymtl.wsshoppingsolver.model.RegistedDevice;
import com.polymtl.wsshoppingsolver.model.ShopBranch;
import com.polymtl.wsshoppingsolver.model.Transact;
import com.thoughtworks.xstream.XStream;
import java.util.ArrayList;
import java.util.Date;
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
    @EJB
    private TransactDAOLocal transactDao;
    @EJB
    private ProductTransactRecordDAOLocal productTransactRecordDao;
    @EJB
    private ProductPriceInShopDAOLocal productPriceInShopDao;
    @EJB
    private ShopBranchDAOLocal shopBranchDao;
    @EJB
    private ProductDAOLocal productDao;

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
    
    @WebMethod(operationName = "getProductPriceInShop")
    public String getProductPriceInShop(@WebParam(name="productBarCode")String productBarCode, @WebParam(name="idShop")long shopId){
        ProductPriceInShop productPriceInShop = productPriceInShopDao.findByKey(productBarCode, shopId);
        if(productPriceInShop!=null){
            return productPriceInShop.toXmlString();
        }else{
            return "null";
        }        
    }
    
    //productsCode must be in format xml : <list>   <string>068100047219</string>   <string>068200010311</string> </list>
    @WebMethod(operationName="createTransaction")
    public boolean createTransaction(@WebParam(name="clientId")long clientId, @WebParam(name="password")String password, @WebParam(name="shopId")long shopId, @WebParam(name = "listProducts")String productsCode){
        Client client = clientDao.findByKey(clientId);
        if(client!=null && password.equals(client.getPassword())){
            XStream xstream = new XStream();
            List<String> listProducts = (List<String>)xstream.fromXML(productsCode);
            List<ProductPriceInShop> prices = new ArrayList<>();
            Double total = 0.0;
            for(int i = 0;i<listProducts.size();i++){
                ProductPriceInShop productPriceInShop = productPriceInShopDao.findByKey(listProducts.get(i), shopId);
                if(productPriceInShop!=null){
                    prices.add(productPriceInShop);
                    total += productPriceInShop.getPrice()*(1+productPriceInShop.getRatioTaxFederal()+productPriceInShop.getRatioTaxProvincial());
                }else{
                    return false;
                }
            }
            total = Double.parseDouble(String.format("%.2f",total));
            Transact aTransact = new Transact(total,client,shopBranchDao.findByKey(shopId));
            Transact transact = transactDao.create(aTransact);
            for(int i = 0;i<prices.size();i++){
                ProductTransactRecord aRecord = new ProductTransactRecord(transact,productDao.findByKey(listProducts.get(i)),prices.get(i).getPrice(),prices.get(i).getRatioTaxFederal(),prices.get(i).getRatioTaxProvincial());
                productTransactRecordDao.create(aRecord);
            }
            return true;
        }else{
            return false;
        }
    }
    
    @WebMethod(operationName="findTransactionByClient")
    public String findTransactionByClient(@WebParam(name="clientId")long clientId, @WebParam(name="password")String password, @WebParam(name="dateBegin")String dateBegin, @WebParam(name="dateEnd")String dateEnd){
        Client client = clientDao.findByKey(clientId);
        XStream xstream = new XStream();
        if(client!=null && password.equals(client.getPassword())){
            Date dBegin = (Date)xstream.fromXML(dateBegin);
            Date dEnd = (Date)xstream.fromXML(dateEnd);
            List<Transact> listTransactions = transactDao.findByClientAndDate(client, dBegin, dEnd);
            xstream.processAnnotations(Transact.class);
            xstream.processAnnotations(ShopBranch.class);
            return xstream.toXML(listTransactions);
            
//            if(listTransactions.size()>0){
//                String strXML = "<Transactions>";
//                for (Transact aTransact : listTransactions) {
//                    strXML += aTransact.toXmlString();
//                }
//                strXML += "</Transactions>";
//                return strXML;
//            }else{
//                return "null";
//            }
        }else{
            return xstream.toXML(null);
        }
    }
    
    @WebMethod(operationName="findTransactionDetail")
    public String findTransactionDetail(@WebParam(name="clientId")long clientId, @WebParam(name="password")String password, @WebParam(name="transactionId")long transactionId){
        Client client = clientDao.findByKey(clientId);
        Transact transaction = transactDao.findByKey(transactionId);
        XStream xstream = new XStream();
        if(client!=null && password.equals(client.getPassword()) && transaction.getClient().equals(client)){
            List<ProductTransactRecord> transactRecords = productTransactRecordDao.findByTransact(transaction);
            xstream.processAnnotations(ProductTransactRecord.class);
            xstream.processAnnotations(Product.class);
            return xstream.toXML(transactRecords);
//            if(transactRecords.size()>0){
//                String strXML = "<TransactionDetail>";
//                for (ProductTransactRecord aRecord : transactRecords) {
//                    strXML += aRecord.toXmlString();
//                }
//                strXML += "</TransactionDetail>";
//                return strXML;
//            }else{
//                return "null";
//            }
        }else{
            return xstream.toXML(null);
        }
    }
}
