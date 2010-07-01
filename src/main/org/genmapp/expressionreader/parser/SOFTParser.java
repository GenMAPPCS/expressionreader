package org.genmapp.expressionreader.parser;

import org.genmapp.expressionreader.data.DataTable;
import org.genmapp.expressionreader.data.GSE;
import org.genmapp.expressionreader.data.SOFT;
import org.genmapp.expressionreader.data.SOFT.Type;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SOFTParser {

    public static final String SAMPLE = "SAMPLE";
    public static final String PLATFORM = "PLATFORM";
    private static final String ID_PATTERN = "\\^(.*)\\s=\\s(.*)";
    private static final String FIELD_PATTERN = "\\!(.*)\\s=\\s(.*)";
    private static final String TABLE_HEADER_PATTERN = "\\#(.*)\\s=\\s(.*)";
    private static final String TABLE_PATTERN = "\\!(.*)_table_begin(\\s=\\s(.*))?";
    private String currentId = null;
    private String currentType = null;
    private int lineNumber = 0;

    private SOFT parseSOFTSection(BufferedReader in) throws IOException, ParseException {
        SOFT soft = new SOFT(currentId);
        Pattern fieldPattern = Pattern.compile(FIELD_PATTERN);
        Pattern tableheaderPattern = Pattern.compile(TABLE_HEADER_PATTERN);
        Pattern idPattern = Pattern.compile(ID_PATTERN);
        Pattern tablePattern = Pattern.compile(TABLE_PATTERN);

        LinkedList<DataTable> tables = new LinkedList<DataTable>();
        LinkedHashMap<String, String> header = new LinkedHashMap<String, String>();
        LinkedHashMap<String, List> data = new LinkedHashMap<String, List>();

        DataTable table = null;

        String line = null;
        LinkedHashMap<String, Object> fields = new LinkedHashMap<String, Object>();
        boolean inTable = false;
        while ((line = in.readLine()) != null) {
            lineNumber++;
            Matcher m = idPattern.matcher(line); // end parsing this section
            if (m.matches()) {
                currentType = m.group(1);
                currentId = m.group(2);
                break;
            }

            m = tableheaderPattern.matcher(line);
            if (m.matches()) {
                header.put(m.group(1), m.group(2));
                continue;
            }

            m = tablePattern.matcher(line); // table starting line
            if (m.matches()) {

                inTable = true;
                in.readLine(); // consumes the header line
                lineNumber++;
                table = new DataTable(m.group(3));
                continue;
            }

            if (inTable) {
                if (line.indexOf("_table_end") > 0) { // end of table
                    inTable = false;
                    table.setData(data);
                    table.setHeaders(header);
                    tables.add(table);

                    header = new LinkedHashMap<String, String>();
                    data = new LinkedHashMap<String, List>();
                } else {
                    List<String> row = Arrays.asList(line.split("\t", -1)); // interesting, see http://stackoverflow.com/questions/545957/java-split-method-strips-empty-strings-at-the-end
                    data.put(row.get(0), row);
                }
                continue;
            }

            m = fieldPattern.matcher(line);  // NOTE! This must be put after checking table header
            if (m.matches()) {  // in a field
                String field = m.group(1);
                String value = m.group(2);
                if (fields.containsKey(field)) {
                    Object obj = fields.get(field);
                    List<String> list = null;
                    if (obj instanceof String) {
                        list = new ArrayList<String>();
                        list.add((String) obj);
                        list.add(value);
                    } else {
                        list = (List<String>) obj;
                        list.add(value);
                    }
                    fields.put(field, list);
                } else {
                    fields.put(field, value);
                }
                continue;
            }

            throw new ParseException("Failed to parse line (" + lineNumber + "): " + line, lineNumber);
        }
        soft.setFields(fields);
        soft.setDataTables(tables);
        return soft;
    }

    public SOFT parseSOFT(InputStream in, SOFT.Type type) throws ParseException, IOException {
        return parseSOFT(in, type, SOFT.Format.full);
    }

    public SOFT parseSOFT(Reader in, SOFT.Type type) throws ParseException {
        return parseSOFT(in, type, SOFT.Format.full);
    }

    public SOFT parseSOFT(InputStream in, SOFT.Type type, SOFT.Format format) throws ParseException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        return parseSOFT(reader, type, format);

    }

    public SOFT parseSOFT(Reader in, SOFT.Type type, SOFT.Format format) throws ParseException {
        SOFT soft = null;
        switch (format) {
            case quick:
                soft = parseQuickSOFT(in, type);
                break;
            case full:
                soft = parseQuickSOFT(in, type);
                break;
            default:
                throw new UnsupportedOperationException("Can't parse format: " + format.name());
        }
        if (soft != null) {
            soft.setFormat(format);
            soft.setType(type);
        }
        return soft;
    }

    private SOFT parseGSEFamilySOFT(Reader in, SOFT.Type type) throws ParseException {
        if (type == SOFT.Type.GPL || type == SOFT.Type.GSM) {
            return parseQuickSOFT(in, type);
        } else if (type == SOFT.Type.GSE) {
            GSE gse = null;
            BufferedReader reader = null;
            try {
                reader = in instanceof BufferedReader ? (BufferedReader) in : new BufferedReader(in);
                Pattern idPattern = Pattern.compile(ID_PATTERN);
                String line = reader.readLine();
                lineNumber++;
                Matcher m = idPattern.matcher(line);
                if (m.matches()) {
                    this.currentId = m.group(2);
                    this.currentType = m.group(1);

                    if (!getTypeHeader(type).equals(currentType)) {
                        throw new ParseException("Wrong type: " + currentType + ", where expecting " + getTypeHeader(type), 0);
                    }
                    SOFT soft = parseSOFTSection(reader);
                    gse = new GSE(soft);
                    List<SOFT> platforms = new ArrayList<SOFT>();
                    List<SOFT> samples = new ArrayList<SOFT>();
                    while ((soft = parseSOFTSection(reader)) != null) {
                        if (soft.getType() == SOFT.Type.GPL) {
                            platforms.add(soft);
                        } else if (soft.getType() == SOFT.Type.GSM) {
                            samples.add(soft);
                        } else {
                            throw new ParseException("Wrong type found: " + soft.getType(), lineNumber);
                        }
                    }
                    gse.setPlatforms(platforms);
                    gse.setSamples(samples);
                } else {
                    throw new ParseException("Invalid first line", 0);
                }
            } catch (IOException ex) {
                Logger.getLogger(SOFTParser.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException ioex) {
                    }
                }
            }
            return gse;
        } else {
            throw new UnsupportedOperationException("Only supports GPL, GSM and GSE parsing");
        }

    }

    private SOFT parseQuickSOFT(Reader in, Type type) throws ParseException {
        SOFT soft = null;
        BufferedReader reader = in instanceof BufferedReader ? (BufferedReader) in : new BufferedReader(in);
        try {
            Pattern idPattern = Pattern.compile(ID_PATTERN);
            String line = reader.readLine();
            lineNumber++;
            Matcher m = idPattern.matcher(line);
            if (m.matches()) {
                this.currentId = m.group(2);
                this.currentType = m.group(1);
                if (!getTypeHeader(type).equals(currentType)) {
                    throw new ParseException("Wrong type: " + currentType + ", where expecting " + getTypeHeader(type), 0);
                }
                soft = (SOFT) parseSOFTSection(reader);
            } else {
                throw new ParseException("Invalid first line", 0);
            }
        } catch (IOException ex) {
            Logger.getLogger(SOFTParser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ioex) {
                }
            }
        }

        return soft;
    }

    public static String getTypeHeader(SOFT.Type type) {
        switch (type) {
            case GPL:
                return "PLATFORM";
            case GSM:
                return "SAMPLE";
            case GSE:
                return "SERIES";
            case GDS:
                return "DATASET";
            default:
                return "";
        }
    }
}
