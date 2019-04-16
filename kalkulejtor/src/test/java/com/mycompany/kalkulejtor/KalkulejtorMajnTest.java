/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.kalkulejtor;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author student
 */
public class KalkulejtorMajnTest {
    
    public KalkulejtorMajnTest() {
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
     * Test of main method, of class KalkulejtorMajn.
     */
    @org.junit.Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        KalkulejtorMajn.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dodawanie method, of class KalkulejtorMajn.
     */
    @org.junit.Test
    public void testDodawanie() {
        System.out.println("dodawanie");
        double a = 0.0;
        double b = 0.0;
        double expResult = 0.0;
        double result = KalkulejtorMajn.dodawanie(a, b);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of odejmowanie method, of class KalkulejtorMajn.
     */
    @org.junit.Test
    public void testOdejmowanie() {
        System.out.println("odejmowanie");
        double a = 0.0;
        double b = 0.0;
        double expResult = 0.0;
        double result = KalkulejtorMajn.odejmowanie(a, b);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mnozenie method, of class KalkulejtorMajn.
     */
    @org.junit.Test
    public void testMnozenie() {
        System.out.println("mnozenie");
        double a = 0.0;
        double b = 0.0;
        double expResult = 0.0;
        double result = KalkulejtorMajn.mnozenie(a, b);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dzielenie method, of class KalkulejtorMajn.
     */
    @org.junit.Test
    public void testDzielenie() {
        System.out.println("dzielenie");
        double a = 0.0;
        double b = 0.0;
        double expResult = 0.0;
        double result = KalkulejtorMajn.dzielenie(a, b);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of potega method, of class KalkulejtorMajn.
     */
    @org.junit.Test
    public void testPotega() {
        System.out.println("potega");
        double a = 0.0;
        double expResult = 0.0;
        double result = KalkulejtorMajn.potega(a);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pierwiastek method, of class KalkulejtorMajn.
     */
    @org.junit.Test
    public void testPierwiastek() {
        System.out.println("pierwiastek");
        double a = 0.0;
        double expResult = 0.0;
        double result = KalkulejtorMajn.pierwiastek(a);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of odwrotnosc method, of class KalkulejtorMajn.
     */
    @org.junit.Test
    public void testOdwrotnosc() {
        System.out.println("odwrotnosc");
        double a = 0.0;
        double expResult = 0.0;
        double result = KalkulejtorMajn.odwrotnosc(a);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of znak method, of class KalkulejtorMajn.
     */
    @org.junit.Test
    public void testZnak() {
        System.out.println("znak");
        double a = 0.0;
        double expResult = 0.0;
        double result = KalkulejtorMajn.znak(a);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
