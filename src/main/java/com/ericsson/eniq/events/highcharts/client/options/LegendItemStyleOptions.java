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
public class LegendItemStyleOptions extends BaseOptions {

    private static final String DEFAULT_FONT_SIZE = "11px";

    public LegendItemStyleOptions() {
        super("itemStyle");
        setDefaults(); // NOPMD by eeicmsy on 09/11/11 17:36
    }

    private void setDefaults() {
        setFontSize(DEFAULT_FONT_SIZE);
        setFontFamily(DEFAULT_FONT_FAMILY);
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

    public void setFontFamily(final String fontFamily) {
        properties.put("fontFamily", fontFamily);
    }

    /**
     * default|pointer etc...
     * @param cursorType
     */
    public void setCursor(final String cursorType){
        properties.put("cursor", cursorType);
    }
}
