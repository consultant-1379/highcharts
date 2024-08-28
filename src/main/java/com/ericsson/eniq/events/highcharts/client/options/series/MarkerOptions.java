/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options.series;

import com.ericsson.eniq.events.highcharts.client.options.BaseOptions;
import com.ericsson.eniq.events.highcharts.client.options.plot.StateOptions;

import static com.ericsson.eniq.events.highcharts.client.ChartConstants.DEFAULT_LINE_COLOR;

/**
 * Line Chart Marker Options
 * @author ecarsea
 * @author egallou
 * @since 2011
 */

public class MarkerOptions extends BaseOptions {

    private static final int DEFAULT_MARKER_RADIUS = 4;

    private static final String DEFAULT_SYMBOL = "diamond";

    public MarkerOptions() {
        super("marker");
        setDefaults(); //NOPMD
    }

    private void setDefaults() {
        setRadius(DEFAULT_MARKER_RADIUS);
        setSymbol(DEFAULT_SYMBOL);
        setLineColor(DEFAULT_LINE_COLOR);
    }

    public void setRadius(final double radius) {
        properties.put("radius", radius);
    }

    public void setSymbol(final String symbolType) {
        properties.put("symbol", symbolType);
    }

    public void setFillColor(final String fillColor) {
        properties.put("fillColor", fillColor);
    }

    public void setLineColor(final String lineColor) {
        properties.put("lineColor", lineColor);
    }

    public void setLineWidth(final int lineWidth) {
        properties.put("lineWidth", lineWidth);
    }

    public void setState(final StateOptions stateOptions) {
        properties.put("states", stateOptions);
    }

    public void setEnabled(final boolean enabled) {
        properties.put("enabled", enabled);
    }
}
