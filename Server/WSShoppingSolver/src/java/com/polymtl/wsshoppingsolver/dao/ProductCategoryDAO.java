/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.dao;

import com.polymtl.wsshoppingsolver.model.ProductCategory;
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
public class ProductCategoryDAO implements ProductCategoryDAOLocal {
    @PersistenceContext(unitName = Constant.persistUnitName)
    private EntityManager em;

    @Override
    public void create(ProductCategory aCategory) {
        em.persist(aCategory);
    }

    @Override
    public ProductCategory findByKey(long id) {
        return em.find(ProductCategory.class, id);
    }
    
    @Override
    public List<ProductCategory> findAllCategory(){
        Query queryFindAllCategory = em.createNamedQuery("ProductCategory.findAllCategory");
        return queryFindAllCategory.getResultList();
    }

    @Override
    public void update(ProductCategory aCategory) {
        em.merge(aCategory);
    }

    @Override
    public void delete(long id) {
        em.remove(findByKey(id));
    }

}
