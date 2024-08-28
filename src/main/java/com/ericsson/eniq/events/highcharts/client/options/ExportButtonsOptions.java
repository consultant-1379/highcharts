package com.ericsson.eniq.events.highcharts.client.options;

/**
 * 
 * @author ekurshi
 * @since 2012
 *
 */
public class ExportButtonsOptions extends BaseOptions {

    public ExportButtonsOptions() {
        super("buttons");
    }

    public void addButtonOptions(final String buttonName, final ButtonOptions options) {
        properties.put(buttonName, options);
    }

    public void setPrintButtonOptions(final PrintButtonOptions options) {
        properties.put("printButton", options);
    }

    public void setExportButtonOptions(final ExportButtonOptions options) {
        properties.put("exportButton", options);
    }
}
