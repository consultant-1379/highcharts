/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options.axis;

import com.ericsson.eniq.events.highcharts.client.RawStringType;
import com.ericsson.eniq.events.highcharts.client.options.BaseOptions;

/**
 * @author ecarsea 
 * @since 2011
 */
public class AxisEventOptions extends BaseOptions {

    public AxisEventOptions() {
        super("events");
    }

    /**
     * @param value
     */
    public void setExtremesEventHandler(final RawStringType handler) {
        properties.put("setExtremes", handler);
    }
}
