/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.config;

import static com.ericsson.eniq.events.highcharts.client.ChartConstants.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ericsson.eniq.events.common.client.datatype.ChartItemDataType;
import com.ericsson.eniq.events.common.client.datatype.ThresholdDataType;
import com.ericsson.eniq.events.highcharts.client.ChartEnums;
import com.ericsson.eniq.events.highcharts.client.ChartEnums.eSeriesType;
import com.ericsson.eniq.events.highcharts.client.RawStringType;
import com.ericsson.eniq.events.highcharts.client.options.BaseOptions;
import com.ericsson.eniq.events.highcharts.client.options.LegendOptions;
import com.ericsson.eniq.events.highcharts.client.options.SeriesOptions;
import com.ericsson.eniq.events.highcharts.client.options.ToolTipOptions;
import com.ericsson.eniq.events.highcharts.client.options.XAxisOptions;
import com.ericsson.eniq.events.highcharts.client.options.YAxisOptions;
import com.ericsson.eniq.events.highcharts.client.options.axis.AxisEventOptions;
import com.ericsson.eniq.events.highcharts.client.options.axis.AxisLabelOptions;
import com.ericsson.eniq.events.highcharts.client.options.axis.AxisPlotBandsOptions;
import com.ericsson.eniq.events.highcharts.client.options.axis.AxisTitleOptions;
import com.ericsson.eniq.events.highcharts.client.options.series.PointOptions;
import com.google.gwt.i18n.client.DateTimeFormat;

/**
 * Methods and Data common to all XY Axes Charts (Line, Column, Bar etc).
 * @author ecarsea
 * @since 2011
 *
 */

/**
 * @author ecarsea
 * @since 2011
 */
public abstract class AbstractAxesChartConfiguration extends AbstractChartConfiguration {

    private static final Logger LOGGER = Logger.getLogger(AbstractAxesChartConfiguration.class.getName());

    protected XAxisOptions xAxis;

    private boolean alignAxes = true;

    @Override
    public void buildChart() {
        setChartLoadFunction("$wnd.onAxesChartLoad(this, '" + getChartName() + "'," + MAX_LABEL_ITEM_LENGTH + ");");
        /** X Axis  **/
        xAxis = getXAxisOptions();
        addAxis(xAxis);

        /** Default Left Y Axis **/
        final YAxisOptions yAxisOptions = getYAxisOptions();
        setAxisType(yAxisOptions, true);
        addAxis(yAxisOptions);
        setTooltipOptions();
        setLegendOptions();
        super.buildChart();
        /** Do not align the YAxes ticks as High Charts may not be able to render a left axis with max 100 if it is required to align the ticks
         * with the right y axis.
         */
        if (!getChartMetaData().secondYAxisColID.isEmpty()) {
            if (chartOptions != null) {
                chartOptions.setAlignTicks(alignAxes);
            }
        }
    }

    /**
     * Set the defaults depending on whether axis is right or left.
     * @param yAxisOptions
     * @param leftYAxis
     */
    protected void setAxisType(final YAxisOptions yAxisOptions, final boolean leftYAxis) {
        /* 
         * (Note "yLabel" in meta data defined for "left" y axis only)
         * If the label indicates that the axis is a percentage set max to 100.0 
         * (so undo for right y axis)**/
        if (getChartMetaData().ylabel.contains("%") && leftYAxis) {
            yAxisOptions.setMax(100.0);
            yAxisOptions.setTickInterval(20.0);
            alignAxes = false;
        }

    }

    /**
     * Get the YAxis Options. Override to Specialize
     *
     * @param yAxisOptions
     * @return
     */
    protected YAxisOptions getYAxisOptions() {
        final YAxisOptions yAxisOptions = new YAxisOptions();
        final AxisTitleOptions yAxisTitle = getYAxisTitleOptions();
        yAxisOptions.setTitleOptions(yAxisTitle);

        if (!getChartMetaData().getThresholds().isEmpty()) {
            final List<AxisPlotBandsOptions> plotBands = new ArrayList<AxisPlotBandsOptions>();
            for (final ThresholdDataType threshold : getChartMetaData().getThresholds()) {
                if (threshold.getLowest() != null) {
                    final AxisPlotBandsOptions plotBandOptions = new AxisPlotBandsOptions();
                    plotBandOptions.setFrom(0);
                    plotBandOptions.setTo(threshold.getLowest());
                    plotBandOptions.setColor(DEFAULT_CHART_PLOTBAND_COLOUR);
                    plotBands.add(plotBandOptions);
                }
                if (threshold.getHighest() != null) {
                    final AxisPlotBandsOptions plotBandOptions = new AxisPlotBandsOptions();
                    plotBandOptions.setFrom(threshold.getHighest());
                    plotBandOptions.setTo(Double.MAX_VALUE);
                    plotBandOptions.setColor(DEFAULT_CHART_PLOTBAND_COLOUR);
                    plotBands.add(plotBandOptions);
                }
            }
            yAxisOptions.setPlotBands(plotBands);
        }
        return yAxisOptions;

    }

    protected void setLegendOptions() {
        setLegendOptions(new LegendOptions());
    }

    /**
     * Get the YAxis Label Options. Override to Specialize
     *
     * @return
     */
    protected AxisTitleOptions getYAxisTitleOptions() {
        final AxisTitleOptions yAxisTitle = new AxisTitleOptions();
        yAxisTitle.setText(getChartMetaData().ylabel);
        return yAxisTitle;
    }

    /**
     * Get the XAxis Options. Override to Specialize
     * return xAxisOptions
     */
    protected XAxisOptions getXAxisOptions() {
        final XAxisOptions xAxisOptions = new XAxisOptions();
        xAxisOptions.setTitleOptions(getXAxisTitleOptions());
        final AxisLabelOptions xAxisLabelOptions = getXAxisLabelOptions();
        xAxisOptions.setEvents(getXAxisEventOptions());
        /**
         * If this X Axis is zoomable (Linear X Axis points i.e. time, the we do not need to display all
         * the axis labels (especially if there is a lot of them which may overlap each other). We can display
         * some of the labels at suitable intervals and allow the user to zoom in to see more detail.
         * If we do not define categories for the XAxis High Charts will just use a number progression for each point
         * i.e. 1 to 10 for 10 points. We can use this number in the label formatter to index into our category array
         * and retrieve the label.
         */
        if (this.getChartMetaData().zoomableXAxis) {
            xAxisOptions.setType("datetime");
        } else {
            xAxisOptions.setCategories(getCategories());
        }
        xAxisOptions.setLabelOptions(xAxisLabelOptions);
        return xAxisOptions;
    }

    protected AxisEventOptions getXAxisEventOptions() {
        final AxisEventOptions axisEventOptions = new AxisEventOptions();
        axisEventOptions.setExtremesEventHandler(new RawStringType("function(event) {if(" + getChartName()
                + "!=null) {" + getChartName() + ".resetZoom.element.setAttribute('display', 'inline');"
                + getChartName() + ".dragToZoom.element.setAttribute('display', 'none');" 
                + getChartName() + ".resetZoomButton.hide();}}"));
        return axisEventOptions;
    }

    protected AxisTitleOptions getXAxisTitleOptions() {
        final AxisTitleOptions axisTitleOptions = new AxisTitleOptions();
        axisTitleOptions.setText(getChartMetaData().xlabel);
        axisTitleOptions.setMargin(20);
        return axisTitleOptions;
    }

    protected AxisLabelOptions getXAxisLabelOptions() {
        final AxisLabelOptions xAxisLabelOptions = new AxisLabelOptions();
        xAxisLabelOptions.setRotation(90);
        xAxisLabelOptions.setAlign(ChartEnums.eAlign.left);
        return xAxisLabelOptions;
    }

    protected void setTooltipOptions() {
        final ToolTipOptions toolTipOptions = new ToolTipOptions();
        toolTipOptions.setFormatter(new RawStringType("function() {var zoomableXAxis = "
                + getChartMetaData().zoomableXAxis + ";"
                + "return $wnd.toolTipFormatterAxesChart(this, zoomableXAxis);}"));
        super.setTooltipOptions(toolTipOptions);
    }

    /**
     * @param seriesItem
     * @return
     */
    protected String getColor(final ChartItemDataType seriesItem) {
        return '#' + seriesItem.color;
    }

    /**
     * Create each Series for the chart
     *
     * @param seriesMetaDataList - Meta Data for Each Series
     * @return
     */
    protected List<BaseOptions> createSeriesList(final List<ChartItemDataType> seriesMetaDataList) {
        final List<BaseOptions> seriesList = new ArrayList<BaseOptions>();
        Collections.sort(seriesMetaDataList);
        for (final ChartItemDataType seriesItem : seriesMetaDataList) {
            /** Dont create a series for the category X axis **/
            if (!seriesItem.id.equals(getChartMetaData().xAxisColID) && !seriesItem.isSystem) {
                seriesList.add(getSeriesOptions(seriesItem));
            }
        }
        return seriesList;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.highcharts.client.config.AbstractChartConfiguration#getSeriesList()
     */
    @Override
    protected List<BaseOptions> getSeriesList() {
        return createSeriesList(Arrays.asList(getChartMetaData().itemInfo));
    }

    /**
     * Create an individual Chart Series from the Meta Data for that series
     *
     * @param seriesItem
     * @return
     */
    protected SeriesOptions getSeriesOptions(final ChartItemDataType seriesItem) {
        final SeriesOptions seriesOptions = new SeriesOptions();
        seriesOptions.setName(seriesItem.name);

        seriesOptions.setType(getSeriesType());
        if ((seriesItem.color != null) && !seriesItem.color.isEmpty()) {
            seriesOptions.setColor(getColor(seriesItem));
        }
        if (getRowCount() > 0) {
            seriesOptions.setData(getSeriesDataPoints(seriesItem, seriesOptions));
        } else {
            seriesOptions.setData(getDefaultDataSeriesValues());
        }

        /** Check for second Y axis and set up if required **/
        if (getChartMetaData().isRightYAxisElement(seriesItem.id)) {
            seriesOptions.setYaxis(1);
            buildRightAxis(seriesItem);
        }

        return seriesOptions;
    }

    /**
     * Return the default series type of the chart
     *
     * @return
     */
    protected abstract eSeriesType getSeriesType();

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.highcharts.client.config.AbstractChartConfiguration#createPointOptions(java.lang.String, java.lang.Object, int)
     */
    @Override
    protected PointOptions getPointOptions(final String category, final Object value, final int rowIndex) {
        final PointOptions pointOptions = new PointOptions();
        if (this.getChartMetaData().zoomableXAxis) {
            final DateTimeFormat dtf = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss.SSS");
            try {
                final Date date = dtf.parse(category);
                pointOptions.setX(date.getTime());

            } catch (final IllegalArgumentException e) {

                pointOptions.setName(category);

                LOGGER.log(Level.WARNING,
                        "IllegalArgumentException attempting to parse following to a date (check zoomableXAxis property is false in meta date)"
                                + category, e);

            }
        } else {
            pointOptions.setName(category);
        }
        pointOptions.setY(value);
        if (getRowCount() > 0) {
            setToolTipData(pointOptions, rowIndex);
        }
        return pointOptions;
    }

    /**
     * Create Right Axis with the Meta Data for the associated series
     *
     * @param seriesItem - series meta data
     */
    protected void buildRightAxis(final ChartItemDataType seriesItem) {

        final YAxisOptions rightYAxis = getYAxisOptions();
        rightYAxis.setLineColor(getColor(seriesItem));
        rightYAxis.setOpposite(true);
        rightYAxis.setTitleOptions(getRightAxisTitleOptions(seriesItem));
        rightYAxis.setGridLineWidth(0);//making right axis's gridLines hide to avoid misalignment of right and left y-axis
        addAxis(rightYAxis);
    }

    /**
     * @param seriesItem series meta data
     * @return
     */
    protected AxisTitleOptions getRightAxisTitleOptions(final ChartItemDataType seriesItem) {
        final AxisTitleOptions yAxisTitleOptions = new AxisTitleOptions();
        yAxisTitleOptions.setText(seriesItem.name);
        return yAxisTitleOptions;
    }

    public void setAlignAxes(final boolean alignAxes) {
        this.alignAxes = alignAxes;
    }
}
