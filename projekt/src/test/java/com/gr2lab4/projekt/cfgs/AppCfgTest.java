/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr2lab4.projekt.cfgs;

import com.gr2lab4.projekt.Entities.Pracownik;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author G
 */
public class AppCfgTest {
    
    public AppCfgTest() {
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
     * Test of setUser method, of class AppCfg.
     */
    @Test
    public void testSetUser() {
        System.out.println("setUser");
        Pracownik user = new Pracownik();
        AppCfg instance = new AppCfg();
        instance.setUser(user);
        assertEquals(instance.user, user);
    }

    /**
     * Test of getUser method, of class AppCfg.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        AppCfg instance = new AppCfg();
        Pracownik expResult = new Pracownik();
        instance.user = expResult;
        Pracownik result = instance.getUser();
        assertEquals(expResult, result);
    }
    
}
