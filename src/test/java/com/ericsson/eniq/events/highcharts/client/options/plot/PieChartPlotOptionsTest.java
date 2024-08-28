/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options.plot;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.highcharts.client.OptionsJSSerializer;

/**
 * @author ecarsea
 * @since 2011
 *
 */
public class PieChartPlotOptionsTest {
    private PieChartPlotOptions objectToTest;

    private static final String EXPECTED_JS = "pie: {allowPointSelect: true, borderWidth: 4, showInLegend: false}";

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        objectToTest = new PieChartPlotOptions();
    }

    @Test
    public void testOptions() {
        objectToTest.setAllowPointSelect(true);
        objectToTest.setBorderWidth(4);
        objectToTest.setShowInLegend(false);
        assertEquals("Object generates correct JS string", OptionsJSSerializer.getSerialisedString(objectToTest),
                EXPECTED_JS);
    }
}