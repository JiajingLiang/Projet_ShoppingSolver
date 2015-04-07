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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Jiajing
 */
@Entity
@Table(name = "client")
@NamedQueries({@NamedQuery(name="Client.findByEmail",query="SELECT c FROM Client c WHERE c.email = :clientEmail")})
@XStreamAlias("Client")
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
//    @SequenceGenerator(name="clientSeqGen",sequenceName=Constants.clientIdSeqName,allocationSize=1,initialValue=1)
//    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="clientSeqGen")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    @XStreamAlias("ClientId")
    private Long id;
    @Column
    @XStreamAlias("Email")
    private String email;
    @Column
    @XStreamAlias("Name")
    private String name;
    @Column
    @XStreamAlias("Password")
    private String password;
    @Column
    @XStreamAlias("Telephone")
    private String telephone;
    @Column
    @XStreamAlias("Street")
    private String street;
    @Column
    @XStreamAlias("City")
    private String city;
    @Column
    @XStreamAlias("Postcode")
    private String postCode;
    @Column
    @XStreamAlias("Country")
    private String country;
    @Column
    @XStreamAlias("Balance")
    private Double balance = 0.0;
    
    @OneToMany(mappedBy="client")
    @XStreamOmitField
    private List<Transact> transactionsHistory;
    @ManyToMany
    @JoinTable(name="client_favorite_products",joinColumns=@JoinColumn(name="clientId"),inverseJoinColumns=@JoinColumn(name="productId"))
    @XStreamOmitField
    private List<Product> favoriteProducts;
    
    @OneToMany(mappedBy="client")
    @XStreamOmitField
    private List<RegistedDevice> devices;
    
    public Client(){
    }

    public Client(String email, String name, String password, String telephone, String street, String city, String postCode, String country) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.telephone = telephone;
        this.street = street;
        this.city = city;
        this.postCode = postCode;
        this.country = country;
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


    public Double getBalance() {
        return balance;
    }

    public List<Transact> getTransactionsHistory() {
        return transactionsHistory;
    }

    public List<Product> getFavoriteProducts() {
        return favoriteProducts;
    }

    public List<RegistedDevice> getDevices() {
        return devices;
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

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setFavoriteProducts(List<Product> favoriteProducts) {
        this.favoriteProducts = favoriteProducts;
    }

    public void setDevices(List<RegistedDevice> devices) {
        this.devices = devices;
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
        return "com.polymtl.wsshoppingsolver.model.Client{" + "id=" + id + ", email=" + email + ", name=" + name + ", password=" + password + ", telephone=" + telephone + ", street=" + street + ", city=" + city + ", postCode=" + postCode + ", country=" + country + ", balance=" + balance + '}';
    }
    
    public String toXmlString(){       
        XStream xstream = new XStream();
        xstream.processAnnotations(Client.class);
        return xstream.toXML(this);
    }
    
}
