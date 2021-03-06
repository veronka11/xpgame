/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import org.junit.Test;
import xpgame.NullNameBuildingUpgradeException;

import static org.junit.Assert.*;

/**
 * @author ondrej
 */
public class BuildingUpgradeTest {
    /**
     * basic initialization tests
     */

    @Test
    public void testInitializationWithCorrectValuesIsSuccessful() {
        try {
            new BuildingUpgrade("test_name", 1.0, 1.2);
        } catch (NullNameBuildingUpgradeException nnbue) {
            fail("Unexpected exception thrown.");
        }
    }

    @Test
    public void testInitializationThrowsErrorIfNameIsNull() {
        try {
            new BuildingUpgrade(null, 1.0, 1.2);
            fail("Exception not thrown.");
        }
        catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void testInitializationThrowsErrorIfNameIsEmptyString() {
        try {
            new BuildingUpgrade("", 1.0, 1.2);
            fail("Exception not thrown.");
        }
        catch (Exception e) {
            assertTrue(true);
        }
    }

    /**
     * basic getters tests
     */

    @Test
    public void testGettersWork() {
        try {
            BuildingUpgrade bu = new BuildingUpgrade("test_name", 1.0, 1.2);
            assertEquals("test_name", bu.getName());
            assertEquals(1.0, bu.getRate(), 0.01);
            assertEquals(1.2, bu.getLevelIncrease(), 0.01);
        } catch (NullNameBuildingUpgradeException nnbue) {
            fail("Unexpected exception thrown.");
        }

    }
}
