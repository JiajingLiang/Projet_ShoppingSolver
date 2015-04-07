/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Jiajing
 */
@Entity
@Table(name = "registeddevice")
public class RegistedDevice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(nullable = false,length=50)
    private String deviceId;
    @ManyToOne
    private Client client;

    public RegistedDevice() {
    }

    public RegistedDevice(String deviceId, Client client) {
        this.deviceId = deviceId;
        this.client = client;
    }

    public String getId() {
        return deviceId;
    }

    public Client getClient() {
        return client;
    }

    public void setId(String deviceId) {
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
