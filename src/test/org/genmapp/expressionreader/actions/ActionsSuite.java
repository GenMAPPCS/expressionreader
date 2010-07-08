/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader.actions;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author djiao
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({org.genmapp.expressionreader.actions.GEOSearchActionTest.class,org.genmapp.expressionreader.actions.ArrayExpressionImportActionTest.class,org.genmapp.expressionreader.actions.SettingsActionTest.class,org.genmapp.expressionreader.actions.GEOImportActionTest.class})
public class ActionsSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

}