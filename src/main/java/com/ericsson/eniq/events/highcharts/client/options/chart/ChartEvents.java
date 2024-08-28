/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options.chart;

import com.ericsson.eniq.events.highcharts.client.RawStringType;
import com.ericsson.eniq.events.highcharts.client.options.BaseOptions;

/**
 * Events for Chart itself
 * @author ecarsea
 * @since 2011
 *
 */
public class ChartEvents extends BaseOptions {
    public ChartEvents() {
        super("events");
    }

    /**
     * Set event to raise when chart loads. 
     * @param loadEvent
     */
    public void setLoadEvent(final RawStringType loadEvent) {
        properties.put("load", loadEvent);
    }

    /**
     * Set event to raise when chart redraws. 
     * @param redrawEvent
     */
    public void setRedrawEvent(final RawStringType redrawEvent) {
        properties.put("redraw", redrawEvent);
    }

    /**
     * Fires when an area of the chart has been selected. Selection is enabled by setting the chart's zoomType. The this keyword refers to the chart object itself.
     * @param redrawEvent
     */
    public void setSelectionEvent(final RawStringType selectionEventHandler) {
        properties.put("selection", selectionEventHandler);
    }
}
