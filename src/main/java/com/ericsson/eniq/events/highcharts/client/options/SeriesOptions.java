/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client.options;

import com.ericsson.eniq.events.highcharts.client.ChartEnums.eSeriesType;
import com.ericsson.eniq.events.highcharts.client.RawStringType;
import com.ericsson.eniq.events.highcharts.client.options.plot.DataLabelOptions;
import com.ericsson.eniq.events.highcharts.client.options.series.MarkerOptions;
import com.ericsson.eniq.events.highcharts.client.options.series.PointEvents;
import com.ericsson.eniq.events.highcharts.client.options.series.PointOptions;

/**
 * @author eendmcm
 * @author ecarsea
 * @since 2011
 *        <p/>
 *        Wrap the Properties exposed by the highcharts API for Series
 *        see http://www.highcharts.com/ref/#series
 */
public class SeriesOptions extends BaseOptions {

   private static final double DEFAULT_POINT_PADDING = 0;

   private static final double DEFAULT_GROUP_PADDING = 0.2;

   private static final double DEFAULT_BORDER_WIDTH = 1;

   private static final String DEFAULT_BORDER_COLOR = "#ffffff";

   public SeriesOptions() {
      super("series");
      setDefaults(); //NOPMD
   }

   private void setDefaults() {
      setPointPadding(DEFAULT_POINT_PADDING);
      setGroupPadding(DEFAULT_GROUP_PADDING);
      setBorderWidth(DEFAULT_BORDER_WIDTH);
      setBorderColor(DEFAULT_BORDER_COLOR);
      setStickyTracking(false);
      setShadow(true);
   }

   /** @param stickyTracking  */
   public void setStickyTracking(final boolean stickyTracking) {
      properties.put("stickyTracking", stickyTracking);
   }

   /** @param borderColor  */
   public void setBorderColor(final String borderColor) {
      properties.put("borderColor", borderColor);
   }

   /**
    * Allow selection of points for drilling
    *
    * @param select
    */
   public void setAllowPointSelect(final boolean select) {
      properties.put("allowPointSelect", select);
   }

   /** @param cursor  */
   public void setCursor(final String cursor) {
      properties.put("cursor", cursor);
   }

   /** @param value  */
   public void setName(final String value) {
      properties.put("name", value.toString());
   }

   /** @param values  */
   public void setData(final Object[] values) {
      properties.put("data", values);
   }

   /** @param values  */
   public void setData(final PointOptions[] values) {
      properties.put("data", values);
   }

   /** @param value  */
   public void setStacked(final String value) {
      properties.put("stack", value.toString());
   }

   /** @param value  */
   public void setStacked(final int value) {
      properties.put("stack", value);
   }

   /** @param value  */
   public void setType(final eSeriesType type) {
      properties.put("type", type);
   }

   /** @param value  */
   public void setXaxis(final int value) {
      properties.put("xAxis", value);
   }

   /** @param value  */
   public void setYaxis(final int value) {
      properties.put("yAxis", value);
   }

   /** @param point  */
   public void setPointOptions(final PointOptions point) {
      properties.put("point", point);
   }

   /** @param color  */
   public void setColor(final String color) {
      properties.put("color", color);
   }

   /** @param width  */
   public void setPointWidth(final int width) {
      properties.put("pointWidth", width);
   }

   /** @param width  */
   public void setBorderWidth(final double width) {
      properties.put("borderWidth", width);
   }

   /** @param padding  */
   public void setPointPadding(final double padding) {
      properties.put("pointPadding", padding);
   }

   /** @param padding  */
   public void setGroupPadding(final double padding) {
      properties.put("groupPadding", padding);
   }

   /** @param markerOptions  */
   public void setMarkerOptions(final MarkerOptions markerOptions) {
      properties.put("marker", markerOptions);
   }

   /** @param shadow  */
   public void setShadow(final boolean shadow) {
      properties.put("shadow", shadow);
   }

   /** @param pointStart  */
   public void setPointStart(final RawStringType pointStart) {
      properties.put("pointStart", pointStart);
   }

    /**
     * @param pointInterval
     */
    public void setPointInterval(final int pointInterval) {
        properties.put("pointInterval", pointInterval);
    }

    /**
     * @param stack
     */
    public void setStack(final String stack) {
        properties.put("stack", stack);
    }

    /**
     * @param dataLabelOptions
     */
    public void setDataLabelOptions(final DataLabelOptions dataLabelOptions) {
        properties.put("dataLabels", dataLabelOptions);
    }

    /**
     * @param show
     */
    public void setShowInLegend(final boolean show) {
        properties.put("showInLegend", show);
    }

    /** @param events  */
    public void setEvents(final PointEvents events) {
        properties.put("events", events);
    }
}
