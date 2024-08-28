/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options.series;

import com.ericsson.eniq.events.highcharts.client.options.BaseOptions;
import com.ericsson.eniq.events.highcharts.client.options.plot.DataLabelOptions;

/**
 * Options for the Points of a Series
 * @author ecarsea
 * @since 2011
 *
 */
public class PointOptions extends BaseOptions {

    private static final String POINT_NAME = "name";

    private static final String POINT_X = "x";

    public PointOptions() {
        super("point");
    }

    /**
     * @param color
     */
    public void setColor(final String color) {
        properties.put("color", color);
    }

    /**
     * @param name
     */
    public void setName(final String name) {
        properties.put(POINT_NAME, name);
    }

    /**
     * @param y
     */
    public void setY(final Object y) {
        properties.put("y", y);
    }

    /**
     * @param events
     */
    public void setEvents(final PointEvents events) {
        properties.put("events", events);
    }

    /**
     * @param data
     */
    public void setToolTipData(final Object[] data) {
        properties.put("tooltipData", data);
    }

    /**
     * @param data
     */
    public void setQueryParams(final Object[] data) {
        properties.put("queryParams", data);
    }

    /**
     * @param x
     */
    public void setX(final Object x) {
        properties.put(POINT_X, x);
    }

    /**
     * @return
     */
    public Object getPointCategory() {
        if (properties.get(POINT_X) == null) {
            return properties.get(POINT_NAME);
        }
        return properties.get(POINT_X);
    }

    /**
     * @param dataLabelOptions
     */
    public void setDataLabelOptions(final DataLabelOptions dataLabelOptions) {
        properties.put("dataLabels", dataLabelOptions);
    }

}
