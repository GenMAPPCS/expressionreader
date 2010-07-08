/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader.data;

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
@Suite.SuiteClasses({org.genmapp.expressionreader.data.DataTableTest.class,org.genmapp.expressionreader.data.GSETest.class,org.genmapp.expressionreader.data.SOFTTest.class,org.genmapp.expressionreader.data.GDSTest.class})
public class DataSuite {

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