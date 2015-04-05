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
public class ProductShopAssoId implements Serializable{
    private static final long serialVersionUID = 1L;
 
    private String product;
    private Long shopBranch;

    public ProductShopAssoId(String product, Long shopBranch) {
        this.product = product;
        this.shopBranch = shopBranch;
    }

    public String getProduct() {
        return product;
    }

    public Long getShopBranch() {
        return shopBranch;
    }
    
    @Override
    public int hashCode(){
        return product.hashCode()+shopBranch.hashCode();
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj instanceof ProductShopAssoId){
            ProductShopAssoId productShopAssoId = (ProductShopAssoId)obj;
            return productShopAssoId.product.equals(this.product)&&productShopAssoId.shopBranch.equals(this.shopBranch);
        }
        return false;
    }
}
