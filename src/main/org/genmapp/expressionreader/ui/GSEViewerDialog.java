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
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author djiao
 */
public class GSEViewerDialog extends javax.swing.JDialog implements SOFTViewer {
    private SOFT soft;
    /** Creates new form GSMViewerDialog */
    public GSEViewerDialog(java.awt.Frame parent, boolean modal, SOFT soft) {
        super(parent, modal);
        this.soft = soft;
        initComponents();
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
        htmlViewerTabbedPane = new javax.swing.JTabbedPane();
        leftPane = new javax.swing.JSplitPane();
        gsmContentTabbedPane = new javax.swing.JTabbedPane();
        samplePane = new javax.swing.JPanel();
        sampleScrollPane = new javax.swing.JScrollPane();
        sampleList = new javax.swing.JList();
        sampleButtonPane = new javax.swing.JPanel();
        viewBtn = new javax.swing.JButton();
        importSampleBtn = new javax.swing.JButton();
        addToGruopBtn = new javax.swing.JButton();
        platformPane = new javax.swing.JPanel();
        platformScrollPane = new javax.swing.JScrollPane();
        platformList = new javax.swing.JList();
        platformButtonPane = new javax.swing.JPanel();
        groupsPane = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        viewGroupBtn = new javax.swing.JButton();
        importGroupBtn = new javax.swing.JButton();
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

        htmlViewerTabbedPane.setPreferredSize(new java.awt.Dimension(250, 500));
        contentSplitPane.setRightComponent(htmlViewerTabbedPane);

        leftPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        gsmContentTabbedPane.setPreferredSize(new java.awt.Dimension(300, 200));

        samplePane.setLayout(new java.awt.BorderLayout());

        sampleList.setModel(new AbstractListModel() {
            Object list = soft.getFields().get("Series_sample_id");
            public int getSize() {
                if (list instanceof List) { return ((List)list).size();}
                else return 1;

            }
            public Object getElementAt(int i) {
                if (list instanceof List) { return ((List)list).get(i); }
                else return list;
            }
        });
        sampleScrollPane.setViewportView(sampleList);

        samplePane.add(sampleScrollPane, java.awt.BorderLayout.CENTER);

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

        gsmContentTabbedPane.addTab("Samples", samplePane);

        platformPane.setLayout(new java.awt.BorderLayout());

        platformList.setModel(new javax.swing.AbstractListModel() {
            Object list = soft.getFields().get("Series_platform_id");
            public int getSize() {
                if (list instanceof List) { return ((List)list).size();}
                else return 1;

            }
            public Object getElementAt(int i) {
                if (list instanceof List) { return ((List)list).get(i); }
                else return list;
            }
        });
        platformScrollPane.setViewportView(platformList);

        platformPane.add(platformScrollPane, java.awt.BorderLayout.CENTER);
        platformPane.add(platformButtonPane, java.awt.BorderLayout.PAGE_END);

        gsmContentTabbedPane.addTab("Platforms", platformPane);

        groupsPane.setLayout(new java.awt.BorderLayout());

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        groupsPane.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        viewGroupBtn.setText("  View  ");
        jPanel1.add(viewGroupBtn);

        importGroupBtn.setText(" Import ");
        jPanel1.add(importGroupBtn);

        groupsPane.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        gsmContentTabbedPane.addTab("Groups", groupsPane);

        leftPane.setBottomComponent(gsmContentTabbedPane);

        gsmInfoPane.setLayout(new java.awt.GridBagLayout());

        metadataWrapperPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Series Information"));
        metadataWrapperPane.setMinimumSize(new java.awt.Dimension(200, 140));
        metadataWrapperPane.setPreferredSize(new java.awt.Dimension(300, 240));
        metadataWrapperPane.setLayout(new java.awt.BorderLayout(5, 5));

        metadataTable.setModel(new AbstractTableModel() {
            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                LinkedHashMap<String, Object> fields = soft.getFields();
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
                return soft.getFields().size();
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

        nameLbl.setText(org.genmapp.expressionreader.ExpressionReaderUtil.getSoftNameLblText(soft));
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
        String gsmId = (String)sampleList.getSelectedValue();
        int index = htmlViewerTabbedPane.indexOfTab(gsmId);
        if (index < 0) { // create a new tab and add to it
            SOFTDownloadTask task = new SOFTDownloadTask(gsmId, this, SOFT.Format.quick);
            JTaskConfig config = task.getDefaultTaskConfig();
            boolean success = TaskManager.executeTask(task, config);
        } else { // bring the tab into focus
            htmlViewerTabbedPane.setSelectedIndex(index);
        }

    }//GEN-LAST:event_viewBtnActionPerformed

    private void viewInBroswerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewInBroswerBtnActionPerformed
        String url = String.format(ExpressionReaderUtil.GEO_URL, soft.getId(), "html", SOFT.Format.quick);
        ExpressionReaderUtil.openURL(url);
    }//GEN-LAST:event_viewInBroswerBtnActionPerformed

    private void importSampleBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importSampleBtnActionPerformed
        String gsmId = (String)sampleList.getSelectedValue();
        if (gsmId != null && !"".equals(gsmId)) {
            SOFTDownloadTask task = new SOFTDownloadTask(gsmId, new SOFTViewer() {

                public void viewSOFT(SOFT soft) {
                    GSMImportDialog dialog = new GSMImportDialog(Cytoscape.getDesktop(), true, soft);
                    dialog.setVisible(true);
                }

                public void closeView(SOFT soft) {
                    // do nothing
                }
            }, SOFT.Format.full);
            TaskManager.executeTask(task, task.getDefaultTaskConfig());
        }
    }//GEN-LAST:event_importSampleBtnActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
		URL url;
                InputStream in = null;
                try {
                    url = new URL(String.format(ExpressionReaderUtil.GEO_URL, "GSE9914", "text", SOFT.Format.full));

                    in = url.openConnection().getInputStream();
                    SOFT gse = new SOFTParser().parseSOFT(in, SOFT.Type.GSE, SOFT.Format.full);
                    if (in != null) {
                        in.close();
                    }

                    GSEViewerDialog dialog = new GSEViewerDialog(new javax.swing.JFrame(), true, gse);
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
    private javax.swing.JPanel groupsPane;
    private javax.swing.JTabbedPane gsmContentTabbedPane;
    private javax.swing.JPanel gsmInfoPane;
    private javax.swing.JTabbedPane htmlViewerTabbedPane;
    private javax.swing.JButton importGroupBtn;
    private javax.swing.JButton importSampleBtn;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane leftPane;
    private javax.swing.JScrollPane metadataScrollPane;
    private javax.swing.JTable metadataTable;
    private javax.swing.JPanel metadataWrapperPane;
    private javax.swing.JLabel nameLbl;
    private javax.swing.JPanel namePane;
    private javax.swing.JPanel platformButtonPane;
    private javax.swing.JList platformList;
    private javax.swing.JPanel platformPane;
    private javax.swing.JScrollPane platformScrollPane;
    private javax.swing.JPanel sampleButtonPane;
    private javax.swing.JList sampleList;
    private javax.swing.JPanel samplePane;
    private javax.swing.JScrollPane sampleScrollPane;
    private javax.swing.JButton viewBtn;
    private javax.swing.JButton viewGroupBtn;
    private javax.swing.JButton viewInBroswerBtn;
    private javax.swing.JPanel viewInBrowserPane;
    // End of variables declaration//GEN-END:variables

    public void viewSOFT(SOFT soft) {
        SOFTViewerPane pane = new SOFTViewerPane();
        pane.setOwner(this);
        pane.setSoft(soft);
        htmlViewerTabbedPane.add(soft.getId(), pane);
        htmlViewerTabbedPane.setSelectedComponent(pane);
    }

    public void closeView(SOFT soft) {
        htmlViewerTabbedPane.remove(htmlViewerTabbedPane.indexOfTab(soft.getId()));
    }
}
