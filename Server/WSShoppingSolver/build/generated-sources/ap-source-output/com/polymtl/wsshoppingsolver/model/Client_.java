package com.polymtl.wsshoppingsolver.model;

import com.polymtl.wsshoppingsolver.model.Product;
import com.polymtl.wsshoppingsolver.model.RegistedDevice;
import com.polymtl.wsshoppingsolver.model.Transact;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-24T15:22:30")
@StaticMetamodel(Client.class)
public class Client_ { 

    public static volatile SingularAttribute<Client, String> country;
    public static volatile ListAttribute<Client, Product> favoriteProducts;
    public static volatile SingularAttribute<Client, String> city;
    public static volatile ListAttribute<Client, RegistedDevice> devices;
    public static volatile SingularAttribute<Client, String> telephone;
    public static volatile SingularAttribute<Client, String> password;
    public static volatile SingularAttribute<Client, Double> balance;
    public static volatile SingularAttribute<Client, String> street;
    public static volatile SingularAttribute<Client, String> name;
    public static volatile ListAttribute<Client, Transact> transactionsHistory;
    public static volatile SingularAttribute<Client, String> postCode;
    public static volatile SingularAttribute<Client, Long> id;
    public static volatile SingularAttribute<Client, String> email;

}