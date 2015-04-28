/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Jiajing
 */
@Entity
@Table(name = "registeddevice")
@NamedQueries({@NamedQuery(name="RegistedDevice.findByDeviceId",query="SELECT d FROM RegistedDevice d WHERE d.deviceId = :deviceId")})
@XStreamAlias("RegistedDevice")
public class RegistedDevice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    @XStreamOmitField
    private Long id;
    @Column(nullable = false,length=200)
    @XStreamAlias("DeviceId")
    private String deviceId;
    @ManyToOne
    @XStreamOmitField
    private Client client;

    public RegistedDevice() {
    }

    public RegistedDevice(String deviceId, Client client) {
        this.deviceId = deviceId;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public Client getClient() {
        return client;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deviceId != null ? deviceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistedDevice)) {
            return false;
        }
        RegistedDevice other = (RegistedDevice) object;
        if ((this.deviceId == null && other.deviceId != null) || (this.deviceId != null && !this.deviceId.equals(other.deviceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.polymtl.wsshoppingsolver.model.RegistedDevice[ deviceId=" + deviceId + " ]";
    }
    
}
