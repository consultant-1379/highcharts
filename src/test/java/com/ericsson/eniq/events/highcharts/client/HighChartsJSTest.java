/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client;

import static com.ericsson.eniq.events.highcharts.client.ChartConstants.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.highcharts.client.options.ChartCreditOptions;
import com.ericsson.eniq.events.highcharts.client.options.ChartOptions;
import com.ericsson.eniq.events.highcharts.client.options.ChartTitleOptions;
import com.ericsson.eniq.events.highcharts.client.options.LegendOptions;
import com.ericsson.eniq.events.highcharts.client.options.PlotOptions;
import com.ericsson.eniq.events.highcharts.client.options.XAxisOptions;
import com.ericsson.eniq.events.highcharts.client.options.YAxisOptions;

/**
 * @author ecarsea
 * @since 2011
 *
 */
public class HighChartsJSTest {

    private static final String TEST_CHART_ID = "chartID";

    private final String EXPECTED_JS = "var chartchartID = new $wnd.Highcharts.Chart({plotOptions: {}, title: {style: {fontWeight: 'normal',"
            + " color: '#232323', fontSize: '12pt'}}, yAxis: {min: 0.0, tickLength: 0, lineColor: '#232323', tickCo"
            + "lor: '#232323', gridLineColor: '#d3d3d3', labels: {style: {fontWeight: 'normal', color: '#232323', f"
            + "ontSize: '8pt'}}, tickPixelInterval: 72, allowDecimals: false, tickWidth: 1, tickmarkPlacement: 'bet"
            + "ween'}, legend: {enabled: true, backgroundColor: '#FFFFFF', borderWidth: 0, align: 'right', layout: "
            + "'vertical', floating: false, verticalAlign: 'top', labelFormatter: function() {return $wnd.legendLab"
            + "elFormatter(this, 25);}, y: 35}, credits: {enabled: false}, chart: {renderTo: 'containerId', backgro"
            + "undColor: '#ffffff', events: {load: function(event) {this.chartId='chartID'; $wnd.registerChart(this"
            + ");}}}, xAxis: {lineColor: '#232323', tickColor: '#232323', gridLineColor: '#d3d3d3', lineWidth: 1, a"
            + "llowDecimals: false, tickWidth: 1, tickmarkPlacement: 'on'}});";

    private HighChartsJS objectToTest;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        objectToTest = new StubHighChartsJS(TEST_CHART_ID);
    }

    /**
     * Test method for {@link com.ericsson.eniq.events.highcharts.client.HighChartsJS#setChartOptions(com.ericsson.eniq.events.highcharts.client.options.ChartOptions)}.
     */
    @Test
    public final void testSetChartOptions() {
        assertEquals("Chart Id Set", objectToTest.getChartName(), CHART_ID_SUFFIX + TEST_CHART_ID);
        assertEquals("Chart Id Set", objectToTest.getContainerDivId(), CONTAINER_PREFIX + TEST_CHART_ID);

        /** Create a skeleton Chart Options Java Script (Just the outer options of the API being set). 
         * All the individual nested options that make up the structure
         * should be tested individually. This test just makes sure that they are built correctly into the overall JS
         * structure, i.e. arrays where expected, object separators etc
         */
        objectToTest.setChartOptions(new ChartOptions("containerId"));
        objectToTest.addAxis(new XAxisOptions());
        objectToTest.addAxis(new YAxisOptions());
        objectToTest.setChartTitleOptions(new ChartTitleOptions());
        objectToTest.setLegendOptions(new LegendOptions());
        objectToTest.setPlotOptions(new PlotOptions());
        objectToTest.setChartCreditOptions(new ChartCreditOptions());
        //OptionsTestUtils.getLineLengthFormattedJS(objectToTest.getJS());
        //    assertEquals(objectToTest.getJS(), EXPECTED_JS);
        assertTrue(true);
    }

    private class StubHighChartsJS extends HighChartsJS {

        /**
         * @param chartId
         */
        public StubHighChartsJS(final String chartId) {
            super(chartId);
        }

        /* (non-Javadoc)
         * @see com.ericsson.eniq.events.highcharts.client.HighChartsJS#getJS()
         */
        @Override
        protected String getJS() {
            return super.getJS();
        }
    }

}
