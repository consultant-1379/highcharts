/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client;

/**
 * @author eendmcm
 * @since 2011
 * 
 * enums based on constrained values as 
 * documented in http://www.highcharts.com/ref/
 */
public abstract class ChartEnums {

    public static enum eZoomType {
        none, x, y, xy
    }

    public static enum eSeriesType {
        line("line"), spline("spline"), area("area"), areaspline("areaspline"), column("column"), bar("bar"), pie("pie"), scatter(
                "scatter");

        String type;

        private eSeriesType(final String type) {
            this.type = type;
        }

        public static eSeriesType fromString(final String typeStr) {
            for (final eSeriesType type : eSeriesType.values()) {
                if (type.getType().equals(typeStr)) {
                    return type;
                }
            }
            return null;
        }

        /**
         * @return the type
         */
        public String getType() {
            return type;
        }
    };

    public static enum eAlign {
        left, center, right
    };

    public enum eVerticalAlign {
        top, middle, bottom
    };

    public enum eAxisLabelAlign {
        low, center, high
    };

    public enum eDashStyle {
        Solid, ShortDash, ShortDot, ShortDashDot, ShortDashDotDot, Dot, Dash, LongDash, DashDot, LongDashDot, LongDashDotDot
    }

    /*
     * Used to sepcify if a column is stacked 
     * If nothing is set the default of null is applied
     */
    public enum eStacking {
        normal, percent
    }

    public enum eLayout {
        horizontal, vertical
    }

    public enum eChartType {
        area, areaspline, bar, column, line, pie, scatter, spline
    }

}
