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
package org.genmapp.expressionreader.tasks;

import cytoscape.CyNetwork;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import cytoscape.cythesaurus.CyThesaurusServiceClient;
import cytoscape.cythesaurus.CyThesaurusServiceMessageBasedClient;
import cytoscape.data.CyAttributes;
import cytoscape.task.ui.JTaskConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.genmapp.expressionreader.geo.GEOQuery;
import org.genmapp.expressionreader.geo.data.DataTable;
import org.genmapp.expressionreader.geo.data.SOFT;

/**
 *
 * @author djiao
 */
public class GSMImportTask extends AbstractTask {

    /**
     * The network key attribute to map
     */
    private String networkKeyAttr;

    /**
     * List of GSM data
     */
    private List<SOFT> softList;

    /**
     * Optional platform data
     */
    private List<SOFT> gplList;

    /**
     * Index of the key in the imported data for mapping
     */
    private int importedKeyIndex;

    /**
     * Index of the value in the imported data
     */
    private int importedValueIndex;

    private String importedValueAttr;

    /**
     * Whether to use ID Mapping service. Default value is true
     */
    private boolean useIdMapping = true;

    /**
     * The type of the network key attribute. It is used by the CyThesaurus ID Mapping Service
     */
    private String networkKeyType;

    /**
     * The type of the Value to be the mapped to. It is used by the CyThesaurus ID Mapping Service
     */
    private String mappedIDType;

    private CyThesaurusServiceClient client = new CyThesaurusServiceMessageBasedClient();

    public void run() {
        CyNetwork network = Cytoscape.getCurrentNetwork();
        List<CyNode> nodes = (List<CyNode>) network.nodesList();
        CyAttributes cyattrs = Cytoscape.getNodeAttributes();
        if (taskMonitor != null) {
            taskMonitor.setStatus("Preparing for importing Data into Network " + network.getTitle());
        }

        for (SOFT soft : softList) {
            DataTable dt = soft.getDataTables().getFirst(); // GSM has only one datatable

            List<String> dataKeys = new ArrayList<String>(dt.getData().keySet()); // The dataKeys are the first column of the data table

            Map<String, List<String>> importedData = new HashMap<String, List<String>>();
            for (String key : dataKeys) {
                List<String> list = new ArrayList<String>();
                list.addAll(dt.getData().get(key));
                if (gplList != null) {  // If there are platform data, merge them to the data
                    for (SOFT gpl : gplList) {
                        list.addAll(gpl.getDataTables().getFirst().getData().get(key));
                    }
                }
                importedData.put(list.get(importedKeyIndex), list);
            }


            System.out.println("Ready to map IDs");
            Map<String, Set<String>> mappedIDs = null;
            if (useIdMapping) {
                mappedIDs = mapNetworkNodeIDs(network, this.networkKeyAttr, networkKeyType, mappedIDType);
                System.out.println(mappedIDs.keySet());
            }

            if (taskMonitor != null) {
                taskMonitor.setStatus("Importing Data into Network");
            }
            
            int totalAdded = 0;
            String attrName = getAttrName(cyattrs, nodes.get(0), importedValueAttr, soft);
            
            // Now map imported data to network nodes
            for (int i = 0; i < nodes.size(); i++) {
                CyNode node = nodes.get(i);
                String nodeKeyVal = "ID".equals(networkKeyAttr)?
                    node.getIdentifier() :
                    (String)cyattrs.getAttribute(node.getIdentifier(), networkKeyAttr);

                //if (idMapConfigBtn.isEnabled()) {
                if (useIdMapping) { // use IDs from the ID Mapping Service
                    Set<String> values = mappedIDs.get(nodeKeyVal); // Each ID can be mapped to multiple IDs

                    System.out.println(values);
                    if (values != null && !values.isEmpty()) {
                        Iterator<String> it = values.iterator();
                        List<String> attrValue = new ArrayList<String>();
                        while (it.hasNext()) {
                            String next = it.next();
                            if (importedData.containsKey(next)) {
                                attrValue.add(importedData.get(next).get(importedValueIndex));
                            }
                        }
                        if (!attrValue.isEmpty()) {
                            cyattrs.setAttribute(node.getIdentifier(), attrName, GEOQuery.join(attrValue, "; "));
                            totalAdded++;
                        }
                    }
                } else { // directly map 
                    if (importedData.containsKey(nodeKeyVal)) {
                        cyattrs.setAttribute(node.getIdentifier(), attrName, importedData.get(nodeKeyVal).get(importedValueIndex));
                        totalAdded++;
                    }
                }
            }
        }
        if (taskMonitor != null) {
            taskMonitor.setStatus(String.format("Importing complete"));
        }
    }

    public void setUseIdMapping(boolean useIdMapping) {
        this.useIdMapping = useIdMapping;
    }

    public void setClient(CyThesaurusServiceClient client) {
        this.client = client;
    }

    public GSMImportTask(String networkKeyAttr, List<SOFT> softList, List<SOFT> gplList, int importedKeyIndex, int importedValueIndex, String importedValueAttr, String networkKeyType, String mappedIDType) {
        this.networkKeyAttr = networkKeyAttr;
        this.softList = softList;
        this.gplList = gplList;
        this.importedKeyIndex = importedKeyIndex;
        this.importedValueIndex = importedValueIndex;
        this.importedValueAttr = importedValueAttr;
        this.networkKeyType = networkKeyType;
        this.mappedIDType = mappedIDType;
    }

    /**
     * Use the ID Mapping Service to map network attribute values
     * to a new list of IDs. 
     *
     * @param network Cytoscape network
     * @param networkKeyAttr The attribute of the network to find the ID mapped from
     * @param srcIdType Type of the network attribute
     * @param tgtIdType Type of the target ID
     * @return Mapped IDs
     * @throws IllegalThreadStateException
     * @throws NullPointerException
     */
    private Map<String, Set<String>> mapNetworkNodeIDs(CyNetwork network, String networkKeyAttr, String srcIdType, String tgtIdType) throws IllegalThreadStateException, NullPointerException {
        List<CyNode> nodes = (List<CyNode>) network.nodesList();
        CyAttributes cyattrs = Cytoscape.getNodeAttributes();
        
        // Get a list of all network node attribute values as IDs to map from.
        List<String> mapFrom = new ArrayList<String>();
        for (CyNode node : nodes) {
            if ("ID".equals(networkKeyAttr)) {
                mapFrom.add(node.getIdentifier());
            } else {
                mapFrom.add((String) cyattrs.getAttribute(node.getIdentifier(), networkKeyAttr));
            }
        }
        if (taskMonitor != null) {
            taskMonitor.setStatus("Mapping IDs");
        }

        Set<String> srcIDs = new HashSet<String>();
        srcIDs.addAll(mapFrom);
        // use ID Mapping to generate a list of mapped Ids.
        return client.mapID(srcIDs, srcIdType, tgtIdType);
    }

    public String getTitle() {
        return "Importing Gene Expression Data";
    }

    /**
     * Returns a new attribute name 
     *
     * @param attrs
     * @param node
     * @param tgtValueAttr
     * @param soft
     * @return
     */
    private String getAttrName(CyAttributes attrs, CyNode node, String tgtValueAttr, SOFT soft) {
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

    public JTaskConfig getDefaultTaskConfig() {
        JTaskConfig config = new JTaskConfig();

        config.displayCancelButton(true);
        config.displayCloseButton(true);
        config.displayStatus(true);
        config.displayTimeElapsed(false);
        config.setAutoDispose(true);
        config.setModal(false);

        return config;
    }
}
