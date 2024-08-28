package com.ericsson.eniq.events.highcharts.client.options.chart;

import com.ericsson.eniq.events.highcharts.client.options.BaseOptions;

/**
 * Created by IntelliJ IDEA.
 * User: emauoco
 * Date: 10/04/12
 * Time: 15:34
 * To change this template use File | Settings | File Templates.
 */
public class Position extends BaseOptions {

    public Position() {
        super("position");
    }

    public void setAlign(String align) {
        properties.put("align", align);
    }

    public void setX(int x) {
        properties.put("x", x);
    }

    public void setY(int y) {
        properties.put("y", y);
    }
}
