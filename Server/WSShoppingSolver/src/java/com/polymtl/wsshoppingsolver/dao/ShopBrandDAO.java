/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.dao;

import com.polymtl.wsshoppingsolver.model.ShopBrand;
import com.polymtl.wsshoppingsolver.util.Constants;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jiajing
 */
@Stateless
public class ShopBrandDAO implements ShopBrandDAOLocal {
    @PersistenceContext(unitName = Constants.persistUnitName)
    private EntityManager em;

    @Override
    public void create(ShopBrand aBrand) {
        em.persist(aBrand);
    }

    @Override
    public ShopBrand findByKey(long id) {
        return em.find(ShopBrand.class, id);
    }

    @Override
    public void update(ShopBrand aBrand) {
        em.merge(aBrand);
    }

    @Override
    public void delete(long id) {
        em.remove(findByKey(id));
    }
}
