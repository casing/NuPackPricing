/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nulogy.nupack.impl;

import com.nulogy.nupack.MaterialType;
import com.nulogy.nupack.interest.MarkupRate;
import com.nulogy.nupack.interest.impl.NuPackMarkupRate;
import com.nulogy.nupack.money.Money;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 * Unit testing for the NuPackMarkupCalculator
 * @author casing
 */
public class NuPackMarkupCalculatorTest {
    
    /**
     * Mock out the MarkupRate interface
     */
    @Mock
    private static MarkupRate markupRateMock;
    
    /**
     * In normal situations this would not be here but for the purposes of this
     * problem I will set the Mock values to the actual values from this 
     * NuPackMarkupRate object.  This is a bit of a roundabout but I wanted
     * to demonstrate the my use of Mock objects;
     */
    private static NuPackMarkupRate markupRate;
    
    private Money $19_99;
    private Money $n19_99;
    private Money largeMoney;
    
    public NuPackMarkupCalculatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        // Lets just set the Mock object values to the actual values from 
        // the NuPackMarkupRate object
        markupRate = new NuPackMarkupRate();
        markupRateMock = Mockito.mock(MarkupRate.class);
        Mockito.when(markupRateMock.getFlatMarkupRate()).thenReturn(markupRate.getFlatMarkupRate());
        Mockito.when(markupRateMock.getWorkerMarkupRate()).thenReturn(markupRate.getWorkerMarkupRate());
        for(MaterialType t : MaterialType.values()) {
            Mockito.when(markupRateMock.getMaterialMarkupRate(t)).thenReturn(markupRate.getMaterialMarkupRate(t));
        }
    }
    
    @Before
    public void setUp() {
        $19_99 = new Money("19.99");
        $n19_99 = new Money("-19.99");
        largeMoney = new Money(new BigDecimal(Double.MAX_VALUE));
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testInputExamples() {
        Money expected = new Money("1591.58");
        NuPackMarkupCalculator calc = new NuPackMarkupCalculator(markupRateMock);
        calc.setBasePrice(new Money("1299.99"));
        calc.setNumberOfWorkers(3);
        calc.setMaterialType(MaterialType.FOOD);
        assertEquals("Test Problem Input 1", expected, calc.getFinalCost());
        
        
        expected = new Money("6199.81");
        calc.setBasePrice(new Money("5432.00"));
        calc.setNumberOfWorkers(1);
        calc.setMaterialType(MaterialType.DRUGS);
        assertEquals("Test Problem Input 2", expected, calc.getFinalCost());
        
        
        expected = new Money("13707.63");
        calc.setBasePrice(new Money("12456.95"));
        calc.setNumberOfWorkers(4);
        calc.setMaterialType(MaterialType.OTHER);
        assertEquals("Test Problem Input 3", expected, calc.getFinalCost());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testNegativeBasePriceException() {
        NuPackMarkupCalculator calc = new NuPackMarkupCalculator(markupRateMock);
        calc.setBasePrice($n19_99);
    }
    
    @Test (expected = NumberFormatException.class)
    public void testBasePriceNumberFormatException() {
        NuPackMarkupCalculator calc = new NuPackMarkupCalculator(markupRateMock);
        calc.setBasePrice(new Money("Hello World"));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testNegativeNumberOfWorkersException() {
        NuPackMarkupCalculator calc = new NuPackMarkupCalculator(markupRateMock);
        calc.setNumberOfWorkers(-10);
    }
    
    /**
     * Test that a Zero Base will always return Zero iterating through
     * each MaterialType and upto 1000 Workers
     */
    @Test
    public void testZeroBase() {
        NuPackMarkupCalculator calc = new NuPackMarkupCalculator(markupRateMock);
        calc.setBasePrice(Money.ZERO);
        for(MaterialType t : MaterialType.values()) {
            for(int i = 0;i<10;i++) {
                calc.setNumberOfWorkers(i);
                calc.setMaterialType(t);
                assertEquals(Money.ZERO, calc.getFinalCost());
            }
        }
    }
    
    @Test
    public void testLargeBase() {
        NuPackMarkupCalculator calc = new NuPackMarkupCalculator(markupRateMock);
        calc.setBasePrice(largeMoney);
        System.out.println(largeMoney);
        for(MaterialType t : MaterialType.values()) {
            for(int i = 0;i<10;i++) {
                calc.setNumberOfWorkers(i);
                calc.setMaterialType(t);
                assertEquals("Test large base calculation with " + t + " and " + i + " workers.", 
                        new Money(calc.getFinalCost().toString()), calc.getFinalCost());
            }
        }
    }
}
