/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options.axis;

import com.ericsson.eniq.events.highcharts.client.ChartEnums.eAxisLabelAlign;
import com.ericsson.eniq.events.highcharts.client.options.BaseOptions;
import com.ericsson.eniq.events.highcharts.client.options.CSSStyleOptions;

import static com.ericsson.eniq.events.highcharts.client.ChartConstants.*;

/**
 * @author eendmcm
 * @author ecarsea
 * @since 2011
 * see http://www.highcharts.com/ref/#xAxis-title
 */
public class AxisTitleOptions extends BaseOptions {

    public static final String DEFAULT_COLOR = "#232323";

    public static final String DEFAULT_FONT_SIZE = "11px";

    public static final String DEFAULT_FONT_WEIGHT = "normal";

    public AxisTitleOptions() {
        super("title");
        setDefaults(); //NOPMD
    }

    private void setDefaults() {
        final CSSStyleOptions styleOptions = new CSSStyleOptions();
        styleOptions.setColor(DEFAULT_COLOR);
        styleOptions.setFontSize(DEFAULT_FONT_SIZE);
        styleOptions.setFontFamily(DEFAULT_FONT_FAMILY);
        styleOptions.setFontWeight(DEFAULT_FONT_WEIGHT);
        setStyleOptions(styleOptions);
    }

    /**
     * @param value
     */
    public void setMargin(final int value) {
        properties.put("margin", value);
    }

    /**
     * @param value
     */
    public void setRotation(final int value) {
        properties.put("rotation", value);
    }

    /**
     * @param value
     */
    public void setText(final String value) {
        properties.put("text", value);
    }

    /**
     * @param value
     */
    public void setAlign(final eAxisLabelAlign value) {
        properties.put("align", value.toString());
    }

    /**
     * @param obj
     */
    public void setStyleOptions(final CSSStyleOptions obj) {
        properties.put("style", obj);
    }

}
