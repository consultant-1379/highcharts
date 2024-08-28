/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options;

import java.util.List;

import com.ericsson.eniq.events.highcharts.client.options.axis.AxisEventOptions;
import com.ericsson.eniq.events.highcharts.client.options.axis.AxisLabelOptions;
import com.ericsson.eniq.events.highcharts.client.options.axis.AxisPlotBandsOptions;
import com.ericsson.eniq.events.highcharts.client.options.axis.AxisPlotLineOptions;
import com.ericsson.eniq.events.highcharts.client.options.axis.AxisTitleOptions;

/**
 * Base Class for Axis Options
 * @author eendmcm
 * @author ecarsea
 * @since 2011
 * http://www.highcharts.com/ref/#xAxis or 
 * http://www.highcharts.com/ref/#yAxis 
 */
public abstract class AbstractAxisOptions extends BaseOptions {
    private final static String CHART_AXIS_COLOR = "#232323";

    private final static String CHART_GRID_COLOR = "#d3d3d3";

    public enum eTickMarkPlacement {
        BETWEEN("between"), ON("on");

        private String placement;

        eTickMarkPlacement(final String str) {
            this.placement = str;
        }

        /* (non-Javadoc)
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString() {
            return placement;
        }
    }

    private static final eTickMarkPlacement DEFAULT_TICKMARK_PLACEMENT = eTickMarkPlacement.ON;

    private static final int DEFAULT_TICK_WIDTH = 1;

    private static final String DEFAULT_TICK_COLOR = "#232323";

    /**
     * @param optionsObjectId - id of options sub class
     */
    public AbstractAxisOptions(final String optionsObjectId) {
        super(optionsObjectId);
        setDefaults(); //NOPMD
    }

    /**
     * Default Axis Options
     */
    private void setDefaults() {
        setTickMarkPlacement(DEFAULT_TICKMARK_PLACEMENT);
        setAxisGridLineColor(CHART_GRID_COLOR);
        setTickWidth(DEFAULT_TICK_WIDTH);
        setLineColor(CHART_AXIS_COLOR);
        setTickColor(DEFAULT_TICK_COLOR);
        setAllowDecimals(false);
    }

    /**
     * @param options
     */
    public void setLabelOptions(final AxisLabelOptions options) {
        properties.put("labels", options);
    }

    /**
     * @param min
     */
    public void setMin(final Object min) {
        properties.put("min", min);
    }

    /**
     * @param max
     */
    public void setMax(final double max) {
        properties.put("max", max);
    }

    /**
     * @param values
     */
    public void setCategories(final Object[] values) {
        properties.put("categories", values);
    }

    /**
     * @param options
     */
    public void setTitleOptions(final AxisTitleOptions options) {
        properties.put("title", options);
    }

    /**
     * @param plotBands
     */
    public void setPlotBands(final List<AxisPlotBandsOptions> plotBands) {
        properties.put("plotBands", plotBands);
    }

    /**
     * @param plotLines
     */
    public void setPlotLines(final List<AxisPlotLineOptions> plotLines) {
        properties.put("plotLines", plotLines);
    }

    /**
     * @param value
     */
    public void setGridLineWidth(final int value) {
        properties.put("gridLineWidth", value);
    }

    /**
     * @param value
     */
    public void setOpposite(final boolean value) {
        properties.put("opposite", value);
    }

    /**
     * @param placement
     */
    public void setTickMarkPlacement(final eTickMarkPlacement placement) {
        properties.put("tickmarkPlacement", placement.toString());
    }

    /**
     * @param b
     */
    public void setStartOnTick(final boolean b) {
        properties.put("startOnTick", b);
    }

    /**
     * @param color
     */
    public void setAxisGridLineColor(final String color) {
        properties.put("gridLineColor", color);
    }

    /**
     * @param color
     */
    public void setLineColor(final String color) {
        properties.put("lineColor", color);
    }

    /**
     * @param width
     */
    public void setLineWidth(final int width) {
        properties.put("lineWidth", width);
    }

    /**
     * @param tickInterval
     */
    public void setTickPixelInterval(final Integer tickInterval) {
        properties.put("tickPixelInterval", tickInterval);
    }

    /**
     * @param tickWidth
     */
    public void setTickWidth(final int tickWidth) {
        properties.put("tickWidth", tickWidth);
    }

    /**
     * @param tickLength
     */
    public void setTickLength(final int tickLength) {
        properties.put("tickLength", tickLength);
    }

    /**
     * @param color
     */
    public void setTickColor(final String color) {
        properties.put("tickColor", color);
    }

    /**
     * @param b
     */
    public void setAllowDecimals(final boolean b) {
        properties.put("allowDecimals", b);
    }

    /**
     * @param events
     */
    public void setEvents(final AxisEventOptions events) {
        properties.put("events", events);
    }

    /**
     * @param endOnTick
     */
    public void setEndOnTick(final boolean endOnTick) {
        properties.put("endOnTick", endOnTick);
    }

    /**
     * @param showLastLabel
     */
    public void setShowLastLabel(final boolean showLastLabel) {
        properties.put("showLastLabel", showLastLabel);
    }
}
