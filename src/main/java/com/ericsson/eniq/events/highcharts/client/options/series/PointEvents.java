/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options.series;

import com.ericsson.eniq.events.highcharts.client.RawStringType;
import com.ericsson.eniq.events.highcharts.client.options.BaseOptions;

/**
 * Handle events on chart points
 * @author ecarsea
 * @since 2011
 *
 */
public class PointEvents extends BaseOptions {
    public PointEvents() {
        super("events");
    }

    public void setClickEvent(final RawStringType handler) {
        properties.put("click", handler);
    }

    public void setLegendItemClickEvent(final RawStringType handler) {
        properties.put("legendItemClick", handler);
    }
}
