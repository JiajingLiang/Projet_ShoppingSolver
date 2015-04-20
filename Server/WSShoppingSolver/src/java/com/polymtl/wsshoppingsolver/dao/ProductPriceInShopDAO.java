/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.dao;

import com.polymtl.wsshoppingsolver.model.Product;
import com.polymtl.wsshoppingsolver.model.ProductPriceInShop;
import com.polymtl.wsshoppingsolver.model.ProductShopAssoId;
import com.polymtl.wsshoppingsolver.model.ShopBranch;
import com.polymtl.wsshoppingsolver.util.Constant;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Jiajing
 */
@Stateless
public class ProductPriceInShopDAO implements ProductPriceInShopDAOLocal {
    @PersistenceContext(unitName = Constant.persistUnitName)
    private EntityManager em;

    @Override
    public void create(ProductPriceInShop aProductPriceInShop) {
        em.persist(aProductPriceInShop);
    }

    @Override
    public List<ProductPriceInShop> findByProduct(Product product) {
        Query queryFindByProduct = em.createNamedQuery("ProductPriceInShop.findByProduct");
        queryFindByProduct.setParameter("product", product);
        return queryFindByProduct.getResultList();
    }

    @Override
    public List<ProductPriceInShop> findByShop(ShopBranch shop) {
        Query queryFindByShop = em.createNamedQuery("ProductPriceInShop.findByShop");
        queryFindByShop.setParameter("shopBranch", shop);
        return queryFindByShop.getResultList();
    }
    
    @Override
    public ProductPriceInShop findByKey(String productBarCode,long shopId) {
        ProductShopAssoId productShopAssoId = new ProductShopAssoId(productBarCode,shopId);
        return em.find(ProductPriceInShop.class, productShopAssoId);
    }

    @Override
    public ProductPriceInShop update(ProductPriceInShop aProductPriceInShop) {
        return em.merge(aProductPriceInShop);
    }

    @Override
    public void delete(String productBarCode,long shopId) {
        em.remove(findByKey(productBarCode,shopId));
    }

}
