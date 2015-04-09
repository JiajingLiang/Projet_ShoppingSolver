/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.dao;

import com.polymtl.wsshoppingsolver.model.Product;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Jiajing
 */
@Local
public interface ProductDAOLocal {
    void create(Product aProduct);
    Product findByKey(String barCode);
    List<Product> findAllProduct();
    void update(Product aProduct);
    void delete(String barCode);
}
