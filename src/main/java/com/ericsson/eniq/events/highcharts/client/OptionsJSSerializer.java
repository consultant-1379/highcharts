/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.highcharts.client;

import static com.ericsson.eniq.events.highcharts.client.ChartConstants.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Vector;

import com.ericsson.eniq.events.highcharts.client.options.BaseOptions;

/**
 * Utility class for serializing a set of HighCharts options to JS String
 * @author ecarsea
 * @since 2011
 *
 */
public abstract class OptionsJSSerializer {

    /**
     * Get the serialized JS String for this HighCharts Options Class
     * @param options
     * @return
     */
    public static String getSerialisedString(final BaseOptions options) {
        final StringBuilder buffer = new StringBuilder();
        buffer.append(options.getOptionsObjectId());
        buffer.append(BASE_PROP_START);
        buffer.append(serialiseProperties(options));

        return buffer.toString();
    }

    /**
     * Serialize and High Charts Options property where the property is a single array of objects i.e.
     * the Chart Colors property which is an array of Strings.
     * @param optionsObjectId
     * @param objectArray
     * @return
     */
    public static String getSerializedArray(final String optionsObjectId, final Object[] objectArray) {
        final StringBuilder buffer = new StringBuilder();
        buffer.append(optionsObjectId);
        buffer.append(BASE_PROP_START);
        buffer.append(arrayToString(objectArray));
        return buffer.toString();
    }

    /**
     * Serialize the Options to JS
     * @param options
     * @return
     */
    protected static String serialiseProperties(final BaseOptions options) {
        StringBuilder propsStr = new StringBuilder();
        propsStr.append(BASE_OBJ_START);

        for (final Entry<String, Object> entry : options.getProperties().entrySet()) {
            propsStr.append(entry.getKey() + BASE_PROP_START + parseObjToJS(entry.getValue()) + BASE_SEPERATOR);
        }

        /*        remove the trailing ", "*/
        if (options.getProperties().size() > 0) {
            propsStr = HighChartUtils.removeTrailingSeperator(propsStr);
        }
        propsStr.append(BLOCK_END);
        return propsStr.toString();
    }

    /**
     * Serialize a single object to JS
     * @param value
     * @return
     */
    private static String parseObjToJS(final Object value) { //NOPMD

        if (value == null) {
            return "null";
        }
        /** Numbers and Booleans and RawStringTypes returned in raw form i.e. not within containing quotes */
        if (value instanceof Number || value instanceof Boolean || value instanceof RawStringType) {
            return value.toString();
        }

        /** primitive arrays  */
        if (value.getClass().isArray()) {
            return arrayToString(value);
        }

        /** Recursive call to serialize sub-properties that are also Options classes **/
        if (value instanceof BaseOptions) {
            return serialiseProperties((BaseOptions) value);
        }

        /** List of Options classes */
        if (value instanceof List) {
            return processList((List<?>) value);

        }
        /** Return quoted string for JS Strings **/
        return "'" + value + "'";
    }

    /**
     * @param lst
     * @return
     */
    private static String processList(final List<?> lst) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(BASE_COL_START);
        /**      Append the List elements to the JS String**/
        for (final Object element : lst) {
            appendElement(buffer, element);
        }
        /** check if buffer contains more that the opening bracket **/
        if (buffer.length() > 1) {
            buffer = HighChartUtils.removeTrailingSeperator(buffer);
        }
        buffer.append(COLLECTION_END);
        return buffer.toString();
    }

    /**
     * Append an element to the JS Buffer
     * @param buffer
     * @param element
     */
    private static void appendElement(final StringBuilder buffer, final Object element) {
        /** Only appending options classes */
        if (element instanceof BaseOptions) {
            buffer.append(parseObjToJS(element));
            buffer.append(BASE_SEPERATOR);
        }
    }

    /**
     * parse array of objects to JS Array
     * @param param
     * @return
     */
    private static String arrayToString(final Object param) { //NOPMD
        final StringBuffer retval = new StringBuffer();
        retval.append(BASE_COL_START);
        final List<String> elems = new Vector<String>();

        for (final Object elem : (Object[]) param) {
            elems.add(parseObjToJS(elem));
        }

        retval.append(concatElements(elems));
        retval.append(COLLECTION_END);
        return retval.toString();
    }

    /**
     * Utility method to concatenate string list
     * @param params
     * @return
     */
    private static String concatElements(final Iterable<? extends Object> params) {
        final Iterator<? extends Object> oIter = params.iterator();
        if (!oIter.hasNext()) {
            return "";
        }
        final StringBuilder oBuilder = new StringBuilder();
        while (oIter.hasNext()) {
            final Object elem = oIter.next();
            concatElement(oBuilder, elem);
        }
        return oBuilder.toString();
    }

    /**
     * Concat single element to the JS
     * @param oBuilder
     * @param elem
     */
    private static void concatElement(final StringBuilder oBuilder, final Object elem) {
        /** First element, no separator before it **/
        if (oBuilder.length() == 0) {
            oBuilder.append(elem);
        } else {
            oBuilder.append(BASE_SEPERATOR).append(elem);
        }
    }
}
