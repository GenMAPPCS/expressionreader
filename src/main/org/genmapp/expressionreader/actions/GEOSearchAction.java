/*
 Copyright 2010 Alexander Pico
 Licensed under the Apache License, Version 2.0 (the "License"); 
 you may not use this file except in compliance with the License. 
 You may obtain a copy of the License at 
 	
 	http://www.apache.org/licenses/LICENSE-2.0 
 	
 Unless required by applicable law or agreed to in writing, software 
 distributed under the License is distributed on an "AS IS" BASIS, 
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 See the License for the specific language governing permissions and 
 limitations under the License. 
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.genmapp.expressionreader.actions;

import cytoscape.Cytoscape;
import cytoscape.util.CytoscapeAction;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JDialog;
import org.genmapp.expressionreader.geo.data.SOFT;
import org.genmapp.expressionreader.geo.ui.GDSViewerDialog;
import org.genmapp.expressionreader.geo.ui.GEOQueryUI;
import org.genmapp.expressionreader.geo.ui.GSEViewerDialog;
import org.genmapp.expressionreader.geo.ui.SOFTViewer;
import org.genmapp.expressionreader.ui.GEOSearchPane;

/**
 *
 * @author djiao
 */
public class GEOSearchAction extends CytoscapeAction {

    public GEOSearchAction() {
        super("Search");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JDialog dialog = new JDialog(Cytoscape.getDesktop(), false);
        dialog.setSize(800, 500);
        SOFTViewer viewer = new SOFTViewer() {

            public void viewSOFT(List<SOFT> list) {
                for (SOFT soft : list) {
                    if (soft.getType() == SOFT.Type.GSE) {
                        GSEViewerDialog dialog = new GSEViewerDialog(Cytoscape.getDesktop(), false);
                        dialog.setSOFT(soft);
                        dialog.setSize(600, 500);
                        dialog.setVisible(true);
                    } else if (soft.getType() == SOFT.Type.GDS) {
                        GDSViewerDialog dialog = new GDSViewerDialog(Cytoscape.getDesktop(), false);
                        dialog.setSOFT(soft);
                        dialog.setVisible(true);
                    } else if (soft.getType() == SOFT.Type.GPL) {
                        GEOQueryUI.showSOFTViewerDialog(Cytoscape.getDesktop(), false, soft);
                    } else {
                        throw new UnsupportedOperationException("Wrong SOFT type: " + soft.getType() + ", Should be GSE/GDS/GPL");
                    }
                }
            }

            public void closeView(SOFT soft) {
                // not implemented
            }
        };
        dialog.setContentPane(new GEOSearchPane(viewer));
        dialog.setVisible(true);
    }
}
