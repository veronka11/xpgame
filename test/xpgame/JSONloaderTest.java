/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xpgame;

import dataloader.JSONloader;
import entity.Building;
import org.junit.*;


import java.io.*;
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
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testJSONParserActualyParsesSomething(){
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
        catch (Exception e){
            Assert.fail("Error while writing to test file, with error: " + e.toString());
        }
        HashMap<Integer, Building> testStructure = JSONloader.JSONloadBuildings("testStructure.json");
        Assert.assertEquals(1, testStructure.size());
    }

    
}
