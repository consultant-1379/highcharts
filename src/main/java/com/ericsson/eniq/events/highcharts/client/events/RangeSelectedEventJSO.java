/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.events;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Javascript Overlay Type containing the Point Click Event properties
 * @author ecarsea
 * @since 2011
 *
 */
public class RangeSelectedEventJSO extends JavaScriptObject {
    protected RangeSelectedEventJSO() {
    }

    /**
     * @param property
     * @return
     */
    public final native Object getProperty(String property)
    /*-{
        return this[property];
    }-*/;

    /**
     * @return
     */
    public final native double getUpperBounds()
    /*-{
        return this.xAxis[0].max;
    }-*/;

    /**
     * @return
     */
    public final native double getLowerBounds()
    /*-{
        return this.xAxis[0].min;
    }-*/;

    /**
     * @return
     */
    public final native String getId()
    /*-{
        return this.id;
    }-*/;

}
