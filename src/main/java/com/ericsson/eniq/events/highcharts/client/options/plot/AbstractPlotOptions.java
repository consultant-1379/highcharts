/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options.plot;

import com.ericsson.eniq.events.highcharts.client.options.AnimationOptions;
import com.ericsson.eniq.events.highcharts.client.options.BaseOptions;

/**
 * Base class for the Plot Options
 * @author ecarsea
 * @since 2011
 *
 */
public abstract class AbstractPlotOptions extends BaseOptions {

    /**
     * @param optionsObjectId - id of the particular plot option implementation
     */
    public AbstractPlotOptions(final String optionsObjectId) {
        super(optionsObjectId);
    }

    /**
     * Enable/Disable animation on plot
     * @param enabled
     */
    public void setAnimationEnabled(final boolean enabled) {
        properties.put("animation", enabled);
    }

    /**
     * Animation properties for plot
     * @param animationOptions
     */
    public void setAnimationOptions(final AnimationOptions animationOptions) {
        properties.put("animation", animationOptions);
    }

    /**
     * Allow selection of points for drilling
     * @param select
     */
    public void setAllowPointSelect(final boolean select) {
        properties.put("allowPointSelect", select);
    }

    /**
     * When a series contains a data array that is longer than this, only one dimensional arrays of numbers, 
     * or two dimensional arrays with x and y values are allowed. Also, only the first point is tested, and 
     * the rest are assumed to be the same format. This saves expensive data checking and indexing in long series
     * 
     * @param turboThreshold
     */
    public void setTurboThreshold(final int turboThreshold) {
        properties.put("turboThreshold", turboThreshold);
    }

    /**
     * Show series in legend
     * @param show
     */
    public void setShowInLegend(final boolean show) {
        properties.put("showInLegend", show);
    }

    /**
     * @param cursor
     */
    public void setCursor(final String cursor) {
        properties.put("cursor", cursor);
    }

    /**
     * @param width
     */
    public void setBorderWidth(final int width) {
        properties.put("borderWidth", width);
    }
}
