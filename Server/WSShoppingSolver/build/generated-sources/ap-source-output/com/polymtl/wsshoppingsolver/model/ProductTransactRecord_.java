package com.polymtl.wsshoppingsolver.model;

import com.polymtl.wsshoppingsolver.model.Product;
import com.polymtl.wsshoppingsolver.model.Transact;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-24T15:22:30")
@StaticMetamodel(ProductTransactRecord.class)
public class ProductTransactRecord_ { 

    public static volatile SingularAttribute<ProductTransactRecord, Float> ratioTaxProvincial;
    public static volatile SingularAttribute<ProductTransactRecord, Transact> transact;
    public static volatile SingularAttribute<ProductTransactRecord, Product> product;
    public static volatile SingularAttribute<ProductTransactRecord, Float> quantity;
    public static volatile SingularAttribute<ProductTransactRecord, Double> price;
    public static volatile SingularAttribute<ProductTransactRecord, Float> ratioTaxFederal;

}