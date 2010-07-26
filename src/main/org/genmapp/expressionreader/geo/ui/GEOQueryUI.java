/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader.geo.ui;

import java.awt.Container;
import java.awt.Frame;
import java.util.List;
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

        pane.setOwner(new SOFTViewer() {

            public void viewSOFT(List<SOFT> soft) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void closeView(SOFT soft) {
                fdialog.dispose();
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
            pane.setOwner(new SOFTViewer() {

                public void viewSOFT(List<SOFT> soft) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                public void closeView(SOFT soft) {
                    tabs.removeTabAt(tabs.indexOfTab(soft.getId()));
                    if (tabs.getTabCount() == 0) {
                        fdialog.dispose();
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
