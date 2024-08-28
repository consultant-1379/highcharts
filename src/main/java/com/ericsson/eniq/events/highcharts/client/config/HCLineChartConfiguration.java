/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.config;

import static com.ericsson.eniq.events.common.client.CommonConstants.*;
import static com.ericsson.eniq.events.highcharts.client.ChartConstants.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ericsson.eniq.events.common.client.datatype.ChartDataType;
import com.ericsson.eniq.events.common.client.datatype.ChartItemDataType;
import com.ericsson.eniq.events.highcharts.client.ChartEnums.eSeriesType;
import com.ericsson.eniq.events.highcharts.client.ChartEnums.eZoomType;
import com.ericsson.eniq.events.highcharts.client.HighChartsJS;
import com.ericsson.eniq.events.highcharts.client.options.BaseOptions;
import com.ericsson.eniq.events.highcharts.client.options.ChartOptions;
import com.ericsson.eniq.events.highcharts.client.options.LegendItemStyleOptions;
import com.ericsson.eniq.events.highcharts.client.options.LegendOptions;
import com.ericsson.eniq.events.highcharts.client.options.SeriesOptions;
import com.ericsson.eniq.events.highcharts.client.options.series.MarkerOptions;
import com.ericsson.eniq.events.widgets.client.dialog.MessageDialog;

/**
 * Line Chart Model. Line Charts in Eniq Events can have their series switched on or off, so this class maintains some
 * datatypes to allow for turning on an off series (lines).
 * @author ecarsea
 * @since 2011
 *
 */
public class HCLineChartConfiguration extends AbstractAxesChartConfiguration {

    /** The list of visible line series **/
    protected final List<ChartItemDataType> visibleSeries = new ArrayList<ChartItemDataType>();

    /** The list of all line series in the chart */
    private final Map<String, ChartItemDataType> allSeries = new HashMap<String, ChartItemDataType>();

    /**
     * Maintain own list of categories as we: 
     * Need to display categories for 1) No Data 2) All Data 3) No Data displayed, but available for selection
     **/
    private Object[] categories = new Object[0];

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.highcharts.client.config.AbstractAxesChartConfiguration#init(com.ericsson.eniq.events.highcharts.client.HighChartsJS, com.ericsson.eniq.events.ui.client.datatype.ChartDataType, com.google.gwt.json.client.JSONValue)
     */
    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.highcharts.client.config.AbstractChartConfiguration#init(com.ericsson.eniq.events.highcharts.client.HighChartsJS, com.ericsson.eniq.events.ui.client.datatype.ChartDataType, java.util.Map)
     */
    @Override
    public void init(final HighChartsJS highChartsJS, final ChartDataType chartMetaData,
            final Map<String, Object[]> data) {
        super.init(highChartsJS, chartMetaData, data);
        setupSeriesMap();
        /** Normal categories available on start up **/
        categories = super.getCategories();
    }

    /**
     * Set up the Map of all the series.
     */
    protected void setupSeriesMap() {
        for (final ChartItemDataType chartItemDataType : getChartMetaData().itemInfo) {
            allSeries.put(chartItemDataType.id, chartItemDataType);
            /** All the series are visible on start up **/
            visibleSeries.add(chartItemDataType);
        }

    }

    @Override
    public void buildChart() {
        super.buildChart();

        /** Default Line Chart Colours Array **/
        setColorOptions(LINE_COLORS);
    }

    @Override
    protected void setLegendOptions() {
        final LegendOptions legendOptions = new LegendOptions();
        if (visibleSeries.size() > 10) {
            final LegendItemStyleOptions styleOptions = new LegendItemStyleOptions();
            styleOptions.setFontSize(LEGEND_FONT_MANY_ITEMS);
            styleOptions.setFontFamily(DEFAULT_FONT_FAMILY);
            legendOptions.setItemStyle(styleOptions);
        }
        setLegendOptions(legendOptions);
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.highcharts.client.config.AbstractAxesChartConfiguration#getChartOptions(java.lang.String)
     */
    @Override
    protected ChartOptions getChartOptions(final String containerDiv) {
        final ChartOptions chartOptions = super.getChartOptions(containerDiv);
        chartOptions.setSeriesType(eSeriesType.spline);
        chartOptions.setZoomType(eZoomType.xy);
        return chartOptions;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.highcharts.client.config.AbstractChartConfiguration#getSeriesList()
     */
    @Override
    protected List<BaseOptions> getSeriesList() {

        final List<BaseOptions> seriesList;

        if (!visibleSeries.isEmpty()) {
            seriesList = createSeriesList(visibleSeries);
        } else {
            /** Get a default series just in order to render an empty graph **/
            final SeriesOptions seriesOptions = new SeriesOptions();
            seriesOptions.setName(" ");
            seriesOptions.setType(getSeriesType());
            seriesOptions.setData(getDefaultDataSeriesValues());
            seriesList = new ArrayList<BaseOptions>();
            seriesList.add(seriesOptions);
        }

        /** Only enable the legend if we are showing at least one series **/
        this.getLegendOptions().setEnabled(!visibleSeries.isEmpty());
        return seriesList;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.highcharts.client.config.AbstractAxesChartConfiguration#createSeries(com.ericsson.eniq.events.ui.client.datatype.ChartItemDataType)
     */
    @Override
    protected SeriesOptions getSeriesOptions(final ChartItemDataType seriesItem) {
        final SeriesOptions seriesOptions = super.getSeriesOptions(seriesItem);
        seriesOptions.setMarkerOptions(new MarkerOptions()); // using defaults
        return seriesOptions;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.highcharts.client.config.AbstractAxesChartConfiguration#getSeriesType()
     */
    @Override
    protected eSeriesType getSeriesType() {
        return eSeriesType.line;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.highcharts.client.AbstractHighChartsConfiguration#showChartElements(java.util.Set)
     */
    @Override
    public boolean showChartElements(final Set<String> chartElementIds) {

        if (isSameLinesVisibleAlready(chartElementIds)) {
            /** no work to do for menu item open-close action */
            return false;
        }
        visibleSeries.clear();
        for (final String elementId : chartElementIds) {
            hideShowChartElement(elementId);
        }
        final boolean isNoCheckedItems = visibleSeries.isEmpty();
        resetMessageOnXAxis(isNoCheckedItems);
        return true;
    }

    /**
     * @param elementId
     * @return
     */
    public boolean hideShowChartElement(final String elementId) {
        /** If is valid element Id **/
        if (allSeries.containsKey(elementId)) {
            final ChartItemDataType chartItemDataType = allSeries.get(elementId);
            if (visibleSeries.contains(chartItemDataType)) {
                return visibleSeries.remove(chartItemDataType);
            }
            return visibleSeries.add(chartItemDataType);
        }
        return false;
    }

    /**
     * Display suitable label on X Axis for when some series lines are selected and when no series lines selected 
     * @param isNoCheckedItems
     */
    private void resetMessageOnXAxis(final boolean isNoCheckedItems) {
        if (this.xAxis != null) {
            /** If no series seleceted **/
            if (isNoCheckedItems) {
                this.categories = new Object[] { DATA_AVAILABLE_NOT_SHOWN_LABEL };
                MessageDialog.get().show(DATA_AVAILABLE, DATA_AVAILABLE_USE_VIEW_MENU_OPTION,
                        MessageDialog.DialogType.INFO);

            } else {
                this.categories = super.getCategories();
            }
        }
    }

    /**
     * If user opens the checkbox menu and closes it again 
     * try and reduce the workload (don't reload graph elements if
     * made no changes to the checkbox selection)
     */
    private boolean isSameLinesVisibleAlready(final Set<String> chartElementIds) {
        final int visibleSeriesCount = visibleSeries.size();

        for (final String elementId : chartElementIds) {
            final ChartItemDataType chartItemDataType = allSeries.get(elementId);
            if (chartItemDataType == null || (!visibleSeries.contains(chartItemDataType))) {
                return false;
            }
        }
        return visibleSeriesCount == chartElementIds.size();
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.highcharts.client.config.AbstractChartConfiguration#getCategories()
     */
    @Override
    protected Object[] getCategories() {
        return categories;
    }

    /* 
     * Need a label for the line chart in order to indicate that No Data is available
     * (non-Javadoc)
     * @see com.ericsson.eniq.events.highcharts.client.config.AbstractChartConfiguration#getCategories()
     */
    @Override
    protected Object[] getDefaultCategories() {
        return new String[] { NO_DATA_LABEL };
    }
}
