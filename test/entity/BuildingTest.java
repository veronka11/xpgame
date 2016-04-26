/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
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
public class BuildingTest {
    
    public BuildingTest() {
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
     * Test of addUpgrade method, of class Building.
     */
    @Test
    public void testAddUpgrade() {
        System.out.println("addUpgrade");
        BuildingUpgrade upgradeData = null;
        Building instance = null;
        instance.addUpgrade(upgradeData);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPrice method, of class Building.
     */
    @Test
    public void testSetPrice() {
        System.out.println("setPrice");
        int[] priceData = null;
        Building instance = null;
        instance.setPrice(priceData);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setProduction method, of class Building.
     */
    @Test
    public void testSetProduction_boolean() {
        System.out.println("setProduction");
        boolean state = false;
        Building instance = null;
        instance.setProduction(state);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setProduction method, of class Building.
     */
    @Test
    public void testSetProduction_intArr() {
        System.out.println("setProduction");
        int[] productData = null;
        Building instance = null;
        instance.setProduction(productData);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class Building.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Building instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Building.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Building instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrice method, of class Building.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        Building instance = null;
        int[] expResult = null;
        int[] result = instance.getPrice();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProduction method, of class Building.
     */
    @Test
    public void testGetProduction() {
        System.out.println("getProduction");
        Building instance = null;
        int[] expResult = null;
        int[] result = instance.getProduction();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isProductive method, of class Building.
     */
    @Test
    public void testIsProductive() {
        System.out.println("isProductive");
        Building instance = null;
        boolean expResult = false;
        boolean result = instance.isProductive();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUpgrades method, of class Building.
     */
    @Test
    public void testGetUpgrades() {
        System.out.println("getUpgrades");
        Building instance = null;
        ArrayList<BuildingUpgrade> expResult = null;
        ArrayList<BuildingUpgrade> result = instance.getUpgrades();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUpgrades method, of class Building.
     */
    @Test
    public void testSetUpgrades() {
        System.out.println("setUpgrades");
        ArrayList<BuildingUpgrade> buildingUpgrades = null;
        Building instance = null;
        instance.setUpgrades(buildingUpgrades);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Building.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Building instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
