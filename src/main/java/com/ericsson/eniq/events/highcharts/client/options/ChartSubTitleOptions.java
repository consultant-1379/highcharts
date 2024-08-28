/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options;

import static com.ericsson.eniq.events.highcharts.client.ChartConstants.*;

/**
 * Chart Sub Title options
 * @author eendmcm
 * @since 2011
 *
 */
public class ChartSubTitleOptions extends AbstractTitleOptions {

    private static final String DEFAULT_FONT_SIZE = "10px";

    private static final String DEFAULT_FONT_WEIGHT = "normal";

    public ChartSubTitleOptions() {
        super("subtitle");
        setDefaults();
    }

    /**
     * 
     */
    private void setDefaults() {
        final CSSStyleOptions styleOptions = new CSSStyleOptions();
        styleOptions.setColor(DEFAULT_TEXT_COLOR);
        styleOptions.setFontSize(DEFAULT_FONT_SIZE);
        styleOptions.setFontWeight(DEFAULT_FONT_WEIGHT);
        setStyleOptions(styleOptions);
    }
}
