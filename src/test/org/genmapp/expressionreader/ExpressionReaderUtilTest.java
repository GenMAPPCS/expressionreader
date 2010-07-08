/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader;

import org.genmapp.expressionreader.data.GSE;
import java.util.List;
import org.genmapp.expressionreader.data.GDS;
import org.genmapp.expressionreader.data.SOFT;
import java.io.File;
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
public class ExpressionReaderUtilTest {

    public ExpressionReaderUtilTest() {
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

    @Test
    public void testDownloadSOFTGZ() throws Exception {
        String url = String.format(ExpressionReaderUtil.GDS_FTP, "GDS507");
        boolean result = ExpressionReaderUtil.downloadSOFTGZ(url, new File(System.getProperty("java.io.tmpdir"), "GDS507.soft"));
        assertEquals(true, result);
        assertEquals(true, new File(System.getProperty("java.io.tmpdir"), "GDS507.soft").exists());
    }

    @Test
    public void testGetSOFT() throws Exception {
        GDS soft = (GDS)ExpressionReaderUtil.getSOFT("GDS507", Type.GDS, null);
        assertNotNull(soft);
        assertEquals(12, soft.getSubsets().size());

        SOFT gsm = (SOFT)ExpressionReaderUtil.getSOFT("GSM11805", Type.GSM, SOFT.Format.quick);
        assertEquals(22283, gsm.getDataTables().getFirst().getData().size());
    }

    @Test
    public void testGetSOFTFamily() throws Exception {
        GSE soft = (GSE) ExpressionReaderUtil.getSOFT("GSE8854", Type.GSE, SOFT.Format.family);
        assertNotNull(soft);
        assertEquals(9, soft.getPlatforms().size());
    }

    @Test
    public void testGetSOFTFamily2() throws Exception {
        GSE soft = (GSE) ExpressionReaderUtil.getSOFT("GSE9914", Type.GSE, SOFT.Format.family);
        assertNotNull(soft);
        assertEquals(6, soft.getDataTables().size());
    }

    /**
     * Test of downloadURL method, of class ExpressionReaderUtil.
     */
    @Test
    public void testDownloadURL() {
        System.out.println("downloadURL");
        String urlStr = "";
        File file = null;
        boolean expResult = false;
        boolean result = ExpressionReaderUtil.downloadURL(urlStr, file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of openURL method, of class ExpressionReaderUtil.
     */
    @Test
    public void testOpenURL() {
        System.out.println("openURL");
        String url = "";
        ExpressionReaderUtil.openURL(url);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of join method, of class ExpressionReaderUtil.
     */
    @Test
    public void testJoin() {
        System.out.println("join");
        List<? extends CharSequence> s = null;
        String delimiter = "";
        String expResult = "";
        String result = ExpressionReaderUtil.join(s, delimiter);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSoftNameLblText method, of class ExpressionReaderUtil.
     */
    @Test
    public void testGetSoftNameLblText() {
        System.out.println("getSoftNameLblText");
        SOFT soft = null;
        String expResult = "";
        String result = ExpressionReaderUtil.getSoftNameLblText(soft);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getType method, of class ExpressionReaderUtil.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        String geoId = "";
        Type expResult = null;
        Type result = ExpressionReaderUtil.getType(geoId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}