/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.events;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Javascript Overlay Type containing the Print Click Event properties
 * @author ekurshi
 * @since 2012
 *
 */
public class PrintClickEvent extends JavaScriptObject {
    protected PrintClickEvent() {
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
    public final native String getId()
    /*-{
    	return this.id;
    }-*/;

}
