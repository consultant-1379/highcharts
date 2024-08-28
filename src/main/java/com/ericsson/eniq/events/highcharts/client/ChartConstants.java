/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client;

/**
 * @author eendmcm
 * @since 2011
 * Javascript String Builder Constants used within Highcharts wrapper code
 */
public abstract class ChartConstants {

    public static final String SINGLE_QUOTE = "'";

    public static final String BASE_SEPERATOR = ", ";

    public static final String COLLECTION_START = ":[";

    public static final String COLLECTION_END = "]";

    public static final String BASE_SEPERATOR_TRIMMED = ",";

    public static final String BLOCK_END = "}";

    public static final String BASE_COL_START = "[";

    public static final String BASE_OBJ_START = "{";

    public static final String BASE_PROP_START = ": ";

    public static final String JS_VAR = "var ";

    public static final String CHART_ID_SUFFIX = "chart";

    public static final String CONTAINER_PREFIX = "chartFrame-";

    public static final String CHART_DECLARATION_START = " = new $wnd.Highcharts.Chart({";

    public static final String CURSOR_POINTER = "pointer";

    /** Max String length of chart label items **/
    public static final int MAX_LABEL_ITEM_LENGTH = 25;
    
    public static final int MAX_X_LABEL_ITEM_LENGTH = 10;

    public static final int WIZARD_OVERLAY_COLLAPSED_HEIGHT = 23;

    public static final int ZOOMABLE_XAXIS_PIXEL_INTERVAL = 40;

    public final static String ELLIPSE = "...";

    public final static String[] PIE_COLORS = new String[] { "#a66c99", "#ef8962", "#f9ba72", "#fed57a", "#bed682",
            "#549895", "#92cce5", "#87888a", "#d2d2d2", "#8d92b4" }; //new tints line 1

    public final static String[] LINE_COLORS = new String[] { "#a66c99", "#ef8962", "#f9ba72", "#fed57a", "#bed682",
            "#549895", "#92cce5", "#87888a", "#d2d2d2", "#8d92b4" };

    public static final String DEFAULT_TEXT_COLOR = "#232323";

    public static final String DEFAULT_LINE_COLOR = "#232323";

    /**
     * Display "no data" on the label (else you get a big black blur (on line chart anyway)
     */
    public final static String NO_DATA_LABEL = "No Data";

    /**
     * Display text below on the x axis label (change of code to try to display blank graph on launch
     * and force user to select lines from view menu - without this since we have no line we would have no axis
     */
    public final static String DATA_AVAILABLE_NOT_SHOWN_LABEL = "Data Available";

    /** Max number of labels for a standard chart in a window. Greater than this requires scrolling **/
    public final static int MAX_LABELS_PER_WINDOW_DEFAULT = 25;

    /** Offset for the chart window when a scrollbar is displayed. **/
    public final static int CHART_WINDOW_SCROLLBAR_OFFSET = 18;

    public final static String LEGEND_FONT_MANY_ITEMS = "10px";

    public final static String DEFAULT_FONT_FAMILY = "Arial";

    public final static String DEFAULT_CHART_PLOTBAND_COLOUR = "#cccedd";

    /**
     * store chart element e.g. Nokia group name information,
     * (as apposed to number value which is all we had),
     * into DataConfig properties (of ChartEvent) with this key.
     * We retrieve it from ChartEvent as chart element click
     */
    public final static String CHART_ELEMENT_SELECTED_KEY = "elementKey";

    /**
     * Constant used as the key to store the Chart Meta ID for
     * parameter passing purposes when end user clicks to drill
     */
    public final static String CHART_META_ID = "CHART_META_ID";

    /**
     * property setting on drillable chart elements,
     * i.e. drillDownWindowType (indicating grid to launch from a chart element)
     */
    public final static String CHART_ELEMENT_DRILLDOWN_KEY = "drilldownKey";

    /** Row Index of clicked point **/
    public final static String DATA_POINT_ROW_INDEX = "rowIndex";

    public final static String CHART_ELEMENT_SERIES_ID = "chartSeriesId";

    public final static String POINT_START_TIME = "pointStartTime";

    public final static String POINT_END_TIME = "pointEndTime";

}
