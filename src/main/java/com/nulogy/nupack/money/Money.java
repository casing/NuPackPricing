/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nulogy.nupack.money;

import java.math.BigDecimal;

/**
 * Class to encapsulate the concept of Money.
 * This is a simple implementation of Money for the purposes of this problem.
 * 
 * With this implementation I am assuming the Money is always provided in the same
 * currency, hence no currency specification.  But if this class were to be extended
 * currency property and many other processing functions could be provided.
 * 
 * @author casing
 */
public class Money implements Comparable<Money> {
    private static final String VALUE_CANT_BE_NULL = "Value can not be null!";
    public static final Money ZERO = new Money();
    private static final int DECIMAL_PLACES = 2;
    private static final int ROUNDING_MODE = BigDecimal.ROUND_HALF_UP;
    
    private BigDecimal value;
    
    public Money(BigDecimal value) {
        validateValue(value);
        this.value = value;
        this.value = this.value.setScale(DECIMAL_PLACES, ROUNDING_MODE);
    }
    
    public Money(String money) {
        this(new BigDecimal(money));
    }

    public Money() {
        this(BigDecimal.ZERO);
    }
    
    private void validateValue(BigDecimal value) {
        if(value == null) {
            throw new IllegalArgumentException(VALUE_CANT_BE_NULL);
        }
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        validateValue(value);
        this.value = value;
    }
    
    private BigDecimal round(BigDecimal bd) {
        return bd.setScale(DECIMAL_PLACES, ROUNDING_MODE);
    }
    
    public Money add(Money money) {
        if(money != null) {
            return new Money(value.add(money.value));
        }
        return new Money(value);
    }
    
    public Money multiply(double m) {
        BigDecimal result = value.multiply(new BigDecimal(m));
        return new Money(round(result));
    }
    
    /**
     * Method to determine if Money if greater than or equal to zero
     * 
     * @return true if Money is greater than or equal to zero
     */
    public boolean isPositive() {
        return value.compareTo(BigDecimal.ZERO) >= 0;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        
        if(!(obj instanceof Money)) {
            return false;
        }
        
        Money test = (Money)obj;
        return value.equals(test.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public int compareTo(Money o) {
        if(this == o) {
            return 0;//EQUAL
        }
        
        if(o == null) {
            return 1;
        }
        
        return this.value.compareTo(o.value);
    }

    @Override
    public String toString() {
        return value.toPlainString();
    }
}
