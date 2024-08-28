/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options;

import com.ericsson.eniq.events.highcharts.client.options.axis.AxisDateTimeFormatOptions;

/**
 * XAxis Options
 * @author eendmcm
 * @since 2011
 *
 */
public class XAxisOptions extends AbstractAxisOptions {

    private static final int DEFAULT_AXIS_LINE_WIDTH = 1;

    public XAxisOptions() {
        super("xAxis");
        setDefaults();
    }

    /**
     * 
     */
    private void setDefaults() {
        setLineWidth(DEFAULT_AXIS_LINE_WIDTH);
        setDateTimeFormat(new AxisDateTimeFormatOptions());
    }

    private void setDateTimeFormat(AxisDateTimeFormatOptions dateTimeLabelFormats) {
        properties.put("dateTimeLabelFormats", dateTimeLabelFormats);
    }

    /**
     * @param type
     */
    public void setType(final String type) {
        properties.put("type", type);
    }

}
