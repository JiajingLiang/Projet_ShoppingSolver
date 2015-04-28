/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.dao;

import com.polymtl.wsshoppingsolver.model.ShopBranch;
import com.polymtl.wsshoppingsolver.util.Constant;
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
public class ShopBranchDAO implements ShopBranchDAOLocal {
    @PersistenceContext(unitName = Constant.persistUnitName)
    private EntityManager em;

    @Override
    public void create(ShopBranch aBranch) {
        em.persist(aBranch);
    }

    @Override
    public ShopBranch findByKey(long id) {
        return em.find(ShopBranch.class, id);
    }
    
    @Override
    public List<ShopBranch> findAllShopBranch(){
        Query queryFindAllBranch= em.createNamedQuery("ShopBranch.findAllShopBranch");
        return queryFindAllBranch.getResultList();
    }

    @Override
    public void update(ShopBranch aBranch) {
        em.merge(aBranch);
    }

    @Override
    public void delete(long id) {
        em.remove(findByKey(id));
    }

}
