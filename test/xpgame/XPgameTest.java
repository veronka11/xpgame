/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xpgame;

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
public class XPgameTest {
    
    public XPgameTest() {
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
     * Test of main method, of class XPgame.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        XPgame.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of XPgame method, of class XPgame.
     */
    @Test
    public void testXPgame() {
        System.out.println("XPgame");
        XPgame instance = new XPgame();
        instance.XPgame();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createBuilding method, of class XPgame.
     */
    @Test
    public void testCreateBuilding() {
        System.out.println("createBuilding");
        int id = 0;
        XPgame instance = new XPgame();
        instance.createBuilding(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of run method, of class XPgame.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        XPgame instance = new XPgame();
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
