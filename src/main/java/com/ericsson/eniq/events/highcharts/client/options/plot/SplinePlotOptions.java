package com.ericsson.eniq.events.highcharts.client.options.plot;

import com.ericsson.eniq.events.highcharts.client.options.series.MarkerOptions;
import com.ericsson.eniq.events.highcharts.client.options.series.PointEvents;

/**
 * 
 * @author ekurshi
 * @since 2012
 *
 */
public class SplinePlotOptions extends AbstractPlotOptions {
    public SplinePlotOptions() {
        super("spline");
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

    public void setLineWidth(final int width) {
        properties.put("lineWidth", width);
    }

    /**
     * @param stateOptions
     */
    public void setStateOptions(final StateOptions stateOptions) {
        properties.put("states", stateOptions);
    }

    /**
     * @param markerOptions
     */
    public void setMarkerOptions(final MarkerOptions markerOptions) {
        properties.put("marker", markerOptions);
    }

    /**
     * @param shadow
     */
    public void setShadow(final boolean shadow) {
        properties.put("shadow", shadow);
    }

    /**
     * @param events
     */
    public void setEvents(final PointEvents events) {
        properties.put("events", events);
    }
}
