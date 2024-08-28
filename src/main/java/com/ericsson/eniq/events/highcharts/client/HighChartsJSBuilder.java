/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client;

import static com.ericsson.eniq.events.highcharts.client.ChartConstants.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.ericsson.eniq.events.highcharts.client.options.BaseOptions;

/**
 * Low Level Javascript Builder for HighCharts. Builds up the High Charts JS object from collections of 
 * Objects and Arrays of Objects.
 * @author eendmcm
 * @author ecarsea
 * @since 2011
 *
 * Builds the Javascript Object required to generate a chart.
 */
public class HighChartsJSBuilder {

    /** Arrays of Object Options Map keyed by the Options Id **/
    private final Map<String, List<? extends BaseOptions>> chartArrayObjects = new HashMap<String, List<? extends BaseOptions>>();

    /** Object Options Map keyed by the Options Id **/
    private final Map<String, BaseOptions> chartObjects = new HashMap<String, BaseOptions>();

    /** Options Arrays Map keyed by the Options Id **/
    private final Map<String, Object[]> chartArrays = new HashMap<String, Object[]>();

    /**
     * Chart Options where the options id has a single property which is an array of objects of any type 
     * as opposed to a BaseOptions object or an Array of BaseOptions objects 
     * i.e. The colors option on the Charts is an array of Strings.
     * @param optionsObjectId
     * @param array
     */
    public void addChartArray(final String optionsObjectId, final Object[] array) {
        chartArrays.put(optionsObjectId, array);
    }

    /** add a Single Chart Options Object to a Map **/
    public void addChartOptions(final BaseOptions obj) {
        /** If this object is already in the Single Options Object Map, then remove it and put both 
         * elements into the array of objects map. I.e. if we have a yAxis already and want to add another one.
         */
        if (chartObjects.containsKey(obj.getOptionsObjectId())) {
            chartArrayObjects.put(obj.getOptionsObjectId(), new LinkedList<BaseOptions>() {
                {
                    add(chartObjects.remove(obj.getOptionsObjectId()));
                    add(obj);
                }
            });
        } else if (chartArrayObjects.containsKey(obj.getOptionsObjectId())) {
            ((LinkedList<BaseOptions>) chartArrayObjects.get(obj.getOptionsObjectId())).add(obj);
        } else {
            chartObjects.put(obj.getOptionsObjectId(), obj);
        }
    }

    /**
     * adds the Object(s) to the chart and accounts for these as been multiples of
     * the property at root level e.g. yAxis: [{object1}, {object2}]
     * @param objs
     * @throws Exception 
     */
    public void addChartPropertyAsCollection(final BaseOptions... optionsList) {
        addChartPropertyAsCollection(Arrays.asList(optionsList));
    }

    /**
     * adds the Object to the chart and accounts for these as been multiples of
     * the property at root level e.g. yAxis: [{object1}, {object2}]
     * @param objs
     * @throws Exception 
     */
    public void addChartPropertyAsCollection(final List<? extends BaseOptions> objs) {
        if (objs != null && !objs.isEmpty()) {
            chartArrayObjects.put(objs.get(0).getOptionsObjectId(), objs);
        }
    }

    /**
     * iterates the chartObjects Map and generates the 
     * a serialised JavaScript object for each element. These are packaged
     * into one JavaScript object and returned to the calling method 
     * @return
     */
    public String getChartObjSerialised() {
        StringBuilder chartStr = new StringBuilder();

        /** Single Javascript Chart Object **/
        for (final Entry<String, BaseOptions> chartObjectsEntry : chartObjects.entrySet()) {
            chartStr.append(OptionsJSSerializer.getSerialisedString(chartObjectsEntry.getValue()));
            chartStr.append(BASE_SEPERATOR);
        }

        /** Iterate through the map of Options Arrays and serialize **/
        for (final Entry<String, List<? extends BaseOptions>> chartArrayObjectsEntry : chartArrayObjects.entrySet()) {
            chartStr.append(chartArrayObjectsEntry.getKey());
            chartStr.append(COLLECTION_START);

            /** Serialize the arraylist elements **/
            for (final BaseOptions elem : chartArrayObjectsEntry.getValue()) {
                chartStr.append(OptionsJSSerializer.serialiseProperties(elem));
                chartStr.append(BASE_SEPERATOR);
            }

            /** remove the trailing ", " **/
            if (chartArrayObjectsEntry.getValue().size() > 0) {
                chartStr = HighChartUtils.removeTrailingSeperator(chartStr);
            }
            chartStr.append(COLLECTION_END);

            chartStr.append(BASE_SEPERATOR);
        }

        /**
         * Add the Chart Options where the Options Identifier maps to a single property which is an array of objects
         */
        for (final Entry<String, Object[]> chartArrayEntry : chartArrays.entrySet()) {
            chartStr.append(OptionsJSSerializer.getSerializedArray(chartArrayEntry.getKey(), chartArrayEntry.getValue()));
            chartStr.append(BASE_SEPERATOR);
        }
        /** remove the trailing ", " if any of the three previous loops inserted some data into the Chart Options buffer**/
        if (hasChartOptionsData()) {
            chartStr = HighChartUtils.removeTrailingSeperator(chartStr);
        }

        return chartStr.toString();
    }

    /** Any of the 3 chart options data structures not empty.
     * @return
     */
    private boolean hasChartOptionsData() {
        return !chartObjects.isEmpty() || !chartArrayObjects.isEmpty() || !chartArrays.isEmpty();
    }

    /** Clear stored Chart Options. Use if we need to redraw the chart with new options  */
    public void clearChartOptions() {
        this.chartArrayObjects.clear();
        this.chartObjects.clear();
        this.chartArrays.clear();
    }
}
