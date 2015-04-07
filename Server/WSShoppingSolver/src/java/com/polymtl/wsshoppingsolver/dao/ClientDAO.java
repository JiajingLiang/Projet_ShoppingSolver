/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.dao;

import com.polymtl.wsshoppingsolver.model.Client;
import com.polymtl.wsshoppingsolver.util.Constants;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Jiajing
 */
@Stateless
public class ClientDAO implements ClientDAOLocal {
    @PersistenceContext(unitName = Constants.persistUnitName)
    private EntityManager em;

    @Override
    public Client create(Client aClient) {
       em.persist(aClient);
       em.flush();
       return aClient;
    }

    @Override
    public Client findByKey(long id) {
        return em.find(Client.class, id);
    }
    
    @Override
    public List<Client> findByEmail(String email){
        Query queryFindByEmail = em.createNamedQuery("Client.findByEmail");
        queryFindByEmail.setParameter("clientEmail", email);
        return queryFindByEmail.getResultList();
    }

    @Override
    public void update(Client aClient) {
        em.merge(aClient);
    }

    @Override
    public void delete(long id) {
        em.remove(findByKey(id));
    }
}
