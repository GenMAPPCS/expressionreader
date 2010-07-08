/*
 * SOFTViewerPane.java
 *
 * Created on Jul 1, 2010, 9:27:54 AM
 */

package org.genmapp.expressionreader.ui;

import org.genmapp.expressionreader.data.SOFT;
import org.genmapp.expressionreader.ExpressionReaderUtil;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import org.genmapp.expressionreader.data.DataTable;

/**
 *
 * @author djiao
 */
public class SOFTViewerPane extends javax.swing.JPanel {
    private SOFT soft;
    private SOFTViewer owner;

    /** Creates new form SOFTViewerPane */
    public SOFTViewerPane() {
        initComponents();
    }

    public SOFTViewer getOwner() {
        return owner;
    }

    public void setOwner(SOFTViewer owner) {
        this.owner = owner;
    }

    public SOFT getSoft() {
        return soft;
    }

    public void setSoft(final SOFT soft) {
        this.soft = soft;

        sampleNameLbl.setText(org.genmapp.expressionreader.ExpressionReaderUtil.getSoftNameLblText(soft));
        int total = soft.getDataTables().getFirst().getData().size();
        int numOfRows = total > 20 ? 20 : total;
            TitledBorder border = (TitledBorder)dataWrapperPane.getBorder();
        if (soft.getType() == SOFT.Type.GSM) {
            border.setTitle(String.format("Data (%s rows; displaying top %d)", soft.getFields().get("Sample_data_row_count"), numOfRows));
        } else {
            border.setTitle(String.format("Data (%d rows; displaying top %d)", total, numOfRows));
        }

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

        dataTable.setModel(new AbstractTableModel() {

            DataTable dt = soft.getDataTables().getFirst();
            List<String> keys = new ArrayList<String>(dt.getData().keySet());

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return dt.getData().get(keys.get(rowIndex)).get(columnIndex);
            }

            @Override
            public int getRowCount() {
                return dt.getData().size() > 20 ? 20 : dt.getData().size();
            }

            @Override
            public int getColumnCount() {
                return dt.getHeaders().size();
            }

            @Override
            public String getColumnName(int column) {
                return (String) new ArrayList(dt.getHeaders().keySet()).get(column);
            }
        });

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

        sampleNamePane = new javax.swing.JPanel();
        sampleNameLbl = new javax.swing.JLabel();
        viewInBrowserPane = new javax.swing.JPanel();
        viewInBrowserBtn = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        metadataWrapperPane = new javax.swing.JPanel();
        metadataScrollPane = new javax.swing.JScrollPane();
        metadataTable = new javax.swing.JTable();
        dataWrapperPane = new javax.swing.JPanel();
        dataScrollPane = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();

        setLayout(new java.awt.GridBagLayout());

        sampleNameLbl.setText("SampleID");
        sampleNamePane.add(sampleNameLbl);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(sampleNamePane, gridBagConstraints);

        viewInBrowserBtn.setText("View in Browser");
        viewInBrowserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewInBrowserBtnActionPerformed(evt);
            }
        });
        viewInBrowserPane.add(viewInBrowserBtn);

        closeButton.setText(" Close ");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        viewInBrowserPane.add(closeButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(viewInBrowserPane, gridBagConstraints);

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        metadataWrapperPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Sample Information"));
        metadataWrapperPane.setMinimumSize(new java.awt.Dimension(500, 140));
        metadataWrapperPane.setPreferredSize(new java.awt.Dimension(500, 340));
        metadataWrapperPane.setLayout(new java.awt.GridBagLayout());

        metadataTable.setMinimumSize(new java.awt.Dimension(0, 150));
        metadataTable.setRowHeight(22);
        metadataScrollPane.setViewportView(metadataTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        metadataWrapperPane.add(metadataScrollPane, gridBagConstraints);

        jSplitPane1.setLeftComponent(metadataWrapperPane);

        dataWrapperPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Data"));
        dataWrapperPane.setPreferredSize(new java.awt.Dimension(500, 229));
        dataWrapperPane.setLayout(new java.awt.GridBagLayout());

        dataTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        dataTable.setRowHeight(22);
        dataScrollPane.setViewportView(dataTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        dataWrapperPane.add(dataScrollPane, gridBagConstraints);

        jSplitPane1.setRightComponent(dataWrapperPane);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jSplitPane1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void viewInBrowserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewInBrowserBtnActionPerformed
        String url = String.format(ExpressionReaderUtil.GEO_URL, soft.getId(), "html", SOFT.Format.quick);
        ExpressionReaderUtil.openURL(url);
}//GEN-LAST:event_viewInBrowserBtnActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        owner.closeView(soft);
    }//GEN-LAST:event_closeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JScrollPane dataScrollPane;
    private javax.swing.JTable dataTable;
    private javax.swing.JPanel dataWrapperPane;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JScrollPane metadataScrollPane;
    private javax.swing.JTable metadataTable;
    private javax.swing.JPanel metadataWrapperPane;
    private javax.swing.JLabel sampleNameLbl;
    private javax.swing.JPanel sampleNamePane;
    private javax.swing.JButton viewInBrowserBtn;
    private javax.swing.JPanel viewInBrowserPane;
    // End of variables declaration//GEN-END:variables
}
