/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nulogy.nupack.interest.impl;

import com.nulogy.nupack.MaterialType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Simple Unit Test for NuPackMarkupRate class
 * @author casing
 */
public class NuPackMarkupRateTest {
    
    public NuPackMarkupRateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getMaterialMarkupRate method, of class NuPackMarkupRate.
     */
    @Test
    public void testGetMaterialMarkupRate() {
        MaterialType type = null;
        NuPackMarkupRate instance = new NuPackMarkupRate();
        double expResult = 0.0;
        double result = instance.getMaterialMarkupRate(type);
        assertEquals("Null MaterialType should return 0.0 rate", expResult, result, 0.0);
    }
}
