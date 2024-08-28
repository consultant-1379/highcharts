/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options.axis;

import static com.ericsson.eniq.events.highcharts.client.ChartConstants.*;

import com.ericsson.eniq.events.highcharts.client.ChartEnums;
import com.ericsson.eniq.events.highcharts.client.options.BaseOptions;
import com.ericsson.eniq.events.highcharts.client.options.CSSStyleOptions;

/**
 * @author ecarsea 
 * @since 2011
 * See http://www.highcharts.com/ref/#xAxis-plotBands-label 
 */
public class AxisPlotBandLabelOptions extends BaseOptions {

    private static final String DEFAULT_FONT_SIZE = "10px";

    private static final String DEFAULT_FONT_WEIGHT = "normal";

    public AxisPlotBandLabelOptions() {
        super("labels");
        setDefaults(); //NOPMD
    }

    private void setDefaults() {
        final CSSStyleOptions style = new CSSStyleOptions();
        style.setColor(DEFAULT_TEXT_COLOR);
        style.setFontSize(DEFAULT_FONT_SIZE);
        style.setFontFamily(DEFAULT_FONT_FAMILY);
        style.setFontWeight(DEFAULT_FONT_WEIGHT);
        setStyleOptions(style);
    }

    /**
     * @param alignment
     */
    public void setAlign(final ChartEnums.eAlign alignment) {
        properties.put("align", alignment.toString());
    }

    /**
     * @param alignment
     */
    public void setVerticalAlign(final ChartEnums.eVerticalAlign alignment) {
        properties.put("verticalAlign", alignment.toString());
    }

    public void setTextAlign(final ChartEnums.eAlign alignment) {
        properties.put("textAlign", alignment.toString());
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
    public void setStyleOptions(final CSSStyleOptions value) {
        properties.put("style", value);
    }

    /**
     * @param value
     */
    public void setX(final int value) {
        properties.put("x", value);
    }

    /**
     * @param value
     */
    public void setY(final int value) {
        properties.put("y", value);
    }

    /**
     * @param value
     */
    public void setText(final String value) {
        properties.put("text", value);
    }
}
