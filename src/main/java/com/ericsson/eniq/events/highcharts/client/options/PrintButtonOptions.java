package com.ericsson.eniq.events.highcharts.client.options;

import com.ericsson.eniq.events.highcharts.client.RawStringType;

/**
 * 
 * @author ekurshi
 * @since 2012
 *
 */
public class PrintButtonOptions extends ButtonOptions {

    public PrintButtonOptions() {
        super("printButton");
    }

    @Override
    protected void setDefaults() {
        super.setDefaults();
        setEnabled(true);
        setX(-36);
        setSymbol("url(./resources/icons/print.png)");
        setSymbolX(10);
        setSymbolY(8);
    }

    public void setClickHandler(final RawStringType handler) {
        properties.put("onclick", handler);
    }

    public void setX(final int x) {
        properties.put("x", x);
    }

    public void setEnabled(final boolean enabled) {
        properties.put("enabled", enabled);
    }
}
