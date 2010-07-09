/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader.tasks;

import cytoscape.task.ui.JTaskConfig;
import org.genmapp.expressionreader.data.SOFT;
import org.genmapp.expressionreader.data.SOFT.Format;
import org.genmapp.expressionreader.ExpressionReaderUtil;
import org.genmapp.expressionreader.ui.SOFTViewer;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

/**
 *
 * @author djiao
 */
public class SOFTDownloadTask extends AbstractTask {

    private String[] geoIDs;
    private SOFTViewer viewer;
    private SOFT.Format format;

    public Format getType() {
        return format;
    }

    public void setType(Format type) {
        this.format = type;
    }


    public SOFTDownloadTask(String[] geoIDs, SOFTViewer viewer) {
        this.geoIDs = geoIDs;
        this.viewer = viewer;
    }

    public void run() {
        try {
            List<SOFT> softList = new ArrayList<SOFT>();
            for (String geoID : geoIDs ) {
                if (taskMonitor != null) {
                    taskMonitor.setStatus("Retrieving data from GEO: " + geoID);
                }
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
                softList.add(soft);
            }
            final List<SOFT> flist = softList;
            // Configures a mapping after the download
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    viewer.viewSOFT(flist);
                }
            });
        } catch (IOException ex) {
        } catch (ParseException ex) {
        }
    }

    public String getTitle() {
        return "Download SOFT files";
    }

    public JTaskConfig getDefaultTaskConfig() {
        JTaskConfig config = new JTaskConfig();

        config.displayCancelButton(true);
        config.displayCloseButton(true);
        config.displayStatus(true);
        config.displayTimeElapsed(false);
        config.setAutoDispose(false);
        config.setModal(false);
        //config.setOwner(Cytoscape.getDesktop());

        return config;
    }
}
