/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.webservice;

import com.polymtl.wsshoppingsolver.webservice_client.ShoppingWS;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author Jiajing
 */
@Path("shoppingwsport")
public class ShoppingWSPort {
    private ShoppingWS port;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ShoppingWSPort
     */
    public ShoppingWSPort() {
        port = getPort();
    }

    /**
     * Invokes the SOAP method getProductPriceInShop
     * @param productBarCode resource URI parameter
     * @param idShop resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    @Path("getproductpriceinshop/")
    public String getProductPriceInShop(@QueryParam("productBarCode") String productBarCode, @QueryParam("idShop")
            @DefaultValue("0") long idShop) {
        try {
            // Call Web Service Operation
            if (port != null) {
                java.lang.String result = port.getProductPriceInShop(productBarCode, idShop);
                return result;
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     * Invokes the SOAP method deleteRegistedDevice
     * @param deviceKey resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    @Path("deleteregisteddevice/")
    public String getDeleteRegistedDevice(@QueryParam("deviceKey") String deviceKey) {
        try {
            // Call Web Service Operation
            if (port != null) {
                boolean result = port.deleteRegistedDevice(deviceKey);
                return new java.lang.Boolean(result).toString();
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     * Invokes the SOAP method createTransaction
     * @param clientId resource URI parameter
     * @param password resource URI parameter
     * @param shopId resource URI parameter
     * @param listProducts resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    @Path("createtransaction/")
    public String getCreateTransaction(@QueryParam("clientId")
            @DefaultValue("0") long clientId, @QueryParam("password") String password, @QueryParam("shopId")
            @DefaultValue("0") long shopId, @QueryParam("listProducts") String listProducts) {
        try {
            // Call Web Service Operation
            if (port != null) {
                boolean result = port.createTransaction(clientId, password, shopId, listProducts);
                return new java.lang.Boolean(result).toString();
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     * Invokes the SOAP method createClientWithDevice
     * @param email resource URI parameter
     * @param name resource URI parameter
     * @param password resource URI parameter
     * @param telephone resource URI parameter
     * @param street resource URI parameter
     * @param city resource URI parameter
     * @param postcode resource URI parameter
     * @param country resource URI parameter
     * @param deviceKey resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    @Path("createclientwithdevice/")
    public String getCreateClientWithDevice(@QueryParam("email") String email, @QueryParam("name") String name, @QueryParam("password") String password, @QueryParam("telephone") String telephone, @QueryParam("street") String street, @QueryParam("city") String city, @QueryParam("postcode") String postcode, @QueryParam("country") String country, @QueryParam("deviceKey") String deviceKey) {
        try {
            // Call Web Service Operation
            if (port != null) {
                java.lang.String result = port.createClientWithDevice(email, name, password, telephone, street, city, postcode, country, deviceKey);
                return result;
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     * Invokes the SOAP method findClientByEmail
     * @param clientEmail resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    @Path("findclientbyemail/")
    public String getFindClientByEmail(@QueryParam("clientEmail") String clientEmail) {
        try {
            // Call Web Service Operation
            if (port != null) {
                java.lang.String result = port.findClientByEmail(clientEmail);
                return result;
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     * Invokes the SOAP method addDeviceToClient
     * @param deviceKey resource URI parameter
     * @param clientId resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    @Path("adddevicetoclient/")
    public String getAddDeviceToClient(@QueryParam("deviceKey") String deviceKey, @QueryParam("clientId")
            @DefaultValue("0") long clientId) {
        try {
            // Call Web Service Operation
            if (port != null) {
                boolean result = port.addDeviceToClient(deviceKey, clientId);
                return new java.lang.Boolean(result).toString();
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     * Invokes the SOAP method findClientById
     * @param clientId resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    @Path("findclientbyid/")
    public String getFindClientById(@QueryParam("clientId")
            @DefaultValue("0") long clientId) {
        try {
            // Call Web Service Operation
            if (port != null) {
                java.lang.String result = port.findClientById(clientId);
                return result;
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     * Invokes the SOAP method createClient
     * @param email resource URI parameter
     * @param name resource URI parameter
     * @param password resource URI parameter
     * @param telephone resource URI parameter
     * @param street resource URI parameter
     * @param city resource URI parameter
     * @param postcode resource URI parameter
     * @param country resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    @Path("createclient/")
    public String getCreateClient(@QueryParam("email") String email, @QueryParam("name") String name, @QueryParam("password") String password, @QueryParam("telephone") String telephone, @QueryParam("street") String street, @QueryParam("city") String city, @QueryParam("postcode") String postcode, @QueryParam("country") String country) {
        try {
            // Call Web Service Operation
            if (port != null) {
                java.lang.String result = port.createClient(email, name, password, telephone, street, city, postcode, country);
                return result;
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     * Invokes the SOAP method findTransactionByClient
     * @param clientId resource URI parameter
     * @param password resource URI parameter
     * @param dateBegin resource URI parameter
     * @param dateEnd resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    @Path("findtransactionbyclient/")
    public String getFindTransactionByClient(@QueryParam("clientId")
            @DefaultValue("0") long clientId, @QueryParam("password") String password, @QueryParam("dateBegin") String dateBegin, @QueryParam("dateEnd") String dateEnd) {
        try {
            // Call Web Service Operation
            if (port != null) {
                java.lang.String result = port.findTransactionByClient(clientId, password, dateBegin, dateEnd);
                return result;
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     * Invokes the SOAP method findTransactionDetail
     * @param clientId resource URI parameter
     * @param password resource URI parameter
     * @param transactionId resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    @Path("findtransactiondetail/")
    public String getFindTransactionDetail(@QueryParam("clientId")
            @DefaultValue("0") long clientId, @QueryParam("password") String password, @QueryParam("transactionId")
            @DefaultValue("0") long transactionId) {
        try {
            // Call Web Service Operation
            if (port != null) {
                java.lang.String result = port.findTransactionDetail(clientId, password, transactionId);
                return result;
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     *
     */
    private ShoppingWS getPort() {
        try {
            // Call Web Service Operation
            com.polymtl.wsshoppingsolver.webservice_client.ShoppingWS_Service service = new com.polymtl.wsshoppingsolver.webservice_client.ShoppingWS_Service();
            com.polymtl.wsshoppingsolver.webservice_client.ShoppingWS p = service.getShoppingWSPort();
            return p;
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }
}
