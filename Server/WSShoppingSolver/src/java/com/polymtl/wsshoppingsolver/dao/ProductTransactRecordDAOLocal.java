/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.dao;

import com.polymtl.wsshoppingsolver.model.Product;
import com.polymtl.wsshoppingsolver.model.ProductTransactRecord;
import com.polymtl.wsshoppingsolver.model.Transact;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Jiajing
 */
@Local
public interface ProductTransactRecordDAOLocal {
    void create(ProductTransactRecord aProductTransactRecord);
    public List<ProductTransactRecord> findByProduct(Product product);
    public List<ProductTransactRecord> findByTransact(Transact transact);
    public ProductTransactRecord findByKey(long transactId,String productCode);
}
