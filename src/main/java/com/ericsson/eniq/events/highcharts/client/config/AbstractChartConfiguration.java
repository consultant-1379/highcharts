/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.config;

import static com.ericsson.eniq.events.highcharts.client.ChartConstants.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ericsson.eniq.events.common.client.datatype.ChartDataType;
import com.ericsson.eniq.events.common.client.datatype.ChartItemDataType;
import com.ericsson.eniq.events.highcharts.client.ChartEnums.eAlign;
import com.ericsson.eniq.events.highcharts.client.HighChartUtils;
import com.ericsson.eniq.events.highcharts.client.HighChartsJS;
import com.ericsson.eniq.events.highcharts.client.RawStringType;
import com.ericsson.eniq.events.highcharts.client.options.AbstractAxisOptions;
import com.ericsson.eniq.events.highcharts.client.options.BaseOptions;
import com.ericsson.eniq.events.highcharts.client.options.ChartCreditOptions;
import com.ericsson.eniq.events.highcharts.client.options.ChartOptions;
import com.ericsson.eniq.events.highcharts.client.options.ChartSubTitleOptions;
import com.ericsson.eniq.events.highcharts.client.options.ChartTitleOptions;
import com.ericsson.eniq.events.highcharts.client.options.ExportButtonOptions;
import com.ericsson.eniq.events.highcharts.client.options.ExportButtonsOptions;
import com.ericsson.eniq.events.highcharts.client.options.ExportingOptions;
import com.ericsson.eniq.events.highcharts.client.options.LegendOptions;
import com.ericsson.eniq.events.highcharts.client.options.NavigationOptions;
import com.ericsson.eniq.events.highcharts.client.options.PlotOptions;
import com.ericsson.eniq.events.highcharts.client.options.PrintButtonOptions;
import com.ericsson.eniq.events.highcharts.client.options.SeriesOptions;
import com.ericsson.eniq.events.highcharts.client.options.ToolTipOptions;
import com.ericsson.eniq.events.highcharts.client.options.series.PointEvents;
import com.ericsson.eniq.events.highcharts.client.options.series.PointOptions;
import com.ericsson.eniq.events.highcharts.client.options.series.QueryParamOptions;
import com.ericsson.eniq.events.highcharts.client.options.series.ToolTipAdditionalData;

/**
 * Base Configuration and Model for the different High Charts types.
 * @author ecarsea
 * @since 2011
 *
 */
public abstract class AbstractChartConfiguration implements ChartConfigTemplate {

    /** High Charts Options API */
    private HighChartsJS highChartsJS;

    protected ChartOptions chartOptions;

    /** Meta Data */
    private ChartDataType chartMetaData;

    /** Data */
    private Map<String, Object[]> dataSeriesMap;

    /** Array of categories i.e. X Axis */
    private Object[] categories;

    /** Flag for toggling the legend */
    private boolean legendEnabled = true;

    /** Legend Options **/
    private LegendOptions legendOptions;

    /** Number of rows of data **/
    private int rowCount;

    private int chartWidth;

    private ChartTitleOptions chartTitleOptions;

    private int maxLabelsLength = MAX_LABELS_PER_WINDOW_DEFAULT;

    /**
     * @param highChartsJS
     * @param chartMetaData
     * @param dataSeriesMap
     */
    @Override
    public void init(final HighChartsJS highChartsJS, final ChartDataType chartMetaData,
            final Map<String, Object[]> dataSeriesMap) {
        this.highChartsJS = highChartsJS;
        this.chartMetaData = chartMetaData;
        this.dataSeriesMap = dataSeriesMap;
        /** Set categories. Never let categories array be null **/
        final boolean hasData = dataSeriesMap.get(getChartMetaData().xAxisColID) != null;
        categories = hasData ? dataSeriesMap.get(getChartMetaData().xAxisColID) : getDefaultCategories(); //NOPMD
        rowCount = hasData ? dataSeriesMap.get(getChartMetaData().xAxisColID).length : 0;
    }

    /** Chart Build Options common to all derived Charts. Override the various getters to specialize any of these options **/
    @Override
    public void buildChart() {
        this.chartTitleOptions = getChartTitleOptions();
        highChartsJS.setChartTitleOptions(chartTitleOptions);
        highChartsJS.setNavigationOptions(getNavigationOptions());
        highChartsJS.setExportingOptions(getExportingOptions());

        /** create and add chart credit options, default enabled in high charts so need to create and add them here.
         *  Credits are disabled by default in the constructor. 
         **/
        highChartsJS.setChartCreditOptions(new ChartCreditOptions());

        chartOptions = getChartOptions(highChartsJS.getContainerDivId());
        highChartsJS.setChartOptions(chartOptions);
        setData();
    }

    /**
     * @return
     */
    protected ExportingOptions getExportingOptions() {
        final ExportingOptions exportingOptions = new ExportingOptions();
        final ExportButtonsOptions buttonsOptions = new ExportButtonsOptions();
        buttonsOptions.setExportButtonOptions(new ExportButtonOptions());
        buttonsOptions.setPrintButtonOptions(new PrintButtonOptions());
        exportingOptions.setExportButtonOptions(buttonsOptions);
        return exportingOptions;
    }

    /**
     * @return
     */
    protected NavigationOptions getNavigationOptions() {
        return new NavigationOptions();
    }

    /** 
     * Optionally add a legend to the chart
     * @param legendOptions
     */
    protected void setLegendOptions(final LegendOptions legendOptions) {
        this.legendOptions = legendOptions;
        highChartsJS.setLegendOptions(legendOptions);
    }

    /**
     * @param chartLoadFunction
     */
    public void setChartLoadFunction(final String chartLoadFunction) {
        highChartsJS.setChartLoadFunction(chartLoadFunction);
    }

    /**
     * @param plotOptions
     * @return
     */
    protected void setPlotOptions(final PlotOptions plotOptions) {
        highChartsJS.setPlotOptions(plotOptions);
    }

    protected void addAxis(final AbstractAxisOptions axisOptions) {
        highChartsJS.addAxis(axisOptions);
    }

    /**
     * @param toolTipOptions
     * @return
     */
    protected void setTooltipOptions(final ToolTipOptions toolTipOptions) {
        highChartsJS.setTooltipOptions(toolTipOptions);
    }

    /**
     * Further Chart Options can be set by Overriding this method;
     * @param chartOptions
     * @return
     */
    protected ChartOptions getChartOptions(final String containerId) {
        return new ChartOptions(containerId);
    }

    /**
     * Get the Series for this Chart. Each Series will consist of an array of Point Objects
     * http://www.highcharts.com/ref/#point
     * @return
     */
    protected abstract List<BaseOptions> getSeriesList();

    private void setData() {
        final List<BaseOptions> seriesList = getSeriesList();
        highChartsJS.setSeriesOptions(seriesList);
    }

    /**
     * @return
     */
    protected Object[] getCategories() {
        return categories;
    }

    /**
     * For the case of empty data received from services return default categories that can be used to render 
     * a graph even when no data is available. 
     * @return 
     */
    protected Object[] getDefaultCategories() {
        /** Blank Label **/
        return new String[] { " " };
    }

    /**
     * @param rowIndex
     * @return
     */
    protected Object getCategory(final int rowIndex) {
        return categories[rowIndex];
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.highcharts.client.config.ChartConfigTemplate#getRowCount()
     */
    @Override
    public int getRowCount() {
        return rowCount;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.highcharts.client.config.ChartConfigTemplate#toggleLegendEnabled()
     */
    @Override
    public void toggleLegendEnabled() {
        this.legendEnabled = !legendEnabled;
        if (legendOptions != null) {
            legendOptions.setEnabled(legendEnabled);
        }
    }

    /**
     * @return the legendEnabled
     */
    public boolean isLegendEnabled() {
        return legendEnabled;
    }

    /**
     * Check boxes indicating which series to display in chart. Override to specialize
     * @param chartElementIds - Set of Chart Series Element Ids for series to be shown.
     * @return
     */
    @Override
    public boolean showChartElements(final Set<String> chartElementIds) {
        return false;
    }

    /**
     * Set the Main Chart Title Options
     */
    protected ChartTitleOptions getChartTitleOptions() {
        chartTitleOptions = new ChartTitleOptions();
        chartTitleOptions.setText(chartMetaData.chartTitle);
        return chartTitleOptions;
    }

    /**
     * @return the chartMetaData
     */
    protected ChartDataType getChartMetaData() {
        return chartMetaData;
    }

    /**
     * 
     * @return the data array if there is data or default data values if we have no data.
     */
    protected Object[] getSeriesObjects(final String id) {
        return isEmptyDataSeries(id) ? getDefaultDataSeriesValues() : toDoubleArray(dataSeriesMap.get(id));
    }

    /**
     * This method should return default data series values in case empty data is received from services. This is
     * required in order to render an empty chart rather than just have a blank window
     * @return
     */
    protected Object[] getDefaultDataSeriesValues() {
        return new String[] { "0" };
    }

    /**
     * @param id
     * @return
     */
    protected boolean isEmptyDataSeries(final String id) {
        return dataSeriesMap.get(id) == null || dataSeriesMap.get(id).length == 0;
    }

    /**
     * Convert objects to doubles, JSON data values are usually JSON strings, need doubles for High Charts
     * @param objects
     * @return
     */
    private Double[] toDoubleArray(final Object[] values) {
        final List<Double> data = new LinkedList<Double>();
        for (final Object value : values) {
            if (value != null && !value.toString().isEmpty()) {
                data.add(Double.parseDouble(value.toString()));
            } else {
                data.add(null);
            }
        }
        return data.toArray(new Double[0]);
    }

    /**
     * Just return the current Legend Options Config
     * @return
     */
    protected LegendOptions getLegendOptions() {
        return legendOptions;
    }

    /**
     * Check if we have a drillable Item by checking if the series is Drillable
     * and that the chart has data.
     * @param drillableSeries
     * @return
     */
    protected boolean isDrillable(final boolean drillableSeries) {
        return drillableSeries && getRowCount() > 0;
    }

    /**
     * Get all the Data Points for this Series. Each Point is a Point Options object 
     * See http://www.highcharts.com/ref/#point.
     * @param chartItemDataType
     * @param seriesOptions
     * @return
     */
    protected PointOptions[] getSeriesDataPoints(final ChartItemDataType chartItemDataType,
            final SeriesOptions seriesOptions) {
        final Object[] values = getSeriesObjects(chartItemDataType.id); // The Y Values
        final List<PointOptions> pointsList = new LinkedList<PointOptions>();

        for (int i = 0; i < values.length; i++) {
            // Create the Point Options Objects
            final PointOptions pointOptions = getPointOptions(getCategory(i).toString(), values[i], i);
            // Set Drillable if it is Drillable
            setDrillablePoint(chartItemDataType, seriesOptions, i, pointOptions);
            pointsList.add(pointOptions);
        }
        return pointsList.toArray(new PointOptions[0]);
    }

    protected void setDrillablePoint(final ChartItemDataType chartItemDataType, final SeriesOptions seriesOptions,
            final int rowIndex, final PointOptions pointOptions) {
        if (isDrillable(chartItemDataType.isDrillAbleSeries())) {
            seriesOptions.setCursor(CURSOR_POINTER);
            seriesOptions.setAllowPointSelect(true);
            configureDrillablePoint(pointOptions, chartItemDataType, rowIndex);
        }
    }

    /**
     * Create the Individual Point Options i.e. the Point Object for each point in the series if using the point object
     * See http://www.highcharts.com/ref/#point
     * @param category - XAxis Label
     * @param value - Y Value
     * @param rowIndex - Position of Point in the Data Array
     * @return
     */
    protected abstract PointOptions getPointOptions(String category, Object value, int rowIndex);

    /**
     * Add the required data and events to enable chart points to be drillable
     * @param pointOptions
     * @param drilldownSeriesId
     * @param rowIndex
     */
    protected void configureDrillablePoint(final PointOptions pointOptions, final ChartItemDataType chartItemDataType,
            final int rowIndex) {

        /** store the meta identifier used for this chart */
        pointOptions.setProperty(CHART_META_ID, getChartMetaData().id);
        /** What chart element was selected  */
        final String pointValue = getSelectedDataPoint(pointOptions);
        pointOptions.setProperty(CHART_ELEMENT_SELECTED_KEY, pointValue);
        pointOptions.setProperty(CHART_ELEMENT_DRILLDOWN_KEY, chartItemDataType.getDrillDownTypeKey());
        pointOptions.setProperty(DATA_POINT_ROW_INDEX, rowIndex);
        /** Chart Id **/
        pointOptions.setProperty("id", highChartsJS.getChartName());
        /** Any query parameters from the chart data required for drilling */
        final List<QueryParamOptions> queryParamsList = new LinkedList<QueryParamOptions>();
        for (int i = 0; i < getChartMetaData().itemInfo.length; i++) {
            setQueryParam(rowIndex, getChartMetaData().itemInfo[i], queryParamsList);
        }
        if (!queryParamsList.isEmpty()) {
            pointOptions.setQueryParams(queryParamsList.toArray());
        }
        /** Add the event to raise on Point Clicks */
        pointOptions.setEvents(getPointEvents());
    }

    /**
     * Return category i.e. x Axis value of the point.
     * @param pointOptions
     * @return
     */
    protected String getSelectedDataPoint(final PointOptions pointOptions) {
        return pointOptions.getPointCategory().toString();
    }

    /**
     * Get the Point Events Object. Override in derived classes for specialization.
     * @return
     */
    protected PointEvents getPointEvents() {
        final PointEvents pointEvents = new PointEvents();
        /**
         * Click event. Call back into GWT with the externalised onHCPointClick method passing "this" which
         * is the Point Object.
         */
        pointEvents.setClickEvent(new RawStringType(
                "function(event) {this.nativeEvent = event; $wnd.onHCPointClick(this); return false;}"));
        return pointEvents;
    }

    /**
     * Get Value at a rowIndex in series array
     * @param rowIndex
     * @param seriesId
     * @return
     */
    protected Object getSeriesValue(final int rowIndex, final String seriesId) {
        final Object[] series = dataSeriesMap.get(seriesId);
        return (series != null && rowIndex < series.length) ? series[rowIndex] : "#";
    }

    /**
     * Add a query parameter to the List
     * @param rowIndex
     * @param metaDataIndex
     * @param queryParamsList
     */
    protected void setQueryParam(final int rowIndex, final ChartItemDataType chartItemDataType,
            final List<QueryParamOptions> queryParamsList) {
        if (!HighChartUtils.isNullOrEmpty(chartItemDataType.queryParam)) {
            final QueryParamOptions queryParamOptions = new QueryParamOptions();
            queryParamOptions.setName(chartItemDataType.queryParam);
            if (chartItemDataType.queryParamValue.isEmpty()) {
                queryParamOptions.setValue(getSeriesValue(rowIndex, chartItemDataType.id).toString());
            } else {
                // fixed query parameter
                queryParamOptions.setValue(chartItemDataType.queryParamValue);
            }
            queryParamsList.add(queryParamOptions);
        }
    }

    /**
     * @param id
     * @return
     */
    protected ChartItemDataType getChartDataTypeForId(final String id) {
        for (final ChartItemDataType chartItemDataType : getChartMetaData().itemInfo) {
            if (chartItemDataType.id.equals(id)) {
                return chartItemDataType;
            }
        }
        return null;
    }

    /**
     * Include any other relevant items possibly from hidden series in the tooltip
     * @param pointOptions
     * @param index
     */
    protected void setToolTipData(final PointOptions pointOptions, final int index) {
        final List<ToolTipAdditionalData> toolTipDataList = new LinkedList<ToolTipAdditionalData>();
        for (final ChartItemDataType chartItemDataType : getChartMetaData().itemInfo) {

            if (chartItemDataType.displayInTooltip) {
                /** Meta Data indicator that this series item should be in tooltip, if it is not on the excluded data point list **/
                final Object dataPoint = getSeriesValue(index, chartItemDataType.id);
                /** If we are excluding this series from a tooltip based on a data point value of a specified series **/
                boolean excluded = false;
                if (!chartItemDataType.getTooltipExcludedDataPoints().isEmpty()) {
                    /** This series value will be excluded from the tooltip if the data point for a specified series in the meta data is matched.
                     * So check the meta data to see if the series and data point is excluded **/
                    excluded = checkIfDataPointExcludedFromTooltip(index,
                            chartItemDataType.getTooltipExcludedDataPoints());
                }
                if (!excluded) {
                    createTooltipData(dataPoint, toolTipDataList, chartItemDataType);
                }

            }
        }
        if (!toolTipDataList.isEmpty()) {
            pointOptions.setToolTipData(toolTipDataList.toArray());
        }
    }

    /**
     * Check all meta data chart series to see if the data point on this row of each series matches with an exclusion pattern for the tooltip
     * @param index
     * @param excludedTooltipDataPointsMap
     * @return
     */
    protected boolean checkIfDataPointExcludedFromTooltip(final int index,
            final Map<String, List<String>> excludedTooltipDataPointsMap) {
        for (final ChartItemDataType chartItemDataType : getChartMetaData().itemInfo) {
            final String id = chartItemDataType.id;
            if (excludedTooltipDataPointsMap.containsKey(id)
                    && excludedTooltipDataPointsMap.get(id).contains(getSeriesValue(index, id))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add a tooltip item to the list of additional items to display in the tooltip
     * @param value
     * @param toolTipDataList
     * @param chartDataType
     */
    protected void createTooltipData(final Object value, final List<ToolTipAdditionalData> toolTipDataList,
            final ChartItemDataType chartDataType) {
        final ToolTipAdditionalData tooltip = new ToolTipAdditionalData();
        tooltip.setName(chartDataType.name);
        tooltip.setValue(value);
        toolTipDataList.add(tooltip);
    }

    /**
     * Render the chart in a scrollable container. Chart width is determined by number of categories as opposed to
     * the size of the parent container.
     * @param clientWidth
     * @param clientHeight
     */
    @Override
    public void renderScrollableChart(final int clientWidth, final int clientHeight) {
        this.chartWidth = (int) (clientWidth * ((double) getCategories().length / maxLabelsLength));

        final int chartHeight = clientHeight - CHART_WINDOW_SCROLLBAR_OFFSET;
        chartOptions.setWidth(chartWidth);
        chartOptions.setHeight(chartHeight);
        if (legendOptions != null) {
            /** Align legend and title suited to a scrollable container **/
            legendOptions.setAlign(eAlign.left);
        }
        chartTitleOptions.setAlign(eAlign.left);
        chartTitleOptions.setXPosition(300);
    }

    /**
     * Set chart size
     * @param chartWidth
     * @param chartHeight
     */
    @Override
    public void setSize(final int chartWidth, final int chartHeight) {
        /** Dont explicitly set  height or width if it is 0. This will cause it to default to 400. Let it get it from its parent container
         * or from a derived chart configuration
         */
        if (chartHeight > 0) {
            chartOptions.setHeight(chartHeight);
        }
        if (chartWidth > 0) {
            chartOptions.setWidth(chartWidth);
        }
    }

    public void setHeight(final int chartHeight) {
        chartOptions.setHeight(chartHeight);
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.highcharts.client.config.ChartConfigTemplate#getWidth()
     */
    @Override
    public int getWidth() {
        return this.chartWidth;
    }

    protected String getChartName() {
        return highChartsJS.getChartName();
    }

    /**
     * @param colors
     */
    protected void setColorOptions(final String[] colors) {
        highChartsJS.setColourOptions(colors);
    }

    protected HighChartsJS getApiRoot() {
        return highChartsJS;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.highcharts.client.config.ChartConfigTemplate#requiresScrollableChart()
     */
    @Override
    public boolean requiresScrollableChart() {
        /** If the x axis is not zoomable and we have labels greater than the max for this window then scrolling is required **/
        if (!getChartMetaData().zoomableXAxis && getCategories().length > maxLabelsLength) {
            return true;
        }
        return false;
    }

    /**
     * 
     * @param id
     * @return the data array
     */
    protected final Map<String, Object[]> getDataSeriesMap() {
        return this.dataSeriesMap;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.highcharts.client.config.ChartConfigTemplate#setSubTitle(java.lang.String)
     */
    @Override
    public void setSubTitle(final String subTitle) {
        final ChartSubTitleOptions subTitleOptions = new ChartSubTitleOptions();
        subTitleOptions.setText(subTitle);
        highChartsJS.setChartSubTitleOptions(subTitleOptions);
    }

    public void setMaxLabelsLength(final int maxLabelsLength) {
        this.maxLabelsLength = maxLabelsLength;
    }
}
