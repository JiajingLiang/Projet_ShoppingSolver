/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.dao;

import com.polymtl.wsshoppingsolver.model.Product;
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
public class ProductDAO implements ProductDAOLocal {
    @PersistenceContext(unitName = Constant.persistUnitName)
    private EntityManager em;

    @Override
    public void create(Product aProduct) {
        em.persist(aProduct);
    }

    @Override
    public Product findByKey(String barCode) {
        return em.find(Product.class, barCode);
    }
    
    @Override
    public List<Product> findAllProduct(){
        Query queryFindAllCategory = em.createNamedQuery("Product.findAllProduct");
        return queryFindAllCategory.getResultList();    
    }

    @Override
    public void update(Product aProduct) {
        em.merge(aProduct);
    }

    @Override
    public void delete(String barCode) {
        em.remove(findByKey(barCode));
    }

}
