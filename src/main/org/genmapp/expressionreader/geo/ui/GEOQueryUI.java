/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader.geo.ui;

import java.awt.Container;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import org.genmapp.expressionreader.geo.data.SOFT;

/**
 *
 * @author djiao
 */
public class GEOQueryUI {

    public static JDialog showSOFTViewerDialog(Container parent, boolean modal, SOFT soft) {
        JDialog dialog = null;
        if (parent instanceof Frame) {
            dialog = new JDialog((Frame) parent, modal);
        } else if (parent instanceof JDialog) {
            dialog = new JDialog((JDialog)parent, modal);
        }

        if (dialog == null) return null;
        final JDialog fdialog = dialog;
        SOFTViewerPane pane = new SOFTViewerPane();
        pane.setSoft(soft);
        dialog.setContentPane(pane);

        pane.addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent pce) {
                    if (pce.getPropertyName().equals("SOFTViewer_ViewStatus")) {
                        if (pce.getNewValue() instanceof Integer) {
                            if ((Integer)pce.getNewValue() == WindowEvent.WINDOW_CLOSING) {
                                fdialog.dispose();
                            }
                        }
                    }
            }
        });

        dialog.setSize(600, 760);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
        return dialog;
    }

    public static JDialog showSOFTViewerDialog(Container parent, boolean modal, List<SOFT> list) {
        if (list == null || list.isEmpty()) return null;
        if (list.size() == 1) return showSOFTViewerDialog(parent, modal, list.get(0));
        JDialog dialog = null;
        if (parent instanceof Frame) {
            dialog = new JDialog((Frame) parent, modal);
        } else if (parent instanceof JDialog) {
            dialog = new JDialog((JDialog)parent, modal);
        }
        if (dialog == null) return null;

        final JDialog fdialog = dialog;
        final JTabbedPane tabs = new JTabbedPane();
        fdialog.setContentPane(tabs);

        for (SOFT soft: list) {
            SOFTViewerPane pane = new SOFTViewerPane();
            pane.setSoft(soft);
            tabs.add(soft.getId(), pane);

            pane.addPropertyChangeListener(new PropertyChangeListener() {

                public void propertyChange(PropertyChangeEvent pce) {
                    if (pce.getPropertyName().equals("SOFTViewer_ViewStatus")) {
                        if (pce.getNewValue() instanceof Integer) {
                            if ((Integer)pce.getNewValue() == WindowEvent.WINDOW_CLOSING) {
                                tabs.remove((JComponent)pce.getSource());
                                if (tabs.getTabCount() == 0) {
                                    fdialog.dispose();
                                }
                            }
                        }
                    }
                }
            });            
        }

        dialog.setSize(600, 760);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
        return dialog;
    }

}
