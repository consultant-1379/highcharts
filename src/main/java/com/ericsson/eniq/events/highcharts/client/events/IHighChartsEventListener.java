/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.events;

/**
 * Listen to High Charts events. Generic interface for the moment. Can be broken into specific interfaces when required
 * @author ecarsea
 * @since 2011
 *
 */
public interface IHighChartsEventListener {
    /**
     * Handle click of a point on the chart
     * @param pointClickEvent
     */
    void onPointClick(PointClickEvent pointClickEvent);
}
