/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options;

import static com.ericsson.eniq.events.highcharts.client.ChartConstants.*;

/**
 * CSS Style Options for HighCharts elements
 * @author eendmcm
 * @since 2011
 */
public class CSSStyleOptions extends BaseOptions {

    public CSSStyleOptions() {
        super("style");
        setDefaults(); // NOPMD by eeicmsy on 09/11/11 17:36
    }

    public void setDefaults() {
        setFontFamily(DEFAULT_FONT_FAMILY);
    }

    /**
     * @param fontFamily
     */
    public void setFontFamily(final String fontFamily) {
        properties.put("fontFamily", fontFamily);
    }

    /**
     * @param value
     */
    public void setColor(final String value) {
        properties.put("color", value);
    }

    /**
     * @param size
     */
    public void setFontSize(final String size) {
        properties.put("fontSize", size);
    }

    /**
     * @param weight
     */
    public void setFontWeight(final String weight) {
        properties.put("fontWeight", weight);
    }
}
