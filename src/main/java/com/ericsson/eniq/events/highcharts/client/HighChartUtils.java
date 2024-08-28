/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client;

import static com.ericsson.eniq.events.highcharts.client.ChartConstants.*;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Random;

/**
 * @author eendmcm
 * @author ecarsea
 * @since 2011
 * 
 * Utility Methods for use within the HighCharts Wrapper
 */
public class HighChartUtils {

    /*
     * Private constr in place to satisfy PMD!
     */
    private HighChartUtils() {

    }

    /**
     * @param str
     * @return
     */
    public static boolean isNullOrEmpty(final String str) {
        return (str == null || str.isEmpty());
    }

    /**
     * removes the trailing seperator char(s) from the provided StringBuilder
     * @param fullStr
     * @return
     */
    public static StringBuilder removeTrailingSeperator(final StringBuilder fullStr) {

        return fullStr.delete(fullStr.length() - BASE_SEPERATOR.length(), fullStr.length());
    }

    /**
     * Removes redundant separators, spaces etc.
     * @return
     */
    public static final String cleanJScript(final StringBuilder js) {

        try {
            int from = -1;
            int to = 0;
            while (true) {
                //get a javascript block
                from = js.indexOf(BASE_SEPERATOR_TRIMMED, from + 1);
                to = js.indexOf(BLOCK_END, from);

                if (from < 0 || to < 0) {
                    break;
                }

                final String tmp = js.substring(from + 1, to);
                if (tmp != null && tmp.trim().length() == 0) {
                    js.replace(from, to, "");
                }
            }
        } catch (final Exception e) {
            return "Failed to Format the provided String to JavaScript: " + e.getMessage();
        }
        return js.toString();
    }

    /**
     * Escape the characters which may break the java script
     * @param value
     * @return
     */
    private static String escapeCharacters(final String value) {
        return value.replace("'", "\\'");
    }

    /**
     * Generate unique id for a chart
     * @param size
     * @return
     */
    public static final String generateID(final int size) {
        final StringBuilder sb = new StringBuilder(size);
        final String itoh = "0123456789abcdef";

        for (int i = 0; i < size; i++) {
            sb.append(itoh.charAt(Random.nextInt(itoh.length())));
        }

        return sb.toString();
    }

    /**
     * Retrieve a property from a JS Object
     * @param obj
     * @param property
     * @return
     */
    public static final native Object getProperty(JavaScriptObject obj, String property)
    /*-{
        return obj[property];
    }-*/;

    /**
     * Utility method for use in debugging
     * @param obj
     */
    public static final native void printObj(JavaScriptObject obj)
    /*-{
        var output = '';
        for (property in obj) {
            output += property + ': ' + obj[property] + '; ';
        }
        $wnd.alert(output);
    }-*/;

    /**
     * Utility method for use in debugging
     * @param obj
     */
    public static final native void printToConsole(JavaScriptObject obj)
    /*-{
        console.log(obj);
    }-*/;
}
