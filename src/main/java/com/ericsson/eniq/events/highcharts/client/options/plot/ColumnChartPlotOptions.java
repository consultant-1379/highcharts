/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options.plot;

import com.ericsson.eniq.events.highcharts.client.ChartEnums.eDashStyle;
import com.ericsson.eniq.events.highcharts.client.ChartEnums.eStacking;

/**
 * Column Chart Plot Options
 * @author eendmcm
 * @since Jun 2011
 *
 * Wrap the Properties exposed by the highcharts API for plotOptions column
 * see http://www.highcharts.com/ref/#plotOptions-column
 */
public class ColumnChartPlotOptions extends AbstractPlotOptions {

    public ColumnChartPlotOptions() {
        super("column");
    }

    /**
     * @param value
     */
    public void setBorderColor(final String value) {
        properties.put("borderColor", value);
    }

    /**
     * @param value
     */
    public void setBorderRadius(final int value) {
        properties.put("borderRadius", value);
    }

    /**
     * @param value
     */
    public void setColorByPoint(final boolean value) {
        properties.put("colorByPoint", value);
    }

    /**
     * @param value
     */
    /**
     * @param value
     */
    public void setGroupPadding(final int value) {
        properties.put("groupPadding", value);
    }

    /**
     * @param value
     */
    public void setMinPointLength(final int value) {
        properties.put("minPointLength", value);
    }

    /**
     * @param value
     */
    public void setPointPadding(final float value) {
        properties.put("pointPadding", value);
    }

    /**
     * @param value
     */
    public void setPointWidth(final int value) {
        properties.put("pointWidth", value);
    }

    /**
     * @param value
     */
    public void setAnimation(final boolean value) {
        properties.put("animation", value);
    }

    /**
     * @param value
     */
    public void setColor(final String value) {
        properties.put("color", value);
    }

    /**
     * @param value
     */
    public void setDashStyle(final eDashStyle value) {
        properties.put("dashStyle", value.toString());
    }

    /**
     * @param dataLabelOptions
     */
    public void setDataLabels(final DataLabelOptions dataLabelOptions) {
        properties.put("dataLabels", dataLabelOptions);
    }

    /**
     * @param value
     */
    public void setEnableMouseTracking(final boolean value) {
        properties.put("enableMouseTracking", value);
    }

    /**
     * @param value
     */
    public void setID(final String value) {
        properties.put("id", value);
    }

    /**
     * @param value
     */
    public void setLineWidth(final int value) {
        properties.put("lineWidth", value);
    }

    /**
     * @param value
     */
    public void setPointStart(final int value) {
        properties.put("pointStart", value);
    }

    /**
     * @param value
     */
    public void setPointInterval(final int value) {
        properties.put("pointInterval", value);
    }

    /**
     * @param value
     */
    public void setSelected(final boolean value) {
        properties.put("selected", value);
    }

    /**
     * @param value
     */
    public void setShadow(final boolean value) {
        properties.put("shadow", value);
    }

    /**
     * @param value
     */
    public void setCheckbox(final boolean value) {
        properties.put("showCheckBox", value);
    }

    /**
     * @param value
     */
    public void setStacking(final eStacking value) {
        properties.put("stacking", value.toString());
    }

    /**
     * @param value
     */
    public void setStickyTracking(final boolean value) {
        properties.put("stickyTracking ", value);
    }

    /**
     * @param value
     */
    public void setVisible(final boolean value) {
        properties.put("visible ", value);
    }

    /**
     * @param value
     */
    public void setZIndex(final int value) {
        properties.put("zIndex ", value);
    }

}
