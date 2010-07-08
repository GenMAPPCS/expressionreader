/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader.tasks;

import cytoscape.task.ui.JTaskConfig;
import org.genmapp.expressionreader.data.SOFT;
import org.genmapp.expressionreader.data.SOFT.Format;
import org.genmapp.expressionreader.ExpressionReaderUtil;
import org.genmapp.expressionreader.data.SOFT.Type;
import org.genmapp.expressionreader.ui.SOFTViewer;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.SwingUtilities;

/**
 *
 * @author djiao
 */
public class SOFTDownloadTask extends AbstractTask {

    private String geoID;
    private SOFTViewer viewer;
    private SOFT.Format format;

    public Format getType() {
        return format;
    }

    public void setType(Format type) {
        this.format = type;
    }


    public SOFTDownloadTask(String geoID, SOFTViewer viewer) {
        this.geoID = geoID;
        this.viewer = viewer;
    }

    public void run() {
        if (taskMonitor != null) {
            taskMonitor.setStatus("Retrieving data from GEO: " + geoID);
        }
        try {
            SOFT.Type type = ExpressionReaderUtil.getType(geoID);
            SOFT soft = null;
            if (type == SOFT.Type.GSE) {
                soft = ExpressionReaderUtil.getSOFT(geoID, type, Format.family);
            } else if (type == SOFT.Type.GDS) {
                soft = ExpressionReaderUtil.getSOFT(geoID, type, Format.full);
            } else {
                if (format == null) {
                    format = Format.full;
                }
                soft = ExpressionReaderUtil.getSOFT(geoID, type, format);
            }
            final SOFT fsoft = soft;
            // Configures a mapping after the download
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    viewer.viewSOFT(fsoft);
                }
            });
        } catch (IOException ex) {
        } catch (ParseException ex) {
        }
    }

    public String getTitle() {
        return "Download " + geoID;
    }

    public JTaskConfig getDefaultTaskConfig() {
        JTaskConfig config = new JTaskConfig();

        config.displayCancelButton(true);
        config.displayCloseButton(true);
        config.displayStatus(true);
        config.displayTimeElapsed(false);
        config.setAutoDispose(true);
        config.setModal(false);
        //config.setOwner(Cytoscape.getDesktop());

        return config;
    }
}
