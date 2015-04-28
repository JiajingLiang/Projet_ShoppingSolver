/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.webservice;

import com.polymtl.wsshoppingsolver.webservice_client.ShopProductAdminWS;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

/**
 * REST Web Service
 *
 * @author Jiajing
 */
@Path("shopproductadminwsport")
public class ShopProductAdminWSPort {
    private ShopProductAdminWS port;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ShopProductAdminWSPort
     */
    public ShopProductAdminWSPort() {
        port = getPort();
    }

    /**
     * Invokes the SOAP method createProduct
     * @param idCategory resource URI parameter
     * @param barCode resource URI parameter
     * @param description resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    @Path("createproduct/")
    public String getCreateProduct(@QueryParam("idCategory")
            @DefaultValue("0") long idCategory, @QueryParam("barCode") String barCode, @QueryParam("description") String description) {
        try {
            // Call Web Service Operation
            if (port != null) {
                boolean result = port.createProduct(idCategory, barCode, description);
                return new java.lang.Boolean(result).toString();
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     * Invokes the SOAP method createShopBrand
     * @param brandName resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    @Path("createshopbrand/")
    public String getCreateShopBrand(@QueryParam("brandName") String brandName) {
        try {
            // Call Web Service Operation
            if (port != null) {
                boolean result = port.createShopBrand(brandName);
                return new java.lang.Boolean(result).toString();
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     * Invokes the SOAP method findAllShopBrand
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    @Path("findallshopbrand/")
    public String getFindAllShopBrand() {
        try {
            // Call Web Service Operation
            if (port != null) {
                java.lang.String result = port.findAllShopBrand();
                return result;
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     * Invokes the SOAP method findAllProduct
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    @Path("findallproduct/")
    public String getFindAllProduct() {
        try {
            // Call Web Service Operation
            if (port != null) {
                java.lang.String result = port.findAllProduct();
                return result;
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     * Invokes the SOAP method addProductToShop
     * @param idProduct resource URI parameter
     * @param idShop resource URI parameter
     * @param price resource URI parameter
     * @param ratioTaxFederal resource URI parameter
     * @param ratioTaxProvincial resource URI parameter
     * @return an instance of java.lang.String
     */
    @POST
    @Produces("text/plain")
    @Consumes("application/xml")
    @Path("addproducttoshop/")
    public String postAddProductToShop(String idProduct, long idShop, JAXBElement<Double> price, JAXBElement<Float> ratioTaxFederal, JAXBElement<Float> ratioTaxProvincial) {
        try {
            // Call Web Service Operation
            if (port != null) {
                boolean result = port.addProductToShop(idProduct, idShop, price.getValue(), ratioTaxFederal.getValue(), ratioTaxProvincial.getValue());
                return new java.lang.Boolean(result).toString();
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     * Invokes the SOAP method createShopBranch
     * @param idBrand resource URI parameter
     * @param street resource URI parameter
     * @param city resource URI parameter
     * @param postcode resource URI parameter
     * @param country resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    @Path("createshopbranch/")
    public String getCreateShopBranch(@QueryParam("idBrand")
            @DefaultValue("0") long idBrand, @QueryParam("street") String street, @QueryParam("city") String city, @QueryParam("postcode") String postcode, @QueryParam("country") String country) {
        try {
            // Call Web Service Operation
            if (port != null) {
                boolean result = port.createShopBranch(idBrand, street, city, postcode, country);
                return new java.lang.Boolean(result).toString();
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     * Invokes the SOAP method getClientDevicesByFavoriteProducts
     * @param productBarCode resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    @Path("getclientdevicesbyfavoriteproducts/")
    public String getClientDevicesByFavoriteProducts(@QueryParam("productBarCode") String productBarCode) {
        try {
            // Call Web Service Operation
            if (port != null) {
                java.lang.String result = port.getClientDevicesByFavoriteProducts(productBarCode);
                return result;
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     * Invokes the SOAP method createProductCategory
     * @param categoryName resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    @Path("createproductcategory/")
    public String getCreateProductCategory(@QueryParam("categoryName") String categoryName) {
        try {
            // Call Web Service Operation
            if (port != null) {
                boolean result = port.createProductCategory(categoryName);
                return new java.lang.Boolean(result).toString();
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     * Invokes the SOAP method getProductPriceFromShop
     * @param productBarCode resource URI parameter
     * @param idShop resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    @Path("getproductpricefromshop/")
    public String getProductPriceFromShop(@QueryParam("productBarCode") String productBarCode, @QueryParam("idShop")
            @DefaultValue("0") long idShop) {
        try {
            // Call Web Service Operation
            if (port != null) {
                java.lang.String result = port.getProductPriceFromShop(productBarCode, idShop);
                return result;
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     * Invokes the SOAP method setProductPriceInShop
     * @param productBarCode resource URI parameter
     * @param idShop resource URI parameter
     * @param newPrice resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    @Path("setproductpriceinshop/")
    public String getSetProductPriceInShop(@QueryParam("productBarCode") String productBarCode, @QueryParam("idShop")
            @DefaultValue("0") long idShop, @QueryParam("newPrice")
            @DefaultValue("0.0") double newPrice) {
        try {
            // Call Web Service Operation
            if (port != null) {
                java.lang.String result = port.setProductPriceInShop(productBarCode, idShop, newPrice);
                return result;
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     * Invokes the SOAP method getAllProductsInShop
     * @param idShop resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    @Path("getallproductsinshop/")
    public String getAllProductsInShop(@QueryParam("idShop")
            @DefaultValue("0") long idShop) {
        try {
            // Call Web Service Operation
            if (port != null) {
                java.lang.String result = port.getAllProductsInShop(idShop);
                return result;
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     * Invokes the SOAP method findAllShopBranch
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    @Path("findallshopbranch/")
    public String getFindAllShopBranch() {
        try {
            // Call Web Service Operation
            if (port != null) {
                java.lang.String result = port.findAllShopBranch();
                return result;
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     * Invokes the SOAP method findAllProductCategory
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    @Path("findallproductcategory/")
    public String getFindAllProductCategory() {
        try {
            // Call Web Service Operation
            if (port != null) {
                java.lang.String result = port.findAllProductCategory();
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
    private ShopProductAdminWS getPort() {
        try {
            // Call Web Service Operation
            com.polymtl.wsshoppingsolver.webservice_client.ShopProductAdminWS_Service service = new com.polymtl.wsshoppingsolver.webservice_client.ShopProductAdminWS_Service();
            com.polymtl.wsshoppingsolver.webservice_client.ShopProductAdminWS p = service.getShopProductAdminWSPort();
            return p;
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }
}
