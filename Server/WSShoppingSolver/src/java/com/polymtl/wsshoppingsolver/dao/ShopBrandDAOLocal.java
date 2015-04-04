/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.dao;

import com.polymtl.wsshoppingsolver.model.ShopBrand;
import javax.ejb.Local;

/**
 *
 * @author Jiajing
 */
@Local
public interface ShopBrandDAOLocal {
    void create(ShopBrand aBrand);
    ShopBrand findByKey(long id);
    void update(ShopBrand aBrand);
    void delete(long id);
}
