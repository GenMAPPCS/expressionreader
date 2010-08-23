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

import cytoscape.task.TaskMonitor;
import cytoscape.task.ui.JTaskConfig;
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
public class AbstractTaskTest {

    public AbstractTaskTest() {
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
     * Test of halt method, of class AbstractTask.
     */
    @Test
    public void testHalt() {
        System.out.println("halt");
        AbstractTask instance = new AbstractTaskImpl();
        instance.halt();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTaskMonitor method, of class AbstractTask.
     */
    @Test
    public void testSetTaskMonitor() {
        System.out.println("setTaskMonitor");
        TaskMonitor tm = null;
        AbstractTask instance = new AbstractTaskImpl();
        instance.setTaskMonitor(tm);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class AbstractTaskImpl extends AbstractTask {

        public void run() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getTitle() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public JTaskConfig getDefaultTaskConfig() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

}
