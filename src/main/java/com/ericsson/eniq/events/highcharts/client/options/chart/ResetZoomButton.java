package com.ericsson.eniq.events.highcharts.client.options.chart;

import com.ericsson.eniq.events.highcharts.client.options.BaseOptions;

public class ResetZoomButton extends BaseOptions {
    public ResetZoomButton() {
        super("resetZoomButton");

    }

    public void setPosition(Position position) {
        properties.put("position", position);
    }

    public void setRelativeTo(String relativeTo) {
        properties.put("relativeTo", relativeTo);
    }
}
