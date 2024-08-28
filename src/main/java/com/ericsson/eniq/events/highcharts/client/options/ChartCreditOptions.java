/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options;

/**
 * Chart Credit Options
 * @author eendmcm
 * @author ecarsea
 * @since Jun 2011
 * see http://www.highcharts.com/ref/#credits
 */
public class ChartCreditOptions extends BaseOptions {

    public ChartCreditOptions() {
        super("credits");
        setDefaults(); //NOPMD
    }

    /**
     * 
     */
    private void setDefaults() {
        /** Disable Chart Credits by default **/
        setEnabled(false);
    }

    /**
     * @param value
     */
    public void setEnabled(final boolean value) {
        properties.put("enabled", value);
    }
}
