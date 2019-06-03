/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr2lab4.projekt.viewContrrollers;

import java.time.LocalDate;
import java.util.Date;
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
public class Raport1Test {
    
    public Raport1Test() {
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
     * Test of convertToLocalDateViaSqlDate method, of class Raport1.
     */
    @Test
    public void testConvertToLocalDateViaSqlDate() {
        System.out.println("convertToLocalDateViaSqlDate");
        Date dateToConvert = new Date();
        LocalDate expResult = new java.sql.Date(dateToConvert.getTime()).toLocalDate();
        LocalDate result = Raport1.convertToLocalDateViaSqlDate(dateToConvert);
        assertEquals(expResult, result);
    }
    
}
