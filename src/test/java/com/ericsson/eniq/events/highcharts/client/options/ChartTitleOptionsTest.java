/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.highcharts.client.ChartEnums.eAlign;
import com.ericsson.eniq.events.highcharts.client.ChartEnums.eVerticalAlign;
import com.ericsson.eniq.events.highcharts.client.OptionsJSSerializer;

/**
 * @author ecarsea
 * @since 2011
 *
 */
public class ChartTitleOptionsTest {
    private ChartTitleOptions objectToTest;

    private static final String EXPECTED_JS = "title: {text: 'Test', style: null, align: 'center', floating: true, margin: 4, verticalAlign: 'bottom', y: 50, x: 10}";

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        objectToTest = new ChartTitleOptions();
    }

    @Test
    public void testOptions() {
        objectToTest.setAlign(eAlign.center);
        objectToTest.setFloating(true);
        objectToTest.setMargin(4);
        /** Empty Java script object, test CSSStyle options object on its own as dont want
         * to be picking up default options here which may break our test if they change 
         */
        objectToTest.setStyleOptions(null);
        objectToTest.setText("Test");
        objectToTest.setVerticalAlign(eVerticalAlign.bottom);
        objectToTest.setXPosition(10);
        objectToTest.setYPosition(50);
        assertEquals(OptionsJSSerializer.getSerialisedString(objectToTest), EXPECTED_JS);
    }

}
