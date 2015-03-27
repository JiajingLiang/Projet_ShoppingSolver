/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Client;
import javax.ejb.Local;

/**
 *
 * @author Jiajing
 */
@Local
public interface ClientDAOLocal {

    void addClient(Client aClient);

    Client getClient(long idClient);

    void editClient(Client aClient);

    void deleteClient(long idClient);
    
}
