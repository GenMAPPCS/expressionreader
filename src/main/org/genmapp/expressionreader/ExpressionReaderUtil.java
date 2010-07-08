package org.genmapp.expressionreader;

import org.genmapp.expressionreader.data.SOFT;
import org.genmapp.expressionreader.parser.SOFTParser;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author djiao
 */
public class ExpressionReaderUtil {

    public static final String GEO_URL = "http://www.ncbi.nlm.nih.gov/projects/geo/query/acc.cgi?acc=%s&targ=self&form=%s&view=%s";
    public static final String GDS_FTP = "ftp://ftp.ncbi.nih.gov/pub/geo/DATA/SOFT/GDS/%s.soft.gz";
    public static final String GSE_FTP = "ftp://ftp.ncbi.nih.gov/pub/geo/DATA/SOFT/by_series/%s_family.soft.gz";

    public static boolean downloadSOFTGZ(String urlStr, File file) {
        final int BUFFER = 2048;
        BufferedInputStream bis = null;
        BufferedOutputStream dest = null;
        GZIPInputStream zis = null;
        System.out.println(urlStr);
        try {
            URL url = new URL(urlStr);
            URLConnection urlc = url.openConnection();
            zis = new GZIPInputStream(urlc.getInputStream());
            int count;
            byte data[] = new byte[BUFFER];
            // write the files to the disk
            FileOutputStream fos = new FileOutputStream(file);
            dest = new BufferedOutputStream(fos, BUFFER);
            while ((count = zis.read(data, 0, BUFFER)) != -1) {
               dest.write(data, 0, count);
            }
            dest.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException ioe) {
                }
            }
            if (dest != null) {
                try {
                    dest.close();
                } catch (IOException ioe) {
                }
            }
            if (zis != null) {
                try {
                    zis.close();
                } catch (IOException ioe) {
                }
            }
        }
        return true;
    }

    public static boolean downloadURL(String urlStr, File file) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            URL url = new URL(urlStr);
            URLConnection urlc = url.openConnection();

            bis = new BufferedInputStream(urlc.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(file));

            int i;
            while ((i = bis.read()) != -1) {
                bos.write(i);
            }
        } catch (IOException ex) {
            Logger.getLogger(ExpressionReaderUtil.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException ioe) {
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException ioe) {
                }
            }
        }
        return true;
    }

    /**
     * Opens the specified web page in the user's default browser
     * @param url A web address (URL) of a web page (ex: "http://www.google.com/")
     */
    public static void openURL(String url) {
        try {  //attempt to use Desktop library from JDK 1.6+
            Class<?> d = Class.forName("java.awt.Desktop");
            d.getDeclaredMethod("browse", new Class[]{java.net.URI.class}).invoke(
                    d.getDeclaredMethod("getDesktop").invoke(null),
                    new Object[]{java.net.URI.create(url)});
            //above code mimicks:  java.awt.Desktop.getDesktop().browse()
        } catch (Exception ignore) {  //library not available or failed
            String osName = System.getProperty("os.name");
            try {
                if (osName.startsWith("Mac OS")) {
                    Class.forName("com.apple.eio.FileManager").getDeclaredMethod(
                            "openURL", new Class[]{String.class}).invoke(null,
                            new Object[]{url});
                } else if (osName.startsWith("Windows")) {
                    Runtime.getRuntime().exec(
                            "rundll32 url.dll,FileProtocolHandler " + url);
                } else { //assume Unix or Linux
                    String browser = null;
                    final String[] browsers = {"google-chrome", "firefox", "opera",
                        "epiphany", "konqueror", "conkeror", "midori", "kazehakase", "mozilla"};
                    for (String b : browsers) {
                        if (browser == null && Runtime.getRuntime().exec(new String[]{"which", b}).getInputStream().read() != -1) {
                            Runtime.getRuntime().exec(new String[]{browser = b, url});
                        }
                    }
                    if (browser == null) {
                        throw new Exception(Arrays.toString(browsers));
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error attempting to launch web browser" + "\n" + e.toString());
            }
        }
    }

    public static String join(List<? extends CharSequence> s, String delimiter) {
	int capacity = 0;
	int delimLength = delimiter.length();
	Iterator<? extends CharSequence> iter = s.iterator();
	if (iter.hasNext()) {
	    capacity += iter.next().length() + delimLength;
	}

	StringBuilder buffer = new StringBuilder(capacity);
	iter = s.iterator();
	if (iter.hasNext()) {
	    buffer.append(iter.next());
	    while (iter.hasNext()) {
		buffer.append(delimiter);
		buffer.append(iter.next());
	    }
	}
	return buffer.toString();
    }

    public static String getSoftNameLblText(SOFT soft) {
        if (soft.getType() == SOFT.Type.GSM) {
            return "Sample: " + soft.getId();
        } else if (soft.getType() == SOFT.Type.GPL) {
            return "Platform: " + soft.getId();
        } else if (soft.getType() == SOFT.Type.GSE) {
            return "Series: " + soft.getId();
        } else if (soft.getType() == SOFT.Type.GDS) {
            return "Dataset: " + soft.getId();
        }
        return soft.getId();
    }


    public static SOFT.Type getType(String geoId) {
        if (geoId.startsWith("GSM"))
            return SOFT.Type.GSM;
        else if (geoId.startsWith("GSE"))
            return SOFT.Type.GSE;
        else if (geoId.startsWith("GPL"))
            return SOFT.Type.GPL;
        else if (geoId.startsWith("GDS"))
            return SOFT.Type.GDS;
        else
            throw new UnsupportedOperationException("geo type is not supported");

    }

    public static synchronized SOFT getSOFT(String geoId, SOFT.Type type, SOFT.Format format) throws ParseException, IOException {
        String tmpDir = System.getProperty("java.io.tmpdir");
        if (type == SOFT.Type.GDS) { // GDS ftp only
            File tmpFile = new File(tmpDir, geoId + ".soft");
            boolean result = true;
            if (!tmpFile.exists()) {
                result = downloadSOFTGZ(String.format(GDS_FTP, geoId), tmpFile);
            }
            InputStream in = null;
            SOFT soft = null;
            try {
                if (result) {
                    in = new FileInputStream(tmpFile);
                } else {
                    // Download failed somehow, maybe not saved to file
                    URL url = new URL(String.format(GDS_FTP, geoId));
                    in = new GZIPInputStream(url.openConnection().getInputStream());
                }
                soft = new SOFTParser().parseSOFT(in, type, format);
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException ex) {
                    }
                }
            }
            return soft;
        } else if (type == SOFT.Type.GSE && format == SOFT.Format.family) {
            File tmpFile = new File(tmpDir, geoId + "_family.soft");
            boolean result = true;
            if (!tmpFile.exists()) {
                result = downloadSOFTGZ(String.format(GSE_FTP, geoId), tmpFile);
            }
            InputStream in = null;
            SOFT soft = null;
            try {
                if (result) {
                    in = new FileInputStream(tmpFile);
                } else {
                    // Download failed somehow, maybe not saved to file
                    URL url = new URL(String.format(GSE_FTP, geoId));
                    in = new GZIPInputStream(url.openConnection().getInputStream());
                }
                soft = new SOFTParser().parseSOFT(in, type, format);
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException ex) {
                    }
                }
            }
            return soft;
        } else {
            File tmpFile = new File(tmpDir, geoId + '-' + format + ".txt");
            boolean result = true;
            if (!tmpFile.exists()) {
                result = downloadURL(String.format(GEO_URL, geoId, "text", format), tmpFile);
            }
            InputStream in = null;
            SOFT soft = null;
            try {
                if (result) {
                    in = new FileInputStream(tmpFile);
                } else {
                    // Download failed somehow, maybe not saved to file
                    URL url = new URL(String.format(GEO_URL, geoId, "text", format));
                    in = url.openConnection().getInputStream();
                }
                soft = new SOFTParser().parseSOFT(in, type, format);
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException ex) {
                    }
                }
            }
            return soft;
        }
    }
}
