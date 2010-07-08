package org.genmapp.expressionreader.ui;

import cytoscape.CyNetwork;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import cytoscape.cythesaurus.CyThesaurusServiceClient;
import cytoscape.cythesaurus.CyThesaurusServiceMessageBasedClient;
import cytoscape.data.CyAttributes;
import cytoscape.task.ui.JTaskConfig;
import cytoscape.task.util.TaskManager;
import org.genmapp.expressionreader.data.DataTable;
import org.genmapp.expressionreader.data.SOFT;
import org.genmapp.expressionreader.ExpressionReaderUtil;
import org.genmapp.expressionreader.parser.SOFTParser;
import org.genmapp.expressionreader.tasks.AbstractTask;
import org.genmapp.expressionreader.tasks.SOFTDownloadTask;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author djiao
 */
public class GSMImportDialog extends javax.swing.JDialog implements SOFTViewer {

    private SOFT soft;
    private SOFT gpl;
    private CyThesaurusServiceClient client = new CyThesaurusServiceMessageBasedClient();

    /** Creates new form SOFTImportDialog */
    public GSMImportDialog(java.awt.Frame parent, boolean modal, SOFT soft) {
        super(parent, modal);
        this.soft = soft;
        initComponents();
        populateAttributes();
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

        idMappingOptionBtnGroup = new javax.swing.ButtonGroup();
        sampleNamePane = new javax.swing.JPanel();
        sampleNameLbl = new javax.swing.JLabel();
        viewSamplePane = new javax.swing.JPanel();
        importGPLBtn = new javax.swing.JButton();
        viewSampleDataBtn = new javax.swing.JButton();
        viewInBrowserBtn = new javax.swing.JButton();
        idMappingOptionWrapperPane = new javax.swing.JPanel();
        idMappingOptionPane = new javax.swing.JPanel();
        useIdMappingLbl = new javax.swing.JLabel();
        useIdMappingYesRadioBtn = new javax.swing.JRadioButton();
        useIdMappingNoRadioBtn = new javax.swing.JRadioButton();
        sourceWrapperPane = new javax.swing.JPanel();
        sourcePane = new javax.swing.JPanel();
        sourceIDTypeLbl = new javax.swing.JLabel();
        sourceIdTypeCombo = new javax.swing.JComboBox();
        sourceKeyAttrLbl = new javax.swing.JLabel();
        sourceKeyAttrCombo = new javax.swing.JComboBox();
        targetWrapperPane = new javax.swing.JPanel();
        targetPane = new javax.swing.JPanel();
        targetIdTypeLbl = new javax.swing.JLabel();
        targetIdTypeCombo = new javax.swing.JComboBox();
        targetKeyAttrLbl = new javax.swing.JLabel();
        targetKeyAttrCombo = new javax.swing.JComboBox();
        targetValueAttrLbl = new javax.swing.JLabel();
        targetValueAttrCombo = new javax.swing.JComboBox();
        idMapConfigPane = new javax.swing.JPanel();
        idMapConfigBtn = new javax.swing.JButton();
        importPane = new javax.swing.JPanel();
        importButton = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Import Gene Expression Data");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        sampleNameLbl.setText("Sample: " + soft.getId());
        sampleNamePane.add(sampleNameLbl);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(sampleNamePane, gridBagConstraints);

        importGPLBtn.setText("Import GPL Data");
        importGPLBtn.setToolTipText("Import GEO Platform Data of the Sample");
        importGPLBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importGPLBtnActionPerformed(evt);
            }
        });
        viewSamplePane.add(importGPLBtn);

        viewSampleDataBtn.setText("View Sample Data");
        viewSampleDataBtn.setToolTipText("Opens a dialog to view sample data");
        viewSampleDataBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewSampleDataBtnActionPerformed(evt);
            }
        });
        viewSamplePane.add(viewSampleDataBtn);

        viewInBrowserBtn.setText("View In Browser");
        viewInBrowserBtn.setToolTipText("Opens a browser to view sample data");
        viewInBrowserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewInBrowserBtnActionPerformed(evt);
            }
        });
        viewSamplePane.add(viewInBrowserBtn);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(viewSamplePane, gridBagConstraints);

        idMappingOptionWrapperPane.setLayout(new java.awt.GridBagLayout());

        useIdMappingLbl.setText("Use ID Mapping Service? ");
        idMappingOptionPane.add(useIdMappingLbl);

        idMappingOptionBtnGroup.add(useIdMappingYesRadioBtn);
        useIdMappingYesRadioBtn.setText("Yes");
        useIdMappingYesRadioBtn.setToolTipText("Choose this option to use CyThesaurus ID Mapping service to map node to probes");
        useIdMappingYesRadioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useIdMappingYesRadioBtnActionPerformed(evt);
            }
        });
        idMappingOptionPane.add(useIdMappingYesRadioBtn);

        idMappingOptionBtnGroup.add(useIdMappingNoRadioBtn);
        useIdMappingNoRadioBtn.setSelected(true);
        useIdMappingNoRadioBtn.setText("No");
        useIdMappingNoRadioBtn.setToolTipText("Choose this option to directly map nodes to probes using attributes");
        useIdMappingNoRadioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useIdMappingNoRadioBtnActionPerformed(evt);
            }
        });
        idMappingOptionPane.add(useIdMappingNoRadioBtn);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        idMappingOptionWrapperPane.add(idMappingOptionPane, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(idMappingOptionWrapperPane, gridBagConstraints);

        sourceWrapperPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Select source attribute/IDType(s)"));
        sourceWrapperPane.setLayout(new java.awt.GridBagLayout());

        sourceIDTypeLbl.setText("Source IDType");
        sourcePane.add(sourceIDTypeLbl);

        sourceIdTypeCombo.setEnabled(false);
        sourcePane.add(sourceIdTypeCombo);

        sourceKeyAttrLbl.setText("     Key Attribute");
        sourcePane.add(sourceKeyAttrLbl);

        sourcePane.add(sourceKeyAttrCombo);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        sourceWrapperPane.add(sourcePane, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(sourceWrapperPane, gridBagConstraints);

        targetWrapperPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Select Imported Data attributes/IDType"));
        targetWrapperPane.setLayout(new java.awt.GridBagLayout());

        targetIdTypeLbl.setText("Target IDType");
        targetPane.add(targetIdTypeLbl);

        targetIdTypeCombo.setEnabled(false);
        targetPane.add(targetIdTypeCombo);

        targetKeyAttrLbl.setText("     Key Attribute");
        targetPane.add(targetKeyAttrLbl);

        targetPane.add(targetKeyAttrCombo);

        targetValueAttrLbl.setText("     Value Attribute");
        targetPane.add(targetValueAttrLbl);

        targetPane.add(targetValueAttrCombo);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        targetWrapperPane.add(targetPane, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(targetWrapperPane, gridBagConstraints);

        idMapConfigBtn.setText("ID Mapping Resource Configuration");
        idMapConfigBtn.setEnabled(false);
        idMapConfigBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idMapConfigBtnActionPerformed(evt);
            }
        });
        idMapConfigPane.add(idMapConfigBtn);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(idMapConfigPane, gridBagConstraints);

        importButton.setText("Import");
        importButton.setToolTipText("Click to start data importing");
        importButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importButtonActionPerformed(evt);
            }
        });
        importPane.add(importButton);

        cancelBtn.setText("Cancel");
        cancelBtn.setToolTipText("Cancel this operation");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });
        importPane.add(cancelBtn);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(importPane, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void useIdMappingYesRadioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_useIdMappingYesRadioBtnActionPerformed
        if (this.useIdMappingYesRadioBtn.isSelected()) {
            if (client.isServiceAvailable()) {
                this.idMapConfigBtn.setEnabled(true);
                populateIDTypes();
            } else {
                JOptionPane.showMessageDialog(this, "No ID Mapping Service available. Please "
                        + "\ninstall CyThesaurus ID Mapping Plugin first. ");
                this.useIdMappingNoRadioBtn.setSelected(true);
            }
        }
    }//GEN-LAST:event_useIdMappingYesRadioBtnActionPerformed

    private void useIdMappingNoRadioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_useIdMappingNoRadioBtnActionPerformed
        // Disable the buttons and comboBoxes
        if (this.useIdMappingNoRadioBtn.isSelected()) {
            this.idMapConfigBtn.setEnabled(false);
            this.sourceIdTypeCombo.setEnabled(false);
            this.targetIdTypeCombo.setEnabled(false);
        }
    }//GEN-LAST:event_useIdMappingNoRadioBtnActionPerformed

    private void importButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importButtonActionPerformed
        final CyAttributes cyattrs = Cytoscape.getNodeAttributes();
        final CyNetwork network = Cytoscape.getCurrentNetwork();
        final List<CyNode> nodes = (List<CyNode>) network.nodesList();

        JTaskConfig config = new JTaskConfig();
        config.setAutoDispose(false);
        config.setModal(false);
        config.displayStatus(true);
        config.setOwner(Cytoscape.getDesktop());

        //this.setVisible(false);
        boolean result = TaskManager.executeTask(new AbstractTask() {

            public void run() {
                if (taskMonitor != null) {
                    taskMonitor.setStatus("Preparing for importing Data into Network " + network.getTitle());
                }
                String srcKeyAttr = (String) sourceKeyAttrCombo.getSelectedItem();
                int tgtValueIndex = targetValueAttrCombo.getSelectedIndex();
                String tgtValueAttr = (String) targetValueAttrCombo.getSelectedItem();

                final int tgtKeyIndex = targetKeyAttrCombo.getSelectedIndex();

                DataTable dt = soft.getDataTables().getFirst();

                List<String> dataKeys = new ArrayList<String>(dt.getData().keySet());
                List<Object> merged = new ArrayList<Object>();
                for (String key : dataKeys) {
                    List<String> list = new ArrayList<String>();
                    list.addAll(dt.getData().get(key));
                    if (gpl != null) {
                        list.addAll(gpl.getDataTables().getFirst().getData().get(key));
                    }
                    merged.add(list);
                }

                List<String> mapFrom = new ArrayList<String>();
                for (CyNode node : nodes) {
                    if ("ID".equals(srcKeyAttr)) {
                        mapFrom.add(node.getIdentifier());
                    } else {
                        mapFrom.add((String) cyattrs.getAttribute(node.getIdentifier(), srcKeyAttr));
                    }
                }

                Collections.sort(merged, new Comparator<Object>() {
                    public int compare(Object t, Object t1) {
                        String s = (String) ((List) t).get(tgtKeyIndex);
                        String s1 = (String) ((List) t1).get(tgtKeyIndex);
                        return s.compareTo(s1);
                    }
                });

                Comparator comparator = new Comparator() {
                    public int compare(Object t, Object t1) {
                        String s = (String) ((List) t).get(tgtKeyIndex);
                        String s1 = (String) t1;
                        return s.compareTo(s1);
                    }
                };
                Map mapTypeAttr = null;
                if (idMapConfigBtn.isEnabled()) { // use id mappings

                    if (taskMonitor != null) {
                        taskMonitor.setStatus("Mapping IDs");
                    }
                    String srcIDType = (String) sourceIdTypeCombo.getSelectedItem();
                    String tgtIDType = (String) targetIdTypeCombo.getSelectedItem();

                    Set<String> srcIDs = new HashSet<String>();
                    srcIDs.addAll(mapFrom);
                    mapTypeAttr = client.mapID(srcIDs, srcIDType, tgtIDType);
                }

                if (taskMonitor != null) {
                    taskMonitor.setStatus("Importing Data into Network");
                }
                int totalAdded = 0;
                String nameAttr = getAttrName(cyattrs, nodes.get(0), tgtValueAttr);
                // find all occurances and add to nodes
                for (int i = 0; i < nodes.size(); i++) {
                    String srcKeyVal = mapFrom.get(i);
                    CyNode node = nodes.get(i);

                    if (idMapConfigBtn.isEnabled()) {
                        Set<String> values = (Set<String>) mapTypeAttr.get(srcKeyVal);

                        if (values != null && !values.isEmpty()) {
                            Iterator<String> it = values.iterator();
                            while (it.hasNext()) {
                                String next = it.next();
                                int index = Collections.binarySearch(merged, next, comparator);
                                if (index >= 0) {
                                    List list = (List) merged.get(index);
                                    cyattrs.setAttribute(node.getIdentifier(), nameAttr,
                                            (String) list.get(tgtValueIndex));
                                    totalAdded++;
                                    break;
                                }
                            }
                        }
                    } else {
                        int index = Collections.binarySearch(merged, srcKeyVal, comparator);
                        if (index >= 0) {
                            List list = (List) merged.get(index);
                            cyattrs.setAttribute(node.getIdentifier(), nameAttr,
                                    (String) list.get(tgtValueIndex));
                            totalAdded++;
                        }
                    }
                }
                if (taskMonitor != null) {
                    taskMonitor.setStatus(String.format("Importing complete. Added value to %d/%d nodes. Attribute name is %s. ",
                            totalAdded, nodes.size(), nameAttr));
                }
            }

            public String getTitle() {
                return "Importing data into Network";
            }
        }, config);
        if (result) {
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Importing Data failed");
        }
    }//GEN-LAST:event_importButtonActionPerformed

    private void viewInBrowserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewInBrowserBtnActionPerformed
        String url = String.format(ExpressionReaderUtil.GEO_URL, soft.getId(), "html", "quick");
        ExpressionReaderUtil.openURL(url);
    }//GEN-LAST:event_viewInBrowserBtnActionPerformed

    private void viewSampleDataBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewSampleDataBtnActionPerformed
        ExpressionReaderUtil.showSOFTViewerDialog(this, false, soft);
    }//GEN-LAST:event_viewSampleDataBtnActionPerformed
    private void idMapConfigBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idMapConfigBtnActionPerformed
        boolean result = client.openMappingResourceConfigDialog();
        if (result) {
            populateIDTypes();
        }
    }//GEN-LAST:event_idMapConfigBtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed
    private void importGPLBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importGPLBtnActionPerformed
        if ("Import GPL Data".equals(evt.getActionCommand())) {
            String gplId = (String) soft.getFields().get("Sample_platform_id");
            SOFTDownloadTask task = new SOFTDownloadTask(gplId, this);
            JTaskConfig config = task.getDefaultTaskConfig();
            TaskManager.executeTask(task, config);
        } else if ("View GPL Data".equals(evt.getActionCommand())) {
            ExpressionReaderUtil.showSOFTViewerDialog(this, false, soft);
        }
    }//GEN-LAST:event_importGPLBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                URL url;
                InputStream gplIn = null;
                try {
                    url = new URL(String.format(ExpressionReaderUtil.GEO_URL, "GSM207569", "text", "full"));
                    gplIn = url.openConnection().getInputStream();

                    SOFT gsm = new SOFTParser().parseSOFT(gplIn, SOFT.Type.GSM);
                    if (gplIn != null) {
                        gplIn.close();
                    }
                    GSMImportDialog dialog = new GSMImportDialog(new javax.swing.JFrame(), true, gsm);
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
                        gplIn.close();
                    } catch (IOException ex) {
                    }
                }
            }
        });


    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton cancelBtn;
    javax.swing.JButton idMapConfigBtn;
    javax.swing.JPanel idMapConfigPane;
    javax.swing.ButtonGroup idMappingOptionBtnGroup;
    javax.swing.JPanel idMappingOptionPane;
    javax.swing.JPanel idMappingOptionWrapperPane;
    javax.swing.JButton importButton;
    javax.swing.JButton importGPLBtn;
    javax.swing.JPanel importPane;
    javax.swing.JLabel sampleNameLbl;
    javax.swing.JPanel sampleNamePane;
    javax.swing.JLabel sourceIDTypeLbl;
    javax.swing.JComboBox sourceIdTypeCombo;
    javax.swing.JComboBox sourceKeyAttrCombo;
    javax.swing.JLabel sourceKeyAttrLbl;
    javax.swing.JPanel sourcePane;
    javax.swing.JPanel sourceWrapperPane;
    javax.swing.JComboBox targetIdTypeCombo;
    javax.swing.JLabel targetIdTypeLbl;
    javax.swing.JComboBox targetKeyAttrCombo;
    javax.swing.JLabel targetKeyAttrLbl;
    javax.swing.JPanel targetPane;
    javax.swing.JComboBox targetValueAttrCombo;
    javax.swing.JLabel targetValueAttrLbl;
    javax.swing.JPanel targetWrapperPane;
    javax.swing.JLabel useIdMappingLbl;
    javax.swing.JRadioButton useIdMappingNoRadioBtn;
    javax.swing.JRadioButton useIdMappingYesRadioBtn;
    javax.swing.JButton viewInBrowserBtn;
    javax.swing.JButton viewSampleDataBtn;
    javax.swing.JPanel viewSamplePane;
    // End of variables declaration//GEN-END:variables

    private void populateAttributes() {
        // Get attributes, and populate attributes comboboxes
        List<String> list = Arrays.asList(cytoscape.Cytoscape.getNodeAttributes().getAttributeNames());
        Collections.sort(list);

        List<String> attributes = new ArrayList<String>();

        // TODO remove in Cytoscape3
        attributes.add(0, "ID");
        // TODO: modify if local attribute implemented

        attributes.addAll(list);
        this.sourceKeyAttrCombo.setModel(new DefaultComboBoxModel(attributes.toArray()));

        List<String> headers = new ArrayList<String>(soft.getDataTables().getFirst().getHeaders().keySet());

        this.targetKeyAttrCombo.setModel(new DefaultComboBoxModel(headers.toArray()));
        this.targetValueAttrCombo.setModel(new DefaultComboBoxModel(headers.toArray()));
        // guess
        if (headers.contains("VALUE")) {
            this.targetValueAttrCombo.setSelectedIndex(headers.indexOf("VALUE"));
        }
        if (headers.contains("ID_REF")) {
            this.targetKeyAttrCombo.setSelectedIndex(headers.indexOf("ID_REF"));
        }

        this.pack();
    }

    private boolean populateIDTypes() {
        JTaskConfig config = new JTaskConfig();
        config.setAutoDispose(true);

        return TaskManager.executeTask(new AbstractTask() {
            public void run() {
                Set<String> srcTypes = client.supportedSrcIDTypes();
                Set<String> tgtTypes = client.supportedTgtIDTypes();

                if (srcTypes.isEmpty() || tgtTypes.isEmpty()) {
                    if (taskMonitor != null)
                        taskMonitor.setException(new Exception(), "No Source Type Found! "
                    + "Please click on \"ID Mapping Resource Configuration\" "
                    + "\nbutton to configure ID Mapping Resources first");
                }
                sourceIdTypeCombo.setEnabled(true);
                sourceIdTypeCombo.setModel(new DefaultComboBoxModel(srcTypes.toArray()));
                targetIdTypeCombo.setEnabled(true);
                targetIdTypeCombo.setModel(new DefaultComboBoxModel(tgtTypes.toArray()));
                pack();
            }

            public String getTitle() {
                return "Initializing CyThesaurus ID Mapping Services";
            }
        }, config);
    }

    private String getAttrName(CyAttributes attrs, CyNode node, String tgtValueAttr) {
        String name = String.format("%s[%s]", soft.getId(), tgtValueAttr);
        if (attrs.hasAttribute(node.getIdentifier(), name)) {
            int i = 1;
            do {
                name = String.format("%s[%s-%d]", soft.getId(), tgtValueAttr, i);
                i = i+1;
            } while (attrs.hasAttribute(node.getIdentifier(), name));
        }
        return name;
    }

    public void viewSOFT(SOFT gpl) {
        this.gpl = gpl;
        if (gpl != null) {
            importGPLBtn.setText("View GPL Data");
            importGPLBtn.setToolTipText("Opens a dialog to view GPL data");
            DefaultComboBoxModel model = (DefaultComboBoxModel) this.targetKeyAttrCombo.getModel();
            for (String key : gpl.getDataTables().getFirst().getHeaders().keySet()) {
                model.addElement(key);
            }
            this.pack();
        } else {
            JOptionPane.showMessageDialog(this, "Import GPL failed! Please look "
                    + "at the cytoscape log for deatils and try again. ",
                    "GPL Import Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void closeView(SOFT soft) {
        // do nothing
    }
}
