/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader.tasks;

import cytoscape.task.ui.JTaskConfig;
import org.genmapp.expressionreader.data.SOFT.Format;
import org.genmapp.expressionreader.ui.SOFTViewer;
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
public class SOFTDownloadTaskTest {

    public SOFTDownloadTaskTest() {
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
     * Test of getFormat method, of class SOFTDownloadTask.
     */
    @Test
    public void testGetFormat() {
        System.out.println("getFormat");
        SOFTDownloadTask instance = null;
        Format expResult = null;
        Format result = instance.getFormat();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFormat method, of class SOFTDownloadTask.
     */
    @Test
    public void testSetFormat() {
        System.out.println("setFormat");
        Format format = null;
        SOFTDownloadTask instance = null;
        instance.setFormat(format);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getViewer method, of class SOFTDownloadTask.
     */
    @Test
    public void testGetViewer() {
        System.out.println("getViewer");
        SOFTDownloadTask instance = null;
        SOFTViewer expResult = null;
        SOFTViewer result = instance.getViewer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setViewer method, of class SOFTDownloadTask.
     */
    @Test
    public void testSetViewer() {
        System.out.println("setViewer");
        SOFTViewer viewer = null;
        SOFTDownloadTask instance = null;
        instance.setViewer(viewer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGeoID method, of class SOFTDownloadTask.
     */
    @Test
    public void testGetGeoID() {
        System.out.println("getGeoID");
        SOFTDownloadTask instance = null;
        String expResult = "";
        String result = instance.getGeoID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGeoID method, of class SOFTDownloadTask.
     */
    @Test
    public void testSetGeoID() {
        System.out.println("setGeoID");
        String geoID = "";
        SOFTDownloadTask instance = null;
        instance.setGeoID(geoID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of run method, of class SOFTDownloadTask.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        SOFTDownloadTask instance = null;
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTitle method, of class SOFTDownloadTask.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        SOFTDownloadTask instance = null;
        String expResult = "";
        String result = instance.getTitle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefaultTaskConfig method, of class SOFTDownloadTask.
     */
    @Test
    public void testGetDefaultTaskConfig() {
        System.out.println("getDefaultTaskConfig");
        SOFTDownloadTask instance = null;
        JTaskConfig expResult = null;
        JTaskConfig result = instance.getDefaultTaskConfig();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}