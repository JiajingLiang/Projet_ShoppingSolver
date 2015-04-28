package com.polymtl.wsshoppingsolver.model;

import com.polymtl.wsshoppingsolver.model.Client;
import com.polymtl.wsshoppingsolver.model.ProductCategory;
import com.polymtl.wsshoppingsolver.model.ProductPriceInShop;
import com.polymtl.wsshoppingsolver.model.ProductTransactRecord;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-24T15:22:30")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile ListAttribute<Product, ProductPriceInShop> productShopAsso;
    public static volatile ListAttribute<Product, Client> potentialClients;
    public static volatile SingularAttribute<Product, String> description;
    public static volatile SingularAttribute<Product, ProductCategory> category;
    public static volatile SingularAttribute<Product, String> barCode;
    public static volatile ListAttribute<Product, ProductTransactRecord> transactionsHasProduct;

}