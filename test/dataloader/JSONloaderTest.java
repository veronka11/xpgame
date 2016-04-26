/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataloader;

import entity.Building;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author peto
 */
public class JSONloaderTest {
    
    public JSONloaderTest() {
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
     * Test of JSONloadBuildings method, of class JSONloader.
     */
    @Test
    public void testJSONloadBuildings() {
        System.out.println("JSONloadBuildings");
        String path = "";
        HashMap<Integer, Building> expResult = null;
        HashMap<Integer, Building> result = JSONloader.JSONloadBuildings(path);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class JSONloader.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        JSONloader.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
