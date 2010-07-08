/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader.tasks;

import gov.nih.nlm.ncbi.soap.eutils.EUtilsService;
import gov.nih.nlm.ncbi.soap.eutils.EUtilsServiceSoap;
import gov.nih.nlm.ncbi.soap.eutils.esearch.ESearchRequest;
import gov.nih.nlm.ncbi.soap.eutils.esearch.ESearchResult;

/**
 *
 * @author djiao
 */
public class GEOSearchTask extends AbstractTask {
    private ESearchRequest query;

    public GEOSearchTask(ESearchRequest query) {
        super();
        this.query = query;
    }

    public void run() {
        EUtilsService service = new EUtilsService();
        EUtilsServiceSoap clientStub = service.getEUtilsServiceSoap();

//        final ESearchRequest searchParam = new ESearchRequest();
//        final String keyword = query;

//        searchParam.setDb("geo");
        //searchParam.setRetMax(props.getValue("max_search_result"));
        // keyword = keyword.replace(" ", "+");
//        searchParam.setTerm(keyword);

        ESearchResult result = clientStub.runESearch(query);
        //return result;


	long startTime = System.currentTimeMillis();
    }

    public String getTitle() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
