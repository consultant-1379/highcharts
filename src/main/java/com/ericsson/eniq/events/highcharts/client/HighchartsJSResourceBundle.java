package com.ericsson.eniq.events.highcharts.client;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * 
 * @author ekurshi
 * @since 2013
 *
 */
public interface HighchartsJSResourceBundle extends ClientBundle {

    @Source("jquery.min.js")
    TextResource jqueryJS();

    @Source("highcharts.js")
    TextResource highchartsJS();

    @Source("exporting.js")
    TextResource exportJS();

    @Source("highchartsutils.js")
    TextResource highchartUtilsJS();

}
