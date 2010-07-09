/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader.tasks;

import java.util.List;
import org.genmapp.expressionreader.data.SOFT;

/**
 *
 * @author djiao
 */
public interface SOFTViewer {
    public void viewSOFT(List<SOFT> soft);
    public void closeView(SOFT soft);
}
