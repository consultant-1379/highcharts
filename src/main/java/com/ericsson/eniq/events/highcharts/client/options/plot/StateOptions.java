/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options.plot;

import com.ericsson.eniq.events.highcharts.client.options.BaseOptions;

/**
 * @author egallou
 * @since 2012
 *
 * Wrap the Properties exposed by the highcharts API for States
 */

public class StateOptions extends BaseOptions {

    public StateOptions() {
        super("states");
    }

    /**
     * @param hoverOptions
     */
    public void setHoverState(final HoverOptions hoverOptions) {
        properties.put("hover", hoverOptions);
    }

}
