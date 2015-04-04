/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.webservice;

import com.polymtl.wsshoppingsolver.dao.ProductCategoryDAOLocal;
import com.polymtl.wsshoppingsolver.dao.ProductDAOLocal;
import com.polymtl.wsshoppingsolver.dao.ShopBranchDAOLocal;
import com.polymtl.wsshoppingsolver.dao.ShopBrandDAOLocal;
import com.polymtl.wsshoppingsolver.model.Product;
import com.polymtl.wsshoppingsolver.model.ProductCategory;
import com.polymtl.wsshoppingsolver.model.ShopBranch;
import com.polymtl.wsshoppingsolver.model.ShopBrand;
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

    @WebMethod(operationName = "createShopBrand")
    public Boolean createShopBrand(@WebParam(name = "BrandName") String brandName) {
        ShopBrand aBrand = new ShopBrand(brandName);
        shopBrandDao.create(aBrand);
        return true;
    }
    
    @WebMethod(operationName = "createShopBranch")
    public Boolean createShopBranch(@WebParam(name="idBrand")long idBrand, @WebParam(name="street")String street, @WebParam(name="city")String city, @WebParam(name="postcode")String postcode, @WebParam(name="country")String country){
        ShopBrand brand = shopBrandDao.findByKey(idBrand);
        if(brand != null){
            String address = street+","+city+","+postcode+","+country;
            ShopBranch aBranch = new ShopBranch(address,brand);
            shopBranchDao.create(aBranch);
            return true;
        }else{
            return false;
        }
    }
    
    @WebMethod(operationName = "createProductCategory")
    public Boolean createProductCategory(@WebParam(name="categoryName")String categoryName){
        ProductCategory aCategory = new ProductCategory(categoryName);
        productCategoryDao.create(aCategory);
        return true;
    }
    
    @WebMethod(operationName = "createProduct")
    public Boolean createProduct(@WebParam(name="idCategory")long idCategory, @WebParam(name="description")String description){
        ProductCategory category = productCategoryDao.findByKey(idCategory);
        if(category != null){
            Product aProduct = new Product(description,category);
            productDao.create(aProduct);
            return true;
        }else{
            return false;
        }
    }
}
