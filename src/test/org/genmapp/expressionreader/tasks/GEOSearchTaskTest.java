/*******************************************************************************
 * Copyright 2010 Alexander Pico
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
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
