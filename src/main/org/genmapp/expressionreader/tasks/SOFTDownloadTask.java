/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader.tasks;

import cytoscape.logger.CyLogger;
import org.genmapp.expressionreader.geo.ui.SOFTViewer;
import cytoscape.task.ui.JTaskConfig;
import org.genmapp.expressionreader.geo.data.SOFT;
import org.genmapp.expressionreader.geo.data.SOFT.Format;
import org.genmapp.expressionreader.geo.GEOQuery;
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
    static public CyLogger logger = CyLogger.getLogger(SOFTDownloadTask.class);
    private String[] geoIDs;
    private SOFTViewer viewer;
    private SOFT.Format format = SOFT.Format.quick;

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format type) {
        this.format = type;
    }

    public SOFTDownloadTask(String[] geoIDs, SOFTViewer viewer, SOFT.Format format) {
        this.geoIDs = geoIDs;
        this.viewer = viewer;
        this.format = format;
    }

    public void run() {
        logger.debug("Downloading " + geoIDs.length + "SOFT files. start ...");
        try {
            List<SOFT> softList = new ArrayList<SOFT>();
            for (String geoID : geoIDs ) {
                if (taskMonitor != null) {
                    taskMonitor.setStatus("Retrieving data from GEO: " + geoID);
                }
                SOFT soft = GEOQuery.getSOFT(geoID, format);
                softList.add(soft);
            }
            final List<SOFT> flist = softList;

            logger.debug("Downloaded " + softList.size() + " files");

            // Configures a mapping after the download
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    viewer.viewSOFT(flist);
                }
            });
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
            ex.printStackTrace();
        } catch (ParseException ex) {
            logger.error(ex.getMessage(), ex);
            ex.printStackTrace();
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
        config.setAutoDispose(true);
        config.setModal(false);

        return config;
    }
}
