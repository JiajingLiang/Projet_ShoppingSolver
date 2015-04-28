package com.polymtl.wsshoppingsolver.model;

import com.polymtl.wsshoppingsolver.model.Product;
import com.polymtl.wsshoppingsolver.model.ShopBranch;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-24T15:22:30")
@StaticMetamodel(ProductPriceInShop.class)
public class ProductPriceInShop_ { 

    public static volatile SingularAttribute<ProductPriceInShop, Float> ratioTaxProvincial;
    public static volatile SingularAttribute<ProductPriceInShop, Product> product;
    public static volatile SingularAttribute<ProductPriceInShop, ShopBranch> shopBranch;
    public static volatile SingularAttribute<ProductPriceInShop, Double> price;
    public static volatile SingularAttribute<ProductPriceInShop, Float> ratioTaxFederal;

}