/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options.axis;

import com.ericsson.eniq.events.highcharts.client.options.BaseOptions;

/**
 * @since 2012
 * See http://www.highcharts.com/ref/#xAxis--dateTimeLabelFormats
 */
public class AxisDateTimeFormatOptions extends BaseOptions {

    public AxisDateTimeFormatOptions() {
        super("dateTimeLabelFormats");
        setDefaults(); //NOPMD
    }

    private void setDefaults() {
        setHour("%d/%m/%y %H:%M");
        setMinute("%d/%m/%y %H:%M");
    }

    public void setHour(String hour) {
        properties.put("hour", hour);
    }

    public void setMinute(String minute) {
        properties.put("minute", minute);
    }
}
