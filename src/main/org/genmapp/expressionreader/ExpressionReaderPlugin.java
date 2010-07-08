package org.genmapp.expressionreader;


import javax.swing.JMenu;

import org.genmapp.expressionreader.actions.ArrayExpressionImportAction;
import org.genmapp.expressionreader.actions.GEOImportAction;
import cytoscape.Cytoscape;
import cytoscape.logger.CyLogger;
import cytoscape.plugin.CytoscapePlugin;
import cytoscape.view.CytoscapeDesktop;
import org.genmapp.expressionreader.actions.GEOSearchAction;

/**
 * This plugin reads gene expression data from GEO and ArrayExpress
 */
public class ExpressionReaderPlugin extends CytoscapePlugin {

    static public CyLogger logger = CyLogger.getLogger(ExpressionReaderPlugin.class);

    /**
     * This is the main constructor, which will be called by Cytoscape's Plugin
     * Manager. Add our listeners and create the main menu.
     */
    public ExpressionReaderPlugin() {
        try {
            // Set ourselves up to listen for new networks
            Cytoscape.getDesktop().getSwingPropertyChangeSupport().addPropertyChangeListener(CytoscapeDesktop.NETWORK_VIEW_CREATED, this);
        } catch (ClassCastException ccex) {
            logger.error("Unable to setup network listeners: " + ccex.getMessage(), ccex);
            return;
        }

        JMenu pluginMenu = Cytoscape.getDesktop().getCyMenus().getOperationsMenu();
        JMenu geReaderMenu = new JMenu("Gene Expression Reader");
        pluginMenu.add(geReaderMenu);
        JMenu geoMenu = new JMenu("GEO");
        geReaderMenu.add(geoMenu);

        try {
            geoMenu.add(new GEOImportAction());
            geoMenu.add(new GEOSearchAction());
//            geReaderMenu.add(new ArrayExpressionImportAction());
        } catch (Exception e) {
            logger.error("Unable to initialize menus: " + e.getMessage(), e);
        }
    }
}
