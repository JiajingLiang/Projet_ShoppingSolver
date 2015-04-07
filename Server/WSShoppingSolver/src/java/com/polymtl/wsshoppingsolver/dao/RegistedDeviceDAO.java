/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.dao;

import com.polymtl.wsshoppingsolver.model.RegistedDevice;
import com.polymtl.wsshoppingsolver.util.Constants;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jiajing
 */
@Stateless
public class RegistedDeviceDAO implements RegistedDeviceDAOLocal {
    @PersistenceContext(unitName = Constants.persistUnitName)
    private EntityManager em;
    
    @Override
    public void create(RegistedDevice aDevice) {
        em.persist(aDevice);
    }

    @Override
    public RegistedDevice findByKey(String deviceId) {
        return em.find(RegistedDevice.class, deviceId);
    }

    @Override
    public void update(RegistedDevice aDevice) {
        em.merge(aDevice);
    }

    @Override
    public void delete(String deviceId) {
        em.remove(findByKey(deviceId));
    }
}
