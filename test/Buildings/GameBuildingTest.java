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
import static org.junit.Assert.*;

/**
 * @author ondrej
 */
public class GameBuildingTest {

    Building b, correct_b;
    BuildingUpgrade bu;
    @Before
    public void setUp() {
        b = new Building(1, "test_name");

        bu = new BuildingUpgrade("test_upgrade", 1, 1.2, 1.2);


        correct_b = new Building(2, "test_name2");
        correct_b.addUpgrade(bu);
        correct_b.setPrice(new int[] {1, 2, 3, 4});
    }


    /**
     * Initialization tests
     */
    @Test
    public void testInitializationThrowsExceptionForEmptyBuildingData() {
        try {
            new GameBuilding(b, 1);
            fail("Exception not thrown.");
        }
        catch (Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testInitializationWorksForPopulatedBuildingData() {
        try {
            b.addUpgrade(bu);
            new GameBuilding(b, 1);
        }
        catch (Exception e){
            fail("Unexpected exception thrown.");
        }
    }

    @Test
    public void testInitializationSetsLevelPeopleToZero() {
        GameBuilding gb = new GameBuilding(correct_b, 1);

        assertEquals(0, gb.getWorkers());
        assertEquals(0, gb.level);
    }

    /**
     * upLevel tests
     */

    @Test
    public void testUpLevelUpsLevel() {
        GameBuilding gb = new GameBuilding(correct_b, 1);
        assertEquals(0, gb.level);
        gb.upLevel();
        assertEquals(1, gb.level);
    }

    @Test
    public void testUpLevelUpsRate() {
        bu = new BuildingUpgrade("test_upgrade", 1, 2, 1.2);
        b = new Building(1, "test_name");
        b.addUpgrade(bu);
        GameBuilding gb = new GameBuilding(b, 1);

        assertEquals(2, gb.upgradeRate, 0.01);
        gb.upLevel();
        assertEquals(3.2, gb.upgradeRate, 0.01);
    }

    @Test
    public void testUpLevelUpsRate2() {
        bu = new BuildingUpgrade("test_upgrade2", 1, 23, 2);
        b = new Building(1, "test_name2");
        b.addUpgrade(bu);
        GameBuilding gb = new GameBuilding(b, 2);

        assertEquals(23, gb.upgradeRate, 0.01);
        gb.upLevel();
        assertEquals(25, gb.upgradeRate, 0.01);
    }

    /**
     * takeWorker tests
     */

    @Test
    public void testTakeWorkersThrowsExceptionIfThereAre0Available() {
        try {
            GameBuilding bg = new GameBuilding(correct_b, 1);

            assertEquals(0, bg.getWorkers());
            bg.takeWorker();
            fail("Exception not thrown.");
        }
        catch (Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testTakeWorkersTakesWorkerAvailable() throws Exception {
        GameBuilding bg = new GameBuilding(correct_b, 1);
        assertEquals(0, bg.getWorkers());
        bg.addWorker();
        assertEquals(1, bg.getWorkers());
        bg.takeWorker();
        assertEquals(0, bg.getWorkers());
    }

    /**
     * getPriceForNextLvl tests
     */
    @Test
    public void getPriceForNextLevelThrowsExceptionIfPriceIsNotSet() throws Exception {
        bu = new BuildingUpgrade("test_upgrade", 1, 2, 1.2);
        b.addUpgrade(bu);
        GameBuilding bg = new GameBuilding(b, 1);

        try {
            bg.getPriceForNextLvl();
            fail("Exception not thrown.");
        }
        catch (Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void getPriceForNextLevelWorksIfAllSet() throws Exception {
        GameBuilding bg = new GameBuilding(correct_b, 1);

        assertArrayEquals(new int[] {1, 2, 3, 4}, bg.getPriceForNextLvl());
        bg.upLevel();
        assertArrayEquals(new int[] {2, 4, 6, 8}, bg.getPriceForNextLvl());
    }

    @Test
    public void getPriceForNextLevelWorksIfAllSet2() throws Exception {
        GameBuilding bg = new GameBuilding(correct_b, 1);

        assertArrayEquals(new int[] {1, 2, 3, 4}, bg.getPriceForNextLvl());
        bg.upLevel(); bg.upLevel(); bg.upLevel();
        assertArrayEquals(new int[] {4, 8, 12, 16}, bg.getPriceForNextLvl());
    }

    /**
     * collect tests
     */
    @Test
    public void collectThrowsExceptionIsProductionDataIsNotSet() {
        GameBuilding gb = new GameBuilding(correct_b, 1);
        try {
            gb.collect();
            fail("Exception not thrown.");
        }
        catch (Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void collectWorksIsProductionDataIsSet() {
        correct_b.setProduction(new int[] {1, 1, 1, 1});
        GameBuilding gb = new GameBuilding(correct_b, 1);
        try {
            gb.collect();
        }
        catch (Exception e){
            fail("Unexpected exception thrown.");
        }
    }
}
