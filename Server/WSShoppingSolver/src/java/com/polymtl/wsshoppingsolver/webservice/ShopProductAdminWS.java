/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.webservice;

import com.polymtl.wsshoppingsolver.dao.ProductCategoryDAOLocal;
import com.polymtl.wsshoppingsolver.dao.ProductDAOLocal;
import com.polymtl.wsshoppingsolver.dao.ProductPriceInShopDAOLocal;
import com.polymtl.wsshoppingsolver.dao.ShopBranchDAOLocal;
import com.polymtl.wsshoppingsolver.dao.ShopBrandDAOLocal;
import com.polymtl.wsshoppingsolver.model.Product;
import com.polymtl.wsshoppingsolver.model.ProductCategory;
import com.polymtl.wsshoppingsolver.model.ProductPriceInShop;
import com.polymtl.wsshoppingsolver.model.ShopBranch;
import com.polymtl.wsshoppingsolver.model.ShopBrand;
import com.thoughtworks.xstream.XStream;
import java.util.List;
import java.util.Vector;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author Jiajing
 */
@WebService(serviceName = "ShopProductAdminWS", targetNamespace = "http://shopproduct_admin_webservice/")
@Stateless()
public class ShopProductAdminWS {
    @EJB
    private ShopBrandDAOLocal shopBrandDao;
    @EJB
    private ShopBranchDAOLocal shopBranchDao;
    @EJB
    private ProductCategoryDAOLocal productCategoryDao;
    @EJB
    private ProductDAOLocal productDao;
    @EJB
    private ProductPriceInShopDAOLocal productPriceInShopDao;

    @WebMethod(operationName = "createShopBrand")
    public boolean createShopBrand(@WebParam(name = "BrandName") String brandName) {
        ShopBrand aBrand = new ShopBrand(brandName);
        shopBrandDao.create(aBrand);
        return true;
    }
    
    @WebMethod(operationName = "findAllShopBrand")
    public String findAllShopBrand() {
        List<ShopBrand> allBrands = shopBrandDao.findAllShopBrand();
        XStream xstream = new XStream();
        xstream.processAnnotations(ShopBrand.class);
        return xstream.toXML(allBrands);
    }
    
    @WebMethod(operationName = "createShopBranch")
    public boolean createShopBranch(@WebParam(name="idBrand")long idBrand, @WebParam(name="street")String street, @WebParam(name="city")String city, @WebParam(name="postcode")String postcode, @WebParam(name="country")String country){
        ShopBrand brand = shopBrandDao.findByKey(idBrand);
        if(brand != null){
            ShopBranch aBranch = new ShopBranch(street,city,postcode,country,brand);
            shopBranchDao.create(aBranch);
            return true;
        }else{
            return false;
        }
    }
    
    @WebMethod(operationName = "findAllShopBranch")
    public String findAllShopBranch() {
        List<ShopBranch> allBranches = shopBranchDao.findAllShopBranch();
        XStream xstream = new XStream();
        xstream.processAnnotations(ShopBranch.class);
        return xstream.toXML(allBranches);
    }
    
    @WebMethod(operationName = "createProductCategory")
    public boolean createProductCategory(@WebParam(name="categoryName")String categoryName){
        ProductCategory aCategory = new ProductCategory(categoryName);
        productCategoryDao.create(aCategory);
        return true;
    }
    
    @WebMethod(operationName = "findAllProductCategory")
    public String findAllProductCategory() {
        List<ProductCategory> allCategories= productCategoryDao.findAllCategory();
        XStream xstream = new XStream();
        xstream.processAnnotations(ProductCategory.class);
        return xstream.toXML(allCategories);
    }
    
    @WebMethod(operationName = "createProduct")
    public boolean createProduct(@WebParam(name="idCategory")long idCategory, @WebParam(name="barCode")String barCode, @WebParam(name="description")String description){
        ProductCategory category = productCategoryDao.findByKey(idCategory);
        if(category != null && barCode != null){
            Product p = productDao.findByKey(barCode);
            if(p==null){
                Product aProduct = new Product(barCode,description,category);
                productDao.create(aProduct);
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    
    @WebMethod(operationName = "findAllProduct")
    public String findAllProduct() {
        List<Product> allProducts= productDao.findAllProduct();
        XStream xstream = new XStream();
        xstream.processAnnotations(Product.class);
        xstream.processAnnotations(ProductCategory.class);
        return xstream.toXML(allProducts);
    }
    
    @WebMethod(operationName = "addProductToShop")
    public boolean addProductToShop(@WebParam(name="idProduct")String productBarCode, @WebParam(name="idShop")long shopId, @WebParam(name="price")Double price, @WebParam(name="ratioTaxFederal")Float ratioTaxFederal, @WebParam(name="ratioTaxProvincial")Float ratioTaxProvincial){
        Product product = productDao.findByKey(productBarCode);
        ShopBranch shop = shopBranchDao.findByKey(shopId);
        if(product != null && shop != null){
            if(productPriceInShopDao.findByKey(productBarCode, shopId)==null){
                ProductPriceInShop aProductPriceInShop = new ProductPriceInShop(product,shop,price,ratioTaxFederal,ratioTaxProvincial);
                productPriceInShopDao.create(aProductPriceInShop);
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    
    @WebMethod(operationName = "getProductPriceInShop")
    public String getProductPriceFromShop(@WebParam(name="productBarCode")String productBarCode, @WebParam(name="idShop")long shopId){
        ProductPriceInShop productPriceInShop = productPriceInShopDao.findByKey(productBarCode, shopId);
        XStream xstream = new XStream();
        xstream.processAnnotations(ProductPriceInShop.class);
        xstream.processAnnotations(Product.class);
        xstream.processAnnotations(ProductCategory.class);
        xstream.processAnnotations(ShopBranch.class);
        xstream.processAnnotations(ShopBrand.class);
        return xstream.toXML(productPriceInShop);
    }
    
    @WebMethod(operationName = "getAllProductsInShop")
    public String getAllProductsInShop(@WebParam(name="idShop")long shopId){
        ShopBranch shop = shopBranchDao.findByKey(shopId);
        XStream xstream = new XStream();
        if(shop != null){
            List<ProductPriceInShop> productsInShop = productPriceInShopDao.findByShop(shop);
            List<Product> products = new Vector<>();
            if(productsInShop.size()>0){
                for (ProductPriceInShop productInShop : productsInShop) {
                    products.add(productInShop.getProduct());
                }
            }
            xstream.processAnnotations(Product.class);
            xstream.processAnnotations(ProductCategory.class);
            return xstream.toXML(products);
        }else{
            return xstream.toXML(null);
        }       
    }
}
