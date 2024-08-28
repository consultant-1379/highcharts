/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.config;

import java.util.Map;
import java.util.Set;

import com.ericsson.eniq.events.common.client.datatype.ChartDataType;
import com.ericsson.eniq.events.highcharts.client.HighChartsJS;

/**
 * Template Interface for a Chart. Implemented by all Chart Templates
 * @author ecarsea
 * @since 2011
 *
 */
public interface ChartConfigTemplate {
    void init(final HighChartsJS highChartsJS, final ChartDataType chartMetaData, final Map<String, Object[]> data);

    /**
     * @param selectedChartElementIds
     */
    boolean showChartElements(Set<String> selectedChartElementIds);

    void buildChart();

    /**
     * @param clientWidth
     * @param clientHeight
     */
    void setSize(int clientWidth, int clientHeight);

    /**
     * @return
     */
    boolean requiresScrollableChart();

    /**
     * @param width
     * @param height
     */
    void renderScrollableChart(int width, int height);

    /**
     * Length of categories will indicate the number of rows
     * @return
     */
    int getRowCount();

    /**
     * Turn on and off the legend display
     */
    void toggleLegendEnabled();

    /**
     * @return
     */
    int getWidth();

    /**
     * @param subTitle
     */
    void setSubTitle(String subTitle);
}
