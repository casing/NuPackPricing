package com.nulogy.nupack.impl;

import com.nulogy.nupack.MarkupCalculator;
import com.nulogy.nupack.interest.MarkupRate;
import com.nulogy.nupack.MaterialType;
import com.nulogy.nupack.money.Money;

/**
 * NuPackMarkupCalculator is an implementation of a MarkupCalcultor.
 * 
 * This class can be used to solve the Nulogy Pricing Problem and calculate
 * the FinalCost after product markup.
 * 
 * @author casing
 */
public class NuPackMarkupCalculator implements MarkupCalculator {
    private static final String NEGATIVE_BASE_PRICE = "Base Price can not be negative!";
    private static final String NEGATIVE_WORKERS = "Number of Workers can not be negative!";
    private MarkupRate markupRate;
    private Money basePrice;
    private int workers;
    private MaterialType materialType;
    
    public NuPackMarkupCalculator(MarkupRate markupRate) {
        basePrice = Money.ZERO;
        workers = 0;
        this.markupRate = markupRate;
    }

    public void setBasePrice(Money basePrice) {
        if(basePrice != null) {
            // Throw exception if user enters negative basePrice
            if(!basePrice.isPositive()) {
                throw new IllegalArgumentException(NEGATIVE_BASE_PRICE);
            }
        }
        this.basePrice = basePrice;
    }

    public void setNumberOfWorkers(int workers) {
        if(workers < 0) {
            throw new IllegalArgumentException(NEGATIVE_WORKERS);
        }
        this.workers = workers;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public Money getBasePrice() {
        return basePrice;
    }

    public int getNumberOfWorkers() {
        return workers;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }
    
    private Money getWorkersMarkup(Money base) {
        if(markupRate != null) {
            return base.multiply(markupRate.getWorkerMarkupRate()*getNumberOfWorkers());
        }
        return Money.ZERO;
    }
    
    private Money getMaterialMarkup(Money base) {
        if(markupRate != null) {
            return base.multiply(markupRate.getMaterialMarkupRate(getMaterialType()));
        }
        return Money.ZERO;
    }
    
    private Money getFlatMarkup(Money base) {
        if(markupRate != null) {
            return base.multiply(markupRate.getFlatMarkupRate());
        }
        return Money.ZERO;
    }
    
    public Money getFinalCost() {
        if(basePrice != null) {
            Money result = basePrice.add(getFlatMarkup(basePrice));
            result = result.add(getWorkersMarkup(result)).add(getMaterialMarkup(result));
            return result;
        }
        return Money.ZERO;//Return zero dollars if basePrice has been set to Null
    }
}
