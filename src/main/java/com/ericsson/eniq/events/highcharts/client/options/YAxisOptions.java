/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options;

import com.ericsson.eniq.events.highcharts.client.options.axis.AxisLabelOptions;

/**
 * Y Axis Options
 * @author eendmcm
 * @author ecarsea
 * @since 2011
 *
 */
public class YAxisOptions extends AbstractAxisOptions {

    private static final int DEFAULT_Y_AXIS_TICK_INTERVAL = 72;

    public YAxisOptions() {
        super("yAxis");
        setDefaults();
    }

    private void setDefaults() {
        setTickMarkPlacement(eTickMarkPlacement.BETWEEN);
        setTickPixelInterval(DEFAULT_Y_AXIS_TICK_INTERVAL);
        /** Set Label Options to be the default set up in the constructor **/
        setLabelOptions(new AxisLabelOptions());
        /** Default to no negative axes **/
        setMin(0);
        /** No ticks **/
        setTickLength(0);
    }

    public Double getMax() {
        return (Double) properties.get("max");
    }

    public void setTickInterval(final Object interval) {
        properties.put("tickInterval", interval);
    }

}
