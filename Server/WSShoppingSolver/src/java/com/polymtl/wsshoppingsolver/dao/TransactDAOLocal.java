/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.dao;

import com.polymtl.wsshoppingsolver.model.Client;
import com.polymtl.wsshoppingsolver.model.ShopBranch;
import com.polymtl.wsshoppingsolver.model.Transact;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Jiajing
 */
@Local
public interface TransactDAOLocal {
    Transact create(Transact aTransact);
    Transact findByKey(long id);
    List<Transact> findByClient(Client client);
    List<Transact> findByShop(ShopBranch shop); 
    List<Transact> findByClientAndDate(Client client, Date dateBegin, Date dateEnd);
    List<Transact> findRecentTransactByClient(Client client);
}
