/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options;

import com.ericsson.eniq.events.highcharts.client.options.plot.BarChartPlotOptions;
import com.ericsson.eniq.events.highcharts.client.options.plot.ColumnChartPlotOptions;
import com.ericsson.eniq.events.highcharts.client.options.plot.PieChartPlotOptions;
import com.ericsson.eniq.events.highcharts.client.options.plot.SeriesPlotOptions;
import com.ericsson.eniq.events.highcharts.client.options.plot.SplinePlotOptions;

/**
 * Plot Options
 * @author eendmcm
 * @since June 2011
 * 
 * Wrap the Properties exposed by the highcharts API for plotOptions
 * see http://www.highcharts.com/ref/#plotOptions
 */
public class PlotOptions extends BaseOptions {

    public PlotOptions() {
        super("plotOptions");
    }

    public void setColumnChartOptions(final ColumnChartPlotOptions options) {
        properties.put("column", options);
    }

    public void setPieChartOptions(final PieChartPlotOptions options) {
        properties.put("pie", options);
    }
    
    public void setBarChartOptions(final BarChartPlotOptions options) {
      properties.put("bar", options);
  }
    
    public void setSeriesChartOptions(final SeriesPlotOptions options) {
      properties.put("series", options);
  }

    public void setSplineChartOptions(final SplinePlotOptions options) {
        properties.put("spline", options);
    }
}
