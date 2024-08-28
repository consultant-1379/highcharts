/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.config;

import com.ericsson.eniq.events.highcharts.client.ChartEnums.eSeriesType;

/**
 * A Chart Config for a Horizontal Bar.
 * @author ecarsea
 * @since 2011
 *
 */
public class HorizontalBarChartConfiguration extends HCBarChartConfiguration {

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.highcharts.client.config.HCBarChartConfiguration#getSeriesType()
     */
    @Override
    protected eSeriesType getSeriesType() {
        return eSeriesType.bar;
    }
}
