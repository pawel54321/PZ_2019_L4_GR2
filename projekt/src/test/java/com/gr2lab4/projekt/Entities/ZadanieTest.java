/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr2lab4.projekt.Entities;

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
public class ZadanieTest {
    
    public ZadanieTest() {
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
     * Test of getTytul method, of class Zadanie.
     */
    @Test
    public void testGetTytul() {
        System.out.println("getTytul");
        Zadanie instance = new Zadanie();
        String expResult = "testTytul";
        instance.tytul = expResult;
        String result = instance.getTytul();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Zadanie.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Zadanie instance = new Zadanie();
        int expResult = 99;
        instance.id = expResult;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStatus method, of class Zadanie.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        Zadanie instance = new Zadanie();
        String expResult = "Aktywne";
        instance.status = expResult;
        String result = instance.getStatus();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPracownik method, of class Zadanie.
     */
    @Test
    public void testGetPracownik() {
        System.out.println("getPracownik");
        Zadanie instance = new Zadanie();
        Pracownik expResult = new Pracownik();
        instance.pracownik = expResult;
        Pracownik result = instance.getPracownik();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPracownik method, of class Zadanie.
     */
    @Test
    public void testSetPracownik() {
        System.out.println("setPracownik");
        Pracownik pracownik = new Pracownik();
        Zadanie instance = new Zadanie();
        instance.setPracownik(pracownik);
        assertEquals(instance.pracownik, pracownik);
    }

    /**
     * Test of getData_rozp method, of class Zadanie.
     */
    @Test
    public void testGetData_rozp() {
        System.out.println("getData_rozp");
        Zadanie instance = new Zadanie();
        Date expResult = new Date();
        instance.data_rozp = expResult;
        Date result = instance.getData_rozp();
        assertEquals(expResult, result);
    }

    /**
     * Test of getData_ukon method, of class Zadanie.
     */
    @Test
    public void testGetData_ukon() {
        System.out.println("getData_ukon");
        Zadanie instance = new Zadanie();
        Date expResult = new Date();
        instance.data_ukon = expResult;
        Date result = instance.getData_ukon();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTytul method, of class Zadanie.
     */
    @Test
    public void testSetTytul() {
        System.out.println("setTytul");
        String tytul = "testTytul";
        Zadanie instance = new Zadanie();
        instance.setTytul(tytul);
        assertEquals(instance.tytul, tytul);
    }

    /**
     * Test of getTresc method, of class Zadanie.
     */
    @Test
    public void testGetTresc() {
        System.out.println("getTresc");
        Zadanie instance = new Zadanie();
        String expResult = "testTresc";
        instance.tresc = expResult;
        String result = instance.getTresc();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTresc method, of class Zadanie.
     */
    @Test
    public void testSetTresc() {
        System.out.println("setTresc");
        String tresc = "testTresc";
        Zadanie instance = new Zadanie();
        instance.setTresc(tresc);
        assertEquals(instance.tresc, tresc);
    }

    /**
     * Test of setData_rozp method, of class Zadanie.
     */
    @Test
    public void testSetData_rozp() {
        System.out.println("setData_rozp");
        Date data_rozp = new Date();
        Zadanie instance = new Zadanie();
        instance.setData_rozp(data_rozp);
        assertEquals(instance.data_rozp, data_rozp);
    }

    /**
     * Test of setData_ukon method, of class Zadanie.
     */
    @Test
    public void testSetData_ukon() {
        System.out.println("setData_ukon");
        Date data_ukon = new Date();
        Zadanie instance = new Zadanie();
        instance.setData_ukon(data_ukon);
        assertEquals(instance.data_ukon, data_ukon);
    }

    /**
     * Test of setStatus method, of class Zadanie.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        String status = "Aktywne";
        Zadanie instance = new Zadanie();
        instance.setStatus(status);
        assertEquals(instance.status, status);
    }

    /**
     * Test of toString method, of class Zadanie.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Date data_rozp = new Date();
        Date data_ukon = new Date();
        Pracownik pracownik = new Pracownik();
        Zadanie instance = new Zadanie("testTytul", "testTresc", data_rozp, "Aktywne", pracownik);
        instance.id = 99;
        instance.data_ukon = data_ukon;
        
        instance.pracownik = pracownik;
        String expResult = "Zadanie [id=99, tytul=testTytul, tresc=testTresc, data_rozp=" + data_rozp
				+ ", data_ukon=" + data_ukon + ", status=Aktywne, pracownicy=" + pracownik + "]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
