/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author ecarsea
 * @since 2011
 *
 */
public class BaseOptionsTest {
    private StubBaseOptions objectToTest;

    private static final String OBJECT_ID = "objectId";

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        objectToTest = new StubBaseOptions(OBJECT_ID);
    }

    /**
     * Test method for {@link com.ericsson.eniq.events.highcharts.client.options.BaseOptions#getOptionsObjectId()}.
     */
    @Test
    public void testGetOptionsObjectId() {
        assertEquals(OBJECT_ID, objectToTest.getOptionsObjectId());
    }

    /**
     * Test method for {@link com.ericsson.eniq.events.highcharts.client.options.BaseOptions#setProperty(java.lang.String, java.lang.Object)}.
     */
    @Test
    public void testGetSetProperty() {
        final Object o = new Object();
        objectToTest.setProperty("property", o);
        assertEquals("Check Object is set in properties", o, objectToTest.getProperties().get("property"));
    }

    private class StubBaseOptions extends BaseOptions {

        /**
         * @param optionsObjectId
         */
        public StubBaseOptions(final String optionsObjectId) {
            super(optionsObjectId);
        }

    }

}
