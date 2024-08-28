/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options;

import com.ericsson.eniq.events.highcharts.client.ChartEnums.eAlign;
import com.ericsson.eniq.events.highcharts.client.ChartEnums.eVerticalAlign;

/**
 * Base class for various title options - Axis title, chart title etc
 * @author eendmcm
 * @since 2011
 * See http://www.highcharts.com/ref/#title 
 */
public abstract class AbstractTitleOptions extends BaseOptions {

    /**
     * @param optionsObjectId - id of options sub class
     */
    public AbstractTitleOptions(final String optionOjectId) {
        super(optionOjectId);
    }

    /**
     * @param value
     */
    public void setText(final String value) {
        properties.put("text", value);
    }

    /**
     * @param value
     */
    public void setFloating(final boolean value) {
        properties.put("floating", value);
    }

    /**
     * @param options
     */
    public void setStyleOptions(final CSSStyleOptions options) {
        properties.put("style", options);
    }

    /**
     * @param value
     */
    public void setAlign(final eAlign value) {
        properties.put("align", value.toString());
    }

    /**
     * @param value
     */
    public void setVerticalAlign(final eVerticalAlign value) {
        properties.put("verticalAlign", value.toString());
    }

    /**
     * @param value
     */
    public void setXPosition(final int value) {
        properties.put("x", value);
    }

    /**
     * @param value
     */
    public void setYPosition(final int value) {
        properties.put("y", value);
    }

}
