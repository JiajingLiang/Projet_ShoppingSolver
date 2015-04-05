/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.model;

import com.polymtl.wsshoppingsolver.util.Constants;
import com.thoughtworks.xstream.XStream;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Jiajing
 */
@Entity
@Table(name = "client")
@NamedQueries({@NamedQuery(name="Client.findByEmail",query="SELECT c FROM Client c WHERE c.email = :clientEmail")})
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
//    @SequenceGenerator(name="clientSeqGen",sequenceName=Constants.clientIdSeqName,allocationSize=1,initialValue=1)
//    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="clientSeqGen")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @Column
    private String email;
    @Column
    private String name;
    @Column
    private String password;
    @Column
    private String telephone;
    @Column
    private String address;
    @Column
    private Double balance = 0.0;
    
    public Client(){
    }

    public Client(String email, String name, String password, String telephone, String address) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.telephone = telephone;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getAddress() {
        return address;
    }

    public Double getBalance() {
        return balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
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
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.polymtl.wsshoppingsolver.model.Client{" + "id=" + id + ", email=" + email + ", name=" + name + ", password=" + password + ", telephone=" + telephone + ", address=" + address + ", balance=" + balance + '}';
    }
    
    public String toXmlString(){       
        XStream xstream = new XStream();
        xstream.alias("client", Client.class);
        return xstream.toXML(this);
    }
    
}
