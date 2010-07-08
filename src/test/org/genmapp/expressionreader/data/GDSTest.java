/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader.data;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author djiao
 */
public class GDSTest {

    public GDSTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getDatabase method, of class GDS.
     */
    @Test
    public void testGetDatabase() {
        System.out.println("getDatabase");
        GDS instance = null;
        SOFT expResult = null;
        SOFT result = instance.getDatabase();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDatabase method, of class GDS.
     */
    @Test
    public void testSetDatabase() {
        System.out.println("setDatabase");
        SOFT database = null;
        GDS instance = null;
        instance.setDatabase(database);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSubsets method, of class GDS.
     */
    @Test
    public void testGetSubsets() {
        System.out.println("getSubsets");
        GDS instance = null;
        List expResult = null;
        List result = instance.getSubsets();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSubsets method, of class GDS.
     */
    @Test
    public void testSetSubsets() {
        System.out.println("setSubsets");
        List<SOFT> subsets = null;
        GDS instance = null;
        instance.setSubsets(subsets);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}