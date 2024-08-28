/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options;

/**
 * Animation Options class
 * @author ecarsea
 * @since 2011
 *
 */
public class AnimationOptions extends BaseOptions {

    /**
     * @param optionsObjectId
     */
    public AnimationOptions() {
        super("animation");
    }

    /**
     * @param duration
     */
    public void setDuration(final int duration) {
        properties.put("duration", duration);
    }

}
