/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.dao;

import com.polymtl.wsshoppingsolver.model.Client;
import com.polymtl.wsshoppingsolver.model.ShopBranch;
import com.polymtl.wsshoppingsolver.model.Transact;
import com.polymtl.wsshoppingsolver.util.Constants;
import java.util.Date;
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
public class TransactDAO implements TransactDAOLocal {
    @PersistenceContext(unitName = Constants.persistUnitName)
    private EntityManager em;
    
    @Override
    public Transact create(Transact aTransact) {
       em.persist(aTransact);
       em.flush();
       return aTransact;
    }

    @Override
    public Transact findByKey(long id) {
        return em.find(Transact.class, id);
    }

    @Override
    public List<Transact> findByClient(Client client) {
        Query queryFindByClient = em.createNamedQuery("Transact.findByClient");
        queryFindByClient.setParameter("transactClient", client);
        return queryFindByClient.getResultList();
    }

    @Override
    public List<Transact> findByShop(ShopBranch shop) {
        Query queryFindByShop = em.createNamedQuery("Transact.findByShop");
        queryFindByShop.setParameter("transactShop", shop);
        return queryFindByShop.getResultList();
    }

    @Override
    public List<Transact> findByClientAndDate(Client client, Date dateBegin, Date dateEnd) {
        Query queryFindByClientAndDate = em.createNamedQuery("Transact.findByClientAndDate");
        queryFindByClientAndDate.setParameter("transactClient", client);
        queryFindByClientAndDate.setParameter("dateBegin", dateBegin);
        queryFindByClientAndDate.setParameter("dateEnd", dateEnd);
        return queryFindByClientAndDate.setMaxResults(10).getResultList();
    }

    @Override
    public List<Transact> findRecentTransactByClient(Client client){
        Query queryFindByShop = em.createNamedQuery("Transact.findRecentTransactByClient");
        queryFindByShop.setParameter("transactClient", client);
        return queryFindByShop.getResultList();
    }
}
