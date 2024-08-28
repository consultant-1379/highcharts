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
public class PointClickEvent extends JavaScriptObject {
    protected PointClickEvent() {
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
    public final native NativeEventJSO getNativeEvent()
    /*-{
                return this.nativeEvent;
    }-*/;

    /**
     * @return
     */
    public final native double getY()
    /*-{
    	return this.y;
    }-*/;

    /**
     * @return
     */
    public final native String getCategory()
    /*-{
    	return this.category;
    }-*/;

    public final native double getTime()
    /*-{
    	return this.category == null ? this.x : this.category;
    }-*/;

    /**
     * @return
     */
    public final native String getId()
    /*-{
    	return this.id;
    }-*/;

    public final native double getDouble(final String property)
    /*-{
    	return this[property];
    }-*/;

}
