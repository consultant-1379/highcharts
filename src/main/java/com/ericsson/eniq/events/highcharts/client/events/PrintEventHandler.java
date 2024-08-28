/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.events;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to handle print events on the High Charts. Register a single Listener for a chart.
 * Needs to be a singleton as registry is shared.
 * @author ekurshi
 * @since 2012
 *
 */
public class PrintEventHandler implements IPrintEventHandler {

    /** Registry implementation. Keyed on Chart Id mapped to a Chart Event Listener **/
    private final Map<String, IPrintEventHandler> registry = new HashMap<String, IPrintEventHandler>();

    private static final PrintEventHandler _INSTANCE = new PrintEventHandler();

    private PrintEventHandler() {
    };

    public static PrintEventHandler get() {
        return _INSTANCE;
    }

    /**
     * Register a listener for a chart
     * @param id - Chart Id
     * @param listener
     */
    public synchronized void registerListener(final String chartId, final IPrintEventHandler listener) {
        registry.put(chartId, listener);
    }

    @Override
    public final void onPrintClick(final PrintClickEvent printClickEvent) {
        if (registry.containsKey(printClickEvent.getId())) {
            registry.get(printClickEvent.getId()).onPrintClick(printClickEvent);
        }
    }

    /**
     * Remove High Charts Listener from registry
     * @param id
     * @return
     */
    public synchronized IPrintEventHandler removeListener(final String chartId) {
        return registry.remove(chartId);
    }
}
