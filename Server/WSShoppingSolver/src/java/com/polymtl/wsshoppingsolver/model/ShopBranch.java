/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Jiajing
 */
@Entity
@Table(name = "shopbranch")
public class ShopBranch implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @Column
    private String address;
    @ManyToOne
    private ShopBrand brand;

    public ShopBranch() {
    }

    public ShopBranch(String address, ShopBrand brand) {
        this.address = address;
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public ShopBrand getBrand() {
        return brand;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBrand(ShopBrand brand) {
        this.brand = brand;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShopBranch)) {
            return false;
        }
        ShopBranch other = (ShopBranch) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.polymtl.wsshoppingsolver.model.ShopBranch[ id=" + id + ", branchAddress=" + address + ", brandName=" + brand.getBrandName() + " ]";
    }
    
}
