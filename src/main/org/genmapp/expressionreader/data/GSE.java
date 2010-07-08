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
public class GSE extends SOFT {
    private List<SOFT> platforms; 
    private List<SOFT> samples;
    private SOFT database;

    public GSE(SOFT soft) {
        super();
        this.setFields(soft.getFields());
        this.setType(Type.GSE);
        this.setId(soft.getId());
        this.setDataTables(soft.getDataTables());
    }

    public SOFT getDatabase() {
        return database;
    }

    public void setDatabase(SOFT database) {
        this.database = database;
    }

    public List<SOFT> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<SOFT> platforms) {
        this.platforms = platforms;
    }

    public List<SOFT> getSamples() {
        return samples;
    }

    public void setSamples(List<SOFT> samples) {
        this.samples = samples;
    }

    /**
     * Returns the type
     * @return
     */
    @Override
    public Type getType() {
        return Type.GSE;
    }


}
