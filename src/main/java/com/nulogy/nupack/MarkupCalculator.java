/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nulogy.nupack;

import java.math.BigDecimal;

/**
 *
 * @author casing
 */
public interface MarkupCalculator {
    void setBasePrice(BigDecimal base);
    void setNumberOfWorkers(int workers);
    void setMaterialType(MaterialType type);
    BigDecimal getBasePrice();
    int getNumberOfWorkers();
    MaterialType getMatrialType();
    BigDecimal getFinalCost();
}
