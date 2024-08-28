/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options.axis;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.highcharts.client.OptionsJSSerializer;

/**
 * @author ecarsea
 * @since 2011
 *
 */
public class AxisPlotBandsOptionsTest {
    private AxisPlotBandsOptions objectToTest;

    private final static String EXPECTED_JS = "plotBands: {to: 10.0, id: 'ID', zIndex: 5, color: '#000000', label: null, from: 0.0}";

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        objectToTest = new AxisPlotBandsOptions();
    }

    @Test
    public void testOptions() {
        objectToTest.setColor("#000000");
        objectToTest.setFrom(0);
        objectToTest.setID("ID");
        /** Empty Java script object, test AxisLabel options object on its own as dont want
         * to be picking up default options here which may break our test if they change 
         */
        objectToTest.setLabel(null);
        objectToTest.setTo(10);
        objectToTest.setZIndex(5);
        assertEquals(EXPECTED_JS, OptionsJSSerializer.getSerialisedString(objectToTest));
    }
}
