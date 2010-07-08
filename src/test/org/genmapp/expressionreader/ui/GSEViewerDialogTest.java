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
public class GSEViewerDialogTest {

    public GSEViewerDialogTest() {
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
     * Test of main method, of class GSEViewerDialog.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        GSEViewerDialog.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of viewSOFT method, of class GSEViewerDialog.
     */
    @Test
    public void testViewSOFT() {
        System.out.println("viewSOFT");
        SOFT soft = null;
        GSEViewerDialog instance = null;
        instance.viewSOFT(soft);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of closeView method, of class GSEViewerDialog.
     */
    @Test
    public void testCloseView() {
        System.out.println("closeView");
        SOFT soft = null;
        GSEViewerDialog instance = null;
        instance.closeView(soft);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}