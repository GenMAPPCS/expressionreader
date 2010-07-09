/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader.tasks;

import cytoscape.task.ui.JTaskConfig;
import cytoscape.task.util.TaskManager;
import gov.nih.nlm.ncbi.soap.eutils.esummary.ItemType;
import gov.nih.nlm.ncbi.soap.eutils.esearch.ESearchRequest;
import gov.nih.nlm.ncbi.soap.eutils.esummary.DocSumType;
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
public class GEOSearchTaskTest {

    public GEOSearchTaskTest() {
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
     * Test of run method, of class GEOSearchTask.
     */
    @Test
    public void testRun() {
        System.out.println("run");

        ESearchRequest request = new ESearchRequest();
        request.setDb("gds");
        request.setRetMax("5");
        request.setRetStart("220");
        request.setTerm("test");

        GEOSearchTask instance = new GEOSearchTask(request, new SearchResultViewer() {
            public void viewResults(int total, List<DocSumType> list) {
                System.out.println("Searching for 'test'");
                System.out.println("Number of records found: " + total);
                for (DocSumType docsum : list) {
                    List<ItemType> items = docsum.getItem();
                    System.out.println("-------------------------------------");
                    for (ItemType item : items) {
                        System.out.println("    " + item.getName() + ": " + item.getItemContent());
                    }
                }
            }
        });
        JTaskConfig config = new JTaskConfig();
        TaskManager.executeTask(instance, config);
    }

    /**
     * Test of getTitle method, of class GEOSearchTask.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        GEOSearchTask instance = null;
        String expResult = "";
        String result = instance.getTitle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}