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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Jiajing
 */
@Entity
@Table(name = "product_transact_record")
@IdClass(ProductTransactRecordId.class)
@NamedQueries({@NamedQuery(name="ProductTransactRecord.findByTransact",query="SELECT p FROM ProductTransactRecord p WHERE p.transact = :transact"),
               @NamedQuery(name="ProductTransactRecord.findByProduct",query="SELECT p FROM ProductTransactRecord p WHERE p.product = :product")})
@XStreamAlias("ProductTransactRecord")
public class ProductTransactRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @ManyToOne
    @JoinColumn(name="transactionId")
    @XStreamOmitField
    private Transact transact;
    @Id
    @ManyToOne
    @JoinColumn(name="productId")
    @XStreamAlias("Product")
    private Product product;
    @Column(nullable = false)
    @XStreamAlias("Price")
    private Double price;
    @Column(nullable = false)
    @XStreamAlias("Quantity")
    private Float quantity;
    @Column(nullable = false)
    @XStreamAlias("RatioTaxFederal")
    private Float ratioTaxFederal;
    @Column(nullable = false)
    @XStreamAlias("RatioTaxProvincial")
    private Float ratioTaxProvincial;

    public ProductTransactRecord() {
    }

    public ProductTransactRecord(Transact transact, Product product, Double price, Float quantity, Float ratioTaxFederal, Float ratioTaxProvincial) {
        this.transact = transact;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.ratioTaxFederal = ratioTaxFederal;
        this.ratioTaxProvincial = ratioTaxProvincial;
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

    public Float getQuantity() {
        return quantity;
    }

    public Float getRatioTaxFederal() {
        return ratioTaxFederal;
    }

    public Float getRatioTaxProvincial() {
        return ratioTaxProvincial;
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
        return "com.polymtl.wsshoppingsolver.model.TransactProductAsso[ productId=" + product.getBarCode() + ",transactionId=" + transact.getId() + ",price" + price + ",quantity" + quantity + ",ratioTaxFederal" + ratioTaxFederal + ",ratioTaxProvincial" + ratioTaxProvincial + " ]";
    }
    
    public String toXmlString(){
        XStream xstream = new XStream();
        xstream.processAnnotations(ProductTransactRecord.class);
        xstream.processAnnotations(Product.class);
        return xstream.toXML(this);
    }
}
