/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options.plot;

import com.ericsson.eniq.events.highcharts.client.options.series.PointOptions;

/**
 * @author ecarsea
 * @since 2011
 *
 */
public class PieChartPlotOptions extends AbstractPlotOptions {

    public PieChartPlotOptions() {
        super("pie");
        setDefaults(); //NOPMD
    }

    /**
     * Default Options values
     */
    private void setDefaults() {
        this.setAllowPointSelect(false);
        this.setShowInLegend(true);
        this.setBorderWidth(0);
    }

    /**
     * @param dataLabelOptions
     */
    public void setDataLabels(final DataLabelOptions dataLabelOptions) {
        properties.put("dataLabels", dataLabelOptions);
    }

    public void setPointOptions(final PointOptions pointOptions) {
        properties.put("point", pointOptions);
    }
}
