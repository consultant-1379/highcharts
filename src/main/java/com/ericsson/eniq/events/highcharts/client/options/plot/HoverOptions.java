/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options.plot;

import com.ericsson.eniq.events.highcharts.client.options.BaseOptions;

/**
 * @author egallou
 * @since 2012
 *
 * Wrap the Properties exposed by the highcharts API for hover state
 */

public class HoverOptions extends BaseOptions {

    public HoverOptions() {
        super("hover");
    }

    /**
     * @param value
     */
    public void setEnabled(final boolean value) {
        properties.put("enabled", value);
    }

    /**
     * @param symbol
     */
    public void setSymbol(final String symbol) {
        properties.put("symbol", symbol);
    }

    /**
     * @param radius
     */
    public void setRadius(final int radius) {
        properties.put("radius", radius);
    }
    /**
     * @param shadow
     */
    public void setShadow(final boolean shadow) {
        properties.put("shadow", shadow);
    }

    /**
     * @param lineWidth
     */
    public void setLineWidth(final int lineWidth) {
        properties.put("lineWidth", lineWidth);
    }

}
