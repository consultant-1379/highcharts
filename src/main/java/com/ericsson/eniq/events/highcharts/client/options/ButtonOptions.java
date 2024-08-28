package com.ericsson.eniq.events.highcharts.client.options;

import com.ericsson.eniq.events.highcharts.client.ChartEnums;
import com.ericsson.eniq.events.highcharts.client.RawStringType;

/**
 * 
 * @author ekurshi
 * @since 2012
 *
 */
public class ButtonOptions extends BaseOptions {

    public ButtonOptions(final String id) {
        super(id);
        setDefaults();
    }

    protected void setDefaults() {
        setAlign(ChartEnums.eAlign.right);
        setHeight(20);
        setWidth(24);
        setSymbolSize(12);
    }

    public void setAlign(final ChartEnums.eAlign alignValue) {
        properties.put("align", alignValue.toString());
    }

    public void setWidth(final int width) {
        properties.put("width", width);
    }

    public void setHeight(final int height) {
        properties.put("height", height);
    }

    public void setY(final int y) {
        properties.put("y", y);
    }

    public void setVerticalAlign(final ChartEnums.eVerticalAlign verticalAlignValue) {
        properties.put("verticalAlign", verticalAlignValue.toString());
    }

    public void setBackgroundColor(final RawStringType value) {
        properties.put("backgroundColor", value);
    }

    public void setBorderColor(final String value) {
        properties.put("borderColor", value.toString());
    }

    public void setBorderRadius(final String value) {
        properties.put("borderRadius", value.toString());
    }

    public void setBorderWidth(final int value) {
        properties.put("borderWidth", value);
    }

    public void setHoverBorderColor(final String color) {
        properties.put("hoverBorderColor", color);
    }

    public void setHoverSymbolFill(final String color) {
        properties.put("hoverSymbolFill", color);
    }

    public void setHoverSymbolStroke(final String color) {
        properties.put("hoverSymbolStroke", color);
    }

    public void setSymbolFill(final String color) {
        properties.put("symbolFill", color);
    }

    public void setSymbolSize(final int value) {
        properties.put("symbolSize", value);
    }

    public void setSymbolStroke(final String color) {
        properties.put("symbolStroke", color);
    }

    public void setSymbolStrokeWidth(final int value) {
        properties.put("symbolStrokeWidth", value);
    }

    public void setSymbol(final String symbol) {
        properties.put("symbol", symbol);
    }

    public void setSymbolX(final double value) {
        properties.put("symbolX", value);
    }

    public void setSymbolY(final double value) {
        properties.put("symbolY", value);
    }
}
