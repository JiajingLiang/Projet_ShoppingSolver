package com.polymtl.wsshoppingsolver.model;

import com.polymtl.wsshoppingsolver.model.Client;
import com.polymtl.wsshoppingsolver.model.ProductTransactRecord;
import com.polymtl.wsshoppingsolver.model.ShopBranch;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-24T15:22:30")
@StaticMetamodel(Transact.class)
public class Transact_ { 

    public static volatile SingularAttribute<Transact, Double> total;
    public static volatile SingularAttribute<Transact, ShopBranch> shop;
    public static volatile ListAttribute<Transact, ProductTransactRecord> productsBought;
    public static volatile SingularAttribute<Transact, Client> client;
    public static volatile SingularAttribute<Transact, Long> id;
    public static volatile SingularAttribute<Transact, Date> transactionTime;

}