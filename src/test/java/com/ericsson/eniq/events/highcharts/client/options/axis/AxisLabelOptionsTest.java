/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options.axis;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.highcharts.client.ChartEnums.eAlign;
import com.ericsson.eniq.events.highcharts.client.OptionsJSSerializer;
import com.ericsson.eniq.events.highcharts.client.RawStringType;

/**
 * @author ecarsea
 * @since 2011
 *
 */
public class AxisLabelOptionsTest {
    private AxisLabelOptions objectToTest;

    private final String EXPECTED_JS = "labels: {formatter: function(e){}), enabled: true, text: 'Test', staggerLines: 5, style: null, rotat"
            + "ion: 4, align: 'center', y: 10, step: 10, x: 5}";

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        objectToTest = new AxisLabelOptions();
    }

    @Test
    public void testOptions() {
        objectToTest.setAlign(eAlign.center);
        objectToTest.setEnabled(true);
        objectToTest.setFormatter(new RawStringType("function(e){})"));
        objectToTest.setRotation(4);
        objectToTest.setStaggerLines(5);
        /** Empty Java script object, test CSSStyle options object on its own as dont want
         * to be picking up default options here which may break our test if they change 
         */
        objectToTest.setStyleOptions(null);
        objectToTest.setText("Test");
        objectToTest.setStep(10);
        objectToTest.setX(5);
        objectToTest.setY(10);
     //   OptionsTestUtils.getLineLengthFormattedJS(OptionsJSSerializer.getSerialisedString(objectToTest));
        assertEquals(EXPECTED_JS, OptionsJSSerializer.getSerialisedString(objectToTest));
    }
}
