package org.genmapp.expressionreader.actions;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import cytoscape.Cytoscape;
import cytoscape.util.CytoscapeAction;

public class ArrayExpressionImportAction extends CytoscapeAction {

    private static final long serialVersionUID = 887056633366647212L;

    public ArrayExpressionImportAction() {
        super("ArrayExpress");
    }

    @Override
    public void actionPerformed(ActionEvent paramActionEvent) {
        /*
        Object response = JOptionPane.showInputDialog(Cytoscape.getDesktop(),
                "Please enter an ArrayExpress ID", "Query ArrayExpress",
                JOptionPane.PLAIN_MESSAGE, null, null, null);
         */
        JOptionPane.showMessageDialog(Cytoscape.getDesktop(), "Not implemented yet");
    }
}
