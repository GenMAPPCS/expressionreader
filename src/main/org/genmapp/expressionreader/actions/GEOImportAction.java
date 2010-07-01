package org.genmapp.expressionreader.actions;

import org.genmapp.expressionreader.data.SOFT;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import cytoscape.Cytoscape;
import cytoscape.task.ui.JTaskConfig;
import cytoscape.task.util.TaskManager;
import cytoscape.util.CytoscapeAction;
import org.genmapp.expressionreader.data.SOFT.Format;
import org.genmapp.expressionreader.tasks.SOFTDownloadTask;
import org.genmapp.expressionreader.ui.GSEViewerDialog;
import org.genmapp.expressionreader.ui.GSMImportDialog;
import org.genmapp.expressionreader.ui.SOFTViewer;

public class GEOImportAction extends CytoscapeAction implements SOFTViewer {

    private static final long serialVersionUID = 1128930960050800232L;

    public GEOImportAction() {
        super("GEO");
    }

    @Override
    public void actionPerformed(ActionEvent paramActionEvent) {
        String response = (String) JOptionPane.showInputDialog(Cytoscape.getDesktop(),
                "Please enter a GEO ID", "Query GEO",
                JOptionPane.PLAIN_MESSAGE, null, null, "GSM207569");

        if (response != null && !"".equals(response.trim())) {
            // Download file
            SOFTDownloadTask task = new SOFTDownloadTask(response.trim(), this, Format.full);
            JTaskConfig config = task.getDefaultTaskConfig();

            TaskManager.executeTask(task, config);
        }
    }

    public void viewSOFT(SOFT soft) {
        System.out.println(soft);
        if (soft.getType() == SOFT.Type.GSM) {
            GSMImportDialog dialog = new GSMImportDialog(Cytoscape.getDesktop(), true, soft);
            dialog.setVisible(true);
        } else if (soft.getType() == SOFT.Type.GSE) {
            GSEViewerDialog dialog = new GSEViewerDialog(Cytoscape.getDesktop(), true, soft);
            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(Cytoscape.getDesktop(), "Only supports GSM and GSE");
        }
    }

    public void closeView(SOFT soft) {
        // not implemented
    }


}
