/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader.actions;

import java.awt.event.ActionEvent;
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
public class GEOImportActionTest {

    public GEOImportActionTest() {
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
     * Test of actionPerformed method, of class GEOImportAction.
     */
    @Test
    public void testActionPerformed() {
        System.out.println("actionPerformed");
        ActionEvent paramActionEvent = null;
        GEOImportAction instance = new GEOImportAction();
        instance.actionPerformed(paramActionEvent);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of viewSOFT method, of class GEOImportAction.
     */
    @Test
    public void testViewSOFT() {
        System.out.println("viewSOFT");
        SOFT soft = null;
        GEOImportAction instance = new GEOImportAction();
        instance.viewSOFT(soft);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of closeView method, of class GEOImportAction.
     */
    @Test
    public void testCloseView() {
        System.out.println("closeView");
        SOFT soft = null;
        GEOImportAction instance = new GEOImportAction();
        instance.closeView(soft);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}