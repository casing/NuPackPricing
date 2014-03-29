/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nulogy.nupack.interest.impl;

import com.nulogy.nupack.interest.MarkupRate;
import com.nulogy.nupack.MaterialType;

/**
 * 
 * @author casing
 */
public class NuPackMarkupRate implements MarkupRate {

    private static final double FLAT_MARKUP = 0.05;
    private static final double PERSON_MARKUP = 0.012;
    private static final double DRUG_MARKUP = 0.075;
    private static final double FOOD_MARKUP = 0.13;
    private static final double ELECT_MARKUP = 0.02;

    public double getMaterialMarkupRate(MaterialType type) {
        switch(type) {
            case DRUGS:
                return DRUG_MARKUP;
            case FOOD:
                return FOOD_MARKUP;
            case ELECTRONICS:
                return ELECT_MARKUP;
            default:
                return 0.0;
        }
    }

    public double getFlatMarkupRate() {
        return FLAT_MARKUP;
    }

    public double getWorkerMarkupRate() {
        return PERSON_MARKUP;
    }
}
