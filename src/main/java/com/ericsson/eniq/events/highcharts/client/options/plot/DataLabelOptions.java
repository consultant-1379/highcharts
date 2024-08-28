/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options.plot;

import com.ericsson.eniq.events.highcharts.client.ChartEnums;
import com.ericsson.eniq.events.highcharts.client.RawStringType;
import com.ericsson.eniq.events.highcharts.client.options.BaseOptions;
import com.ericsson.eniq.events.highcharts.client.options.CSSStyleOptions;

/**
 * @author eendmcm
 * @since 2011
 *
 * Wrap the Properties exposed by the highcharts API for Column DataLabels
 * see http://www.highcharts.com/ref/#plotOptions-column-dataLabels
 */
public class DataLabelOptions extends BaseOptions {

    public DataLabelOptions() {
        super("dataLabels");
    }

    /**
     * @param value
     */
    public void setAlign(final ChartEnums.eAlign value) {
        properties.put("align", value.toString());
    }

    /**
     * @param value
     */
    public void setColor(final String value) {
        properties.put("color", value.toString());
    }

    /**
     * @param value
     */
    public void setEnabled(final boolean value) {
        properties.put("enabled", value);
    }

    /**
     * @param value
     */
    public void setRotation(final int value) {
        properties.put("rotation", value);
    }

    /**
     * @param value
     */
    public void setStyle(final CSSStyleOptions value) {
        properties.put("style", value);
    }

    /**
     * @param value
     */
    public void setX(final int value) {
        properties.put("x", value);
    }

    /**
     * @param value
     */
    public void setY(final int value) {
        properties.put("y", value);
    }

    /**
     * @param value
     */
    public void setDistance(final int value) {
        properties.put("distance", value);
    }

    /**
     * @param formatter
     */
    public void setFormatter(final RawStringType formatter) {
        properties.put("formatter", formatter);
    }
}
