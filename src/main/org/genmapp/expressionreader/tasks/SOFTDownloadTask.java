/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader.tasks;

import cytoscape.Cytoscape;
import cytoscape.task.ui.JTaskConfig;
import org.genmapp.expressionreader.data.SOFT;
import org.genmapp.expressionreader.data.SOFT.Format;
import org.genmapp.expressionreader.ExpressionReaderUtil;
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
    private Format format;


    public SOFTDownloadTask(String geoID, SOFTViewer viewer, SOFT.Format format) {
        this.geoID = geoID;
        this.viewer = viewer;
        this.format = format;
    }

    /**
     * Get the value of format
     *
     * @return the value of format
     */
    public Format getFormat() {
        return format;
    }

    /**
     * Set the value of format
     *
     * @param format new value of format
     */
    public void setFormat(Format format) {
        this.format = format;
    }

    /**
     * Get the value of viewer
     *
     * @return the value of viewer
     */
    public SOFTViewer getViewer() {
        return viewer;
    }

    /**
     * Set the value of viewer
     *
     * @param viewer new value of viewer
     */
    public void setViewer(SOFTViewer viewer) {
        this.viewer = viewer;
    }


    /**
     * Get the value of geoID
     *
     * @return the value of geoID
     */
    public String getGeoID() {
        return geoID;
    }

    /**
     * Set the value of geoID
     *
     * @param geoID new value of geoID
     */
    public void setGeoID(String geoID) {
        this.geoID = geoID;
    }


    public void run() {
        if (taskMonitor != null) {
            taskMonitor.setStatus("Retrieving data from GEO: " + geoID);
        }
        try {
            SOFT.Type type = ExpressionReaderUtil.getType(geoID);
            final SOFT soft = ExpressionReaderUtil.getSOFT(geoID, type, format);
            // Configures a mapping after the download
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    viewer.viewSOFT(soft);
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
