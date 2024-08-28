/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.events;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Javascript Overlay Type containing the Native Library Event properties - in this case JQuery
 * @author ecarsea
 * @since 2011
 *
 */
public class NativeEventJSO extends JavaScriptObject {
    protected NativeEventJSO() {
    }

    /**
     * @param pageX
     * @return
     */
    public final native int getPageX()
    /*-{
    	return this.pageX;
    }-*/;

    /**
     * @param pageY
     * @return
     */
    public final native int getPageY()
    /*-{
        return this.pageY;
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
