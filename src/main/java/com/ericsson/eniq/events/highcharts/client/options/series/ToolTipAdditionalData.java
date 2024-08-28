/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options.series;

import com.ericsson.eniq.events.highcharts.client.options.BaseOptions;

/**
 * Class to hold additional name/value data for displaying on the chart tooltips
 * @author ecarsea
 * @since 2011
 *
 */
public class ToolTipAdditionalData extends BaseOptions {

    public ToolTipAdditionalData() {
        super("tooltipData");
    }

    /**
     * @param name
     */
    public void setName(final String name) {
        properties.put("name", name);
    }

    /**
     * @param value
     */
    public void setValue(final Object value) {
        properties.put("value", value);
    }

}
