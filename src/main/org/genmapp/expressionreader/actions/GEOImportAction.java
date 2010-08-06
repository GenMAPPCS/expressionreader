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

package org.genmapp.expressionreader.actions;

import org.genmapp.expressionreader.geo.data.SOFT;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import cytoscape.Cytoscape;
import cytoscape.logger.CyLogger;
import cytoscape.task.ui.JTaskConfig;
import cytoscape.task.util.TaskManager;
import cytoscape.util.CytoscapeAction;
import java.util.List;
import org.genmapp.expressionreader.geo.GEOQuery;
import org.genmapp.expressionreader.tasks.SOFTDownloadTask;
import org.genmapp.expressionreader.geo.ui.GDSViewerDialog;
import org.genmapp.expressionreader.geo.ui.GEOQueryUI;
import org.genmapp.expressionreader.geo.ui.GSEViewerDialog;
import org.genmapp.expressionreader.ui.GSMImportDialog;
import org.genmapp.expressionreader.geo.ui.SOFTViewer;

public class GEOImportAction extends CytoscapeAction implements SOFTViewer {

    private static final long serialVersionUID = 1128930960050800232L;

    static public CyLogger logger = CyLogger.getLogger(GEOImportAction.class);

    public GEOImportAction() {
        super("Import");
    }

    @Override
    public void actionPerformed(ActionEvent paramActionEvent) {
        String response = (String) JOptionPane.showInputDialog(Cytoscape.getDesktop(),
                "Please enter a GEO ID", "Query GEO",
                JOptionPane.PLAIN_MESSAGE, null, null, "GSM207569");

        if (response != null && !"".equals(response.trim())) {
            // Download file
            String id = response.trim();
            SOFT.Type type = GEOQuery.getType(id);

            SOFTDownloadTask task = (type == SOFT.Type.GSM) ?
                new SOFTDownloadTask(new String[]{id}, this, SOFT.Format.full) :
                new SOFTDownloadTask(new String[]{id}, this, SOFT.Format.quick);
            JTaskConfig config = task.getDefaultTaskConfig();
            TaskManager.executeTask(task, config);
        }
    }

    public void viewSOFT(List<SOFT> list) { // should expect only one back
        logger.debug("View SOFT: " + list.size());

        SOFT soft = list.get(0);
        if (soft.getType() == SOFT.Type.GSM) { // If GSM, import it
            //GSMImportTask task = new GSMImportTask(preferredMenu, list, list, keyCode, keyCode, preferredMenu, consoleName, DEFAULT);
            
            GSMImportDialog dialog = new GSMImportDialog(Cytoscape.getDesktop(), false);
            dialog.setSOFTList(list);
            dialog.setVisible(true);
        } else if (soft.getType() == SOFT.Type.GSE) {
            GSEViewerDialog dialog = new GSEViewerDialog(Cytoscape.getDesktop(), false);
            dialog.setSize(600, 500);
            dialog.setSOFT(soft);
            dialog.setVisible(true);
        } else if (soft.getType() == SOFT.Type.GDS) {
            GDSViewerDialog dialog = new GDSViewerDialog(Cytoscape.getDesktop(), false);
            dialog.setSOFT(soft);
            dialog.setVisible(true);
        } else if (soft.getType() == SOFT.Type.GPL) {
            GEOQueryUI.showSOFTViewerDialog(Cytoscape.getDesktop(), false, soft);
        }
    }

    public void closeView(SOFT soft) {
        // not implemented
    }
}
