/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.events;

/**
 * Handle Point Click Events directly from Native JS.
 * @author ecarsea
 * @since 2011
 *
 */
public interface IPointEventHandler {

    /**
     * PointClick from JS.
     * @param pointClickEvent - JS object with event properties
     */
    void onPointClick(PointClickEvent pointClickEvent);
}
