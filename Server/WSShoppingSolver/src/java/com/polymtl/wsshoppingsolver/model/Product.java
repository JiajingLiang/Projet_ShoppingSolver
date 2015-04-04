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
@Table(name = "product")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String description;
    @ManyToOne
    private ProductCategory category;

    public Product() {
    }

    public Product(String description, ProductCategory category) {
        this.description = description;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
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
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.polymtl.wsshoppingsolver.model.Product[ id=" + id + ", description" + description + ", category" + category.getCategoryName() + " ]";
    }
    
}
