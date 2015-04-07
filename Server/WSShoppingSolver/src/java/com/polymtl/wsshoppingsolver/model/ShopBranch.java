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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Jiajing
 */
@Entity
@Table(name = "shopbranch")

@XStreamAlias("Shop")
public class ShopBranch implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    @XStreamAlias("ShopId")
    private Long id;
    @Column
    @XStreamAlias("Street")
    private String street;
    @Column
    @XStreamAlias("City")
    private String city;
    @Column
    @XStreamAlias("PostCode")
    private String postCode;
    @Column
    @XStreamAlias("Country")
    private String country;
    @ManyToOne
    @XStreamAlias("Brand")
    private ShopBrand brand;
    
    @OneToMany(mappedBy="shopBranch")
    @XStreamOmitField
    private List<ProductPriceInShop> productPrices;
    
    @OneToMany(mappedBy="shop")
    @XStreamOmitField
    private List<Transact> transactionsInShop;

    public ShopBranch() {
    }

    public ShopBranch(String street, String city, String postCode, String country, ShopBrand brand) {
        this.street = street;
        this.city = city;
        this.postCode = postCode;
        this.country = country;
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getCountry() {
        return country;
    }

    public ShopBrand getBrand() {
        return brand;
    }
    
    public List<ProductPriceInShop> getProductPrices() {
        return productPrices;
    }

    public List<Transact> getTransactionsInShop() {
        return transactionsInShop;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public void setCountry(String country) {
        this.country = country;
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
        return "com.polymtl.wsshoppingsolver.model.ShopBranch[ id=" + id + ", street=" + street + ", city=" + city + ", postCode=" + postCode + ", country=" + country + ", brandName=" + brand.getBrandName() + " ]";
    }
    
}
