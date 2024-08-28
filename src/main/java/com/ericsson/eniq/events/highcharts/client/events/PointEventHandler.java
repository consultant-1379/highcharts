/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.events;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to handle events on the High Charts Point items. Register a single Listener for a chart.
 * Needs to be a singleton as registry is shared.
 * @author ecarsea
 * @since 2011
 *
 */
public class PointEventHandler implements IPointEventHandler {

    /** Registry implementation. Keyed on Chart Id mapped to a Chart Event Listener **/
    private final Map<String, IHighChartsEventListener> registry = new HashMap<String, IHighChartsEventListener>();

    private static final PointEventHandler _INSTANCE = new PointEventHandler();

    private PointEventHandler() {
    };

    public static PointEventHandler get() {
        return _INSTANCE;
    }

    /**
     * Register a listener for a chart
     * @param id - Chart Id
     * @param listener
     */
    public synchronized void registerListener(final String chartId, final IHighChartsEventListener listener) {
        registry.put(chartId, listener);
    }

    /* 
     * Map a Point Click event from native JS to a GWT Listener using the Chart Id
     * (non-Javadoc)
     * @see com.ericsson.eniq.events.highcharts.client.events.IPointEventHandler#onPointClick(com.ericsson.eniq.events.highcharts.client.events.PointClickEvent)
     */
    @Override
    public final void onPointClick(final PointClickEvent pointClickEvent) {
        if (registry.containsKey(pointClickEvent.getId())) {
            registry.get(pointClickEvent.getId()).onPointClick(pointClickEvent);
        }
    }

    /**
     * Remove High Charts Listener from registry
     * @param id
     * @return
     */
    public synchronized IHighChartsEventListener removeListener(final String chartId) {
        return registry.remove(chartId);
    }
}
