/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2013 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.config;

import static com.ericsson.eniq.events.highcharts.client.ChartConstants.MAX_X_LABEL_ITEM_LENGTH;

import com.ericsson.eniq.events.highcharts.client.ChartEnums;
import com.ericsson.eniq.events.highcharts.client.RawStringType;
import com.ericsson.eniq.events.highcharts.client.ChartEnums.eSeriesType;
import com.ericsson.eniq.events.highcharts.client.options.ChartOptions;
import com.ericsson.eniq.events.highcharts.client.options.axis.AxisLabelOptions;

/**
 * 
 * Purpose of this class is to truncate the x-axis long labels of workspace bar charts.
 * Thereby improve the chart visibility.
 * 
 * @author xkancha
 * @since 2013
 *
 */
public class WorkSpaceHCBarChartConfiguration extends AbstractAxesChartConfiguration {

    @Override
    public void buildChart() {
        super.buildChart();
        AxisLabelOptions alo = (AxisLabelOptions)xAxis.getProperties().get("labels");
        if (alo != null){        	
        	alo.setFormatter(new RawStringType("function() {return $wnd.axisLabelFormatter(this, " + MAX_X_LABEL_ITEM_LENGTH
                + ");}"));
        	xAxis.getProperties().put("labels", alo);
        }
        
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
