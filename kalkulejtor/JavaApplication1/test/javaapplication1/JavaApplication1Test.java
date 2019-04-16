package javaapplication1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class JavaApplication1Test {
    
    public JavaApplication1Test() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {}
    
    @After
    public void tearDown() {}

    /**
     * Test of suma method, of class JavaApplication1.
     */
    @Test
    public void testSuma() {
        System.out.println("suma");
        double a = 20.0;
        double b = 6.4;
        double expResult = 26.4;
        double result = JavaApplication1.suma(a, b);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of roznica method, of class JavaApplication1.
     */
    @Test
    public void testRoznica() {
        System.out.println("roznica");
        double a = 13.4;
        double b = 8;
        double expResult = 5.4;
        double result = JavaApplication1.roznica(a, b);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of iloczyn method, of class JavaApplication1.
     */
    @Test
    public void testIloczyn() {
        System.out.println("iloczyn");
        double a = 3.05;
        double b = -10.0;
        double expResult = -30.5;
        double result = JavaApplication1.iloczyn(a, b);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of iloraz method, of class JavaApplication1.
     */
    @Test
    public void testIloraz() {
        System.out.println("iloraz");
        double a = 60.0;
        double b = 20.0;
        double expResult = 3.0;
        double result = JavaApplication1.iloraz(a, b);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of kwadrat method, of class JavaApplication1.
     */
    @Test
    public void testKwadrat() {
        System.out.println("kwadrat");
        double a = 0.0;
        double expResult = 0.0;
        double result = JavaApplication1.kwadrat(a);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of pierwiastek method, of class JavaApplication1.
     */
    @Test
    public void testPierwiastek() {
        System.out.println("pierwiastek");
        double a = 4.0;
        double expResult = 2.0;
        double result = JavaApplication1.pierwiastek(a);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of odwr method, of class JavaApplication1.
     */
    @Test
    public void testOdwr() {
        System.out.println("odwr");
        double a = 0.5;
        double expResult = 2;
        double result = JavaApplication1.odwr(a);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of przec method, of class JavaApplication1.
     */
    @Test
    public void testPrzec() {
        System.out.println("przec");
        double a = -450.5606;
        double expResult = 450.5606;
        double result = JavaApplication1.przec(a);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of main method, of class JavaApplication1.
     */
    /*
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        JavaApplication1.main(args);
    }
    */
}