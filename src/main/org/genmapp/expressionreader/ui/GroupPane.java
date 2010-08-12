/*
 Copyright 2010 Alexander Pico
 Licensed under the Apache License, Version 2.0 (the "License"); 
 you may not use this file except in compliance with the License. 
 You may obtain a copy of the License at 
 	
 	http://www.apache.org/licenses/LICENSE-2.0 
 	
 Unless required by applicable law or agreed to in writing, software 
 distributed under the License is distributed on an "AS IS" BASIS, 
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 See the License for the specific language governing permissions and 
 limitations under the License. 
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GroupPane.java
 *
 * Created on Aug 4, 2010, 3:20:28 PM
 */
package org.genmapp.expressionreader.ui;

import cytoscape.task.ui.JTaskConfig;
import cytoscape.task.util.TaskManager;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.TransferHandler;
import javax.swing.table.AbstractTableModel;
import org.genmapp.expressionreader.geo.GEOQuery;
import org.genmapp.expressionreader.geo.data.SOFT;
import org.genmapp.expressionreader.geo.ui.SOFTViewer;
import org.genmapp.expressionreader.geo.ui.SOFTViewerPane;
import org.genmapp.expressionreader.tasks.SOFTDownloadTask;

/**
 *
 * @author djiao
 */
public class GroupPane extends javax.swing.JPanel {

    private List<SOFT> softList;

    /** Creates new form GroupPane */
    public GroupPane() {
        softList = new ArrayList<SOFT>();
        initComponents();
        initDnD();
    }

    public void setSamples(List<SOFT> softList) {
        this.softList = softList;
        this.sampleTable.revalidate();
    }

    public void addSample(SOFT soft) {
        if (!softList.contains(soft)) {
            softList.add(soft);
            this.sampleTable.revalidate();
        }
    }

    public void updateSampleTable() {
        this.sampleTable.revalidate();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jSplitPane1 = new javax.swing.JSplitPane();
        sampleTabbedPane = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        sampleScrollPane = new javax.swing.JScrollPane();
        sampleTable = new javax.swing.JTable();
        buttonPane = new javax.swing.JPanel();
        buttonPane1 = new javax.swing.JPanel();
        addBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        viewBtn = new javax.swing.JButton();
        closeBtn = new javax.swing.JButton();
        buttonPane2 = new javax.swing.JPanel();
        importBtn = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        sampleTabbedPane.setMinimumSize(new java.awt.Dimension(12, 120));
        jSplitPane1.setBottomComponent(sampleTabbedPane);

        jPanel1.setLayout(new java.awt.BorderLayout());

        sampleScrollPane.setPreferredSize(new java.awt.Dimension(452, 302));

        sampleTable.setModel(new AbstractTableModel() {

            @Override
            public String getColumnName(int i) {
                switch (i) {
                    case 0: return "ID";
                    case 1: return "Title";
                    case 2: return "Source";
                    default: return "";
                }
            }

            public int getRowCount() {
                return softList.size();
            }

            public int getColumnCount() {
                return 3;
            }

            public Object getValueAt(int i, int i1) {
                SOFT sample = softList.get(i);
                if (i1 == 0) {
                    return sample.getId();
                } else if (i1 == 1) {
                    return sample.getFields().get("Sample_title");
                } else {
                    return sample.getFields().get("Sample_source_name_ch1");
                }
            }
        });
        sampleTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sampleTableMouseClicked(evt);
            }
        });
        sampleScrollPane.setViewportView(sampleTable);

        jPanel1.add(sampleScrollPane, java.awt.BorderLayout.CENTER);

        buttonPane.setLayout(new java.awt.GridBagLayout());

        addBtn.setText("  Add  ");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        buttonPane1.add(addBtn);

        deleteBtn.setText(" Delete ");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        buttonPane1.add(deleteBtn);

        viewBtn.setText("  View  ");
        viewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewBtnActionPerformed(evt);
            }
        });
        buttonPane1.add(viewBtn);

        closeBtn.setText(" Close ");
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });
        buttonPane1.add(closeBtn);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weighty = 1.0;
        buttonPane.add(buttonPane1, gridBagConstraints);

        importBtn.setText("Import Group");
        importBtn.setEnabled(false);
        importBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importBtnActionPerformed(evt);
            }
        });
        buttonPane2.add(importBtn);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        buttonPane.add(buttonPane2, gridBagConstraints);

        jPanel1.add(buttonPane, java.awt.BorderLayout.SOUTH);

        jSplitPane1.setTopComponent(jPanel1);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void importBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_importBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        String response = (String) JOptionPane.showInputDialog(null,
                "Please enter a GSM ID", "Add Sample to Group",
                JOptionPane.PLAIN_MESSAGE, null, null, "GSM");

        if (response != null && !"".equals(response.trim())) {
            // Download file
            String id = response.trim();
            SOFT.Type type = GEOQuery.getType(id);

            if (type == SOFT.Type.GSM) {
                SOFTDownloadTask task = new SOFTDownloadTask(new String[]{id}, new SOFTViewer() {

                    public void viewSOFT(List<SOFT> soft) {
                        for (SOFT sample : soft) {
                            addSample(sample);
                        }
                        sampleTable.revalidate();  // refresh table
                    }

                    public void closeView(SOFT soft) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }
                }, SOFT.Format.full);
                JTaskConfig config = task.getDefaultTaskConfig();
                TaskManager.executeTask(task, config);
            } else {
            }

        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void viewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewBtnActionPerformed
        int[] rows = sampleTable.getSelectedRows();
        for (int row : rows) {
            int index = sampleTabbedPane.indexOfTab(softList.get(row).getId());
            if (index >=0) continue;

            SOFTViewerPane pane = new SOFTViewerPane();
            pane.addPropertyChangeListener(new PropertyChangeListener() {

                public void propertyChange(PropertyChangeEvent pce) {
                    if (pce.getPropertyName().equals("SOFTViewer_ViewStatus")) {
                        if (pce.getNewValue() instanceof Integer) {
                            if ((Integer)pce.getNewValue() == WindowEvent.WINDOW_CLOSING) {
                                sampleTabbedPane.remove((JComponent)pce.getSource());
                            }
                        }
                    }
                }
            });
            SOFT soft = softList.get(row);
            pane.setSoft(soft);
            sampleTabbedPane.add(soft.getId(), pane);
            sampleTabbedPane.setSelectedComponent(pane);
        }
    }//GEN-LAST:event_viewBtnActionPerformed

    private void sampleTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sampleTableMouseClicked
        if (evt.getClickCount() == 2) {
            viewBtnActionPerformed(null);
        }
    }//GEN-LAST:event_sampleTableMouseClicked

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        int[] rows = sampleTable.getSelectedRows();
        for (int i = rows.length-1; i >=0; i--) {
            SOFT soft = softList.get(rows[i]);
            softList.remove(rows[i]);

            // also close view if it's open
            int index = sampleTabbedPane.indexOfTab(soft.getId());
            if (index >= 0) {
                sampleTabbedPane.remove(index);
            }
        }
        sampleTable.revalidate();
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
        this.firePropertyChange("GroupPane_ViewStatus", WindowEvent.WINDOW_OPENED, WindowEvent.WINDOW_CLOSING);
    }//GEN-LAST:event_closeBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                JDialog dialog = new JDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setContentPane(new GroupPane());
                dialog.setSize(600, 800);
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JPanel buttonPane;
    private javax.swing.JPanel buttonPane1;
    private javax.swing.JPanel buttonPane2;
    private javax.swing.JButton closeBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton importBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JScrollPane sampleScrollPane;
    private javax.swing.JTabbedPane sampleTabbedPane;
    private javax.swing.JTable sampleTable;
    private javax.swing.JButton viewBtn;
    // End of variables declaration//GEN-END:variables

    private void initDnD() {
        sampleTable.setTransferHandler(new TransferHandler() {
            @Override
            public boolean canImport(JComponent comp, DataFlavor[] transferFlavors) {
                if (transferFlavors.length == 1) {
                    if (transferFlavors[0].isMimeTypeEqual(DataFlavor.javaJVMLocalObjectMimeType) &&
                            transferFlavors[0].getHumanPresentableName().equals("SOFT")) {
                        return true;
                    }
                }
                return false;
            }

            public boolean importData(JComponent comp, Transferable t) {
                // fetch the data and bail if this fails
                Object data;
                try {
                    data = t.getTransferData(new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType));
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(GroupPane.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                } catch (UnsupportedFlavorException e) {
                    return false;
                } catch (IOException e) {
                    return false;
                }

                if (data != null) {
                    List<SOFT> list = (List<SOFT>)data;
                    for (SOFT soft: list) {
                        addSample(soft);
                    }
                }

                return true;
            }

        });

    }
}
