package com.polymtl.wsshoppingsolver.model;

import com.polymtl.wsshoppingsolver.model.ProductPriceInShop;
import com.polymtl.wsshoppingsolver.model.ShopBrand;
import com.polymtl.wsshoppingsolver.model.Transact;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-24T15:22:30")
@StaticMetamodel(ShopBranch.class)
public class ShopBranch_ { 

    public static volatile SingularAttribute<ShopBranch, String> country;
    public static volatile SingularAttribute<ShopBranch, String> city;
    public static volatile SingularAttribute<ShopBranch, String> street;
    public static volatile ListAttribute<ShopBranch, Transact> transactionsInShop;
    public static volatile ListAttribute<ShopBranch, ProductPriceInShop> productPrices;
    public static volatile SingularAttribute<ShopBranch, String> postCode;
    public static volatile SingularAttribute<ShopBranch, Long> id;
    public static volatile SingularAttribute<ShopBranch, ShopBrand> brand;

}