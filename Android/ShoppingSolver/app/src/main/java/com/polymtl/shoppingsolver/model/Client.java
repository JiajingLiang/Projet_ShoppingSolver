package com.polymtl.shoppingsolver.model;

import java.io.Serializable;
import com.thoughtworks.xstream.XStream;

/**
 * Created by Zoe on 15-04-08.
 */
public class Client implements Serializable {
    private long clientId;
    private String name;
    private String email;
    private String password;
    private String telephone;
    private String street;
    private String city;
    private String postcode;
    private String country;
    private double balance;
    private String deviceKey;

    public Client() {}
    public Client(long id, String name,String email, String password,
                  String telephone, String street, String city,
                  String postcode, String country, double balance) {
        this.clientId = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
        this.street = street;
        this.city = city;
        this.postcode = postcode;
        this.country = country;
        this.balance = balance;
    }
    public void setClientId(long id) {
        this.clientId = id;
    }
    public long getClientId() {
        return this.clientId;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setEmail(String data) {
        this.email = data;
    }
    public String getEmail() {
        return this.email;
    }

    public void setPassword(String data) {
        this.password = data;
    }
    public String getPassword() {
        return this.password;
    }

    public void setTelephone(String data) {
        this.telephone = data;
    }
    public String getTelephone() {
        return this.telephone;
    }

    public void setStreet(String data) {
        this.street = data;
    }
    public String getStreet() {
        return this.street;
    }

    public void setCity(String data) {
        this.city = data;
    }
    public String getCity() {
        return this.city;
    }

    public void setPostcode(String data) {
        this.postcode = data;
    }
    public String getPostcode() {
        return this.postcode;
    }

    public void setCountry(String data) {
        this.country = data;
    }
    public String getCountry() {
        return this.country;
    }

    public void setBalance(double data) {
        this.balance = data;
    }
    public double getBalance() {
        return this.balance;
    }

    public void setDeviceKey(String key) {
        deviceKey = key;
    }
    public String getDeviceKey() {
        return this.deviceKey;
    }

    @Override
    public String toString() {
        return "com.polymtl.wsshoppingsolver.model.Client{"
                + "id=" + clientId + ", email=" + email + ", name="
                + name + ", password=" + password + ", telephone="
                + telephone + ", street=" + street + ", city="
                + city + ", postCode=" + postcode + ", country=" + country + ", balance=" + balance + '}';
    }

    public String toXmlString(){
        XStream xstream = new XStream();
        xstream.processAnnotations(Client.class);
        return xstream.toXML(this);
    }

}
