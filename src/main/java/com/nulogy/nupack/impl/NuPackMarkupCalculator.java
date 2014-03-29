package com.nulogy.nupack.impl;

import com.nulogy.nupack.MarkupCalculator;
import com.nulogy.nupack.MaterialType;
import java.math.BigDecimal;

/**
 * Nulogy Coding Exercise: Pricing Problem
 * @author casing
 */
public class NuPackMarkupCalculator implements MarkupCalculator {

    private static final int DECIMAL_PLACES = 2;
    private static final int ROUNDING_MODE = BigDecimal.ROUND_HALF_UP;
    private static final BigDecimal FLAT_MARKUP = new BigDecimal("0.05");
    private static final BigDecimal PERSON_MARKUP = new BigDecimal("0.012");
    private static final BigDecimal PHARMA_MARKUP = new BigDecimal("0.075");
    private static final BigDecimal FOOD_MARKUP = new BigDecimal("0.13");
    private static final BigDecimal ELECT_MARKUP = new BigDecimal("0.02");
    
    private BigDecimal basePrice;
    private int workers;
    private MaterialType type;
    
    public NuPackMarkupCalculator() {
    }
    
    public void setBasePrice(BigDecimal base) {
        this.basePrice = base;
    }

    public void setNumberOfWorkers(int workers) {
        this.workers = workers;
    }

    public void setMaterialType(MaterialType type) {
        this.type = type;
    }
    
    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public int getNumberOfWorkers() {
        return workers;
    }

    public MaterialType getMatrialType() {
        return type;
    }

    private BigDecimal getMaterialMarkup(MaterialType type) {
        switch(type) {
            case PHARMACEUTICALS:
                return PHARMA_MARKUP;
            case FOOD:
                return FOOD_MARKUP;
            case ELECTRONICS:
                return ELECT_MARKUP;
            default:
                return BigDecimal.ZERO;
        }
    }
    
    private BigDecimal round(BigDecimal bd) {
        return bd.setScale(DECIMAL_PLACES, ROUNDING_MODE);
    }
    
    private BigDecimal getWorkersMarkup(BigDecimal base) {
        BigDecimal result = base.multiply(PERSON_MARKUP).multiply(new BigDecimal(getNumberOfWorkers()));
        return round(result);
    }
    
    private BigDecimal getMaterialMarkup(BigDecimal base) {
        BigDecimal result = base.multiply(getMaterialMarkup(getMatrialType()));
        return round(result);
    }
    
    public BigDecimal getFinalCost() {
        BigDecimal result = basePrice.add(basePrice.multiply(FLAT_MARKUP));
        result = result.add(getWorkersMarkup(result)).add(getMaterialMarkup(result));
        return round(result);
    }
}
