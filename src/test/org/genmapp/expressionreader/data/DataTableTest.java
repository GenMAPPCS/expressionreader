/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader.data;

import java.util.LinkedHashMap;
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
public class DataTableTest {

    public DataTableTest() {
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
     * Test of getTitle method, of class DataTable.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        DataTable instance = null;
        String expResult = "";
        String result = instance.getTitle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTitle method, of class DataTable.
     */
    @Test
    public void testSetTitle() {
        System.out.println("setTitle");
        String title = "";
        DataTable instance = null;
        instance.setTitle(title);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getData method, of class DataTable.
     */
    @Test
    public void testGetData() {
        System.out.println("getData");
        DataTable instance = null;
        LinkedHashMap expResult = null;
        LinkedHashMap result = instance.getData();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setData method, of class DataTable.
     */
    @Test
    public void testSetData() {
        System.out.println("setData");
        LinkedHashMap<String, List> data = null;
        DataTable instance = null;
        instance.setData(data);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHeaders method, of class DataTable.
     */
    @Test
    public void testGetHeaders() {
        System.out.println("getHeaders");
        DataTable instance = null;
        LinkedHashMap expResult = null;
        LinkedHashMap result = instance.getHeaders();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHeaders method, of class DataTable.
     */
    @Test
    public void testSetHeaders() {
        System.out.println("setHeaders");
        LinkedHashMap<String, String> headers = null;
        DataTable instance = null;
        instance.setHeaders(headers);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}