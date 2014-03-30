/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nulogy.nupack;

import com.nulogy.nupack.money.Money;

/**
 * Interface for MarkupCalculator
 * @author casing
 */
public interface MarkupCalculator {
    void setBasePrice(Money base);
    void setNumberOfWorkers(int workers);
    void setMaterialType(MaterialType type);
    Money getBasePrice();
    int getNumberOfWorkers();
    MaterialType getMaterialType();
    Money getFinalCost();
}
