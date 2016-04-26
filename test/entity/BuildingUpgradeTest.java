/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

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
public class BuildingUpgradeTest {
    
    public BuildingUpgradeTest() {
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
     * Test of getName method, of class BuildingUpgrade.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        BuildingUpgrade instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getType method, of class BuildingUpgrade.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        BuildingUpgrade instance = null;
        int expResult = 0;
        int result = instance.getType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRate method, of class BuildingUpgrade.
     */
    @Test
    public void testGetRate() {
        System.out.println("getRate");
        BuildingUpgrade instance = null;
        double expResult = 0.0;
        double result = instance.getRate();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLevelIncrease method, of class BuildingUpgrade.
     */
    @Test
    public void testGetLevelIncrease() {
        System.out.println("getLevelIncrease");
        BuildingUpgrade instance = null;
        double expResult = 0.0;
        double result = instance.getLevelIncrease();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
