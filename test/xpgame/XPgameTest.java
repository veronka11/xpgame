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
        try {
            String[] args = null;
            XPgame.main(args);
        }
        catch (GameInicializaitonFailedException e){
            fail("Game couldn't be initialized with the Exception: \n" + e.getMessage());
        }
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
