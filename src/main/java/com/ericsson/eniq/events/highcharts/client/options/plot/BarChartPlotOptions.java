/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options.plot;

import com.ericsson.eniq.events.highcharts.client.options.series.PointEvents;

/**
 * @author ecarsea
 * @since 2011
 * 
 */
public class BarChartPlotOptions extends AbstractPlotOptions {

    public BarChartPlotOptions() {
        super("bar");
    }

    public void setBorderColors(final String color) {
        properties.put("borderColor", color);
    }

    public void setBorderWidth(final double borderWidth) {
        properties.put("borderWidth", borderWidth);
    }

    public void setDataLabelOptions(final DataLabelOptions options) {
        properties.put("dataLabels", options);
    }

    public void setPointWidth(final int width) {
        properties.put("pointWidth", width);

    }

    public void setNormalStacking() {
        properties.put("stacking", "normal");
    }

    /**
     * @param events
     */
    public void setEvents(final PointEvents events) {
        properties.put("events", events);
    }

}
