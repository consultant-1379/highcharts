/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client;

import com.ericsson.eniq.events.highcharts.client.events.IPointEventHandler;
import com.ericsson.eniq.events.highcharts.client.events.IPrintEventHandler;
import com.ericsson.eniq.events.highcharts.client.events.IRangeSelectedEventHandler;
import com.ericsson.eniq.events.highcharts.client.events.PointEventHandler;
import com.ericsson.eniq.events.highcharts.client.events.PrintEventHandler;
import com.ericsson.eniq.events.highcharts.client.events.RangeSelectedEventHandler;

/**
 * Class for exporting methods as Javascript global variables. Used in High Charts for event
 * callbacks. These variables are global in the Javascript namespace.
 *
 * @author ecarsea
 * @since 2011
 */
public class HighChartsJSMethodExporter {

    private static final HighChartsJSMethodExporter _INSTANCE = new HighChartsJSMethodExporter();

    private boolean exportCompleted;

    private HighChartsJSMethodExporter() {
    }

    public static HighChartsJSMethodExporter get() {
        return _INSTANCE;
    }

    public void exportJSMethods() {
        if (!exportCompleted) {
            exportRegisterChartsMethod(ChartRegistry.get());
            exportPointClickMethod(PointEventHandler.get());
            exportRangeSelectedMethod(RangeSelectedEventHandler.get());
            exportPrintClickMethod(PrintEventHandler.get());
            exportCompleted = true;
        }
    }

    /**
     * Export the registChart method. Allow a chart to register itself from native JS so GWT
     * can then use the Chart JS Object for resizing, adding event listeners etc.
     *
     * @param registry
     */
    private native void exportRegisterChartsMethod(ChartRegistry registry)
    /*-{
    	$wnd.registerChart = function(obj) {
    		return @com.ericsson.eniq.events.highcharts.client.ChartRegistry::registerChart(Lcom/ericsson/eniq/events/highcharts/client/ChartRegistry;Lcom/google/gwt/core/client/JavaScriptObject;)(registry, obj);
    	}
    }-*/;

    /**
     * Export the onHCPointClickMethod to Javascript so point clicks on a chart element can pass the click event back to
     * GWT code.
     *
     * @param peh
     */
    private native void exportPointClickMethod(IPointEventHandler peh)
    /*-{
    	var handler = peh;
    	$wnd.onHCPointClick = function(obj) {
    		return handler.@com.ericsson.eniq.events.highcharts.client.events.PointEventHandler::onPointClick(Lcom/ericsson/eniq/events/highcharts/client/events/PointClickEvent;)(obj);
    	}
    }-*/;

    private native void exportRangeSelectedMethod(IRangeSelectedEventHandler rsh)
    /*-{
    	var handler = rsh;
    	$wnd.onRangeSelected = function(obj) {
    		return handler.@com.ericsson.eniq.events.highcharts.client.events.RangeSelectedEventHandler::onRangeSelected(Lcom/ericsson/eniq/events/highcharts/client/events/RangeSelectedEventJSO;)(obj);
    	}
    }-*/;

    private native void exportPrintClickMethod(IPrintEventHandler peh)
    /*-{
                var handler = peh;
                $wnd.onPrintClick = function(obj) {
                        return handler.@com.ericsson.eniq.events.highcharts.client.events.PrintEventHandler::onPrintClick(Lcom/ericsson/eniq/events/highcharts/client/events/PrintClickEvent;)(obj);
                }
    }-*/;
}
