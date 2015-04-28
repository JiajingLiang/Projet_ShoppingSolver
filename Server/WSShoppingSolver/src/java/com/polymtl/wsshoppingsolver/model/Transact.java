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
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jiajing
 */
@Entity
@Table(name = "transact")
@NamedQueries({@NamedQuery(name="Transact.findByClient",query="SELECT t FROM Transact t WHERE t.client = :transactClient"),
               @NamedQuery(name="Transact.findByShop",query="SELECT t FROM Transact t WHERE t.shop = :transactShop"),
               @NamedQuery(name="Transact.findByClientAndDate",query="SELECT t FROM Transact t WHERE t.client = :transactClient AND t.transactionTime > :dateBegin AND t.transactionTime > :dateEnd"),
               @NamedQuery(name="Transact.findRecentTransactByClient",query="SELECT t FROM Transact t WHERE t.client = :transactClient ORDER BY t.transactionTime DESC")})

@XStreamAlias("Transaction")
public class Transact implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XStreamAlias("Id")
    private Long id;
    @Column(nullable = false)
    @XStreamAlias("Total")
    private Double total;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",nullable = false,insertable=false,updatable=false)
    @XStreamAlias("TransactionTime")
    private java.util.Date transactionTime;
    
    @ManyToOne
    @XStreamOmitField
    private Client client;
    @ManyToOne
    @XStreamAlias("Shop")
    private ShopBranch shop;
    
    @OneToMany(mappedBy="transact")
    @XStreamOmitField
    private List<ProductTransactRecord> productsBought;

    public Transact() {
    }

    public Transact(Double total, Client client, ShopBranch shop) {
        this.total = total;
        this.client = client;
        this.shop = shop;
    }
    
    public Long getId() {
        return id;
    }

    public Double getTotal() {
        return total;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public Client getClient() {
        return client;
    }

    public ShopBranch getShop() {
        return shop;
    }

    public List<ProductTransactRecord> getProductsBought() {
        return productsBought;
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
        if (!(object instanceof Transact)) {
            return false;
        }
        Transact other = (Transact) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.polymtl.wsshoppingsolver.model.Transaction[ id=" + id + ", transactionTime"+transactionTime.toString() + ", total" + total + " ]";
    }
    
    public String toXmlString(){
        XStream xstream = new XStream();
        xstream.processAnnotations(Transact.class);
        xstream.processAnnotations(ShopBranch.class);
        return xstream.toXML(this);
    }
    
}
