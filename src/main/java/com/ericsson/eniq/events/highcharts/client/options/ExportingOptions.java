/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options;

import com.google.gwt.core.client.GWT;

/**
 * Chart Options
 * @author eendmcm
 * @author ecarsea
 * @since 2011
 * see http://www.highcharts.com/ref/#chart
 */
public class ExportingOptions extends BaseOptions {

    public static final String HIGHCHARTS_EXPORTER_URL = "highcharts/exporter";

    public ExportingOptions() {
        super("exporting");
        setDefaults(); //NOPMD
    }

    protected void setDefaults() {
        setUrl(GWT.getHostPageBaseURL() + HIGHCHARTS_EXPORTER_URL);
    }

    /**
     * @param url
     */
    public void setUrl(final String url) {
        properties.put("url", url);
    }

    /**
     * @param enabled
     */
    public void setEnabled(final boolean enabled) {
        properties.put("enabled", enabled);
    }

    /**
     * @param exportFileName
     */
    public void setFileName(final String exportFileName) {
        properties.put("filename", exportFileName);
    }

    public void setExportButtonOptions(final ExportButtonsOptions buttonsOptions) {
        properties.put(buttonsOptions.getOptionsObjectId(), buttonsOptions);
    }
}
