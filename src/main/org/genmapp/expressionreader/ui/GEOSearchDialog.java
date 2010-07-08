/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GEOSearchDialog.java
 *
 * Created on Jul 1, 2010, 5:29:56 PM
 */

package org.genmapp.expressionreader.ui;

import corejava.Format;
import cytoscape.Cytoscape;
import cytoscape.task.Task;
import cytoscape.task.ui.JTaskConfig;
import cytoscape.task.util.TaskManager;
import gov.nih.nlm.ncbi.soap.eutils.EUtilsService;
import gov.nih.nlm.ncbi.soap.eutils.EUtilsServiceSoap;
import gov.nih.nlm.ncbi.soap.eutils.esearch.ESearchRequest;
import gov.nih.nlm.ncbi.soap.eutils.esearch.ESearchResult;
import gov.nih.nlm.ncbi.soap.eutils.esummary.DocSumType;
import gov.nih.nlm.ncbi.soap.eutils.esummary.ESummaryRequest;
import gov.nih.nlm.ncbi.soap.eutils.esummary.ESummaryResult;
import gov.nih.nlm.ncbi.soap.eutils.esummary.ItemType;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import org.genmapp.expressionreader.ExpressionReaderUtil;
import org.genmapp.expressionreader.data.SOFT;
import org.genmapp.expressionreader.tasks.AbstractTask;
import org.genmapp.expressionreader.tasks.SOFTDownloadTask;

/**
 *
 * @author djiao
 */
public class GEOSearchDialog extends javax.swing.JDialog {

    private int page = 0;
    private int itemPerPage = 20;
    private int total = 0;

    /** Creates new form GEOSearchDialog */
    public GEOSearchDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
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

        searchPane = new javax.swing.JPanel();
        searchFld = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        bottomPane = new javax.swing.JPanel();
        viewBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        resultScrollPane = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        prevBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        pageField = new javax.swing.JTextField();
        totalLbl = new javax.swing.JLabel();
        nextBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        searchPane.setLayout(new java.awt.BorderLayout());

        searchFld.setMinimumSize(new java.awt.Dimension(150, 27));
        searchFld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFldActionPerformed(evt);
            }
        });
        searchPane.add(searchFld, java.awt.BorderLayout.CENTER);

        searchBtn.setText("Search!");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });
        searchPane.add(searchBtn, java.awt.BorderLayout.LINE_END);

        getContentPane().add(searchPane, java.awt.BorderLayout.PAGE_START);

        viewBtn.setText(" View ");
        viewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewBtnActionPerformed(evt);
            }
        });
        bottomPane.add(viewBtn);

        getContentPane().add(bottomPane, java.awt.BorderLayout.PAGE_END);

        jPanel1.setLayout(new java.awt.BorderLayout());

        resultTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        resultScrollPane.setViewportView(resultTable);

        jPanel1.add(resultScrollPane, java.awt.BorderLayout.CENTER);

        prevBtn.setText(" << ");
        prevBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevBtnActionPerformed(evt);
            }
        });
        jPanel2.add(prevBtn);

        jLabel1.setText("Page");
        jPanel2.add(jLabel1);

        pageField.setText("0");
        pageField.setMinimumSize(new java.awt.Dimension(50, 27));
        pageField.setPreferredSize(new java.awt.Dimension(50, 27));
        pageField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pageFieldActionPerformed(evt);
            }
        });
        jPanel2.add(pageField);

        totalLbl.setText("of ");
        jPanel2.add(totalLbl);

        nextBtn.setText(" >> ");
        nextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBtnActionPerformed(evt);
            }
        });
        jPanel2.add(nextBtn);

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        ESearchRequest request = new ESearchRequest();
        request.setDb("gds");
        request.setRetMax(String.valueOf(this.itemPerPage));
        request.setRetStart(String.valueOf(this.page*this.itemPerPage));
        request.setTerm(searchFld.getText());

        final ESearchRequest query = request;

        Task task = new AbstractTask() {

            public void run() {
                EUtilsService service = new EUtilsService();
                EUtilsServiceSoap clientStub = service.getEUtilsServiceSoap();
                final ESearchResult result = clientStub.runESearch(query);
                total = Integer.parseInt(result.getCount());
                String ids = ExpressionReaderUtil.join(result.getIdList().getId(), ",");

                System.out.println(String.format("Total: %d, IDS: %s", total, ids));

                ESummaryRequest req = new ESummaryRequest();
                req.setDb("gds");
                req.setId(ids);

                ESummaryResult res = clientStub.runESummary(req);
                final List<DocSumType> docsum = res.getDocSum();
                for (DocSumType ds : docsum) {
                    List<ItemType> items = ds.getItem();
                    for (ItemType item: items) {
                        System.out.println("   " + item.getName() + ": " + item.getItemContent());
                    }
                    System.out.println("--------------------------------");
                }

                SwingUtilities.invokeLater(new Runnable() {

                    public void run() {
                        pageField.setText(String.valueOf(page+1));
                        int totalPages = total % itemPerPage == 0 ? total / itemPerPage : (total/itemPerPage+1);
                        totalLbl.setText("Of " + totalPages);

                        prevBtn.setEnabled(page != 0);
                        nextBtn.setEnabled(page != totalPages);

                        // populate result list
                        //System.out.println(result.getCount());
                        resultTable.setModel(new AbstractTableModel() {
                            
                            @Override
                            public String getColumnName(int i) {
                                if (i == 0) {
                                    return "ID";
                                } else {
                                    return "Title";
                                }
                            }

                            public int getRowCount() {
                                return docsum.size();
                            }

                            public int getColumnCount() {
                                return 2;
                            }

                            public Object getValueAt(int i, int i1) {
                                DocSumType ds = docsum.get(i);
                                if (i1 == 0) {
                                    ItemType item = ds.getItem().get(8);
                                    String entryType = item.getItemContent();
                                    if ("GDS".equals(entryType)) {
                                        item = ds.getItem().get(0);
                                    } else if ("GSE".equals(entryType)) {
                                        item = ds.getItem().get(4);
                                    } else if ("GPL".equals(entryType)) {
                                        item = ds.getItem().get(3);
                                    }
                                    return entryType + item.getItemContent();
                                } else {
                                    ItemType item = ds.getItem().get(i1);
                                    return item.getItemContent();
                                }
                            }
                        });
                    }
                });
            }

            public String getTitle() {
                return "Search GEO";
            }

        };
        JTaskConfig config = new JTaskConfig();
        config.setModal(false);
        config.setOwner(Cytoscape.getDesktop());
        config.setAutoDispose(true);
        TaskManager.executeTask(task, config);
    }//GEN-LAST:event_searchBtnActionPerformed

    private void nextBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBtnActionPerformed
        this.page++;
        this.searchBtnActionPerformed(evt);
    }//GEN-LAST:event_nextBtnActionPerformed

    private void prevBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevBtnActionPerformed
        this.page--;
        this.searchBtnActionPerformed(evt);
    }//GEN-LAST:event_prevBtnActionPerformed

    private void pageFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pageFieldActionPerformed
        this.page = Integer.parseInt(this.pageField.getText())-1;
        this.searchBtnActionPerformed(evt);
    }//GEN-LAST:event_pageFieldActionPerformed

    private void viewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewBtnActionPerformed
        int row = resultTable.getSelectedRow();
        if (row >= 0) {
            Object value = resultTable.getModel().getValueAt(row, 0);
            if (value != null && !"".equals(value)) {
            // Download file
            SOFTDownloadTask task = new SOFTDownloadTask((String)value, new SOFTViewer() {

                    public void viewSOFT(SOFT soft) {
                        if (soft.getType() == SOFT.Type.GSM) {
                            GSMImportDialog dialog = new GSMImportDialog(Cytoscape.getDesktop(), true, soft);
                            dialog.setVisible(true);
                        } else if (soft.getType() == SOFT.Type.GSE) {
                            GSEViewerDialog dialog = new GSEViewerDialog(Cytoscape.getDesktop(), true, soft);
                            dialog.setVisible(true);
                        } else if (soft.getType() == SOFT.Type.GDS) {
                            GDSViewerDialog dialog = new GDSViewerDialog(Cytoscape.getDesktop(), true);
                            dialog.setSOFT(soft);
                            dialog.setVisible(true);
                        }
                    }

                    public void closeView(SOFT soft) {
                        // not implemented
                    }
                }, SOFT.Format.full);
            JTaskConfig config = task.getDefaultTaskConfig();

            TaskManager.executeTask(task, config);
        }
        }
    }//GEN-LAST:event_viewBtnActionPerformed

    private void searchFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFldActionPerformed
        this.searchBtnActionPerformed(evt);
    }//GEN-LAST:event_searchFldActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GEOSearchDialog dialog = new GEOSearchDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton nextBtn;
    private javax.swing.JTextField pageField;
    private javax.swing.JButton prevBtn;
    private javax.swing.JScrollPane resultScrollPane;
    private javax.swing.JTable resultTable;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField searchFld;
    private javax.swing.JPanel searchPane;
    private javax.swing.JLabel totalLbl;
    private javax.swing.JButton viewBtn;
    // End of variables declaration//GEN-END:variables

}
