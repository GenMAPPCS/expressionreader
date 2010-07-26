package org.genmapp.expressionreader.actions;

import org.genmapp.expressionreader.geo.data.SOFT;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import cytoscape.Cytoscape;
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
        SOFT soft = list.get(0);
        if (soft.getType() == SOFT.Type.GSM) {
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
