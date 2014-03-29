/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nulogy.nupack.impl;

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
     * Test of getFinalCost method, of class NuPackMarkupCalculator.
     */
    public void testGetFinalCost() {
        System.out.println("getFinalCost");
        NuPackMarkupCalculator instance = new NuPackMarkupCalculator();
        BigDecimal expResult = null;
        BigDecimal result = instance.getFinalCost();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
