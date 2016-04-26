/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buildings;

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
public class GameBuildingControllerTest {
    
    public GameBuildingControllerTest() {
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
     * Test of CreateBuilding method, of class GameBuildingController.
     */
    @Test
    public void testCreateBuilding() {
        System.out.println("CreateBuilding");
        int id = 0;
        GameBuildingController instance = null;
        instance.CreateBuilding(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEnoughResources method, of class GameBuildingController.
     */
    @Test
    public void testIsEnoughResources() {
        System.out.println("isEnoughResources");
        int[] p = null;
        GameBuildingController instance = null;
        boolean expResult = false;
        boolean result = instance.isEnoughResources(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of useResources method, of class GameBuildingController.
     */
    @Test
    public void testUseResources() {
        System.out.println("useResources");
        int[] p = null;
        GameBuildingController instance = null;
        instance.useResources(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
