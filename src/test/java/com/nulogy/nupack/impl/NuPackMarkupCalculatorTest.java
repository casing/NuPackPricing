/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nulogy.nupack.impl;

import com.nulogy.nupack.MaterialType;
import java.math.BigDecimal;
import junit.framework.TestCase;

/**
 * 
 * @author casing
 */
public class NuPackMarkupCalculatorTest extends TestCase {
    
    public NuPackMarkupCalculatorTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test Input Examples given by problem
     */
    public void testInputExamples() {
        BigDecimal expected = new BigDecimal("1591.58");
        NuPackMarkupCalculator calc = new NuPackMarkupCalculator();
        calc.setBasePrice(new BigDecimal("1299.99"));
        calc.setNumberOfWorkers(3);
        calc.setMaterialType(MaterialType.FOOD);
        assertEquals(expected, calc.getFinalCost());
        
        
        expected = new BigDecimal("6199.81");
        calc.setBasePrice(new BigDecimal("5432.00"));
        calc.setNumberOfWorkers(1);
        calc.setMaterialType(MaterialType.PHARMACEUTICALS);
        assertEquals(expected, calc.getFinalCost());
        
        
        expected = new BigDecimal("13707.63");
        calc.setBasePrice(new BigDecimal("12456.95"));
        calc.setNumberOfWorkers(4);
        calc.setMaterialType(MaterialType.OTHER);
        assertEquals(expected, calc.getFinalCost());
    }
    
    public void testZeroBase() {
        BigDecimal expected = new BigDecimal("0.00");
        NuPackMarkupCalculator calc = new NuPackMarkupCalculator();
        calc.setBasePrice(BigDecimal.ZERO);
        for(MaterialType t : MaterialType.values()) {
            for(int i = 0;i<1000;i++) {
                calc.setNumberOfWorkers(i);
                calc.setMaterialType(t);
                assertEquals(expected, calc.getFinalCost());
            }
        }
    }
}
