/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import org.junit.Test;
import xpgame.NullNameBuildingUpgradeException;

import static org.junit.Assert.*;

/**
 *
 * @author ondrej
 */
public class BuildingTest {

    /**
     * Building inicialization tests
     */

    @Test
    public void testInitializationWorks() {
        Building b = new Building(1, "test_name");
        assertFalse(b.getUpgrade() != null);
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
        try {
            b.setPrice(new int[] {1, 2, 3, 4});
            b.toString();
        }
        catch (Exception e) {
            fail("Unexpected exception thrown.");
        }
    }

    @Test
    public void toStringReturnsCorrectOutput(){
        Building b = new Building(1, "test_name");
        try {
            b.setPrice(new int[]{1, 2, 3, 4});
            b.setUpgrade(new BuildingUpgrade("MYNAME", 1, 2));
        } catch (NullNameBuildingUpgradeException nnbue) {
            fail("Unexpected exception thrown.");
        } catch (Exception nnbue) {
            fail("Unexpected exception thrown.");
        }
        assertEquals("Building name:test_name| Price:1-2-3-4| Upgrade: MYNAME", b.toString());

    }

    @Test
    public void toStringReturnsCorrectOutput2(){
        Building b = new Building(1, "test_name");

        ArrayList<BuildingUpgrade> al = new ArrayList<BuildingUpgrade>();
        try {
            b.setPrice(new int[]{11, 22, 33, 44});
            b.setUpgrade(new BuildingUpgrade("name", 1, 2));
        } catch (NullNameBuildingUpgradeException nnbue) {
            fail("Unexpected exception thrown.");
        } catch (Exception e) {
            fail("Unexpected exception thrown.");
        }
        assertEquals("Building name:test_name| Price:11-22-33-44| Upgrade: name", b.toString());
    }

    /**
     * price setter tests
     */

    @Test
    public void priceDataForSettersCanBeOfSize4() {
        Building b = new Building(1, "test_name");
        try {
            b.setPrice(new int[]{1, 2, 3, 4});
        }catch (Exception e ) {
            fail("Unexpected exception thrown.");
        }

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

        try {
            b.setPrice(new int[]{1, 4});
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
        b.setProductivity(true);
        assertTrue(b.isProductive());
        b.setProductivity(false);
        assertFalse(b.isProductive());
    }

    @Test
    public void productionSetterSetsProductionTrueIfGivenProductData() {
        Building b = new Building(1, "name");
        b.setProduction(new int[]{1, 1, 1, 1});
        assertTrue(b.isProductive());
    }

}
