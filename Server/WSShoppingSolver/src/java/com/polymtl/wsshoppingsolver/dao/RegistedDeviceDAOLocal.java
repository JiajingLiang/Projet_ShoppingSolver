/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.dao;

import com.polymtl.wsshoppingsolver.model.RegistedDevice;
import javax.ejb.Local;

/**
 *
 * @author Jiajing
 */
@Local
public interface RegistedDeviceDAOLocal {
    void create(RegistedDevice aDevice);
    RegistedDevice findByKey(String deviceId);
    void update(RegistedDevice aDevice);
    void delete(String deviceId);
}
