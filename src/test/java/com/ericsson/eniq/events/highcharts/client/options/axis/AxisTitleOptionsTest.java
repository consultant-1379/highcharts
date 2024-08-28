/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options.axis;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.highcharts.client.ChartEnums.eAxisLabelAlign;
import com.ericsson.eniq.events.highcharts.client.OptionsJSSerializer;

/**
 * @author ecarsea
 * @since 2011
 *
 */
public class AxisTitleOptionsTest {
    private AxisTitleOptions objectToTest;

    private static final String EXPECTED_JS = "title: {text: 'Test', style: null, rotation: 3, align: 'center', margin: 4}";

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        objectToTest = new AxisTitleOptions();
    }

    @Test
    public void testOptions() {
        objectToTest.setAlign(eAxisLabelAlign.center);
        objectToTest.setMargin(4);
        /** Empty Java script object, test CSSStyle options object on its own as dont want
         * to be picking up default options here which may break our test if they change 
         */
        objectToTest.setStyleOptions(null);
        objectToTest.setText("Test");
        objectToTest.setRotation(3);
        assertEquals(EXPECTED_JS, OptionsJSSerializer.getSerialisedString(objectToTest));
    }
}
