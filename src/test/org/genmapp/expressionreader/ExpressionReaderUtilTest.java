/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader;

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

}