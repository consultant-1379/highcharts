/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options;

import com.ericsson.eniq.events.highcharts.client.ChartEnums;
import com.ericsson.eniq.events.highcharts.client.options.chart.ChartEvents;

/**
 * Chart Options
 * @author eendmcm
 * @author ecarsea
 * @since 2011
 * see http://www.highcharts.com/ref/#chart
 */
public class ChartOptions extends BaseOptions {
    private final static String CHART_BACKGROUND_COLOR = "#ffffff";// "#eeffee"; // it would be nicer if CSS (light green)

    /**
     * @param containerDiv - HTML Id of DOM element container of the chart
     */
    public ChartOptions(final String containerDiv) {
        super("chart");
        setDefaults(containerDiv); //NOPMD
    }

    private void setDefaults(final String containerDiv) {
        setRenderTo(containerDiv);
        setBackgroundColor(CHART_BACKGROUND_COLOR);
        /** Disable overall chart animation. Initial Series Loading animation will still be enabled, but zooming
         * will not now animate
         */
        setAnimationEnabled(false);
    }

    /**
     * @param enabled
     */
    private void setAnimationEnabled(final boolean enabled) {
        properties.put("animation", enabled);
    }

    /**
     * @param value
     */
    public void setSeriesType(final ChartEnums.eSeriesType value) {
        properties.put("defaultSeriesType", value.toString());
    }

    /**
     * @param value
     */
    public void setZoomType(final ChartEnums.eZoomType value) {
        properties.put("zoomType", value.toString());
    }

    /**
     * @param value
     */
    public void setRenderTo(final String value) {
        properties.put("renderTo", value);
    }

    /**
     * @param events
     */
    public void setEvents(final ChartEvents events) {
        properties.put("events", events);
    }

    /**
     * @param color
     */
    public void setBackgroundColor(final String color) {
        properties.put("backgroundColor", color);
    }

    /**
     * @param chartWidth
     */
    public void setWidth(final int chartWidth) {
        properties.put("width", chartWidth);
    }

    /**
     * @param chartHeight
     */
    public void setHeight(final int chartHeight) {
        properties.put("height", chartHeight);
    }

    public void setAlignTicks(final boolean alignTicks) {
        properties.put("alignTicks", alignTicks);

    }

    public void setIgnoreHiddenSeries(boolean ignoreHiddenSeries) {
        properties.put("ignoreHiddenSeries", ignoreHiddenSeries);

    }
}
