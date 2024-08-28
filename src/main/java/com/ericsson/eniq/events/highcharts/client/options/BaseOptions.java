/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options;

import java.util.HashMap;
import java.util.Map;

/**
 * @author eendmcm
 * @author ecarsea
 * @since 2011
 * 
 * Base class for all HighChart Option Types
 */
public abstract class BaseOptions { //NOPMD

    /** Identifier of the option type */
    private final String optionsObjectId;

    /** Map of all the options relating to the Option Type    */
    protected final Map<String, Object> properties = new HashMap<String, Object>();

    /**
     * @param optionsObjectId
     */
    public BaseOptions(final String optionsObjectId) {
        this.optionsObjectId = optionsObjectId;
    }

    /**
     * @return
     */
    public String getOptionsObjectId() {
        return optionsObjectId;
    }

    /**
     * @param property
     * @param o
     */
    public void setProperty(final String property, final Object o) {
        properties.put(property, o);
    }

    /**
     * @return
     */
    public Map<String, Object> getProperties() {
        return properties;
    }

    @Override public String toString() {
        final StringBuilder sb = new StringBuilder(128);
        sb.append("BaseOptions");
        sb.append("{optionsObjectId='").append(optionsObjectId).append('\'');
        sb.append(", properties=").append(properties);
        sb.append('}');
        return sb.toString();
    }
}
