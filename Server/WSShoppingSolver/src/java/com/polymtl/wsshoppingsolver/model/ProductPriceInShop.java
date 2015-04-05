/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.model;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Jiajing
 */
@Entity
@IdClass(ProductShopAssoId.class)
@NamedQueries({@NamedQuery(name="ProductPriceInShop.findByProduct",query="SELECT p FROM ProductPriceInShop p WHERE p.product = :product"),
               @NamedQuery(name="ProductPriceInShop.findByShop",query="SELECT p FROM ProductPriceInShop p WHERE p.shopBranch = :shopBranch")})

@XStreamAlias("ProductInShop")
public class ProductPriceInShop implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @ManyToOne
    @JoinColumn(name="productId")
    @XStreamAlias("Product")
    private Product product;
    @Id
    @ManyToOne
    @JoinColumn(name="shopBranchId")
    @XStreamAlias("Shop")
    private ShopBranch shopBranch;
    @Column(nullable = false)
    @XStreamAlias("Price")
    private Double price;

    public ProductPriceInShop() {
    }

    public ProductPriceInShop(Product product, ShopBranch shopBranch, Double price) {
        this.product = product;
        this.shopBranch = shopBranch;
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public ShopBranch getShopBranch() {
        return shopBranch;
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
        hash += (shopBranch != null ? shopBranch.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductPriceInShop)) {
            return false;
        }
        ProductPriceInShop other = (ProductPriceInShop) object;
        if ((this.product == null && other.product != null) || (this.product != null && !this.product.equals(other.product))) {
            return false;
        }
        if ((this.shopBranch == null && other.shopBranch != null) || (this.shopBranch != null && !this.shopBranch.equals(other.shopBranch))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.polymtl.wsshoppingsolver.model.ProductPriceInShop[ productId=" + product.getBarCode() + ",shopBranchId=" + shopBranch.getId() + ",price" + price + " ]";
    }
    
    public String toXmlString(){       
        XStream xstream = new XStream();
        xstream.processAnnotations(ProductPriceInShop.class);
        xstream.processAnnotations(Product.class);
        xstream.processAnnotations(ProductCategory.class);
        xstream.processAnnotations(ShopBranch.class);
        xstream.processAnnotations(ShopBrand.class);
        return xstream.toXML(this);
    }
    
}
