/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.model;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Jiajing
 */
@Entity
@Table(name = "product")

@XStreamAlias("Product")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,length=20)
    @XStreamAlias("BarCode")
//    private Long id;
    private String barCode;
    @Column(nullable = false)
    @XStreamAlias("Description")
    private String description;
    @ManyToOne
    @XStreamAlias("Category")
    private ProductCategory category;
    
    @OneToMany(mappedBy="product")
    @XStreamOmitField
    private List<ProductPriceInShop> productShopAsso;
    
    @OneToMany(mappedBy="product")
    @XStreamOmitField
    private List<ProductTransactRecord> transactionsHasProduct;
    
    @ManyToMany(mappedBy="favoriteProducts")
    @XStreamOmitField
    private List<Client> potentialClients;

    public Product() {
    }

    public Product(String barCode, String description, ProductCategory category) {
        this.barCode = barCode;
        this.description = description;
        this.category = category;
    }

    public String getBarCode() {
        return barCode;
    }

    public String getDescription() {
        return description;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public List<ProductPriceInShop> getProductShopAsso() {
        return productShopAsso;
    }

    public List<ProductTransactRecord> getTransactionsHasProduct() {
        return transactionsHasProduct;
    }

    public List<Client> getPotentialClients() {
        return potentialClients;
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
        hash += (barCode != null ? barCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.barCode == null && other.barCode != null) || (this.barCode != null && !this.barCode.equals(other.barCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.polymtl.wsshoppingsolver.model.Product[ barCode=" + barCode + ", description" + description + ", category" + category.getCategoryName() + " ]";
    }
    
    public String toXmlString(){
        XStream xstream = new XStream();
        xstream.processAnnotations(Product.class);
        xstream.processAnnotations(ProductCategory.class);
        return xstream.toXML(this);
    }
    
}
