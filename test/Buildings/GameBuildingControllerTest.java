/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buildings;

import entity.Building;
import entity.BuildingUpgrade;
import org.junit.Before;
import org.junit.Test;
import xpgame.NullNameBuildingUpgradeException;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * @author ondrej
 */
public class GameBuildingControllerTest {

    private GameBuildingController empty_gbc;

    @Before
    public void setUp() {
        empty_gbc = new GameBuildingController(new HashMap<>());
    }

    /**
     * Initialization tests
     */

    public void assert100OfAll() {
        assertXofAll(100);
    }

    public void assertXofAll(double x) {
        assertEquals(x, empty_gbc.getFood(), 0.01);
        assertEquals(x, empty_gbc.getGold(), 0.01);
        assertEquals(x, empty_gbc.getStone(), 0.01);
        assertEquals(x, empty_gbc.getWood(), 0.01);
    }

    @Test
    public void testInitializationSetsDefaultResources() {
        assert100OfAll();
        assertEquals(10, empty_gbc.getPeople());
    }

    @Test
    public void testToStringStatusWorksAfterInitialization() {
        String expected = "Food: 100.0, Gold: 100.0, Stone: 100.0, Wood: 100.0";
        assertEquals(expected, empty_gbc.toString());
    }

    @Test
    public void testWorkingPeopleIs0AfterInitialization() {
        assertEquals(0, empty_gbc.getWorkingPeople());
    }

    @Test
    public void testUpgradeResourcesDoesNothingWithNoBuildings() {
        String expected = "Food: 100.0, Gold: 100.0, Stone: 100.0, Wood: 100.0";
        assertEquals(expected, empty_gbc.toString());
        empty_gbc.updateResources();
        assertEquals(expected, empty_gbc.toString());
    }

    /**
     * Resources tests
     */

    @Test
    public void testIsEnoughResourcesReturnsTrueForValid() {
        assert100OfAll();
        int[] cost = new int[]{10, 20, 30, 10};
        assertTrue(empty_gbc.isEnoughResources(cost));
    }

    @Test
    public void testIsEnoughResourcesReturnsTrueForEqual() {
        assert100OfAll();
        int[] cost = new int[]{100, 100, 100, 100};
        assertTrue(empty_gbc.isEnoughResources(cost));
    }

    @Test
    public void testIsEnoughResourcesReturnsFalseForTooBigCost() {
        assert100OfAll();
        int[] cost = new int[]{1000, 2000, 300, 400};
        assertFalse(empty_gbc.isEnoughResources(cost));
    }

    @Test
    public void testIsEnoughResourcesNeedsArrayOfLength4() {
        assert100OfAll();
        int[] cost = new int[]{10, 20, 40};
        assertFalse(empty_gbc.isEnoughResources(cost));
    }

    @Test
    public void testUseResourcesWorksCorrectly() {
        assert100OfAll();
        int[] cost = new int[]{10, 10, 10, 10};
        empty_gbc.useResources(cost);
        assertXofAll(90);
        cost = new int[]{50, 50, 50, 50};
        empty_gbc.useResources(cost);
        assertXofAll(40);
    }
    /**
     * Resources tests
     */

    @Test
    public void testCreateBuildingUsesResources() {
        try {
            BuildingUpgrade bu = new BuildingUpgrade("test_upgrade", 1, 1.2);
            Building b = new Building(1, "test_name");
            b.setUpgrade(bu);
            b.setPrice(new int[]{1, 1, 1, 1});
            HashMap<Integer, Building> building_data = new HashMap<>();
            building_data.put(0, b);

            GameBuildingController filled_gbc = new GameBuildingController(building_data);

            filled_gbc.CreateBuilding(0);
            assertEquals(99, filled_gbc.getFood(), 0.01);
            assertEquals(99, filled_gbc.getGold(), 0.01);
            assertEquals(99, filled_gbc.getStone(), 0.01);
            assertEquals(99, filled_gbc.getWood(), 0.01);
        } catch (NullNameBuildingUpgradeException nnbue) {
            fail("Unexpected exception thrown.");
        }

    }

    @Test
    public void testAddWorkerWorksAndDoesNotGoToNegativeNumbers() {
        try {
            BuildingUpgrade bu = new BuildingUpgrade("test_upgrade", 1, 1.2);
            Building b = new Building(1, "test_name");
            b.setUpgrade(bu);
            b.setPrice(new int[]{1, 1, 1, 1});
            HashMap<Integer, Building> building_data = new HashMap<>();
            building_data.put(0, b);

            GameBuildingController filled_gbc = new GameBuildingController(building_data);

            filled_gbc.CreateBuilding(0);

            assertEquals(10, filled_gbc.getPeople());
            assertEquals(0, filled_gbc.getWorkingPeople());
            filled_gbc.addWorker(0);
            assertEquals(1, filled_gbc.getWorkingPeople());

            for(int i = 0; i < 11; i++){ // more than we have left
                filled_gbc.addWorker(0);
            }
            assertEquals(10, filled_gbc.getWorkingPeople());
        } catch (NullNameBuildingUpgradeException nnbue) {
            fail("Unexpected exception thrown.");
        }

    }
}
