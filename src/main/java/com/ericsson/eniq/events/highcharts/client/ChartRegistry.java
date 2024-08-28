/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * A Registry maintaining the active Charts. The Charts are loaded in native Javascript which calls back into GWT
 * and registers the Chart JavascriptObject with a unique id in this registry. This allows the GWT Java code to retrieve
 * the Chart JS Object and perform operations such as resize, register event handlers etc.
 * Class is a singleton as need to share same instance across application.
 * @author ecarsea
 * @since 2011
 *
 */
public class ChartRegistry {
    /** Internal Registry implementation **/
    private final Map<String, JavaScriptObject> registry = new HashMap<String, JavaScriptObject>();

    private final Map<String, IChartRegisterHandler> handlerMap = new HashMap<String, IChartRegisterHandler>();

    private static final ChartRegistry _INSTANCE = new ChartRegistry();

    protected ChartRegistry() {
    };

    /**
     * 
     * @return
     */
    public static ChartRegistry get() {
        return _INSTANCE;
    }

    /**
     * Register a chart JS object in the Registry. Allow to pass in the Registry Implementation for Junit purposes.
     * @param registry
     * @param jso
     */
    public static void registerChart(final ChartRegistry registry, final JavaScriptObject jso) {
        registry.registerChart(registry.getChartId(jso), jso);
    }

    /**
     * Get Chart JS object.
     * @param id
     * @return
     */
    public JavaScriptObject getChart(final String id) {
        return registry.get(id);
    }

    /**
     * Remove chart from Registry.
     * @param chartId
     * @return
     */
    public synchronized JavaScriptObject removeChart(final String chartId) {
        return registry.remove(chartId);
    }

    /** 
     * Register a chart in the Registry
     * @param id
     * @param jso
     */
    private synchronized void registerChart(final String id, final JavaScriptObject jso) {
        registry.put(id, jso);
        if (handlerMap.containsKey(id)) {
            handlerMap.get(id).onChartRegistered(jso);
        }
    }

    /**
     * @return
     */
    protected int size() {
        return registry.size();
    }

    /**
     * Get the id of the chart which is a property of the native JS object
     * @param jso
     * @return
     */
    protected String getChartId(final JavaScriptObject jso) {
        return getChartIdNative(jso);
    }

    private static native String getChartIdNative(JavaScriptObject jso) /*-{
		return jso.chartId;
    }-*/;

    /**
     * check is the chart with this id in the registry
     * @param chartId
     * @return
     */
    public boolean containsChart(final String chartId) {
        return registry.containsKey(chartId);
    }

    public void addHandler(final String id, final IChartRegisterHandler handler) {
        handlerMap.put(id, handler);
    }

    public void removeHandler(final String id) {
        handlerMap.remove(id);
    }
}
