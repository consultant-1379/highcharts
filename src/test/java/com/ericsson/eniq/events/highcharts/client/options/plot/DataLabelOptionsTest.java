/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options.plot;

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
public class DataLabelOptionsTest {
    private DataLabelOptions objectToTest;

    private final String EXPECTED_JS = "dataLabels: {formatter: function(e){}, enabled: true, style: null, color: '#000000', rotation: 0, "
            + "align: 'center', y: 10, x: 5}";

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        objectToTest = new DataLabelOptions();
    }

    @Test
    public void testOptions() {
        objectToTest.setAlign(eAlign.center);
        objectToTest.setColor("#000000");
        objectToTest.setEnabled(true);
        objectToTest.setFormatter(new RawStringType("function(e){}"));
        objectToTest.setRotation(0);
        /** Empty Java script object, test CSSStyle options object on its own as dont want
         * to be picking up default options here which may break our test if they change 
         */
        objectToTest.setStyle(null);
        objectToTest.setX(5);
        objectToTest.setY(10);
        assertEquals("Object generates correct JS string", OptionsJSSerializer.getSerialisedString(objectToTest),
                EXPECTED_JS);
    }
}
