/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Jiajing
 */
@Entity
@Table(name = "shopbrand")

@XStreamAlias("Brand")
public class ShopBrand implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    @XStreamAlias("BrandId")
    private Long id;
    @Column
    @XStreamAlias("BrandName")
    private String brandName;
    @OneToMany
    @XStreamOmitField
    private List<ShopBranch> branchList;
    
    public ShopBrand(){
    }
    
    public ShopBrand(String brandName){
        this.brandName = brandName;
    }

    public Long getId() {
        return id;
    }

    public String getBrandName() {
        return brandName;
    }

    public List<ShopBranch> getBranchList() {
        return branchList;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
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
        if (!(object instanceof ShopBrand)) {
            return false;
        }
        ShopBrand other = (ShopBrand) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.polymtl.wsshoppingsolver.model.ShopBrand[ id=" + id + ", brandName=" + brandName + " ]";
    }
    
}
