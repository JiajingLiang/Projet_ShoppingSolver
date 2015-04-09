/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.dao;

import com.polymtl.wsshoppingsolver.model.ProductCategory;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Jiajing
 */
@Local
public interface ProductCategoryDAOLocal {
    void create(ProductCategory aCategory);
    ProductCategory findByKey(long id);
    List<ProductCategory> findAllCategory();
    void update(ProductCategory aCategory);
    void delete(long id);
}
