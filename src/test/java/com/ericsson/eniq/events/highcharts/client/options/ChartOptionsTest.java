/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options;

import static junit.framework.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.highcharts.client.ChartEnums.eSeriesType;
import com.ericsson.eniq.events.highcharts.client.ChartEnums.eZoomType;
import com.ericsson.eniq.events.highcharts.client.OptionsJSSerializer;

/**
 * @author ecarsea
 * @since 2011
 *
 */
public class ChartOptionsTest {
    private ChartOptions objectToTest;

    private static final String EXPECTED_JS = "chart: {renderTo: 'containerId', animation: false, backgroundColor: '#023456', zoomType: 'xy', defaultSeriesType: 'bar'}";

    private static final String CONTAINER_ID = "containerId";

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        objectToTest = new ChartOptions(CONTAINER_ID);
    }

    @Test
    public void testOptions() {
        objectToTest.setBackgroundColor("#023456");
        objectToTest.setSeriesType(eSeriesType.bar);
        objectToTest.setZoomType(eZoomType.xy);
        assertEquals(OptionsJSSerializer.getSerialisedString(objectToTest), EXPECTED_JS);
    }

}
