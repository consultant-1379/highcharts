/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options.plot;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.highcharts.client.ChartEnums.eDashStyle;
import com.ericsson.eniq.events.highcharts.client.ChartEnums.eStacking;
import com.ericsson.eniq.events.highcharts.client.OptionsJSSerializer;

/**
 * @author ecarsea
 * @since 2011
 *
 */
public class ColumnChartPlotOptionsTest {
    private ColumnChartPlotOptions objectToTest;

    private final String EXPECTED_JS = "column: {borderRadius: 5, enableMouseTracking: true, cursor: 'cursor', borderColor: '#ffffff', id: '"
            + "id', dataLabels: null, selected: false, pointPadding: 1.0, shadow: false, stacking: 'percent', visib"
            + "le : true, stickyTracking : true, pointInterval: 2, pointWidth: 2, showInLegend: true, dashStyle: 'D"
            + "ash', animation: false, colorByPoint: false, lineWidth: 5, pointStart: 0, minPointLength: 2, allowPo"
            + "intSelect: true, color: '#000000', zIndex : 3, showCheckBox: true, borderWidth: 10, groupPadding: 2}";

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        objectToTest = new ColumnChartPlotOptions();
    }

    @Test
    public void testOptions() {
        objectToTest.setAllowPointSelect(true);
        objectToTest.setColor("#000000");
        objectToTest.setAnimation(false);
        objectToTest.setBorderColor("#ffffff");
        objectToTest.setBorderRadius(5);
        objectToTest.setBorderWidth(10);
        objectToTest.setCheckbox(true);
        objectToTest.setColorByPoint(false);
        objectToTest.setCursor("cursor");
        objectToTest.setDashStyle(eDashStyle.Dash);
        /** Empty Java script object, test DataLabel options object on its own as dont want
         * to be picking up default options here which may break our test if they change 
         */
        objectToTest.setDataLabels(null);
        objectToTest.setGroupPadding(2);
        objectToTest.setID("id");
        objectToTest.setLineWidth(5);
        objectToTest.setMinPointLength(2);
        objectToTest.setPointInterval(2);
        objectToTest.setPointPadding(1);
        objectToTest.setPointStart(0);
        objectToTest.setEnableMouseTracking(true);
        objectToTest.setPointWidth(2);
        objectToTest.setSelected(false);
        objectToTest.setShadow(false);
        objectToTest.setShowInLegend(true);
        objectToTest.setStacking(eStacking.percent);
        objectToTest.setStickyTracking(true);
        objectToTest.setVisible(true);
        objectToTest.setZIndex(3);
        //  OptionsTestUtils.getLineLengthFormattedJS(OptionsJSSerializer.getSerialisedString(objectToTest));
        assertEquals("Object generates correct JS string", OptionsJSSerializer.getSerialisedString(objectToTest),
                EXPECTED_JS);
    }
}
