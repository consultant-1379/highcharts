/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options;

import static com.ericsson.eniq.events.highcharts.client.ChartConstants.*;

import com.ericsson.eniq.events.highcharts.client.ChartEnums;
import com.ericsson.eniq.events.highcharts.client.RawStringType;
import com.ericsson.eniq.events.highcharts.client.ChartEnums.eAlign;
import com.ericsson.eniq.events.highcharts.client.ChartEnums.eLayout;
import com.ericsson.eniq.events.highcharts.client.ChartEnums.eVerticalAlign;

/**
 * Wrap the Properties exposed by the highcharts API for Legend
 * see http://www.highcharts.com/ref/#legend
 * @author eendmcm
 * @since 2011
 */
public class LegendOptions extends BaseOptions {
    /** Legend offset from Chart Top **/
    protected static final int DEFAULT_LEGEND_Y = 35;

    public LegendOptions() {
        super("legend");
        setDefaults(); //NOPMD
    }

    /**
     * 
     */
    private void setDefaults() {
        setEnabled(true);
        setBackgroundColor("#FFFFFF");
        setBorderWidth(0);
        setFloating(false);
        setVerticalAlign(eVerticalAlign.top);
        setLayout(eLayout.vertical);
        setAlign(eAlign.right);
        setY(DEFAULT_LEGEND_Y);
        setItemStyle(new LegendItemStyleOptions());
        /**
         * Truncate Legend Item Labels longer than MAX_LABEL_ITEM_LENGTH
         */
        setFormatter(new RawStringType("function() {return $wnd.legendLabelFormatter(this, " + MAX_LABEL_ITEM_LENGTH
                + ");}"));
    }

    /**
     * @param value
     */
    public void setAlign(final ChartEnums.eAlign value) {
        properties.put("align", value.toString());
    }

    /**
     * @param options
     */
    public void setStyleOptions(final LegendItemStyleOptions options) {
        properties.put("itemStyle", options);
    }

    /**
     * @param value
     */
    public void setBackgroundColor(final String value) {
        properties.put("backgroundColor", value.toString());
    }

    /**
     * @param value
     */
    public void setBorderColor(final String value) {
        properties.put("borderColor", value.toString());
    }

    /**
     * @param value
     */
    public void setBorderRadius(final String value) {
        properties.put("borderRadius", value.toString());
    }

    /**
     * @param value
     */
    public void setBorderWidth(final int value) {
        properties.put("borderWidth", value);
    }

    /**
     * @param value
     */
    public void setEnabled(final boolean value) {
        properties.put("enabled", value);
    }

    /**
     * @param value
     */
    public void setFloating(final boolean value) {
        properties.put("floating", value);
    }

    /**
     * @param style
     */
    public void setItemHiddenStyle(final CSSStyleOptions style) {
        properties.put("itemHiddenStyle", style);
    }

    /**
     * @param style
     */
    public void setItemHoverStyle(final CSSStyleOptions style) {
        properties.put("itemHoverStyle", style);
    }

    /**
     * @param style
     */
    public void setItemStyle(final LegendItemStyleOptions style) {
        properties.put("itemStyle", style);
    }

    /**
     * @param value
     */
    public void setItemWidth(final int value) {
        properties.put("itemWidth", value);
    }

    /**
     * @param value
     */
    public void setLayout(final eLayout value) {
        properties.put("layout", value.toString());
    }

    /**
     * @param value
     */
    public void setLineHeight(final int value) {
        properties.put("lineHeight", value);
    }

    /**
     * @param value
     */
    public void setMargin(final int value) {
        properties.put("margin", value);
    }

    /**
     * @param value
     */
    public void setReversed(final boolean value) {
        properties.put("reversed", value);
    }

    /**
     * @param value
     */
    public void setShadow(final boolean value) {
        properties.put("shadow", value);
    }

    /**
     * @param style
     */
    public void setStyle(final CSSStyleOptions style) {
        properties.put("itemStyle", style);
    }

    /**
     * @param value
     */
    public void setSymbolPadding(final int value) {
        properties.put("symbolPadding", value);
    }

    /**
     * @param value
     */
    public void setSymbolWidth(final int value) {
        properties.put("symbolWidth", value);
    }

    /**
     * @param value
     */
    public void setVerticalAlign(final eVerticalAlign value) {
        properties.put("verticalAlign", value.toString());
    }

    /**
     * @param value
     */
    public void setWidth(final int value) {
        properties.put("width", value);
    }

    /**
     * @param value
     */
    public void setX(final int value) {
        properties.put("x", value);
    }

    /**
     * @param value
     */
    public void setY(final int value) {
        properties.put("y", value);
    }

    /**
     * @param formatter
     */
    public final void setFormatter(final RawStringType formatter) {
        properties.put("labelFormatter", formatter);
    }
}
