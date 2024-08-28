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
public class AxisPlotBandsOptions extends BaseOptions {

    public AxisPlotBandsOptions() {
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
    public void setFrom(final double value) {
        properties.put("from", value);
    }

    /**
     * @param value
     */
    public void setID(final String value) {
        properties.put("id", value);
    }

    /**
     * @param options
     */
    public void setLabel(final AxisPlotBandLabelOptions options) {
        properties.put("label", options);
    }

    /**
     * @param value
     */
    public void setTo(final double value) {
        properties.put("to", value);
    }

    /**
     * @param value
     */
    public void setZIndex(final int value) {
        properties.put("zIndex", value);
    }
}
