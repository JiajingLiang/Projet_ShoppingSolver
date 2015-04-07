/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.model;

import java.io.Serializable;

/**
 *
 * @author Jiajing
 */
public class ProductTransactRecordId implements Serializable{
    private static final long serialVersionUID = 1L;
 
    private Long transact;
    private String product;

    public ProductTransactRecordId(Long transact, String product) {
        this.transact = transact;
        this.product = product;
    }

    public Long getTransact() {
        return transact;
    }

    public String getProduct() {
        return product;
    }
    
    @Override
    public int hashCode(){
        return transact.hashCode()+product.hashCode();
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj instanceof ProductTransactRecordId){
            ProductTransactRecordId productTransactRecordId = (ProductTransactRecordId)obj;
            return productTransactRecordId.product.equals(this.product)&&productTransactRecordId.transact.equals(this.transact);
        }
        return false;
    }
}
