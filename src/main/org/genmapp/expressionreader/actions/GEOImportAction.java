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

package org.genmapp.expressionreader.actions;

import cytoscape.CyEdge;
import cytoscape.CyNode;
import org.genmapp.expressionreader.geo.data.SOFT;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import cytoscape.Cytoscape;
import cytoscape.command.CyCommandException;
import cytoscape.command.CyCommandHandler;
import cytoscape.command.CyCommandResult;
import cytoscape.data.CyAttributes;
import cytoscape.layout.Tunable;
import cytoscape.logger.CyLogger;
import cytoscape.task.ui.JTaskConfig;
import cytoscape.task.util.TaskManager;
import cytoscape.util.CytoscapeAction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JDialog;
import org.genmapp.expressionreader.geo.GEOQuery;
import org.genmapp.expressionreader.geo.data.DataTable;
import org.genmapp.expressionreader.tasks.SOFTDownloadTask;
import org.genmapp.expressionreader.geo.ui.GDSViewerDialog;
import org.genmapp.expressionreader.geo.ui.GEOQueryUI;
import org.genmapp.expressionreader.geo.ui.GSEViewerDialog;
import org.genmapp.expressionreader.geo.ui.SOFTViewer;
import org.genmapp.expressionreader.ui.GEOSearchPane;
import org.genmapp.expressionreader.ui.GSMImportDialog;

public class GEOImportAction extends CytoscapeAction implements SOFTViewer, CyCommandHandler {

    private static final long serialVersionUID = 1128930960050800232L;

    static public CyLogger logger = CyLogger.getLogger(GEOImportAction.class);
    private static boolean USE_ID_MAPPING = false;

    public GEOImportAction() {
        super("GEO");
    }

    @Override
    public void actionPerformed(ActionEvent paramActionEvent) {
        String response = (String) JOptionPane.showInputDialog(Cytoscape.getDesktop(),
                "Please enter a GEO ID or a Search Term", "Query GEO",
                JOptionPane.PLAIN_MESSAGE, null, null, "GSM207569");

        if (response != null && !"".equals(response.trim())) {
            // Download file
            String id = response.trim();
            SOFT.Type type = GEOQuery.getType(id);
            if (type == null) {
                // Bring up the search interface
                JDialog dialog = new JDialog(Cytoscape.getDesktop(), false);
                dialog.setSize(800, 500);
                SOFTViewer viewer = new SOFTViewer() {

                    public void viewSOFT(List<SOFT> list) {
                        for (SOFT soft : list) {
                            if (soft.getType() == SOFT.Type.GSE) {
                                GSEViewerDialog dialog = new GSEViewerDialog(Cytoscape.getDesktop(), false);
                                dialog.setSOFT(soft);
                                dialog.setSize(600, 500);
                                dialog.setVisible(true);
                            } else if (soft.getType() == SOFT.Type.GDS) {
                                GDSViewerDialog dialog = new GDSViewerDialog(Cytoscape.getDesktop(), false);
                                dialog.setSOFT(soft);
                                dialog.setVisible(true);
                            } else if (soft.getType() == SOFT.Type.GPL) {
                                GEOQueryUI.showSOFTViewerDialog(Cytoscape.getDesktop(), false, soft);
                            } else {
                                throw new UnsupportedOperationException("Wrong SOFT type: " + soft.getType() + ", Should be GSE/GDS/GPL");
                            }
                        }
                    }
                };
                GEOSearchPane pane = new GEOSearchPane(viewer);
                dialog.setContentPane(pane);
                dialog.setVisible(true);
                pane.search(response);
            } else {
                SOFTDownloadTask task = null;
                if (type == SOFT.Type.GSM) {
                    task = new SOFTDownloadTask(new String[]{id}, this, SOFT.Format.full);
                } else {
                    task = new SOFTDownloadTask(new String[]{id}, this, SOFT.Format.quick);
                }
                JTaskConfig config = task.getDefaultTaskConfig();
                TaskManager.executeTask(task, config);
            }
        }
    }

    public void viewSOFT(List<SOFT> list) { // should expect only one back
        logger.debug("View SOFT: " + list.size());

        SOFT soft = list.get(0);
        if (soft.getType() == SOFT.Type.GSM) { // If GSM, import it
            if (this.USE_ID_MAPPING) {
                // create new network
                // create a new network, and bring up CyCommand
                CyAttributes cyattrs = Cytoscape.getNodeAttributes();
                for (SOFT s : list) {
                    DataTable dt = s.getDataTables().get(0);

                    Set<String> headers = dt.getHeaders().keySet();
                    Set<String> keys = dt.getData().keySet();
                    List<CyNode> nodes = new ArrayList<CyNode>();
                    for (String key : keys) {
                        CyNode node = Cytoscape.getCyNode(key, true);
                        int headerIndex = 0;
                        for (String header : headers) {
                            cyattrs.setAttribute(key, header, Double.valueOf((String) dt.getData().get(key).get(headerIndex)));
                            headerIndex++;
                        }
                        nodes.add(node);
                    }
                    Cytoscape.createNetwork(nodes, new ArrayList<CyEdge>(), "Imported " + s.getId());

                    // TODO: Brings up CyCommand
                }
            } else {
                GSMImportDialog dialog = new GSMImportDialog(Cytoscape.getDesktop(), false);
                dialog.setSOFTList(list);
                dialog.setVisible(true);
            }
        } else if (soft.getType() == SOFT.Type.GSE) {
            GSEViewerDialog dialog = new GSEViewerDialog(Cytoscape.getDesktop(), false);
            dialog.setSize(600, 500);
            dialog.setSOFT(soft);
            dialog.setVisible(true);
        } else if (soft.getType() == SOFT.Type.GDS) {
            GDSViewerDialog dialog = new GDSViewerDialog(Cytoscape.getDesktop(), false);
            dialog.setSOFT(soft);
            dialog.setVisible(true);
        } else if (soft.getType() == SOFT.Type.GPL) {
            GEOQueryUI.showSOFTViewerDialog(Cytoscape.getDesktop(), false, soft);
        }
    }


    public final static String COMMAND = "GEO Sample Import";

    public List<String> getCommands() {
        List<String> val = new ArrayList<String>();
        val.add(COMMAND);
        return val;
    }

    public List<String> getArguments(String command) {
        if (COMMAND.equals(command)) {
            List<String> args = new ArrayList<String>();
            args.add("useIdMapping");
            return args;
        }
        return null;
    }

    public Map<String, Object> getSettings(String string) {
        Map<String, Object> val = new HashMap<String, Object>();
        val.put("useIdMapping", false);
        return val;
    }

    public Map<String, Tunable> getTunables(String string) {
        Tunable t = new Tunable("useIdMapping", "Whether to use CyThesaurus ID Mapping Service", Tunable.BOOLEAN, false);
        Map<String, Tunable> map = new HashMap<String, Tunable>();
        map.put(t.getName(), t);
        return map;
    }

    public String getDescription(String string) {
        return "This CyCommandHandler sets the 'Create Network View' toggle for Gene Expression Data Reader";
    }

    public CyCommandResult execute(String command, Map<String, Object> map) throws CyCommandException {
        CyCommandResult result = new CyCommandResult();
        if (COMMAND.equals(command)) {
            Boolean val = (Boolean)map.get("useIdMapping");
            USE_ID_MAPPING = val;
            result.addMessage("useIdMapping sets to " + val);
        }
        return result;
    }

    public CyCommandResult execute(String string, Collection<Tunable> clctn) throws CyCommandException {
        Map<String, Object> kvSettings = new HashMap();
        for (Tunable t: clctn) {
            Object v = t.getValue();
            if (v != null)
                kvSettings.put(t.getName(), v.toString());
            else
                kvSettings.put(t.getName(), null);
        }
        return execute(string, kvSettings);
    }
}
