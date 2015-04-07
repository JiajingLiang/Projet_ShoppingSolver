/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Jiajing
 */
@Entity
@Table(name = "product_transact_record")
@IdClass(ProductTransactRecordId.class)
public class ProductTransactRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @ManyToOne
    @JoinColumn(name="transactionId")
    private Transact transact;
    @Id
    @ManyToOne
    @JoinColumn(name="productId")
    private Product product;
    @Column(nullable = false)
    private Double price;

    public ProductTransactRecord() {
    }

    public ProductTransactRecord(Transact transact, Product product, Double price) {
        this.transact = transact;
        this.product = product;
        this.price = price;
    }

    public Transact getTransact() {
        return transact;
    }

    public Product getProduct() {
        return product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (product != null ? product.hashCode() : 0);
        hash += (transact != null ? transact.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductTransactRecord)) {
            return false;
        }
        ProductTransactRecord other = (ProductTransactRecord) object;
        if ((this.product == null && other.product != null) || (this.product != null && !this.product.equals(other.product))) {
            return false;
        }
        if ((this.transact == null && other.transact != null) || (this.transact != null && !this.transact.equals(other.transact))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.polymtl.wsshoppingsolver.model.TransactProductAsso[ productId=" + product.getBarCode() + ",transactionId=" + transact.getId() + ",price" + price + " ]";
    }
    
}
