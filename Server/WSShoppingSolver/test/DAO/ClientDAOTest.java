/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Client;
import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jiajing
 */
public class ClientDAOTest {
    @EJB
    private ClientDAOLocal clientDAO = new ClientDAO();
    
    private String testClientEmail = "jj@polymtl.ca";
    private String testClientName = "jjliang";
    private String testClientPassword = "jjliang";
    private String testClientTelephone = "0402057894";
    private String testClientAddress = "xxx";
    private String testClientAddress2 = "yyy";
    
    public ClientDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    /**
     * Test of CRUD, of class ClientDAO.
     */
    public void testCRUD() {
        System.out.println("----- Test CRUD -----");
        
        System.out.println("create");
        Client aClient = new Client(testClientEmail,testClientName, testClientPassword, testClientTelephone, testClientAddress);
        clientDAO.addClient(aClient);
    }
    
}
