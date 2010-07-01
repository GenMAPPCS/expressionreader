/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader.data;

import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author djiao
 */
public class DataTable {
    private String title;
    private LinkedHashMap<String, String> headers;
    private LinkedHashMap<String, List> data;

    public DataTable(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LinkedHashMap<String, List> getData() {
        return data;
    }

    public void setData(LinkedHashMap<String, List> data) {
        this.data = data;
    }

    public LinkedHashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(LinkedHashMap<String, String> headers) {
        this.headers = headers;
    }    
}
