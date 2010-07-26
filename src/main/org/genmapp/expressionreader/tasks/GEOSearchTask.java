/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader.tasks;

import gov.nih.nlm.ncbi.soap.eutils.EUtilsService;
import gov.nih.nlm.ncbi.soap.eutils.EUtilsServiceSoap;
import gov.nih.nlm.ncbi.soap.eutils.esearch.ESearchRequest;
import gov.nih.nlm.ncbi.soap.eutils.esearch.ESearchResult;
import gov.nih.nlm.ncbi.soap.eutils.esummary.DocSumType;
import gov.nih.nlm.ncbi.soap.eutils.esummary.ESummaryRequest;
import gov.nih.nlm.ncbi.soap.eutils.esummary.ESummaryResult;
import java.util.List;
import javax.swing.SwingUtilities;
import org.genmapp.expressionreader.geo.GEOQuery;

/**
 *
 * @author djiao
 */
public class GEOSearchTask extends AbstractTask {
    
    private ESearchRequest query = null;
    private SearchResultViewer viewer = null;

    public GEOSearchTask(ESearchRequest query, SearchResultViewer viewer) {
        super();
        this.query = query;
        this.viewer = viewer;
    }

    public void run() {
        EUtilsService service = new EUtilsService();
        EUtilsServiceSoap clientStub = service.getEUtilsServiceSoap();
        final ESearchResult result = clientStub.runESearch(query);
        final int total = Integer.parseInt(result.getCount());
        String ids = GEOQuery.join(result.getIdList().getId(), ",");

        ESummaryRequest req = new ESummaryRequest();
        req.setDb("gds");
        req.setId(ids);

        ESummaryResult res = clientStub.runESummary(req);
        final List<DocSumType> docsum = res.getDocSum();

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                viewer.viewResults(total, docsum);
            }
        });
    }

    public String getTitle() {
        return "Searching GEO";
    }

}
