/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.config;

import com.ericsson.eniq.events.common.client.datatype.ChartItemDataType;
import com.ericsson.eniq.events.highcharts.client.ChartEnums;
import com.ericsson.eniq.events.highcharts.client.RawStringType;
import com.ericsson.eniq.events.highcharts.client.ChartEnums.eSeriesType;
import com.ericsson.eniq.events.highcharts.client.options.*;
import com.ericsson.eniq.events.highcharts.client.options.plot.DataLabelOptions;
import com.ericsson.eniq.events.highcharts.client.options.plot.PieChartPlotOptions;
import com.ericsson.eniq.events.highcharts.client.options.series.PointEvents;
import com.ericsson.eniq.events.highcharts.client.options.series.PointOptions;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static com.ericsson.eniq.events.highcharts.client.ChartConstants.*;

/**
 * @author ecarsea
 * @since 2011
 *
 */
public class HCPieChartConfiguration extends AbstractChartConfiguration {

    /** Extra configuration parameters to add to the Point Options object **/
    private static final String LEGEND_PREFIX = "legendPrefix";

    private static final String SAVED_POINT_ATTR = "savedPointAttr";

    private static final String Y_TMP = "y_tmp";

    private boolean hasSubTitle;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.highcharts.client.config.AbstractChartConfiguration#buildChart()
     */
    @Override
    public void buildChart() {
        setChartLoadFunction("$wnd.onPieChartLoad(this, '" + getChartName() + "', " + MAX_LABEL_ITEM_LENGTH + ");");
        /** Default Pie Chart Colours Array **/
        setColorOptions(PIE_COLORS);
        setPlotOptions();
        setTooltipOptions();
        setLegendOptions();
        super.buildChart();
    }

    private void setLegendOptions() {
        final LegendOptions legendOptions = new LegendOptions();
        if (getCategories().length > 10) {
            final LegendItemStyleOptions styleOptions = new LegendItemStyleOptions();
            styleOptions.setFontSize(LEGEND_FONT_MANY_ITEMS);
            styleOptions.setFontFamily(DEFAULT_FONT_FAMILY);
            legendOptions.setItemStyle(styleOptions);
        }
        if (hasSubTitle) {
            legendOptions.setY(60);
        }
        /** For a pie chart the categories are actually the legend, so we need to disable the legend if we have
         * no data rows and therefore no categories
         */
        legendOptions.setEnabled(getRowCount() > 0);
        setLegendOptions(legendOptions);
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.highcharts.client.config.AbstractChartConfiguration#setSubTitle(java.lang.String)
     */
    @Override
    public void setSubTitle(final String subTitle) {
        super.setSubTitle(subTitle);
        hasSubTitle = true;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.highcharts.client.config.AbstractChartConfiguration#getChartOptions(java.lang.String)
     */
    @Override
    protected ChartOptions getChartOptions(final String containerDiv) {
        final ChartOptions chartOptions = new ChartOptions(containerDiv);
        chartOptions.setSeriesType(ChartEnums.eSeriesType.pie);
        return chartOptions;
    }

    protected DataLabelOptions getDataLabelOptions() {
        final DataLabelOptions dataLabelOptions = new DataLabelOptions();
        /** Truncate Data Labels longer than MAX_LABEL_ITEM_LENGTH **/
        dataLabelOptions.setFormatter(new RawStringType("function() {return $wnd.dataLabelFormatter(this, "
                + MAX_LABEL_ITEM_LENGTH + ");}"));
        return dataLabelOptions;
    }

    private void setPlotOptions() {
        final PlotOptions plotOptions = new PlotOptions();
        plotOptions.setPieChartOptions(getPieChartPlotOptions());
        super.setPlotOptions(plotOptions);
    }

    /**
     * @return
     */
    protected PieChartPlotOptions getPieChartPlotOptions() {
        final PieChartPlotOptions pieChartPlotOptions = new PieChartPlotOptions();
        pieChartPlotOptions.setDataLabels(getDataLabelOptions());
        pieChartPlotOptions.setPointOptions(getPointOptions());
        return pieChartPlotOptions;
    }

    /**
     * @return
     */
    protected PointOptions getPointOptions() {
        final PointOptions pointOptions = new PointOptions();
        pointOptions.setEvents(getSeriesPointEvents());
        return pointOptions;
    }

    /**
     * @return
     */
    protected PointEvents getSeriesPointEvents() {
        final PointEvents pointEvents = new PointEvents();
        /** When legend Item is clicked remove the point from the chart and Redraw the pie chart.
         * Seems to be a high charts bug that sometimes PointAttr[''].fill becomes undefined so
         * need to take that into account when saving the point attributes.  */
        //@formatter:off
        pointEvents.setLegendItemClickEvent(new RawStringType(
                "function(event) {$wnd.pieChartLegendItemClick(this);}"
          ));
        //@formatter:on
        return pointEvents;
    }

    @Override
    protected PointOptions[] getSeriesDataPoints(final ChartItemDataType chartItemDataType,
            final SeriesOptions seriesOptions) {
        final Object[] values = getSeriesObjects(chartItemDataType.id);
        final List<PointOptions> pointsList = new LinkedList<PointOptions>();
        for (int i = 0; i < values.length; i++) {
            final PointOptions pointOptions = getPointOptions(getCategory(i).toString(), values[i], i);
            setDrillablePoint(chartItemDataType, seriesOptions, i, pointOptions);
            pointsList.add(pointOptions);
        }
        Collections.sort(pointsList, new Comparator<PointOptions>() {

            @Override
            public int compare(final PointOptions po1, final PointOptions po2) {
                return po1.getProperties().get("name").toString().compareTo(po2.getProperties().get("name").toString());
            }
        });
        return pointsList.toArray(new PointOptions[0]);
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.highcharts.client.config.AbstractChartConfiguration#getSeriesList()
     */
    @Override
    protected List<BaseOptions> getSeriesList() {
        final SeriesOptions seriesOptions = new SeriesOptions();
        seriesOptions.setType(eSeriesType.pie);

        seriesOptions.setData(getSeriesDataPoints(getChartDataTypeForId(getChartMetaData().secondYAxisColID),
                seriesOptions));

        return (new LinkedList<BaseOptions>() {
            {
                add(seriesOptions);
            }
        });
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.highcharts.client.config.AbstractChartConfiguration#createPointOptions(java.lang.Object[], int)
     */
    @Override
    protected PointOptions getPointOptions(final String category, final Object value, final int rowIndex) {
        final PointOptions pointOptions = new PointOptions();
        pointOptions.setName(category);
        pointOptions.setY(value);
        pointOptions.setProperty(Y_TMP, null);
        pointOptions.setProperty(SAVED_POINT_ATTR, null);
        if (getRowCount() > 0) {
            setToolTipData(pointOptions, rowIndex);
        }

        for (final ChartItemDataType chartDataType : getChartMetaData().itemInfo) {
            /** Meta Data indicator that this series item should be in a prefix of the legend. Assume only
             * one of the chart series types can be used as a prefix. **/
            if (chartDataType.isLegendPrefix) {
                pointOptions.setProperty(LEGEND_PREFIX, getSeriesValue(rowIndex, chartDataType.id));
                break;
            }
        }
        return pointOptions;
    }

    private void setTooltipOptions() {
        final ToolTipOptions toolTipOptions = new ToolTipOptions();
        toolTipOptions.setFormatter(new RawStringType("function() {return $wnd.toolTipFormatterPieChart(this);}"));
        super.setTooltipOptions(toolTipOptions);
    }

    /* 
    * This method should return default data series values in case empty data is received from services. This is
    * required in order to render an empty chart rather than just have a blank window. Default is 1 for pie chart the chart wont render for a value of 0
    * 
    * @return
    */
    @Override
    protected String[] getDefaultDataSeriesValues() {
        return new String[] { "1" };
    }

    /* 
     * Need a label for the pie chart in order to indicate that No Data is available
     * (non-Javadoc)
     * @see com.ericsson.eniq.events.highcharts.client.config.AbstractChartConfiguration#getCategories()
     */
    @Override
    protected Object[] getDefaultCategories() {
        return new String[] { NO_DATA_LABEL };
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.highcharts.client.config.AbstractChartConfiguration#requiresScrollableChart()
     */
    @Override
    public boolean requiresScrollableChart() {
        // Pie is not scrollable
        return false;
    }

}
