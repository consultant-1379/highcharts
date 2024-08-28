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
public class SeriesPlotOptions extends AbstractPlotOptions {

    public SeriesPlotOptions() {
        super("series");
    }

    public void setPointPadding(final double padding) {
        properties.put("pointPadding", padding);
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
