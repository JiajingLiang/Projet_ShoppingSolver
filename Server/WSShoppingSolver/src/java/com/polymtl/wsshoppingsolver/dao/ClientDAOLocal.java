/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.dao;

import com.polymtl.wsshoppingsolver.model.Client;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Jiajing
 */
@Local
public interface ClientDAOLocal {
    void create(Client aClient);
    Client findByKey(long id);
    List<Client> findByEmail(String email);
    void updateClient(Client aClient);
    void delete(long id);
}
