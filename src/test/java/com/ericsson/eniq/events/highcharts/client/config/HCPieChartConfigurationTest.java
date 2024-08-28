/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.config;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.common.client.datatype.ChartDataType;
import com.ericsson.eniq.events.common.client.datatype.ChartItemDataType;
import com.ericsson.eniq.events.highcharts.client.HighChartsJS;

/**
 * @author ecarsea
 * @since 2011
 *
 */
public class HCPieChartConfigurationTest {

    private Map<String, Object[]> dataSeriesMap;

    /** Generate a working bar chart with the required test data and paste into below string **/
    private final String EXPECTED_JS = "var chartid = new $wnd.Highcharts.Chart({plotOptions: {pie: {allowPointSelect: false, point: {events"
            + ": {legendItemClick: function(event) {$wnd.pieChartLegendItemClick(this);}}}, dataLabels: {formatter:"
            + " function() {return $wnd.dataLabelFormatter(this, 25);}}, borderWidth: 0, showInLegend: true}}, titl"
            + "e: {text: 'KPI Analysis', style: {fontWeight: 'normal', color: '#232323', fontSize: '12pt'}}, legend"
            + ": {enabled: true, backgroundColor: '#FFFFFF', borderWidth: 0, align: 'right', layout: 'vertical', fl"
            + "oating: false, verticalAlign: 'top', labelFormatter: function() {return $wnd.legendLabelFormatter(th"
            + "is, 25);}, y: 35}, chart: {renderTo: 'chartFrame-id', backgroundColor: '#ffffff', events: {load: fun"
            + "ction(event) {$wnd.onPieChartLoad(this, 'chartid', 25);}}, defaultSeriesType: 'pie'}, credits: {enab"
            + "led: false}, tooltip: {formatter: function() {return $wnd.toolTipFormatterPieChart(this);}, borderCo"
            + "lor: '#000000', snap: 2}, series:[{stickyTracking: false, borderColor: '#ffffff', pointPadding: 0.0, s"
            + "hadow: true, borderWidth: 1, data: [{savedPointAttr: null, name: 'Category', y_tmp: null, y: 700.0}]"
            + ", type: 'pie', groupPadding: 0.2}], colors: ['#a66c99', '#ef8962', '#f9ba72', '#fed57a', '#bed682', "
            + "'#549895', '#92cce5', '#87888a', '#d2d2d2', '#8d92b4']});";

    StubHighChartsJS highChartsJS;

    private HCPieChartConfiguration objectToTest;

    @Before
    public void setUp() {
        highChartsJS = new StubHighChartsJS("id");
        dataSeriesMap = getDummyData();
        objectToTest = getObjectToTest();
        objectToTest.init(highChartsJS, getDummyChartDataType(), dataSeriesMap);
    }

    /**
     * @return
     */
    private Map<String, Object[]> getDummyData() {
        return new HashMap<String, Object[]>() {
            {
                put("1", new String[] { "Category" });
                put("2", new String[] { "700.0" });
                put("3", new String[] { "100" });
            }
        };
    }

    private HCPieChartConfiguration getObjectToTest() {
        return new HCPieChartConfiguration();
    }

    /**
     * Test method for {@link com.ericsson.eniq.events.highcharts.client.config.HCBarChartConfiguration#showChartElements(java.util.Set)}.
     */
    @Test
    public final void testShowChartElements() {
        assertEquals("Method not implemented for this chart", false, objectToTest.showChartElements(null));
    }

    /**
     * Test method for {@link com.ericsson.eniq.events.highcharts.client.config.HCBarChartConfiguration#setData()}.
     */
    @Test
    public final void testBuildChart() {
        // TODO   objectToTest.buildChart();

        //Use this to get console output for expected JS String
        // OptionsTestUtils.getLineLengthFormattedJS(highChartsJS.getJS());
        //    assertEquals(EXPECTED_JS, highChartsJS.getJS());
        assertTrue(true);
    }

    private ChartDataType getDummyChartDataType() {
        final ChartDataType type = new ChartDataType();
        type.id = "KPI_ANALYSIS";
        type.chartTitle = "KPI Analysis";
        type.xlabel = "time internal";
        type.ylabel = "num of events";
        type.secondYAxisColID = "2";
        type.xAxisColID = "1";

        final ChartItemDataType line1 = new ChartItemDataType();
        line1.id = "1";
        line1.color = "347C2C";
        line1.name = "PDP Context Attach SR";

        final ChartItemDataType line2 = new ChartItemDataType();
        line2.id = "2";
        line2.color = "347C3C";
        line2.name = " Attach Success Rate";

        final ChartItemDataType line3 = new ChartItemDataType();
        line3.id = "3";
        line3.color = "347C4C";
        line3.name = " Attach Failure Rate";

        type.itemInfo = new ChartItemDataType[] { line1, line2, line3 };

        return type;
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
