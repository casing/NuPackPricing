/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nulogy.nupack.money;

import java.math.BigDecimal;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author casing
 */
public class MoneyTest {
    private Money $19_99;
    private Money $n19_99;
    private Money $39_98;
    private Money $n39_98;
    private Money $9999_99;
    
    public MoneyTest() {
    }
    
    @Before
    public void setUp() {
        $19_99 = new Money("19.99");
        $n19_99 = new Money("-19.99");
        $39_98 = new Money("39.98");
        $n39_98 = new Money("-39.98");
        $9999_99 = new Money("9999.99");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setValue method, of class Money.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testSetValueNull() {
        BigDecimal value = null;
        Money instance = new Money(value);
    }
    
    @Test (expected = NumberFormatException.class)
    public void testSetValueEmptyStr() {
        Money instance = new Money("");
    }
    
    @Test
    public void testSetValue() {
        Money a = new Money(new BigDecimal(19.99));
        Money b = new Money("19.98999");
        Money c = new Money("0");
        Money d = new Money("0.0");
        Money e = new Money("0.00");
        Money f = new Money(BigDecimal.ZERO);
        
        assertTrue("Set with BigDecimal value", a.equals($19_99));
        assertTrue("Set value with multiple decimal places", b.equals($19_99));
        assertTrue("Set value with 0", c.equals(Money.ZERO));
        assertTrue("Set value with 0.0", d.equals(Money.ZERO));
        assertTrue("Set value with 0.00", e.equals(Money.ZERO));
        assertTrue("Set value with BigDecimal.ZERO", f.equals(Money.ZERO));
    }

    /**
     * Test of add method, of class Money.
     */
    @Test
    public void testAdd() {
        Money a = $19_99.add($19_99);
        Money b = $19_99.add(Money.ZERO);
        Money c = $19_99.add($n19_99);
        Money d = $n19_99.add($n19_99);
        assertEquals("Simple Add",a, $39_98);
        assertEquals("Add to Zero", b, $19_99);
        assertEquals("Add pos-neg Money",c, Money.ZERO);
        assertEquals("Add neg-neg Money", d, $n39_98);
    }

    /**
     * Test of multiply method, of class Money.
     */
    @Test
    public void testMultiply() {
        Money a = $19_99.multiply(0);
        Money b = $19_99.multiply(2);
        Money c = $9999_99.multiply(0.001999);
        Money d = $19_99.multiply(-1);
        assertEquals("Multiply by zero", a, Money.ZERO);
        assertEquals("Multiply by 2", b, $39_98);
        assertEquals("Multiply by 0.001999", c, $19_99);
        assertEquals("Multiply by -1", d, $n19_99);
    }

    /**
     * Test of isPositive method, of class Money.
     */
    @Test
    public void testIsPositive() {
        assertFalse("Test negative value", $n19_99.isPositive());
        assertTrue("Test positive value", $19_99.isPositive());
        assertTrue("Test zero value", Money.ZERO.isPositive());
    }

    /**
     * Test of equals method, of class Money.
     */
    @Test
    public void testEquals() {
        Money a = new Money("19.99");
        Money b = new Money("99999.89");
        Money c = new Money("19.99");
        assertFalse("Test different values", a.equals(b));
        assertTrue("Test same object", a.equals(a));
        assertTrue("Test different objects but same value", a.equals(c));
    }
    
    /**
     * Test of compareTo method, of class Money.
     */
    @Test
    public void testCompareTo() {
        assertEquals("CompareTo equals", $19_99.compareTo($19_99),0);
        assertEquals("CompareTo less than", $19_99.compareTo($39_98),-1);
        assertEquals("Compareto greater than", $9999_99.compareTo($39_98), 1);
    }
}
