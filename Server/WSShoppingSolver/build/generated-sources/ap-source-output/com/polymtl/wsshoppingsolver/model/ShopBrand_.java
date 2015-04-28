package com.polymtl.wsshoppingsolver.model;

import com.polymtl.wsshoppingsolver.model.ShopBranch;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-24T15:22:30")
@StaticMetamodel(ShopBrand.class)
public class ShopBrand_ { 

    public static volatile SingularAttribute<ShopBrand, String> brandName;
    public static volatile ListAttribute<ShopBrand, ShopBranch> branchList;
    public static volatile SingularAttribute<ShopBrand, Long> id;

}