/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options.axis;

import com.ericsson.eniq.events.highcharts.client.options.BaseOptions;

/**
 * @author eendmcm
 * @author ecarsea
 * @since 2011
 * see http://www.highcharts.com/ref/#xAxis-plotBands
 */
public class AxisPlotLineOptions extends BaseOptions {

    public AxisPlotLineOptions() {
        super("plotBands");
    }

    /**
     * @param value
     */
    public void setColor(final String value) {
        properties.put("color", value);
    }

    /**
     * @param value
     */
    public void setValue(final double value) {
        properties.put("value", value);
    }

    /**
     * @param width
     */
    public void setWidth(final int width) {
        properties.put("width", width);
    }

    /**
     * @param value
     */
    public void setID(final String value) {
        properties.put("id", value);
    }

    /**
     * @param zIndex
     */
    public void setZIndex(final int zIndex) {
        properties.put("zIndex", zIndex);
    }
}
