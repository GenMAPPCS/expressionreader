/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader.data;

import java.util.List;

/**
 *
 * @author djiao
 */
public class GDS extends SOFT {

    private List<SOFT> subsets;
    private SOFT database;

    public SOFT getDatabase() {
        return database;
    }

    public void setDatabase(SOFT database) {
        this.database = database;
    }

    public List<SOFT> getSubsets() {
        return subsets;
    }

    public void setSubsets(List<SOFT> subsets) {
        this.subsets = subsets;
    }

    public GDS(SOFT soft) {
        super();
        this.setFields(soft.getFields());
        this.setType(Type.GSE);
        this.setId(soft.getId());
        this.setDataTables(soft.getDataTables());
    }

    
}
