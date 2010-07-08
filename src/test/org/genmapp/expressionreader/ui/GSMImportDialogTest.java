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
public class GSMImportDialogTest {

    public GSMImportDialogTest() {
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
     * Test of main method, of class GSMImportDialog.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        GSMImportDialog.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of viewSOFT method, of class GSMImportDialog.
     */
    @Test
    public void testViewSOFT() {
        System.out.println("viewSOFT");
        SOFT gpl = null;
        GSMImportDialog instance = null;
        instance.viewSOFT(gpl);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of closeView method, of class GSMImportDialog.
     */
    @Test
    public void testCloseView() {
        System.out.println("closeView");
        SOFT soft = null;
        GSMImportDialog instance = null;
        instance.closeView(soft);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}