/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Client;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jiajing
 */
@Stateless
public class ClientDAO implements ClientDAOLocal {
    @PersistenceContext(unitName = "WSShoppingSolverPU")
    private EntityManager em;

    @Override
    public void addClient(Client aClient) {
        em.persist(aClient);
    }

    @Override
    public Client getClient(long idClient) {
        return em.find(Client.class, idClient);
    }

    @Override
    public void editClient(Client aClient) {
        em.merge(aClient);
    }

    @Override
    public void deleteClient(long idClient) {
        Client c = getClient(idClient);
        em.remove(c);
    }
    
}
