/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author ecarsea
 * @since 2011
 *
 */
public class ChartRegistryTest {

    private StubChartRegistry objectToTest;

    private StubJSO stubJSO;

    private static final String TEST_CHART_ID = "chartId";

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        objectToTest = new StubChartRegistry();
        stubJSO = new StubJSO();
    }

    /**
     * Test method for {@link com.ericsson.eniq.events.highcharts.client.ChartRegistry#get()}.
     */
    @Test
    public final void testGet() {
        assertNotNull(ChartRegistry.get());
    }

    /**
     * Test method for {@link com.ericsson.eniq.events.highcharts.client.ChartRegistry#registerChart(com.google.gwt.core.client.JavaScriptObject)}.
     */
    @Test
    public final void testRegisterChart() {
        StubChartRegistry.registerChart(objectToTest, stubJSO);
        assertEquals(1, objectToTest.size());
    }

    /**
     * Test method for {@link com.ericsson.eniq.events.highcharts.client.ChartRegistry#getChart(java.lang.String)}.
     */
    @Test
    public final void testGetChart() {
        StubChartRegistry.registerChart(objectToTest, stubJSO);
        assertNotNull(objectToTest.getChart(TEST_CHART_ID));
    }

    /**
     * Test method for {@link com.ericsson.eniq.events.highcharts.client.ChartRegistry#removeChart(java.lang.String)}.
     */
    @Test
    public final void testRemoveChart() {
        StubChartRegistry.registerChart(objectToTest, stubJSO);
        assertEquals(1, objectToTest.size());
        objectToTest.removeChart(TEST_CHART_ID);
        assertEquals(0, objectToTest.size());
    }

    private class StubJSO extends JavaScriptObject {

    }

    private class StubChartRegistry extends ChartRegistry {

        /* (non-Javadoc)
         * @see com.ericsson.eniq.events.highcharts.client.ChartRegistry#getChartId(com.google.gwt.core.client.JavaScriptObject)
         */
        @Override
        protected String getChartId(final JavaScriptObject jso) {
            return TEST_CHART_ID;
        }

    }
}
