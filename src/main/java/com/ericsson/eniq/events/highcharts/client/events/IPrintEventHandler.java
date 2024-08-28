/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.events;

/**
 * Handle print button click event from Native Highchart JS
 * @author ekurshi
 * @since 2012
 *
 */
public interface IPrintEventHandler {

    void onPrintClick(PrintClickEvent printClickEvent);
}
