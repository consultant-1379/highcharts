/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options;

import static com.ericsson.eniq.events.highcharts.client.ChartConstants.*;

import com.ericsson.eniq.events.highcharts.client.RawStringType;

/**
 * Tooltip Options
 * @author ecarsea
 * @since 2011
 *
 */
public class ToolTipOptions extends BaseOptions {

    private static final String DEFAULT_BORDER_COLOR = "#000000";

    private static final int DEFAULT_SNAP = 2;

    private static final String DEFAULT_FONT_SIZE = "10px";

    private static final String DEFAULT_FONT_WEIGHT = "normal";

    public ToolTipOptions() {
        super("tooltip");
        setDefaults(); //NOPMD
    }

    private void setDefaults() {
        setBorderColor(DEFAULT_BORDER_COLOR);
        setSnap(DEFAULT_SNAP);
        final CSSStyleOptions style = new CSSStyleOptions();
        style.setColor(DEFAULT_TEXT_COLOR);
        style.setFontSize(DEFAULT_FONT_SIZE);
        style.setFontFamily(DEFAULT_FONT_FAMILY);
        style.setFontWeight(DEFAULT_FONT_WEIGHT);
        setStyleOptions(style);
    }

    /**
     * @param snap
     */
    public void setSnap(final int snap) {
        properties.put("snap", snap);
    }

    /**
     * @param color
     */
    public void setBorderColor(final String color) {
        properties.put("borderColor", color);
    }

    /**
     * @param formatter
     */
    public void setFormatter(final RawStringType formatter) {
        properties.put("formatter", formatter);
    }

    /**
     * @param value
     */
    public void setStyleOptions(final CSSStyleOptions value) {
        properties.put("style", value);
    }
}
