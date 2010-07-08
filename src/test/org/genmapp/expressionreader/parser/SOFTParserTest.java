/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader.parser;

import java.io.InputStream;
import java.io.Reader;
import org.genmapp.expressionreader.data.SOFT;
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
public class SOFTParserTest {

    public SOFTParserTest() {
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
     * Test of parseSOFT method, of class SOFTParser.
     */
    @Test
    public void testParseSOFT_InputStream_SOFTType() throws Exception {
        System.out.println("parseSOFT");
        InputStream in = null;
        Type type = null;
        SOFTParser instance = new SOFTParser();
        SOFT expResult = null;
        SOFT result = instance.parseSOFT(in, type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseSOFT method, of class SOFTParser.
     */
    @Test
    public void testParseSOFT_Reader_SOFTType() throws Exception {
        System.out.println("parseSOFT");
        Reader in = null;
        Type type = null;
        SOFTParser instance = new SOFTParser();
        SOFT expResult = null;
        SOFT result = instance.parseSOFT(in, type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseSOFT method, of class SOFTParser.
     */
    @Test
    public void testParseSOFT_3args_1() throws Exception {
        System.out.println("parseSOFT");
        InputStream in = null;
        Type type = null;
        Format format = null;
        SOFTParser instance = new SOFTParser();
        SOFT expResult = null;
        SOFT result = instance.parseSOFT(in, type, format);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseSOFT method, of class SOFTParser.
     */
    @Test
    public void testParseSOFT_3args_2() throws Exception {
        System.out.println("parseSOFT");
        Reader in = null;
        Type type = null;
        Format format = null;
        SOFTParser instance = new SOFTParser();
        SOFT expResult = null;
        SOFT result = instance.parseSOFT(in, type, format);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTypeHeader method, of class SOFTParser.
     */
    @Test
    public void testGetTypeHeader() {
        System.out.println("getTypeHeader");
        Type type = null;
        String expResult = "";
        String result = SOFTParser.getTypeHeader(type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}