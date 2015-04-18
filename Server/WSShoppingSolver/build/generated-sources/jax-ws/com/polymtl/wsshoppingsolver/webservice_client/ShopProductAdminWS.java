
package com.polymtl.wsshoppingsolver.webservice_client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10-b140803.1500
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ShopProductAdminWS", targetNamespace = "http://shopproduct_admin_webservice/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ShopProductAdminWS {


    /**
     * 
     * @param ratioTaxProvincial
     * @param idShop
     * @param price
     * @param idProduct
     * @param ratioTaxFederal
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addProductToShop", targetNamespace = "http://shopproduct_admin_webservice/", className = "com.polymtl.wsshoppingsolver.webservice_client.AddProductToShop")
    @ResponseWrapper(localName = "addProductToShopResponse", targetNamespace = "http://shopproduct_admin_webservice/", className = "com.polymtl.wsshoppingsolver.webservice_client.AddProductToShopResponse")
    @Action(input = "http://shopproduct_admin_webservice/ShopProductAdminWS/addProductToShopRequest", output = "http://shopproduct_admin_webservice/ShopProductAdminWS/addProductToShopResponse")
    public boolean addProductToShop(
        @WebParam(name = "idProduct", targetNamespace = "")
        String idProduct,
        @WebParam(name = "idShop", targetNamespace = "")
        long idShop,
        @WebParam(name = "price", targetNamespace = "")
        Double price,
        @WebParam(name = "ratioTaxFederal", targetNamespace = "")
        Float ratioTaxFederal,
        @WebParam(name = "ratioTaxProvincial", targetNamespace = "")
        Float ratioTaxProvincial);

    /**
     * 
     * @param brandName
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "createShopBrand", targetNamespace = "http://shopproduct_admin_webservice/", className = "com.polymtl.wsshoppingsolver.webservice_client.CreateShopBrand")
    @ResponseWrapper(localName = "createShopBrandResponse", targetNamespace = "http://shopproduct_admin_webservice/", className = "com.polymtl.wsshoppingsolver.webservice_client.CreateShopBrandResponse")
    @Action(input = "http://shopproduct_admin_webservice/ShopProductAdminWS/createShopBrandRequest", output = "http://shopproduct_admin_webservice/ShopProductAdminWS/createShopBrandResponse")
    public boolean createShopBrand(
        @WebParam(name = "BrandName", targetNamespace = "")
        String brandName);

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findAllShopBrand", targetNamespace = "http://shopproduct_admin_webservice/", className = "com.polymtl.wsshoppingsolver.webservice_client.FindAllShopBrand")
    @ResponseWrapper(localName = "findAllShopBrandResponse", targetNamespace = "http://shopproduct_admin_webservice/", className = "com.polymtl.wsshoppingsolver.webservice_client.FindAllShopBrandResponse")
    @Action(input = "http://shopproduct_admin_webservice/ShopProductAdminWS/findAllShopBrandRequest", output = "http://shopproduct_admin_webservice/ShopProductAdminWS/findAllShopBrandResponse")
    public String findAllShopBrand();

    /**
     * 
     * @param country
     * @param city
     * @param idBrand
     * @param street
     * @param postcode
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "createShopBranch", targetNamespace = "http://shopproduct_admin_webservice/", className = "com.polymtl.wsshoppingsolver.webservice_client.CreateShopBranch")
    @ResponseWrapper(localName = "createShopBranchResponse", targetNamespace = "http://shopproduct_admin_webservice/", className = "com.polymtl.wsshoppingsolver.webservice_client.CreateShopBranchResponse")
    @Action(input = "http://shopproduct_admin_webservice/ShopProductAdminWS/createShopBranchRequest", output = "http://shopproduct_admin_webservice/ShopProductAdminWS/createShopBranchResponse")
    public boolean createShopBranch(
        @WebParam(name = "idBrand", targetNamespace = "")
        long idBrand,
        @WebParam(name = "street", targetNamespace = "")
        String street,
        @WebParam(name = "city", targetNamespace = "")
        String city,
        @WebParam(name = "postcode", targetNamespace = "")
        String postcode,
        @WebParam(name = "country", targetNamespace = "")
        String country);

    /**
     * 
     * @param description
     * @param idCategory
     * @param barCode
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "createProduct", targetNamespace = "http://shopproduct_admin_webservice/", className = "com.polymtl.wsshoppingsolver.webservice_client.CreateProduct")
    @ResponseWrapper(localName = "createProductResponse", targetNamespace = "http://shopproduct_admin_webservice/", className = "com.polymtl.wsshoppingsolver.webservice_client.CreateProductResponse")
    @Action(input = "http://shopproduct_admin_webservice/ShopProductAdminWS/createProductRequest", output = "http://shopproduct_admin_webservice/ShopProductAdminWS/createProductResponse")
    public boolean createProduct(
        @WebParam(name = "idCategory", targetNamespace = "")
        long idCategory,
        @WebParam(name = "barCode", targetNamespace = "")
        String barCode,
        @WebParam(name = "description", targetNamespace = "")
        String description);

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findAllProduct", targetNamespace = "http://shopproduct_admin_webservice/", className = "com.polymtl.wsshoppingsolver.webservice_client.FindAllProduct")
    @ResponseWrapper(localName = "findAllProductResponse", targetNamespace = "http://shopproduct_admin_webservice/", className = "com.polymtl.wsshoppingsolver.webservice_client.FindAllProductResponse")
    @Action(input = "http://shopproduct_admin_webservice/ShopProductAdminWS/findAllProductRequest", output = "http://shopproduct_admin_webservice/ShopProductAdminWS/findAllProductResponse")
    public String findAllProduct();

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findAllProductCategory", targetNamespace = "http://shopproduct_admin_webservice/", className = "com.polymtl.wsshoppingsolver.webservice_client.FindAllProductCategory")
    @ResponseWrapper(localName = "findAllProductCategoryResponse", targetNamespace = "http://shopproduct_admin_webservice/", className = "com.polymtl.wsshoppingsolver.webservice_client.FindAllProductCategoryResponse")
    @Action(input = "http://shopproduct_admin_webservice/ShopProductAdminWS/findAllProductCategoryRequest", output = "http://shopproduct_admin_webservice/ShopProductAdminWS/findAllProductCategoryResponse")
    public String findAllProductCategory();

    /**
     * 
     * @param productBarCode
     * @param idShop
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getProductPriceInShop", targetNamespace = "http://shopproduct_admin_webservice/", className = "com.polymtl.wsshoppingsolver.webservice_client.GetProductPriceInShop")
    @ResponseWrapper(localName = "getProductPriceInShopResponse", targetNamespace = "http://shopproduct_admin_webservice/", className = "com.polymtl.wsshoppingsolver.webservice_client.GetProductPriceInShopResponse")
    @Action(input = "http://shopproduct_admin_webservice/ShopProductAdminWS/getProductPriceInShopRequest", output = "http://shopproduct_admin_webservice/ShopProductAdminWS/getProductPriceInShopResponse")
    public String getProductPriceInShop(
        @WebParam(name = "productBarCode", targetNamespace = "")
        String productBarCode,
        @WebParam(name = "idShop", targetNamespace = "")
        long idShop);

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findAllShopBranch", targetNamespace = "http://shopproduct_admin_webservice/", className = "com.polymtl.wsshoppingsolver.webservice_client.FindAllShopBranch")
    @ResponseWrapper(localName = "findAllShopBranchResponse", targetNamespace = "http://shopproduct_admin_webservice/", className = "com.polymtl.wsshoppingsolver.webservice_client.FindAllShopBranchResponse")
    @Action(input = "http://shopproduct_admin_webservice/ShopProductAdminWS/findAllShopBranchRequest", output = "http://shopproduct_admin_webservice/ShopProductAdminWS/findAllShopBranchResponse")
    public String findAllShopBranch();

    /**
     * 
     * @param categoryName
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "createProductCategory", targetNamespace = "http://shopproduct_admin_webservice/", className = "com.polymtl.wsshoppingsolver.webservice_client.CreateProductCategory")
    @ResponseWrapper(localName = "createProductCategoryResponse", targetNamespace = "http://shopproduct_admin_webservice/", className = "com.polymtl.wsshoppingsolver.webservice_client.CreateProductCategoryResponse")
    @Action(input = "http://shopproduct_admin_webservice/ShopProductAdminWS/createProductCategoryRequest", output = "http://shopproduct_admin_webservice/ShopProductAdminWS/createProductCategoryResponse")
    public boolean createProductCategory(
        @WebParam(name = "categoryName", targetNamespace = "")
        String categoryName);

    /**
     * 
     * @param idShop
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAllProductsInShop", targetNamespace = "http://shopproduct_admin_webservice/", className = "com.polymtl.wsshoppingsolver.webservice_client.GetAllProductsInShop")
    @ResponseWrapper(localName = "getAllProductsInShopResponse", targetNamespace = "http://shopproduct_admin_webservice/", className = "com.polymtl.wsshoppingsolver.webservice_client.GetAllProductsInShopResponse")
    @Action(input = "http://shopproduct_admin_webservice/ShopProductAdminWS/getAllProductsInShopRequest", output = "http://shopproduct_admin_webservice/ShopProductAdminWS/getAllProductsInShopResponse")
    public String getAllProductsInShop(
        @WebParam(name = "idShop", targetNamespace = "")
        long idShop);

}
