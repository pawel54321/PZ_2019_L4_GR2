/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr2lab4.projekt.Entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class PracownikTest {
    
    public PracownikTest() {
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
     * Test of addZadania method, of class Pracownik.
     */
    @Test
    public void testAddZadania() {
        System.out.println("addZadania");
        Zadanie tempZadanie = new Zadanie();
        Pracownik instance = new Pracownik();
        instance.addZadania(tempZadanie);
        List<Zadanie> zadania = new ArrayList<>();
        zadania.add(tempZadanie);
        boolean test = Arrays.asList(instance.zadania).contains(zadania);
        assertTrue(test);
    }

    /**
     * Test of getZadania method, of class Pracownik.
     */
    @Test
    public void testGetZadania() {
        System.out.println("getZadania");
        Pracownik instance = new Pracownik();
        Zadanie test = new Zadanie();
        List<Zadanie> expResult = new ArrayList<>();
        expResult.add(test);
        instance.zadania = expResult;
        List<Zadanie> result = instance.getZadania();
        assertEquals(expResult, result);
    }

    /**
     * Test of setZadania method, of class Pracownik.
     */
    @Test
    public void testSetZadania() {
        System.out.println("setZadania");
        Zadanie test = new Zadanie();
        List<Zadanie> zadania = new ArrayList<>();
        zadania.add(test);
        Pracownik instance = new Pracownik();
        instance.setZadania(zadania);
        assertEquals(instance.zadania, zadania);
    }

    /**
     * Test of getId_pracownika method, of class Pracownik.
     */
    @Test
    public void testGetId_pracownika() {
        System.out.println("getId_pracownika");
        Pracownik instance = new Pracownik();
        int expResult = 99;
        instance.id_pracownika = expResult;
        int result = instance.getId_pracownika();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId_pracownika method, of class Pracownik.
     */
    @Test
    public void testSetId_pracownika() {
        System.out.println("setId_pracownika");
        int id_pracownika = 0;
        Pracownik instance = new Pracownik();
        instance.setId_pracownika(id_pracownika);
        assertEquals(id_pracownika, instance.id_pracownika);
    }

    /**
     * Test of getImie method, of class Pracownik.
     */
    @Test
    public void testGetImie() {
        System.out.println("getImie");
        Pracownik instance = new Pracownik();
        String expResult = "testImie";
        instance.imie = expResult;
        String result = instance.getImie();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNazwisko method, of class Pracownik.
     */
    @Test
    public void testGetNazwisko() {
        System.out.println("getNazwisko");
        Pracownik instance = new Pracownik();
        String expResult = "testNazwisko";
        instance.nazwisko = expResult;
        String result = instance.getNazwisko();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStanowisko method, of class Pracownik.
     */
    @Test
    public void testGetStanowisko() {
        System.out.println("getStanowisko");
        Pracownik instance = new Pracownik();
        String expResult = "testStanowisko";
        instance.stanowisko = expResult;
        String result = instance.getStanowisko();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLogin method, of class Pracownik.
     */
    @Test
    public void testGetLogin() {
        System.out.println("getLogin");
        Pracownik instance = new Pracownik();
        String expResult = "testLogin";
        instance.login = expResult;
        String result = instance.getLogin();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHaslo method, of class Pracownik.
     */
    @Test
    public void testGetHaslo() {
        System.out.println("getHaslo");
        Pracownik instance = new Pracownik();
        String expResult = "testHaslo";
        instance.haslo = expResult;
        String result = instance.getHaslo();
        assertEquals(expResult, result);
    }

    /**
     * Test of setImie method, of class Pracownik.
     */
    @Test
    public void testSetImie() {
        System.out.println("setImie");
        String imie = "testImie";
        Pracownik instance = new Pracownik();
        instance.setImie(imie);
        assertEquals(instance.imie, imie);
    }

    /**
     * Test of setNazwisko method, of class Pracownik.
     */
    @Test
    public void testSetNazwisko() {
        System.out.println("setNazwisko");
        String nazwisko = "testNazwisko";
        Pracownik instance = new Pracownik();
        instance.setNazwisko(nazwisko);
        assertEquals(instance.nazwisko, nazwisko);
    }

    /**
     * Test of setStanowisko method, of class Pracownik.
     */
    @Test
    public void testSetStanowisko() {
        System.out.println("setStanowisko");
        String stanowisko = "testStanowisko";
        Pracownik instance = new Pracownik();
        instance.setStanowisko(stanowisko);
        assertEquals(instance.stanowisko, stanowisko);
    }

    /**
     * Test of setLogin method, of class Pracownik.
     */
    @Test
    public void testSetLogin() {
        System.out.println("setLogin");
        String login = "testLogin";
        Pracownik instance = new Pracownik();
        instance.setLogin(login);
        assertEquals(instance.login, login);
    }

    /**
     * Test of setHaslo method, of class Pracownik.
     */
    @Test
    public void testSetHaslo() {
        System.out.println("setHaslo");
        String haslo = "testHaslo";
        Pracownik instance = new Pracownik();
        instance.setHaslo(haslo);
        assertEquals(instance.haslo, haslo);
    }

    /**
     * Test of toString method, of class Pracownik.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Pracownik instance = new Pracownik();
        instance.setId_pracownika(99);
        instance.setImie("testImie");
        instance.setNazwisko("testNazwisko");
        String expResult = "99, testImie testNazwisko";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
