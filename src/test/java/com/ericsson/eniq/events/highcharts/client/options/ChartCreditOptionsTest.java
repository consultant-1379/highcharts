/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options;

import static junit.framework.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.highcharts.client.OptionsJSSerializer;

/**
 * @author ecarsea
 * @since 2011
 *
 */
public class ChartCreditOptionsTest {
    private ChartCreditOptions objectToTest;

    private static final String EXPECTED_JS = "credits: {enabled: true}";

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        objectToTest = new ChartCreditOptions();
    }

    @Test
    public void testOptions() {
        objectToTest.setEnabled(true);
        assertEquals(OptionsJSSerializer.getSerialisedString(objectToTest), EXPECTED_JS);
    }

}
