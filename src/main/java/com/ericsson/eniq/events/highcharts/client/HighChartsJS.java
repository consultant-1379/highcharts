/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client;

import static com.ericsson.eniq.events.highcharts.client.ChartConstants.*;

import java.util.List;

import com.ericsson.eniq.events.highcharts.client.options.AbstractAxisOptions;
import com.ericsson.eniq.events.highcharts.client.options.BaseOptions;
import com.ericsson.eniq.events.highcharts.client.options.ChartCreditOptions;
import com.ericsson.eniq.events.highcharts.client.options.ChartOptions;
import com.ericsson.eniq.events.highcharts.client.options.ChartSubTitleOptions;
import com.ericsson.eniq.events.highcharts.client.options.ChartTitleOptions;
import com.ericsson.eniq.events.highcharts.client.options.ExportingOptions;
import com.ericsson.eniq.events.highcharts.client.options.LegendOptions;
import com.ericsson.eniq.events.highcharts.client.options.NavigationOptions;
import com.ericsson.eniq.events.highcharts.client.options.PlotOptions;
import com.ericsson.eniq.events.highcharts.client.options.ToolTipOptions;
import com.google.gwt.core.client.JavaScriptObject;

/**
 * Root of the HighCharts API. Exposes the Root Objects of the High Charts API and the methods such as resize etc.
 * Calls on the Javascript Builder to build up the Javascript string for the chart.
 * @author ecarsea
 * @since 2011
 *
 */
/**
 * @author ecarsea
 * @since 2011
 *
 */
public class HighChartsJS {

    /** Unique Chart Id. Uses also as the Id of the container Div for the chart to avoid confusion between the logical
     * chart id and the DOM chart id.
     */
    private final String chartId;

    /**
     * Builder for generating the JS String to be evaluated.
     */
    private final HighChartsJSBuilder builder;

    /** Offsets from the chart container if space needed between container and chart borders **/
    private int widthOffset;

    private int heightOffset;

    private String chartLoadFunction = "";

    /**
     * This id of the Chart Container HTML element
     */
    private final String containerDivId;

    public HighChartsJS(final String chartId) {
        this.chartId = chartId;
        builder = new HighChartsJSBuilder();
        this.containerDivId = CONTAINER_PREFIX + chartId;
    }

    /**
     * Inject the Javascript for the chart into the DOM 
     */
    public void doRender() {
        //TODO Remove(useful for debugging issues)   
        //        System.out.println(this.getJS());
        this.injectJS(this.containerDivId, this.getJS());
    }

    /**
     * 
     * @param chartOptions
     */
    public void setChartOptions(final ChartOptions chartOptions) {
        builder.addChartOptions(chartOptions);
    }

    /**
     * @param colors
     */
    public void setColourOptions(final String[] colors) {
        builder.addChartArray("colors", colors);
    }

    public void setExportingOptions(final ExportingOptions exportingOptions) {
        builder.addChartOptions(exportingOptions);
    }

    /*
     * @param chartTitleOptions
     */
    public void setChartTitleOptions(final ChartTitleOptions chartTitleOptions) {
        builder.addChartOptions(chartTitleOptions);
    }

    public void setNavigationOptions(final NavigationOptions navigationOptions) {
        builder.addChartOptions(navigationOptions);
    }

    /**
     * @param subTitleOptions
     */
    public void setChartSubTitleOptions(final ChartSubTitleOptions subTitleOptions) {
        builder.addChartOptions(subTitleOptions);
    }

    /**
     * @param chartLoadFunction
     */
    public void setChartLoadFunction(final String chartLoadFunction) {
        this.chartLoadFunction = chartLoadFunction;
    }

    /**
     * @param plotOptions
     */
    public void setPlotOptions(final PlotOptions plotOptions) {
        builder.addChartOptions(plotOptions);
    }

    /**
     * @param axis
     */
    public void addAxis(final AbstractAxisOptions axis) {
        builder.addChartOptions(axis);
    }

    /**
     * @param legendOptions
     */
    public void setLegendOptions(final LegendOptions legendOptions) {
        builder.addChartOptions(legendOptions);
    }

    /**
     * @param seriesOptionsList
     */
    public void setSeriesOptions(final List<BaseOptions> seriesOptionsList) {
        builder.addChartPropertyAsCollection(seriesOptionsList);
    }

    /**
     * @param toolTipOptions
     */
    public void setTooltipOptions(final ToolTipOptions toolTipOptions) {
        builder.addChartOptions(toolTipOptions);
    }

    /**
     * @param chartCreditOptions
     */
    public void setChartCreditOptions(final ChartCreditOptions chartCreditOptions) {
        builder.addChartOptions(chartCreditOptions);
    }

    /**
     * @return
     */
    public final String getContainerDivId() {
        return this.containerDivId;
    }

    /**
     * @return
     */
    public String getChartName() {
        return CHART_ID_SUFFIX + this.chartId;
    }

    /**
     * Get the name of the chart, given the chartId.
     * @param chartId
     * @return
     */
    public static String getChartName(final String chartId) {
        return CHART_ID_SUFFIX + chartId;
    }

    /**
     * @param widthOffset
     */
    public final void setWidthOffset(final int widthOffset) {
        if (widthOffset >= 0) {
            this.widthOffset = widthOffset;
        }
    }

    /**
     * @param heightOffset
     */
    public final void setHeightOffset(final int heightOffset) {
        if (heightOffset >= 0) {
            this.heightOffset = heightOffset;
        }
    }

    /**
     * @param width
     * @param height
     */
    public void doResize(final int width, final int height) {
        resize(ChartRegistry.get().getChart(this.getChartName()), width - this.widthOffset, height - this.heightOffset);
    }

    /**
     * @param jso
     * @param width
     * @param height
     */
    private native void resize(JavaScriptObject jso, int width, int height) /*-{
                                                                            jso.setSize(width, height, false);
                                                                            }-*/;

    /**
     * Serialize the Chart Options to JS
     * @return
     */
    protected String getJS() {
        final StringBuilder buffer = new StringBuilder();
        buffer.append("var " + this.getChartName() + CHART_DECLARATION_START);
        buffer.append(builder.getChartObjSerialised());
        buffer.append("}");
        buffer.append(",function(chart) {" + chartLoadFunction + "});");
        return HighChartUtils.cleanJScript(buffer);
    }

    /**
     * Injects the provided JSscript to the element specified 
     * at render stage using jscript eval. Container obviously must be present in the DOM
     **/
    public native synchronized void injectJS(final String divID, final String jsCode) /*-{
                                                                                      var container = $doc.getElementById(divID);

                                                                                      if (container != null && jsCode != null) {
                                                                                      eval(jsCode);
                                                                                      }
                                                                                      }-*/;

    /**
     * Clear the current chart options for redrawing a chart with a new model
     */
    public void clearJS() {
        builder.clearChartOptions();
    }
}
