/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymtl.wsshoppingsolver.dao;

import com.polymtl.wsshoppingsolver.model.ShopBranch;
import javax.ejb.Local;

/**
 *
 * @author Jiajing
 */
@Local
public interface ShopBranchDAOLocal {
    void create(ShopBranch aBranch);
    ShopBranch findByKey(long id);
    void update(ShopBranch aBranch);
    void delete(long id);
}
