/*******************************************************************************
 * Copyright 2010 Alexander Pico
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader.tasks;

import cytoscape.CyNetwork;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import cytoscape.data.CyAttributes;
import cytoscape.task.ui.JTaskConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import org.genmapp.expressionreader.geo.data.DataTable;
import org.genmapp.expressionreader.geo.data.GDS;

/**
 *
 * @author djiao
 */
public class GDSDataImportTask extends AbstractTask {
    private GDS gds;
    private String nodeAttr = null;

    public void setNodeAttr(String nodeAttr) {
        this.nodeAttr = nodeAttr;
    }

    public GDSDataImportTask(String networkKeyAttr, GDS gds) {
        this.nodeAttr = networkKeyAttr;
        this.gds = gds;
    }

    @Override
    public JTaskConfig getDefaultTaskConfig() {
        JTaskConfig config = new JTaskConfig();

        config.setOwner(null);
        config.displayCancelButton(true);
        config.displayCloseButton(true);
        config.displayStatus(true);
        config.setAutoDispose(false);

        return config;
    }

    public void run() {
        CyNetwork network = Cytoscape.getCurrentNetwork();
        List<CyNode> nodes = (List<CyNode>) network.nodesList();
        CyAttributes cyattrs = Cytoscape.getNodeAttributes();

        if (taskMonitor != null) {
            taskMonitor.setStatus("Preparing for importing Data into Network " + network.getTitle());
        }

        DataTable dt = gds.getDataTables().getFirst(); // GDS should only have one datatable

        if (taskMonitor != null) {
            taskMonitor.setStatus("Importing Data into Network");
        }

        List<String> headers = new ArrayList<String>(dt.getHeaders().keySet());

        int totalAdded = 0;

        for (int j = 0; j < nodes.size(); j++) {
            CyNode node = nodes.get(j);
            String nid = node.getIdentifier();

            if ("ID".equals(nodeAttr)) {
                String attrValue = node.getIdentifier();
                if (dt.getData().containsKey(attrValue)) {
                    List row = dt.getData().get(attrValue);
                    for (int i = 0; i < row.size() -2; i++) {
                        cyattrs.setAttribute(nid, headers.get(i + 2) + "[VALUE]", (String) row.get(i + 2));
                    }
                }
            } else {
                switch (cyattrs.getType(nodeAttr)) {
                    case CyAttributes.TYPE_SIMPLE_LIST:
                        List list = cyattrs.getListAttribute(nid, nodeAttr);
                        Set<String> keys = dt.getData().keySet();
                        for (String key : keys) {
                            if (list.contains(key)) {
                                List row = dt.getData().get(key);
                                for (int i = 0; i < row.size() - 2; i++) {
                                    cyattrs.setAttribute(nid, headers.get(i + 2) + "[VALUE]", (String) row.get(i + 2));
                                }
                                totalAdded++;
                                break;
                            }
                        }
                        break;
                    case CyAttributes.TYPE_STRING:
                        String attrValue = cyattrs.getStringAttribute(nid, nodeAttr);
                        if (dt.getData().containsKey(attrValue)) {
                            List row = dt.getData().get(attrValue);
                            for (int i = 0; i < row.size() - 2; i++) {
                                cyattrs.setAttribute(nid, headers.get(i + 2) + "[VALUE]", (String) row.get(i + 2));
                            }
                            totalAdded++;
                        }
                        break;
                    default:
                        JOptionPane.showConfirmDialog(null, "Can't import. Only support LIST and STRING attribute type. Wrong attribute type: " + nodeAttr,
                                "Error", JOptionPane.OK_OPTION);
                    }
            }
        }
        if (taskMonitor != null) {
            StringBuilder status = new StringBuilder();
            status.append("Import complete. Imported ");
            status.append(headers.size()-2);
            status.append(" samples.\n Summary: Network total number of nodes: ");
            status.append(nodes.size());
            status.append(".\n Imported attributes to ");
            status.append(totalAdded);
            status.append(" nodes");

            taskMonitor.setStatus(status.toString());
        }
    }

    public String getTitle() {
        return "Import GDS Data";
    }

}
