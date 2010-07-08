/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GSMViewerDialog.java
 *
 * Created on Jun 30, 2010, 10:23:05 AM
 */

package org.genmapp.expressionreader.ui;

import cytoscape.Cytoscape;
import cytoscape.task.ui.JTaskConfig;
import cytoscape.task.util.TaskManager;
import org.genmapp.expressionreader.data.SOFT;
import org.genmapp.expressionreader.ExpressionReaderUtil;
import org.genmapp.expressionreader.parser.SOFTParser;
import org.genmapp.expressionreader.tasks.SOFTDownloadTask;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import org.genmapp.expressionreader.data.DataTable;
import org.genmapp.expressionreader.data.GSE;

/**
 *
 * @author djiao
 */
public class GSEViewerDialog extends javax.swing.JDialog implements SOFTViewer {
    private SOFT soft;
    /** Creates new form GSMViewerDialog */
    public GSEViewerDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public void setSOFT(SOFT soft) {
        this.soft = soft;
        final GSE gse = (GSE) soft;
        sampleTable.setModel(new AbstractTableModel() {

            List<SOFT> samples = gse.getSamples();

            @Override
            public String getColumnName(int i) {
                if (i == 0) {
                    return "ID";
                } else {
                    return "Title";
                }
            }

            public int getRowCount() {
                return samples.size();
            }

            public int getColumnCount() {
                return 2;
            }

            public Object getValueAt(int i, int i1) {
                SOFT sample = samples.get(i);
                if (i1 == 0) {
                    return sample.getId();
                } else {
                    return sample.getFields().get("Sample_title");
                }
            }
        });

        platformTable.setModel(new AbstractTableModel() {

            List<SOFT> platforms = gse.getPlatforms();

            @Override
            public String getColumnName(int i) {
                if (i == 0) {
                    return "ID";
                } else {
                    return "Title";
                }
            }

            public int getRowCount() {
                return platforms.size();
            }

            public int getColumnCount() {
                return 2;
            }

            public Object getValueAt(int i, int i1) {
                SOFT sample = platforms.get(i);
                if (i1 == 0) {
                    return sample.getId();
                } else {
                    return sample.getFields().get("Platform_title");
                }
            }
        });

        if (gse.getDataTables().size() > 0) { // has gruops
            groupList.setModel(new AbstractListModel() {

                public int getSize() {
                    return gse.getDataTables().size();
                }

                public Object getElementAt(int i) {
                    return gse.getDataTables().get(i).getTitle();
                }
            });
        }

        metadataTable.setModel(new AbstractTableModel() {

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                LinkedHashMap<String, Object> fields = gse.getFields();
                List<String> fieldNames = new ArrayList<String>(fields.keySet());
                if (columnIndex == 0) {
                    return fieldNames.get(rowIndex);
                } else {
                    Object obj = fields.get(fieldNames.get(rowIndex));
                    if (obj instanceof String) {
                        return obj;
                    } else {
                        List<String> list = (List) obj;
                        return org.genmapp.expressionreader.ExpressionReaderUtil.join(list, "\n");
                    }
                }
            }

            @Override
            public int getRowCount() {
                // TODO Auto-generated method stub
                return gse.getFields().size();
            }

            @Override
            public int getColumnCount() {
                // TODO Auto-generated method stub
                return 2;
            }

            @Override
            public String getColumnName(int column) {
                return column == 0 ? "Field" : "Value";
            }
        });

        nameLbl.setText(org.genmapp.expressionreader.ExpressionReaderUtil.getSoftNameLblText(soft));
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

        contentSplitPane = new javax.swing.JSplitPane();
        sampleTabbedPane = new javax.swing.JTabbedPane();
        leftPane = new javax.swing.JSplitPane();
        gsmContentTabbedPane = new javax.swing.JTabbedPane();
        samplePane = new javax.swing.JPanel();
        sampleButtonPane = new javax.swing.JPanel();
        viewBtn = new javax.swing.JButton();
        importSampleBtn = new javax.swing.JButton();
        addToGruopBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        sampleTable = new javax.swing.JTable();
        platformPane = new javax.swing.JPanel();
        platformButtonPane = new javax.swing.JPanel();
        gplViewBtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        platformTable = new javax.swing.JTable();
        groupsPane = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        viewGroupBtn = new javax.swing.JButton();
        importGroupBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        groupList = new javax.swing.JList();
        gsmInfoPane = new javax.swing.JPanel();
        metadataWrapperPane = new javax.swing.JPanel();
        metadataScrollPane = new javax.swing.JScrollPane();
        metadataTable = new javax.swing.JTable();
        namePane = new javax.swing.JPanel();
        nameLbl = new javax.swing.JLabel();
        viewInBrowserPane = new javax.swing.JPanel();
        viewInBroswerBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 400));

        sampleTabbedPane.setPreferredSize(new java.awt.Dimension(250, 500));
        contentSplitPane.setRightComponent(sampleTabbedPane);

        leftPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        gsmContentTabbedPane.setPreferredSize(new java.awt.Dimension(300, 200));

        samplePane.setLayout(new java.awt.BorderLayout());

        viewBtn.setText("  View  ");
        viewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewBtnActionPerformed(evt);
            }
        });
        sampleButtonPane.add(viewBtn);

        importSampleBtn.setText(" Import ");
        importSampleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importSampleBtnActionPerformed(evt);
            }
        });
        sampleButtonPane.add(importSampleBtn);

        addToGruopBtn.setText(" Group ");
        addToGruopBtn.setEnabled(false);
        sampleButtonPane.add(addToGruopBtn);

        samplePane.add(sampleButtonPane, java.awt.BorderLayout.PAGE_END);

        jScrollPane2.setViewportView(sampleTable);

        samplePane.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        gsmContentTabbedPane.addTab("Samples", samplePane);

        platformPane.setLayout(new java.awt.BorderLayout());

        gplViewBtn.setText("  View  ");
        gplViewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gplViewBtnActionPerformed(evt);
            }
        });
        platformButtonPane.add(gplViewBtn);

        platformPane.add(platformButtonPane, java.awt.BorderLayout.PAGE_END);

        jScrollPane3.setViewportView(platformTable);

        platformPane.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        gsmContentTabbedPane.addTab("Platforms", platformPane);

        groupsPane.setLayout(new java.awt.BorderLayout());

        viewGroupBtn.setText("  View  ");
        viewGroupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewGroupBtnActionPerformed(evt);
            }
        });
        jPanel1.add(viewGroupBtn);

        importGroupBtn.setText(" Import ");
        importGroupBtn.setEnabled(false);
        importGroupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importGroupBtnActionPerformed(evt);
            }
        });
        jPanel1.add(importGroupBtn);

        groupsPane.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jScrollPane1.setViewportView(groupList);

        groupsPane.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        gsmContentTabbedPane.addTab("Groups", groupsPane);

        leftPane.setBottomComponent(gsmContentTabbedPane);

        gsmInfoPane.setLayout(new java.awt.GridBagLayout());

        metadataWrapperPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Series Information"));
        metadataWrapperPane.setMinimumSize(new java.awt.Dimension(200, 140));
        metadataWrapperPane.setPreferredSize(new java.awt.Dimension(300, 240));
        metadataWrapperPane.setLayout(new java.awt.BorderLayout(5, 5));

        metadataTable.setMinimumSize(new java.awt.Dimension(0, 150));
        metadataTable.setRowHeight(22);
        metadataScrollPane.setViewportView(metadataTable);

        metadataWrapperPane.add(metadataScrollPane, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gsmInfoPane.add(metadataWrapperPane, gridBagConstraints);

        namePane.setFont(new java.awt.Font("DejaVu Serif", 1, 18));

        nameLbl.setText("SeriesID");
        namePane.add(nameLbl);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gsmInfoPane.add(namePane, gridBagConstraints);

        viewInBroswerBtn.setText("View in Browser");
        viewInBroswerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewInBroswerBtnActionPerformed(evt);
            }
        });
        viewInBrowserPane.add(viewInBroswerBtn);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gsmInfoPane.add(viewInBrowserPane, gridBagConstraints);

        leftPane.setLeftComponent(gsmInfoPane);

        contentSplitPane.setLeftComponent(leftPane);

        getContentPane().add(contentSplitPane, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void viewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewBtnActionPerformed
        int[] rows = sampleTable.getSelectedRows();
        for (int row : rows) {
            String gsmId = (String)sampleTable.getModel().getValueAt(row, 0);
            int index = sampleTabbedPane.indexOfTab(gsmId);
            if (index < 0) { // create a new tab and add to it
                SOFTDownloadTask task = new SOFTDownloadTask(gsmId, this);
                JTaskConfig config = task.getDefaultTaskConfig();
                boolean success = TaskManager.executeTask(task, config);
            } else { // bring the tab into focus
                sampleTabbedPane.setSelectedIndex(index);
            }
        }

    }//GEN-LAST:event_viewBtnActionPerformed

    private void viewInBroswerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewInBroswerBtnActionPerformed
        String url = String.format(ExpressionReaderUtil.GEO_URL, soft.getId(), "html", SOFT.Format.quick);
        ExpressionReaderUtil.openURL(url);
    }//GEN-LAST:event_viewInBroswerBtnActionPerformed

    private void importSampleBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importSampleBtnActionPerformed
        int[] rows = sampleTable.getSelectedRows();
            String gsmId = (String)sampleTable.getModel().getValueAt(rows[0], 0);
            if (gsmId != null && !"".equals(gsmId)) {
                SOFTDownloadTask task = new SOFTDownloadTask(gsmId, new SOFTViewer() {

                    public void viewSOFT(SOFT soft) {
                        GSMImportDialog dialog = new GSMImportDialog(Cytoscape.getDesktop(), true, soft);
                        dialog.setVisible(true);
                    }

                    public void closeView(SOFT soft) {
                        // do nothing
                    }
                });
                TaskManager.executeTask(task, task.getDefaultTaskConfig());
            }
    }//GEN-LAST:event_importSampleBtnActionPerformed

    private void gplViewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gplViewBtnActionPerformed
        int[] rows = platformTable.getSelectedRows();
        for (int row : rows) {
            String gplId = (String) platformTable.getModel().getValueAt(row, 0);
            int index = sampleTabbedPane.indexOfTab(gplId);
            if (index < 0) { // create a new tab and add to it
                SOFTDownloadTask task = new SOFTDownloadTask(gplId, this);
                JTaskConfig config = task.getDefaultTaskConfig();
                boolean success = TaskManager.executeTask(task, config);
            } else { // bring the tab into focus
                sampleTabbedPane.setSelectedIndex(index);
            }
        }
    }//GEN-LAST:event_gplViewBtnActionPerformed

    private void viewGroupBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewGroupBtnActionPerformed
        final int index = groupList.getSelectedIndex();
        if (index < 0) return;

        String id = soft.getDataTables().get(index).getTitle();
        if ( sampleTabbedPane.indexOfTab(id) < 0) {
            JTable table = new JTable();
            table.setModel(new AbstractTableModel() {

                DataTable dt = soft.getDataTables().get(index);
                List<String> keys = new ArrayList<String>(dt.getData().keySet());
                public int getRowCount() {
                    return dt.getData().size();
                }

                public int getColumnCount() {
                    return dt.getHeaders().size();
                }

                public Object getValueAt(int i, int i1) {
                    return dt.getData().get(keys.get(i)).get(i1);
                }

                @Override
                public String getColumnName(int i) {
                    return (String) new ArrayList(dt.getHeaders().keySet()).get(i);
                }
            });
            JScrollPane pane = new JScrollPane(table);
            sampleTabbedPane.add(id, pane);
            sampleTabbedPane.setSelectedComponent(pane);
        } else {
            sampleTabbedPane.setSelectedIndex(sampleTabbedPane.indexOfTab(id));
        }
    }//GEN-LAST:event_viewGroupBtnActionPerformed

    private void importGroupBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importGroupBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_importGroupBtnActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
		URL url;
                InputStream in = null;
                try {
                    SOFT gse = ExpressionReaderUtil.getSOFT("GSE9914", SOFT.Type.GSE, SOFT.Format.family);

                    GSEViewerDialog dialog = new GSEViewerDialog(new javax.swing.JFrame(), true);
                    dialog.setSOFT(gse);
                    dialog.setSize(1000, 800);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (Exception ex) {
                } finally {
                    try {
                        in.close();
                    } catch (IOException ex) {
                    }
                }

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addToGruopBtn;
    private javax.swing.JSplitPane contentSplitPane;
    private javax.swing.JButton gplViewBtn;
    private javax.swing.JList groupList;
    private javax.swing.JPanel groupsPane;
    private javax.swing.JTabbedPane gsmContentTabbedPane;
    private javax.swing.JPanel gsmInfoPane;
    private javax.swing.JButton importGroupBtn;
    private javax.swing.JButton importSampleBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane leftPane;
    private javax.swing.JScrollPane metadataScrollPane;
    private javax.swing.JTable metadataTable;
    private javax.swing.JPanel metadataWrapperPane;
    private javax.swing.JLabel nameLbl;
    private javax.swing.JPanel namePane;
    private javax.swing.JPanel platformButtonPane;
    private javax.swing.JPanel platformPane;
    private javax.swing.JTable platformTable;
    private javax.swing.JPanel sampleButtonPane;
    private javax.swing.JPanel samplePane;
    private javax.swing.JTabbedPane sampleTabbedPane;
    private javax.swing.JTable sampleTable;
    private javax.swing.JButton viewBtn;
    private javax.swing.JButton viewGroupBtn;
    private javax.swing.JButton viewInBroswerBtn;
    private javax.swing.JPanel viewInBrowserPane;
    // End of variables declaration//GEN-END:variables

    public void viewSOFT(SOFT soft) {
        SOFTViewerPane pane = new SOFTViewerPane();
        pane.setOwner(this);
        pane.setSoft(soft);
        sampleTabbedPane.add(soft.getId(), pane);
        sampleTabbedPane.setSelectedComponent(pane);
    }

    public void closeView(SOFT soft) {
        sampleTabbedPane.remove(sampleTabbedPane.indexOfTab(soft.getId()));
    }
}
