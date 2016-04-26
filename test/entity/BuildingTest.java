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
     * Building inicialization tests
     */

    @Test
    public void testInicializationWorks() {
        Building b = new Building(1, "test_name");
        assertTrue(b.getUpgrades().size() == 0);
    }

    /**
     * toString tests
     */

    @Test
    public void testToStringWithoutPriceRaisesError() {
        Building b = new Building(1, "test_name");
        try {
            b.toString();
            fail("Exception not thrown.");
        }
        catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testToStringWithoutUpgradesRaisesError() {
        Building b = new Building(1, "test_name");
        b.setPrice(new int[] {1, 2, 3, 4});
        try {
            b.toString();
        }
        catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            fail("Exceptio thrown.");
        }
    }

    @Test
    public void toStringReturnsCorrectOutput(){
        Building b = new Building(1, "test_name");
        b.setPrice(new int[]{1, 2, 3, 4});
        b.setUpgrades(new ArrayList<>());
        assertEquals("Building name:test_name| Price:1-2-3-4| Total Upgrades: 0", b.toString());
    }

    @Test
    public void toStringReturnsCorrectOutput2(){
        Building b = new Building(1, "test_name");
        b.setPrice(new int[]{11, 22, 33, 44});
        ArrayList<BuildingUpgrade> al = new ArrayList<BuildingUpgrade>();
        al.add(new BuildingUpgrade("name", 1, 2, 3));
        b.setUpgrades(al);
        assertEquals("Building name:test_name| Price:11-22-33-44| Total Upgrades: 1", b.toString());
    }

    /**
     * price setter tests
     */

    @Test
    public void priceDataForSettersCanBeOfSize4() {
        Building b = new Building(1, "test_name");
        b.setPrice(new int[]{1, 2, 3, 4});
    }
    @Test
    public void priceDataForSettersCantBeSize3() {
        Building b = new Building(1, "test_name");
        try {
            b.setPrice(new int[]{1, 2, 3});
            fail("Exception not thrown.");
        }
        catch (Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void priceDataForSettersCantBeSize2() {
        Building b = new Building(1, "test_name");
        b.setPrice(new int[]{1, 4});
        try {
            b.setPrice(new int[]{1, 2, 3});
            fail("Exception not thrown.");
        }
        catch (Exception e){
            assertTrue(true);
        }
    }

    /**
     * setProduction tests
     */

    @Test
    public void productionSetterSetsProductionFalseAsDefault() {
        Building b = new Building(1, "name");
        b.setProduction(true);
        assertFalse(b.isProductive());
        b.setProduction(false);
        assertFalse(b.isProductive());
    }

    @Test
    public void productionSetterSetsProductionTrueIfGivenProductData() {
        Building b = new Building(1, "name");
        b.setProduction(new int[]{1, 1, 1, 1});
        assertTrue(b.isProductive());
    }

    @Test
    public void productivityDataCantBeOfLengthOtherThan4() {
        Building b = new Building(1, "name");
        try {
            b.setProduction(new int[]{1, 1, 1});
            b.setProduction(new int[]{1});
            b.setProduction(new int[]{1, 3});
            fail("Exception not thrown.");
        }
        catch (Exception e){
            assertTrue(true);
        }
    }
}
