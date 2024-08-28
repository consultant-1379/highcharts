/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client;

/**
 * A raw string is used for options which string representation must not be quoted.
 * Example - it is useful for expressing functions to append as handlers
 * @author ecarsea
 * @since 2011
 *
 */
public class RawStringType {
    private final String value;

    public RawStringType(final String s) {
        if ((s != null) && (s.length() > 0)) {
            this.value = s.trim();
        } else {
            this.value = null;
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
