/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.config;

import com.ericsson.eniq.events.common.client.datatype.ChartItemDataType;
import com.ericsson.eniq.events.highcharts.client.ChartEnums.eSeriesType;
import com.ericsson.eniq.events.highcharts.client.options.SeriesOptions;
import com.ericsson.eniq.events.highcharts.client.options.series.MarkerOptions;

/**
 * @author ecarsea
 * @since 2011
 *
 */
public class HCBarWithLineChartConfiguration extends HCBarChartConfiguration {

    @Override
    protected SeriesOptions getSeriesOptions(final ChartItemDataType seriesItem) {
        final SeriesOptions seriesOptions = super.getSeriesOptions(seriesItem);

        if (seriesItem.id.equalsIgnoreCase(getChartMetaData().secondYAxisColID)) {
            seriesOptions.setType(eSeriesType.scatter);
            seriesOptions.setMarkerOptions(getMarkerOptions()); // using defaults
        }
        return seriesOptions;
    }

    protected MarkerOptions getMarkerOptions() {
        return new MarkerOptions();
    }

}
