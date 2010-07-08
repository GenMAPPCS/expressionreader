/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader.ui;

import org.genmapp.expressionreader.data.SOFT;
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
public class SOFTViewerPaneTest {

    public SOFTViewerPaneTest() {
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
     * Test of getOwner method, of class SOFTViewerPane.
     */
    @Test
    public void testGetOwner() {
        System.out.println("getOwner");
        SOFTViewerPane instance = new SOFTViewerPane();
        SOFTViewer expResult = null;
        SOFTViewer result = instance.getOwner();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOwner method, of class SOFTViewerPane.
     */
    @Test
    public void testSetOwner() {
        System.out.println("setOwner");
        SOFTViewer owner = null;
        SOFTViewerPane instance = new SOFTViewerPane();
        instance.setOwner(owner);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSoft method, of class SOFTViewerPane.
     */
    @Test
    public void testGetSoft() {
        System.out.println("getSoft");
        SOFTViewerPane instance = new SOFTViewerPane();
        SOFT expResult = null;
        SOFT result = instance.getSoft();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSoft method, of class SOFTViewerPane.
     */
    @Test
    public void testSetSoft() {
        System.out.println("setSoft");
        SOFT soft = null;
        SOFTViewerPane instance = new SOFTViewerPane();
        instance.setSoft(soft);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}