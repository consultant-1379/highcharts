/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options.series;

import com.ericsson.eniq.events.highcharts.client.options.BaseOptions;

/**
 * Class holding a single query parameter for use with drillable chart points
 * @author ecarsea
 * @since 2011
 *
 */
public class QueryParamOptions extends BaseOptions {

    public static final String OBJECT_ID = "queryParams";

    public QueryParamOptions() {
        super(OBJECT_ID);
    }

    /**
     * @param name
     */
    public void setName(final String name) {
        properties.put("name", name);
    }

    /**
     * @param value
     */
    public void setValue(final Object value) {
        properties.put("value", value);
    }

}
