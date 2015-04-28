/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.dao;

import com.polymtl.wsshoppingsolver.model.Product;
import com.polymtl.wsshoppingsolver.model.ProductPriceInShop;
import com.polymtl.wsshoppingsolver.model.ShopBranch;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Jiajing
 */
@Local
public interface ProductPriceInShopDAOLocal {
    void create(ProductPriceInShop aProductPriceInShop);
    public List<ProductPriceInShop> findByProduct(Product product);
    public List<ProductPriceInShop> findByShop(ShopBranch shop);
    public ProductPriceInShop findByKey(String productBarCode,long shopId);
    ProductPriceInShop update(ProductPriceInShop aProductPriceInShop);
    void delete(String productBarCode,long shopId);    
}
