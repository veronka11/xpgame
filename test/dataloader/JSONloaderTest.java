/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataloader;

import dataloader.JSONloader;
import entity.Building;
import org.junit.*;


import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;

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
        StringBuilder inputStringForTestFile = new StringBuilder();
        inputStringForTestFile.append(
                "{" +
                        "\"buildings\": " +
                        "[{" +
                        "\"name\":" + " \"Building Name\"," +
                        "\"price\":" +" [100, 100, 0, 0]," +
                        "\"productive\" : false," +
                        "\"production\" " + ": []," +
                        "\"upgrades\": [" +
                        "{" +
                        "\"upgrade_name\" : " + "\"Upgrade Name\"," +
                        "\"upgrade_type\" :    1," +
                        "\"upgrade_rate\" :    1.2," +
                        "\"upgrade_level_increase\" : 0.4" +
                        "}," +
                        "{" +
                        "\"upgrade_name\" :\"Upgrade Name2\"," +
                        "\"upgrade_type\" :    2," +
                        "\"upgrade_rate\" :    1.15," +
                        "\"upgrade_level_increase\" : 0.2" +
                        "}" +
                        "]" +
                        "}]" +
                        "}");
        try {
            PrintWriter file = new PrintWriter("testStructure.json");
            file.write(inputStringForTestFile.toString());
            file.close();

        }
        catch (FileNotFoundException e){
            Assert.fail("Error while writing to test file, with error: " + e.toString());
        }
    }
    
    @After
    public void tearDown() {
    }

    @Ignore
    @Test
    public void testJSONParserActuallyParsesSomething(){
        try {
            HashMap<Integer, Building> testStructure = JSONloader.JSONloadBuildings("testStructure.json");
            Assert.assertEquals(1, testStructure.size());
        }
        catch (FileNotFoundException e){
            Assert.fail("File not found exception thrown.");
        }

    }

    @Ignore
    @Test
    public void testJSONParserReturnsInstanceOfBuilding(){
        try {
            HashMap<Integer, Building> testStructure = JSONloader.JSONloadBuildings("testStructure.json");
            Assert.assertEquals(1, testStructure.size());
            Assert.assertEquals(testStructure.get(0).getClass(), Building.class);
        }
        catch (FileNotFoundException e){
            Assert.fail("File not found exception thrown.");
        }
    }

    @Ignore
    @Test
    public void testJSONParserWithMissingFileThrowsException(){
        try {
            HashMap<Integer, Building> testStructure = JSONloader.JSONloadBuildings("wrongFile.json");
            Assert.fail("Exception not thrown.");
        }
        catch (FileNotFoundException e){
            Assert.assertTrue(true);
        }
    }

    @Ignore
    @Test
    public void testJSONParserParsesCorrectData(){
        try {
            HashMap<Integer, Building> testStructure = JSONloader.JSONloadBuildings("testStructure.json");
            Assert.assertEquals(1, testStructure.size());
            Building testBuilding = testStructure.get(0);

            assertEquals("id", testBuilding.getId(), 0);
            assertEquals("name", testBuilding.getName(), "Building Name");
            assertArrayEquals("price", new int[] {100, 100, 0, 0}, testBuilding.getPrice());
            assertEquals("production", testBuilding.getProduction(), new int[0]);
            assertEquals("productive", testBuilding.isProductive(), false);

        }
        catch (FileNotFoundException e){
            Assert.fail("File not found exception thrown.");
        }
    }

}
