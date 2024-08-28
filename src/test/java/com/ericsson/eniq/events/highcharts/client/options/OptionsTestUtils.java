/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options;

/**
 * @author ecarsea
 * @since 2011
 *
 */
public class OptionsTestUtils {
    private final static int LINE_LENGTH = 100;

    private OptionsTestUtils() {
    }

    public static void getLineLengthFormattedJS(final String js) {
        for (int i = 0; i <= js.length(); i += LINE_LENGTH) {
            System.out.println("\"" + js.substring(i, Math.min((i + LINE_LENGTH), js.length())) + "\""
                    + (((i + LINE_LENGTH) <= js.length()) ? "+" : ""));
        }
    }
}
