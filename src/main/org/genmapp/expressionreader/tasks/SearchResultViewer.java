/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader.tasks;

import gov.nih.nlm.ncbi.soap.eutils.esummary.DocSumType;
import java.util.List;

/**
 *
 * @author djiao
 */
public interface SearchResultViewer {
    public void viewResults(int total, List<DocSumType> list);
}
