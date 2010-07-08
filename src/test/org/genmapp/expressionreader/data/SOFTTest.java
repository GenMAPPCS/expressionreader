/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader.data;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import org.genmapp.expressionreader.data.SOFT.Format;
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
public class SOFTTest {

    public SOFTTest() {
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
     * Test of getFormat method, of class SOFT.
     */
    @Test
    public void testGetFormat() {
        System.out.println("getFormat");
        SOFT instance = new SOFT();
        Format expResult = null;
        Format result = instance.getFormat();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFormat method, of class SOFT.
     */
    @Test
    public void testSetFormat() {
        System.out.println("setFormat");
        Format format = null;
        SOFT instance = new SOFT();
        instance.setFormat(format);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDataTables method, of class SOFT.
     */
    @Test
    public void testGetDataTables() {
        System.out.println("getDataTables");
        SOFT instance = new SOFT();
        LinkedList expResult = null;
        LinkedList result = instance.getDataTables();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDataTables method, of class SOFT.
     */
    @Test
    public void testSetDataTables() {
        System.out.println("setDataTables");
        LinkedList<DataTable> dataTables = null;
        SOFT instance = new SOFT();
        instance.setDataTables(dataTables);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getType method, of class SOFT.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        SOFT instance = new SOFT();
        Type expResult = null;
        Type result = instance.getType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setType method, of class SOFT.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        Type type = null;
        SOFT instance = new SOFT();
        instance.setType(type);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFields method, of class SOFT.
     */
    @Test
    public void testGetFields() {
        System.out.println("getFields");
        SOFT instance = new SOFT();
        LinkedHashMap expResult = null;
        LinkedHashMap result = instance.getFields();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFields method, of class SOFT.
     */
    @Test
    public void testSetFields() {
        System.out.println("setFields");
        LinkedHashMap<String, Object> fields = null;
        SOFT instance = new SOFT();
        instance.setFields(fields);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class SOFT.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        SOFT instance = new SOFT();
        String expResult = "";
        String result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class SOFT.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        String id = "";
        SOFT instance = new SOFT();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTypeStr method, of class SOFT.
     */
    @Test
    public void testGetTypeStr() {
        System.out.println("getTypeStr");
        SOFT instance = new SOFT();
        String expResult = "";
        String result = instance.getTypeStr();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTypeStr method, of class SOFT.
     */
    @Test
    public void testSetTypeStr() {
        System.out.println("setTypeStr");
        String typeStr = "";
        SOFT instance = new SOFT();
        instance.setTypeStr(typeStr);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}