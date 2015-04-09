/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.dao;

import com.polymtl.wsshoppingsolver.model.Product;
import com.polymtl.wsshoppingsolver.model.ProductTransactRecord;
import com.polymtl.wsshoppingsolver.model.ProductTransactRecordId;
import com.polymtl.wsshoppingsolver.model.Transact;
import com.polymtl.wsshoppingsolver.util.Constants;
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
public class ProductTransactRecordDAO implements ProductTransactRecordDAOLocal {
    @PersistenceContext(unitName = Constants.persistUnitName)
    private EntityManager em;
    
    @Override
    public void create(ProductTransactRecord aProductTransactRecord) {
        em.persist(aProductTransactRecord);
    }

    @Override
    public List<ProductTransactRecord> findByProduct(Product product) {
        Query queryFindByProduct = em.createNamedQuery("ProductTransactRecord.findByProduct");
        queryFindByProduct.setParameter("product", product);
        return queryFindByProduct.getResultList();
    }

    @Override
    public List<ProductTransactRecord> findByTransact(Transact transact) {
        Query queryFindByProduct = em.createNamedQuery("ProductTransactRecord.findByTransact");
        queryFindByProduct.setParameter("transact", transact);
        return queryFindByProduct.getResultList();
    }

    @Override
    public ProductTransactRecord findByKey(long transactId, String productCode) {
        ProductTransactRecordId productTransactRecordId = new ProductTransactRecordId(transactId,productCode);
        return em.find(ProductTransactRecord.class, productTransactRecordId);
    }

}
