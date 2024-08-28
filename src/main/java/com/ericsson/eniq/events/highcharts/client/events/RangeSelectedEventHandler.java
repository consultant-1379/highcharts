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
public class RangeSelectedEventHandler implements IRangeSelectedEventHandler {

    /** Registry implementation. Keyed on Chart Id mapped to a Chart Event Handler **/
    private final Map<String, IRangeSelectedEventHandler> registry = new HashMap<String, IRangeSelectedEventHandler>();

    private static final RangeSelectedEventHandler _INSTANCE = new RangeSelectedEventHandler();

    private RangeSelectedEventHandler() {
    };

    public static RangeSelectedEventHandler get() {
        return _INSTANCE;
    }

    /**
     * Register a handler for a chart
     * @param id - Chart Id
     * @param handler
     */
    public synchronized void registerHandler(final String chartId, final IRangeSelectedEventHandler handler) {
        registry.put(chartId, handler);
    }

    /**
     * Remove handler from registry
     * @param id
     * @return
     */
    public synchronized IRangeSelectedEventHandler removeHandler(final String chartId) {
        return registry.remove(chartId);
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.highcharts.client.events.IRangeSelectedEventHandler#onRangeSelected(com.ericsson.eniq.events.highcharts.client.events.RangeSelectedEventJSO)
     */
    @Override
    public void onRangeSelected(final RangeSelectedEventJSO rangeSelectedEvent) {
        if (registry.containsKey(rangeSelectedEvent.getId())) {
            registry.get(rangeSelectedEvent.getId()).onRangeSelected(rangeSelectedEvent);
        }
    }
}
