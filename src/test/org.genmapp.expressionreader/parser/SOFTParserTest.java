package org.genmapp.expressionreader.parser;

import static org.junit.Assert.assertEquals;
import org.genmapp.expressionreader.data.SOFT;
import org.genmapp.expressionreader.ExpressionReaderUtil;

import java.io.InputStream;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author djiao
 *
 */
public class SOFTParserTest {

    static String root;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        String path = SOFTParser.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        root = path.substring(0, path.indexOf("build/classes/"));
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link geReader.SOFTParser#parseSOFT(java.io.InputStream, geReader.data.SOFT.Type)}.
     */
    @Test
    public void testParseGSM() throws Exception {
        URL url = new URL(String.format(ExpressionReaderUtil.GEO_URL, "GSM11805", "text", SOFT.Format.quick));

        InputStream gplIn = url.openConnection().getInputStream();
        SOFT gsm = new SOFTParser().parseSOFT(gplIn, SOFT.Type.GSM, SOFT.Format.quick);
        if (gplIn != null) {
            gplIn.close();
        }

        assertEquals(20, gsm.getDataTables().getFirst().getData().size());
        assertEquals("617-414-1646", gsm.getFields().get("Sample_contact_fax"));

        assertEquals(3, gsm.getDataTables().getFirst().getHeaders().keySet().size());
/*
        url = new URL(String.format(ExpressionReaderUtil.GEO_URL, "GSM11805", "text", SOFT.Format.full));

        gplIn = url.openConnection().getInputStream();
        gsm = new SOFTParser().parseSOFT(gplIn, SOFT.Type.GSM, SOFT.Format.full);
        if (gplIn != null) {
            gplIn.close();
        }
        assertEquals(22283, gsm.getDataTables().getFirst().getData().size());

 */
    }

    /**
     * Test method for {@link geReader.SOFTParser#parseSOFT(java.io.InputStream, geReader.data.SOFT.Type)}.
     */
    @Test
    public void testParseGPL() throws Exception {
        URL url = new URL(String.format(ExpressionReaderUtil.GEO_URL, "GPL96", "text", SOFT.Format.quick));

        InputStream gplIn = url.openConnection().getInputStream();
        SOFT gpl = new SOFTParser().parseSOFT(gplIn, SOFT.Type.GPL);
        if (gplIn != null) {
            gplIn.close();
        }

        // Show the list of fields to let people to map to column
        assertEquals(20, gpl.getDataTables().getFirst().getData().size());
    }

    /**
     * Test method for {@link geReader.SOFTParser#parseSOFT(java.io.InputStream, geReader.data.SOFT.Type)}.
     */
    @Test
    public void testParseGSE() throws Exception {
        URL url = new URL(String.format(ExpressionReaderUtil.GEO_URL, "GSE9914", "text", SOFT.Format.quick));

        InputStream in = url.openConnection().getInputStream();
        SOFT soft = new SOFTParser().parseSOFT(in, SOFT.Type.GSE);
        if (in != null) {
            in.close();
        }

        // Show the list of fields to let people to map to column
        assertEquals(6, soft.getDataTables().size());
    }

    /**
     * Test method for {@link geReader.SOFTParser#parseSOFT(java.io.InputStream, geReader.data.SOFT.Type)}.
     */
    @Test
    public void testParseGSEFamily() throws Exception {
        URL url = new URL(String.format(ExpressionReaderUtil.GSE_FTP, "GSE9914"));

        InputStream in = url.openConnection().getInputStream();
        SOFT soft = new SOFTParser().parseSOFT(new GZIPInputStream(in), SOFT.Type.GSE, SOFT.Format.family);
        if (in != null) {
            in.close();
        }

        // Show the list of fields to let people to map to column
        assertEquals(6, soft.getDataTables().size());
    }

    @Test
    public void testParseGDS() throws Exception {
        URL url = new URL(String.format(ExpressionReaderUtil.GDS_FTP, "GDS507"));

        InputStream in = url.openConnection().getInputStream();
        SOFT soft = new SOFTParser().parseSOFT(new GZIPInputStream(in), SOFT.Type.GDS);
        if (in != null) {
            in.close();
        }

        // Show the list of fields to let people to map to column
        assertEquals(1, soft.getDataTables().size());
    }
}
