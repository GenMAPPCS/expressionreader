/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader.data;

import java.util.List;
import org.genmapp.expressionreader.data.SOFT.Type;
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
public class GSETest {

    public GSETest() {
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
     * Test of getDatabase method, of class GSE.
     */
    @Test
    public void testGetDatabase() {
        System.out.println("getDatabase");
        GSE instance = null;
        SOFT expResult = null;
        SOFT result = instance.getDatabase();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDatabase method, of class GSE.
     */
    @Test
    public void testSetDatabase() {
        System.out.println("setDatabase");
        SOFT database = null;
        GSE instance = null;
        instance.setDatabase(database);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlatforms method, of class GSE.
     */
    @Test
    public void testGetPlatforms() {
        System.out.println("getPlatforms");
        GSE instance = null;
        List expResult = null;
        List result = instance.getPlatforms();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlatforms method, of class GSE.
     */
    @Test
    public void testSetPlatforms() {
        System.out.println("setPlatforms");
        List<SOFT> platforms = null;
        GSE instance = null;
        instance.setPlatforms(platforms);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSamples method, of class GSE.
     */
    @Test
    public void testGetSamples() {
        System.out.println("getSamples");
        GSE instance = null;
        List expResult = null;
        List result = instance.getSamples();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSamples method, of class GSE.
     */
    @Test
    public void testSetSamples() {
        System.out.println("setSamples");
        List<SOFT> samples = null;
        GSE instance = null;
        instance.setSamples(samples);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getType method, of class GSE.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        GSE instance = null;
        Type expResult = null;
        Type result = instance.getType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}