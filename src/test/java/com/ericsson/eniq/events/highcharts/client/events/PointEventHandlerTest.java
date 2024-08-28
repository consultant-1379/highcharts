/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.events;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author ecarsea
 * @since 2011
 *
 */
public class PointEventHandlerTest {
    private PointEventHandler objectToTest;

    private IHighChartsEventListener mockListener;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        objectToTest = PointEventHandler.get();
        mockListener = new IHighChartsEventListener() {
            public void onPointClick(PointClickEvent pointClickEvent) {
            }
        };
    }

    /**
     * Test method for {@link com.ericsson.eniq.events.highcharts.client.events.PointEventHandler#get()}.
     */
    @Test
    public void testGet() {
        assertNotNull("PointEventHandler Singleton", objectToTest);
    }

    /**
     * Test method for {@link com.ericsson.eniq.events.highcharts.client.events.PointEventHandler#registerListener(java.lang.String, com.ericsson.eniq.events.highcharts.client.events.IHighChartsEventListener)}.
     */
    @Test
    public void testRegisterRemoveListener() {
        objectToTest.registerListener("id", mockListener);
        assertEquals("Listener registered and removed", mockListener, objectToTest.removeListener("id"));
        assertEquals("Listener no longer in registry", null, objectToTest.removeListener("id"));
    }
}
