/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.admin_ui.action;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Constants;
import com.polymtl.wsshoppingsolver.model.Product;
import com.polymtl.wsshoppingsolver.model.ProductCategory;
import com.polymtl.wsshoppingsolver.model.ProductPriceInShop;
import com.polymtl.wsshoppingsolver.model.RegistedDevice;
import com.polymtl.wsshoppingsolver.model.ShopBranch;
import com.polymtl.wsshoppingsolver.model.ShopBrand;
import com.polymtl.wsshoppingsolver.util.Constant;
import com.thoughtworks.xstream.XStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jiajing
 */
public class ChangeProductPriceInShop extends Action{
    private static final Executor threadPool = Executors.newFixedThreadPool(5);

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
            String shopAddress = productPriceInShop.getShopBranch().getStreet()+","+productPriceInShop.getShopBranch().getCity()+","+productPriceInShop.getShopBranch().getPostCode()+","+productPriceInShop.getShopBranch().getCountry();
            String productDescription = productPriceInShop.getProduct().getDescription();
            sendMessageToDevices(devicesToNotificate,shopAddress,productDescription,newPrice);
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
    
    private void sendMessageToDevices(List<RegistedDevice> devices, String shopAddress,String productDescription, Double newPrice) throws IOException{
        String status;
        Message message = new Message.Builder()
                .addData("shopAddress", shopAddress)
                .addData("productDescription", productDescription)
                .addData("newPrice", newPrice.toString()).build();
        if(devices.size()==1){
            String registrationId = devices.get(0).getDeviceId();
            Result result = notificationSender.send(message, registrationId, 5);
            status = "Sent message to one device:"+result;
        }else{
            // send a multicast message using JSON
            // must split in chunks of 1000 devices (GCM limit)
            int total = devices.size();
            List<String> partialDevices = new ArrayList<String>(total);
            int counter = 0;
            int tasks = 0;
            for (RegistedDevice device : devices) {
                counter++;
                partialDevices.add(device.getDeviceId());
                int partialSize = partialDevices.size();
                if (partialSize == Constant.MULTICAST_SIZE || counter == total) {
                  asyncSend(partialDevices,message);
                  partialDevices.clear();
                  tasks++;
                }
            }
            status = "Asynchronously sending " + tasks + " multicast messages to " +
            total + " devices";
        }
        System.out.println(status);
    }
    
    private void asyncSend(List<String> partialDevices, Message message) {
        // make a copy
        final List<String> devices = new ArrayList<String>(partialDevices);
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                MulticastResult multicastResult;
                try {
                  multicastResult = notificationSender.send(message, devices, 5);
                } catch (IOException e) {
                  logger.log(Level.SEVERE, "Error posting messages", e);
                  return;
                }
                List<Result> results = multicastResult.getResults();
                // analyze the results
                for (int i = 0; i < devices.size(); i++) {
                    String regId = devices.get(i);
                    Result result = results.get(i);
                    String messageId = result.getMessageId();
                    if (messageId != null) {
                        logger.fine("Succesfully sent message to device: " + regId + "; messageId = " + messageId);
                        String canonicalRegId = result.getCanonicalRegistrationId();
                        if (canonicalRegId != null) {
                            // same device has more than on registration id: update it
                            logger.info("canonicalRegId " + canonicalRegId);
//                            Datastore.updateRegistration(regId, canonicalRegId);
                        }
                    } else {
                        String error = result.getErrorCodeName();
                        if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
                            // application has been removed from device - unregister it
                            logger.info("Unregistered device: " + regId);
//                            Datastore.unregister(regId);
                        } else {
                            logger.severe("Error sending message to " + regId + ": " + error);
                        }
                    }
                }
            }
        });
    }
}
