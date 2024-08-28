/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options;

import static com.ericsson.eniq.events.highcharts.client.ChartConstants.*;

/**
 * Chart Title Options
 * @author eendmcm
 * @author ecarsea
 * @since 2011
 *
 */
public class ChartTitleOptions extends AbstractTitleOptions {

    private static final String DEFAULT_FONT_SIZE = "12px";

    private static final String DEFAULT_FONT_WEIGHT = "normal";

    public ChartTitleOptions() {
        super("title");
        setDefaults();
    }

    /**
     * 
     */
    private void setDefaults() {
        final CSSStyleOptions styleOptions = new CSSStyleOptions();
        styleOptions.setColor(DEFAULT_TEXT_COLOR);
        styleOptions.setFontSize(DEFAULT_FONT_SIZE);
        styleOptions.setFontFamily(DEFAULT_FONT_FAMILY);
        styleOptions.setFontWeight(DEFAULT_FONT_WEIGHT);
        setStyleOptions(styleOptions);
    }

    /**
     * @param margin
     */
    public void setMargin(final int margin) {
        properties.put("margin", margin);
    }

    /**
     * @param y
     */
    public void setY(final int y) {
        properties.put("y", y);
    }
}
