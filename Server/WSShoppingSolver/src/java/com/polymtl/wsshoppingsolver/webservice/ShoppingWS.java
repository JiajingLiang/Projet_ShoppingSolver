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
import com.polymtl.wsshoppingsolver.model.ProductCategory;
import com.polymtl.wsshoppingsolver.model.ProductPriceInShop;
import com.polymtl.wsshoppingsolver.model.ProductTransactRecord;
import com.polymtl.wsshoppingsolver.model.RegistedDevice;
import com.polymtl.wsshoppingsolver.model.ShopBranch;
import com.polymtl.wsshoppingsolver.model.ShopBrand;
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
            
            List<RegistedDevice> devices = registedDeviceDao.findByDeviceId(deviceKey);
            if(devices.isEmpty()){
                RegistedDevice device = new RegistedDevice(deviceKey,theClient);
                registedDeviceDao.create(device);
            }else{
                RegistedDevice device = devices.get(0);
                if(!device.getClient().equals(theClient)){
                    device.setClient(theClient);
                    registedDeviceDao.update(device);
                }
            }
            
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
        System.out.print(client.toString());
        if(client != null){
            List<RegistedDevice> devices = registedDeviceDao.findByDeviceId(deviceKey);
            if(devices.isEmpty()){
                RegistedDevice device = new RegistedDevice(deviceKey,client);
                registedDeviceDao.create(device);
            }else{
                RegistedDevice device = devices.get(0);
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
    
    //productsCode must be in format xml : <list>   <string>068100047219</string>   <string>068200010311</string> </list>
    @WebMethod(operationName="setClientFavoriteProduct")
    public boolean setClientFavoriteProduct(@WebParam(name="clientId")long clientId, @WebParam(name="password")String password, @WebParam(name="productsCode")String productsCode){
        Client client = clientDao.findByKey(clientId);
        if(client!=null && password.equals(client.getPassword())){
            XStream xstream = new XStream();
            List<String> listProducts = (List<String>)xstream.fromXML(productsCode);
            List<Product> favoriteProducts = new ArrayList<>();
            for(int i = 0;i<listProducts.size();i++){
                Product aProduct = productDao.findByKey(listProducts.get(i));
                if(aProduct != null){
                    favoriteProducts.add(aProduct);
                }else{
                    return false;
                }
            }
            client.setFavoriteProducts(favoriteProducts);
            clientDao.update(client);
            for(int i=0;i<client.getFavoriteProducts().size();i++){
                if(!favoriteProducts.contains(client.getFavoriteProducts().get(i))){
                    Product oldProductsToRemove = client.getFavoriteProducts().get(i);
                    oldProductsToRemove.getPotentialClients().remove(client);
                    productDao.update(oldProductsToRemove);
                }
            }
            for(int i=0;i<favoriteProducts.size();i++){
                if(!client.getFavoriteProducts().contains(favoriteProducts.get(i))){
                    Product newProductsToAdd = favoriteProducts.get(i);
                    newProductsToAdd.getPotentialClients().add(client);
                    productDao.update(newProductsToAdd);
                }
            }
            return true;
        }else{
            return false;
        }
    }
    
    @WebMethod(operationName="deleteRegistedDevice")
    public boolean deleteRegistedDevice(@WebParam(name = "deviceKey")String deviceKey){
        List<RegistedDevice> devices = registedDeviceDao.findByDeviceId(deviceKey);
        if(devices.isEmpty()){
            return false;
        }else{
            registedDeviceDao.delete(deviceKey);
            return true;
        }
    }
    
    @WebMethod(operationName="findShopById")
    public String findShopById(@WebParam(name="shopBranchId")long shopId){
        ShopBranch shop = shopBranchDao.findByKey(shopId);
        XStream xstream = new XStream();
        xstream.processAnnotations(ShopBranch.class);
        xstream.processAnnotations(ShopBrand.class);
        return xstream.toXML(shop);
    }
    
    @WebMethod(operationName = "getProductPriceInShop")
    public String getProductPriceInShop(@WebParam(name="productBarCode")String productBarCode, @WebParam(name="idShop")long shopId){
        ProductPriceInShop productPriceInShop = productPriceInShopDao.findByKey(productBarCode, shopId);
        XStream xstream = new XStream();
        xstream.processAnnotations(ProductPriceInShop.class);
        xstream.processAnnotations(Product.class);
        xstream.processAnnotations(ProductCategory.class);
        xstream.processAnnotations(ShopBranch.class);
        xstream.processAnnotations(ShopBrand.class);
        return xstream.toXML(productPriceInShop); 
    }
    
    //productsCode must be in format xml : <list>   <string>068100047219</string>   <string>068200010311</string> </list>
    //quantities must be in format xml : <list>   <float>1.0</float>   <float>1.0</float> </list>
    @WebMethod(operationName="createTransaction")
    public String createTransaction(@WebParam(name="clientId")long clientId, @WebParam(name="password")String password, @WebParam(name="shopId")long shopId, @WebParam(name = "listProducts")String productsCode,@WebParam(name = "listQuantities")String quantities){
        Client client = clientDao.findByKey(clientId);
        XStream xstream = new XStream();
        if(client!=null && password.equals(client.getPassword())){
            List<String> listProducts = (List<String>)xstream.fromXML(productsCode);
            List<Float> listQuantities = (List<Float>)xstream.fromXML(quantities);
            List<ProductPriceInShop> prices = new ArrayList<>();
            Double total = 0.0;
            for(int i = 0;i<listProducts.size();i++){
                ProductPriceInShop productPriceInShop = productPriceInShopDao.findByKey(listProducts.get(i), shopId);
                if(productPriceInShop!=null){
                    prices.add(productPriceInShop);
                    total += productPriceInShop.getPrice()*listQuantities.get(i)*(1+productPriceInShop.getRatioTaxFederal()+productPriceInShop.getRatioTaxProvincial());
                }else{
                    return xstream.toXML(null);
                }
            }
            total = Double.parseDouble(String.format("%.2f",total));
            Transact aTransact = new Transact(total,client,shopBranchDao.findByKey(shopId));
            Transact transact = transactDao.create(aTransact);
            List<ProductTransactRecord> productTransactRecords = new ArrayList<>();
            for(int i = 0;i<prices.size();i++){
                ProductTransactRecord aRecord = new ProductTransactRecord(transact,productDao.findByKey(listProducts.get(i)),prices.get(i).getPrice(),listQuantities.get(i),prices.get(i).getRatioTaxFederal(),prices.get(i).getRatioTaxProvincial());
                productTransactRecordDao.create(aRecord);
                productTransactRecords.add(aRecord);
            }
            //update count balance
            client.setBalance(client.getBalance()-total);
            clientDao.update(client);
            xstream.processAnnotations(Transact.class);
            xstream.processAnnotations(ShopBranch.class);
            xstream.processAnnotations(ProductTransactRecord.class);
            xstream.processAnnotations(Product.class);
            return xstream.toXML(transact) + xstream.toXML(productTransactRecords);
        }else{
            return xstream.toXML(null);
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
            xstream.processAnnotations(ShopBrand.class);
            xstream.setMode(XStream.NO_REFERENCES);
            return xstream.toXML(listTransactions);
        }else{
            return xstream.toXML(null);
        }
    }
    
        @WebMethod(operationName="findRecentTransactionByClient")
    public String findRecentTransactionByClient(@WebParam(name="clientId")long clientId, @WebParam(name="password")String password){
        Client client = clientDao.findByKey(clientId);
        XStream xstream = new XStream();
        if(client!=null && password.equals(client.getPassword())){
            List<Transact> listTransactions = transactDao.findRecentTransactByClient(client);
            xstream.processAnnotations(Transact.class);
            xstream.processAnnotations(ShopBranch.class);
            xstream.processAnnotations(ShopBrand.class);
            xstream.setMode(XStream.NO_REFERENCES);
            return xstream.toXML(listTransactions);
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
        }else{
            return xstream.toXML(null);
        }
    }
}
