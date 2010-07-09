/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader.actions;

import cytoscape.Cytoscape;
import cytoscape.util.CytoscapeAction;
import java.awt.event.ActionEvent;
import org.genmapp.expressionreader.ui.GEOSearchDialog;

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
        GEOSearchDialog dialog = new GEOSearchDialog(Cytoscape.getDesktop(), false);
        dialog.setVisible(true);
    }

}
