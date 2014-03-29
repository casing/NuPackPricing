package com.nulogy.nupack.impl;

import com.nulogy.nupack.MarkupCalculator;
import com.nulogy.nupack.interest.MarkupRate;
import com.nulogy.nupack.MaterialType;
import com.nulogy.nupack.interest.impl.NuPackMarkupRate;
import java.math.BigDecimal;

/**
 * Nulogy Coding Exercise: Pricing Problem
 * @author casing
 */
public class NuPackMarkupCalculator implements MarkupCalculator {

    private static final int DECIMAL_PLACES = 2;
    private static final int ROUNDING_MODE = BigDecimal.ROUND_HALF_UP;
    private MarkupRate markupRate;
    private BigDecimal basePrice;
    private int workers;
    private MaterialType materialType;
    
    public NuPackMarkupCalculator() {
        markupRate = new NuPackMarkupRate();
    }
    
    public void setBasePrice(BigDecimal base) {
        this.basePrice = base;
    }

    public void setNumberOfWorkers(int workers) {
        this.workers = workers;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }
    
    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public int getNumberOfWorkers() {
        return workers;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    private BigDecimal getMaterialRate(MaterialType type) {
        if(markupRate != null) {
            return new BigDecimal(markupRate.getMaterialMarkupRate(type));
        }
        return BigDecimal.ZERO;
    }
    
    private BigDecimal getFlatRate() {
        if(markupRate != null) {
            return new BigDecimal(markupRate.getFlatMarkupRate());
        }
        return BigDecimal.ZERO;
    }
    
    private BigDecimal getWorkerRate() {
        if(markupRate != null) {
            return new BigDecimal(markupRate.getWorkerMarkupRate());
        }
        return BigDecimal.ZERO;
    }
    
    private BigDecimal round(BigDecimal bd) {
        return bd.setScale(DECIMAL_PLACES, ROUNDING_MODE);
    }
    
    private BigDecimal getWorkersMarkup(BigDecimal base) {
        BigDecimal result = base.multiply(getWorkerRate().multiply(new BigDecimal(getNumberOfWorkers())));
        return round(result);
    }
    
    private BigDecimal getMaterialMarkup(BigDecimal base) {
        BigDecimal result = base.multiply(getMaterialRate(getMaterialType()));
        return round(result);
    }
    
    public BigDecimal getFinalCost() {
        BigDecimal result = basePrice.add(basePrice.multiply(getFlatRate()));
        result = result.add(getWorkersMarkup(result)).add(getMaterialMarkup(result));
        return round(result);
    }
}
