/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.config;

import com.ericsson.eniq.events.highcharts.client.ChartEnums;
import com.ericsson.eniq.events.highcharts.client.ChartEnums.eSeriesType;
import com.ericsson.eniq.events.highcharts.client.options.ChartOptions;

/**
 * @author ecarsea
 * @since 2011
 *
 */
/**
 * @author ecarsea
 * @since 2011
 *
 */
public class HCBarChartConfiguration extends AbstractAxesChartConfiguration {

    @Override
    public void buildChart() {
        super.buildChart();
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.highcharts.client.config.AbstractAxesChartConfiguration#getChartOptions(java.lang.String)
     */
    @Override
    protected ChartOptions getChartOptions(final String containerDiv) {
        final ChartOptions chartOptions = super.getChartOptions(containerDiv);
        chartOptions.setSeriesType(getSeriesType());
        chartOptions.setZoomType(ChartEnums.eZoomType.xy);
        return chartOptions;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.highcharts.client.config.AbstractAxesChartConfiguration#getSeriesType()
     */
    @Override
    protected eSeriesType getSeriesType() {
        return eSeriesType.column;
    }
}
