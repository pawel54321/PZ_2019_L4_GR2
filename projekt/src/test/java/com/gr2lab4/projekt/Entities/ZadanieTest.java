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
     * Test of getAktywne method, of class Zadanie.
     */
    @Test
    public void testGetAktywne() {
        System.out.println("getAktywne");
        Zadanie instance = new Zadanie();
        int expResult = 1;
        instance.aktywne = expResult;
        int result = instance.getAktywne();
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
     * Test of isAktywne method, of class Zadanie.
     */
    @Test
    public void testIsAktywne() {
        System.out.println("isAktywne");
        Zadanie instance = new Zadanie();
        int expResult = 1;
        instance.aktywne = expResult;
        int result = instance.isAktywne();
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
     * Test of setAktywne method, of class Zadanie.
     */
    @Test
    public void testSetAktywne() {
        System.out.println("setAktywne");
        int aktywne = 1;
        Zadanie instance = new Zadanie();
        instance.setAktywne(aktywne);
        assertEquals(instance.aktywne, aktywne);
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
        Zadanie instance = new Zadanie("testTytul", "testTresc", data_rozp, data_ukon, 1, pracownik);
        instance.id = 99;
        
        instance.pracownik = pracownik;
        String expResult = "Zadanie [id=99, tytul=testTytul, tresc=testTresc, data_rozp=" + data_rozp
				+ ", data_ukon=" + data_ukon + ", aktywne=1, pracownicy=" + pracownik + "]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
