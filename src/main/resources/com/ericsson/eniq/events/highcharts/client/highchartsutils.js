/**
 * Utility Javascript Methods for use with High Charts. Called from the GWT Code
 *
 * @author ecarsea
 * @since
 */

/** HighCharts Global options * */
Highcharts.setOptions({
    global:{
        useUTC:false
    },
    /** Disable the old Reset Zoom button by setting the text of it blank * */
    lang:{
        resetZoom:''
    }
});

/**
 * Format legend labels. Append Label Prefix is it is present in the Point
 * object.
 *
 * @param series -
 *            The Series Object for the legend
 * @param maxlen -
 *            Maximum Label Length
 */
function legendLabelFormatter(series, maxlen) {
    var label = series.legendPrefix ? (series.legendPrefix + '-' + series.name)
        : series.name;
    return wrapString(label, 60);
}

/**
 * Format data labels. Append Label Prefix is it is present in the Point object.
 *
 * @param label -
 *            The label object for the point
 * @param maxlen -
 *            Maximum Label Length
 */
function dataLabelFormatter(label, maxlen) {
    return label.point.legendPrefix ? (label.point.legendPrefix)
        : label.point.name;
}

function wrapString(str, maxlen) {
    var len = str.length;
    var index = 0;
    var separator = '<br/>';
    for (var i = maxlen; i < len; i += maxlen) {
        for (var pos = i; str.charAt(pos) != " "; pos--) {
            if (pos == i - maxlen) {
                pos = --i;
                break;
            }
        }
        str = str.substring(0, pos) + separator + str.substring(pos + 1, len);
        i = pos + 4;
        len += 4;
        index++;
    }
    return str;
}

/**
 *
 * @param label -
 *            The Axis label object
 * @param maxlen -
 *            max label length
 *
 */
function axisLabelFormatter(label, maxlen) {
    if (label.value.length > maxlen) {
        return label.value.substring(0, maxlen) + "...";
    }
    return label.value;
}

/**
 * Format the tool tips for a Pie Chart
 *
 * @param tooltip -
 *            The Tooltip object
 */
function toolTipFormatterPieChart(tooltip) {

    var s = tooltip.point.name;

    if (s.length > 60) {
        /* EEUNM-901 (and not bold text when huge) */
        s = wrapString(s, 60) + '<br/>';
    } else {
        s = '<b>' + s + '</b><br/>';
    }

    /**
     * Any additional tooltip data that might have been put into the point
     * object *
     */
    if (tooltip.point.tooltipData) {
        for (var i = 0; i < tooltip.point.tooltipData.length; i++) {
            s += tooltip.point.tooltipData[i].name + ' : ';
            s += tooltip.point.tooltipData[i].value + '<br/>';
        }
    }
    return s;
}

/**
 * Format the tool tips for Axes based charts
 *
 * @param tooltip -
 *            The tooltip object
 * @param zoomableXAxis -
 *            Flag for zoomable X Axis
 * @param categoryArray -
 *            Array containing all the categories (if x axis is zoomable
 *            sampling is done on the category array).
 * @returns {String} - the tooltip
 */
function toolTipFormatterAxesChart(tooltip, zoomableXAxis) {
    var s = '<b>' + tooltip.series.name + '</b><br/>';

    var yAxisTitle = "";
    /** Check for 2nd Y Axis. If Y Axis is an array then we have multiple Y Axes */
    if ($.isArray(tooltip.series.chart.options.yAxis)) {
        /**
         * Which Y Axis is associated with current point (if its undefined then
         * its Axis 0 or the normal left axis).
         */
        var index = tooltip.point.series.options.yAxis ? tooltip.point.series.options.yAxis
            : 0;
        yAxisTitle = tooltip.series.chart.options.yAxis[index].title.text;
    } else {
        yAxisTitle = tooltip.series.chart.options.yAxis.title.text;
    }

    /** Dont show null in tooltip if no xAxisTitle, just show label value * */
    var xAxisTitle = tooltip.series.chart.options.xAxis.title.text == null ? ''
        : tooltip.series.chart.options.xAxis.title.text + '=';

    s += yAxisTitle
        + '='
        + tooltip.point.y
        + ', '
        + xAxisTitle
        + ((zoomableXAxis) ? Highcharts.dateFormat('%Y-%m-%d %H:%M',
        tooltip.point.x) : tooltip.point.category) + '<br/>';
    if (tooltip.point.tooltipData) {
        for (var i = 0; i < tooltip.point.tooltipData.length; i++) {
            s += tooltip.point.tooltipData[i].name + ' : ';
            s += tooltip.point.tooltipData[i].value + '<br/>';
        }
    }
    return s;
}

/**
 * Remove the slice from the pie chart on legend item click
 *
 * @param point -
 *            The clicked Legend Item point
 */
function pieChartLegendItemClick(point) {
    var y, y_tmp, attr;

    if (typeof point.y_tmp == 'number' && point.y == 0) {
        /** Restore the Legend Point to Pie Chart */
        y = point.y_tmp;

        /** Restore Point attributes (fill color etc, they change between clicks */
        attr = point.savedPointAttr;
        y_tmp = null;
    } else {
        /** Remove pie slice by setting the point.y = 0; */
        y = 0;
        y_tmp = point.y; // Save y
        attr = point.pointAttr;
        if (point.pointAttr[''].fill !== undefined) {
            point.savedPointAttr = attr;
            /** Save attributes */
        }
    }
    /** update the new point values */
    point.update({
        y:y,
        y_tmp:y_tmp
    });
    point.pointAttr = attr;
}

/**
 * Event handler called on load of a pie chart
 *
 * @param chart -
 *            the chart object
 * @param chartId -
 *            id of chart object
 * @param maxlenLegendLabel -
 *            Max length of legend labels
 */
function onPieChartLoad(chart, chartId, maxlenLegendLabel) {
    chart.chartId = chartId;
    /** Register the chart in a registry to allow for resizing etc */
    registerChart(chart);
}

/**
 * Event handler called on load of an Axes chart
 *
 * @param chart -
 *            Chart Object
 * @param chartId -
 *            Chart Id
 * @param maxlenLegendLabel -
 *            Max length of legend labels
 */
function onAxesChartLoad(chart, chartId, maxlenLegendLabel) {
    chart.chartId = chartId;
    var divId = chart.options.chart.renderTo;
    /** Register the chart in a registry to allow for resizing etc */
    registerChart(chart);
    setResetZoom(chart);
    /***************************************************************************
     * Bring legend items and tooltips to the front of the Chart elements i.e.
     * bars, and ensure tooltips show over legend
     **************************************************************************/
    $('#' + divId + ' .highcharts-legend').appendTo(chart.container.firstChild);
    $('#' + divId + ' .highcharts-tooltip')
        .appendTo(chart.container.firstChild);
    /**
     * Scatter points when embedded within bars in the same chart do not have
     * their tooltip shown on hover as the Z-Index of the bar charts are higher.
     * Set the zIndex of the scatter series to 10. Am presuming we will only
     * have one scatter series per chart
     */
    $.each(chart.series, function (index, series) {
        if (series.options.type && series.options.type == "scatter") {
            series.group.attr({
                zIndex: 10
            }).add();
}
    });
}

/**
 * Add tool tips to truncated data label items longer than the max length. Pie
 * Charts data label tooltips show the full tooltip for the point
 *
 * @param chart -
 *            the chart object
 *
 */
function addHoverToDataLabelsPieChart(chart) {
    var divId = chart.options.chart.renderTo;
    var query = $.browser.msie ? '#' + divId + ' .highcharts-data-labels span'
        : '#' + divId + ' .highcharts-data-labels text';
    $(query).each(function (index, element) {
        $(element).hover(function () {
            chart.tooltip.refresh(chart.series[0].data[index]);
        }, function () {
            chart.tooltip.hide();
        });
    });
}

/**
 * @param tooltip -
 *            The tool tip object.
 * @returns {String}
 */
function tooltipMultiYAxis(tooltip) {
    var s = '<b>' + tooltip.series.name + '</b><br/>';
    var index = 0;
    /** Get the index of the YAxis to which this point is attached */
    if (tooltip.point.series.options.yAxis) {
        index = tooltip.point.series.options.yAxis;
    }
    s += tooltip.series.chart.options.yAxis[index].title.text + '='
        + tooltip.point.y + ', '
        + tooltip.series.chart.options.xAxis.title.text + '='
        + tooltip.point.category;
    return s;
}
/**
 * Create a new reset zoom link in order to have it on the top left of the chart
 *
 * @param chart
 */
function setResetZoom(chart) {

    var dragToZoom = chart.renderer.text('Drag To Zoom', chart.plotLeft, chart.plotTop - 15)
        .attr({
            zIndex:20
        }).css({
            cursor:'default',
            color:'#333333',
            fill:'#333333',
            fontSize:'10px',
            fontFamily:'Arial'
        }).add();

    var resetZoom = chart.renderer.text('Reset Zoom', chart.plotLeft, chart.plotTop - 15)
        .attr({
            zIndex:20
        }).css({
            cursor:'pointer',
            color:'#0066b3',
            fill:'#0066b3',
            fontSize:'10px'
        }).on('click',
        function () {
            /** Restore all axis extremes and hide the button * */
            for (var i = 0; i < chart.yAxis.length; i++) {
                chart.yAxis[i].setExtremes(null, null);
            }
            for (var i = 0; i < chart.xAxis.length; i++) {
                chart.xAxis[i].setExtremes(null, null);
            }
            this.setAttribute("display", "none");
            chart.dragToZoom.element.setAttribute("display", "inline");
        }).add();

    /** Hide button at start * */
    resetZoom.element.setAttribute("display", "none");
    dragToZoom.element.setAttribute("display", "inline");
    if (chart.options.chart.zoomType == null
        || chart.options.chart.zoomType == 'none') {
        dragToZoom.element.setAttribute("display", "none");
    }
    /**
     * Set this new button as the chart resetZoom button. Need to pick it up in
     * axis events *
     */
    chart.resetZoom = resetZoom;
    chart.dragToZoom = dragToZoom;
}

function toolTipFormatterRoamerPortalChart(tooltip) {
    var s = '<b>' + tooltip.series.name + '</b><br/>';
    s += 'Country : ' + tooltip.point.name + '<br/>';
    s += tooltip.series.name + ' : ' + tooltip.point.y;
    return s;
}

/**
 * Truncate axis labels. Add hidden span with full label in case tooltips required
 * @param label
 * @param length - substring to this length
 * @returns
 */
function truncateAxisLabels(label, length) {
    return label.length <= length ? label : (label.substring(0, length) + '..' + '<span style="display:none">' + label + '</span>');
}

/**
 * Add tooltips to X Axis Labels
 * @param chart
 */
function setupXAxisLabelTooltips(chart) {
    $('#' + chart.options.chart.renderTo + ' .highcharts-axis text').hover(function (event) {
        chart.axisLabelTooltip = chart.renderer.text($($(this).find('tspan')[1]).text(), chart.plotLeft +(this.getAttribute("x")/10), this.getAttribute("y") - 5).attr({
            zIndex:20
        }).css({
                cursor:'default',
                color:'#333333',
                fill:'#333333',
                fontSize:'11px',
                fontFamily:'Arial'
            }).add();
        chart.axisLabelTooltip.element.setAttribute("display", "inline");
    }, function (event) {
        chart.axisLabelTooltip.element.setAttribute("display", "none");
    });
}

/**
 * Axis Labels for a chart converting to the correct category of byte multiplier i.e. GB, doesnt need to be completely accurate,
 * can round the converted values as it is only an axis label.
 * @param value
 * @returns
 */
function getByteAxisLabel(value) {
    var newValue = Math.abs(value);

    var label = 'B';
    if(newValue >= 1073741824) {
        newValue = Math.round(newValue*100 / 1073741824) / 100;
        label = 'GB';
    }
    else if(newValue >= 1048576) {
        newValue = Math.round(newValue*100 / 1048576) / 100;
        label = 'MB';
    }
    else if(newValue >= 1024) {
        newValue = Math.round(newValue*100 / 1024) / 100;
        label='KB';
    }
    //window.console.log('getByteAxisLabel function was called...');
    return newValue+' '+label;
}

/**
 *
 * @param x - the x coordinate of the group.
 * @param y - the y coordinate of the group.
 * @param textLines - the text lines to display.
 */
function generateTextSVG(x, y, textLines){
    //generate the transform...
    var gSVG = '<g transform="translate('+x+','+y+')">';
    var margin = 20;
    //create the first line of text...
    gSVG += '<text x="0" y="0" font-family="Arial" style="text-anchor: middle;">';

    for(i = 0; i < textLines.length; i++){
        if(i == 0){
            gSVG += textLines[i];
        }else{
            //start a tspan line...
            gSVG += '<tspan x="0" y="' + (margin*i) +'">';
            //add the text to the tspan...
            gSVG += textLines[i];
            //close the tspan...
            gSVG += '</tspan>';
        }
    }
    gSVG += '</text></g>';
    return gSVG;
}

/**
 * This function gets the titles, chart and footers and wraps them up in an SVG.
 * @param title
 * @param titleLength
 * @param chart
 * @param footer
 * @param footerLength
 */
function generateTitleChartFooterSVG(title, chart, footer){
    var center = chart.chartWidth/2;
    //get the height of the chart
    var chartHeight = chart.chartHeight;

    //get the title of the chart...
    var titleSVG  = generateTextSVG(center, 20, title);
    var footerSVG = generateTextSVG(center, chartHeight+50, footer);

    var titleHeight  = 10 + 20*title.length;
    var footerHeight = 30 + 20*footer.length;
    var totalSVGHeight = titleHeight + chartHeight + footerHeight;

    //crete the group to house the chart...
    var chartGroup = '<g transform="translate(0,'+titleHeight+')">';
    var chartSVG = chart.getSVG();
    chartGroup += chart.getSVG();
    chartGroup += '</g>';

    var totalHeight = chartHeight + 30;

    //wrap everything up in an SVG
    var svg = '<svg xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" xmlns="http://www.w3.org/2000/svg" ';
    svg += 'height="'+totalSVGHeight+'" width="'+chart.chartWidth+'">';
    svg += titleSVG + chartGroup + footerSVG+'</svg>';
    return svg;
}

/**
 * This function gets the titles and chart and wraps them up in an SVG.
 * @param title
 * @param chart
 */
function generateTitleChartSVG(title, chart){

    var center = chart.chartWidth/2;

    //get the title of the chart...
    var title = generateTextSVG(center, 20, title);

    //get the height of the chart
    var chartHeight = chart.chartHeight;

    //crete the group to house the chart...
    var chartGroup = '<g transform="translate(0,30)">';
    var chartSVG = chart.getSVG();
    chartGroup += chart.getSVG();
    chartGroup += '</g>';

    var totalHeight = chartHeight + 30;

    //wrap everything up in an SVG
    var svg = '<svg xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" xmlns="http://www.w3.org/2000/svg" ';
    svg += 'height="'+totalHeight+'" width="'+chart.chartWidth+'">';
    svg += title + chartGroup + '</svg>';
    return svg;
}

/**
 * This function exports a Chart with Title(s) and Footer(s).
 * @param title   - An array of lines, which make up the title.
 * @param footer  - An array of lines, which make up the footer.
 * @param chart   - The chart.
 * @param chartId - The "ChartName"
 * @param options - The options: (JPG, PNG, PDF, URL)
 */
function exportSVGChartWithTitlesAndFooters(title, footer, chart, chartId, options) {
    chart.chartId = chartId;
    var form;
    var svg = generateTitleChartFooterSVG(title, chart, footer);
    // create the form
    form = Highcharts.createElement('form', {
        method: 'post',
        action: options.url
    }, {
        display: 'none'
    }, document.body);

    // add the values
    Highcharts.each(['filename', 'type', 'width', 'svg'], function(name) {
        Highcharts.createElement('input', {
            type: 'hidden',
            name: name,
            value: {
                filename: options.filename || 'chart',
                type: options.type,
                width: options.width,
                svg: svg
            }[name]
        }, null, form);
    });

    //submit the form to our servlet.
    form.submit();
    // clean up
    form.parentNode.removeChild(form);
}

/**
 * This function exports a Chart with Title(s).
 * @param title
 * @param chart
 * @param chartId
 * @param options
 */
function exportSVGChartWithTitle(title, chart, chartId, options) {
    chart.chartId = chartId;
    var form;
    var svg = generateTitleChartSVG(title, chart);
    // create the form
    form = Highcharts.createElement('form', {
        method: 'post',
        action: options.url
    }, {
        display: 'none'
    }, document.body);

    // add the values
    Highcharts.each(['filename', 'type', 'width', 'svg'], function(name) {
        Highcharts.createElement('input', {
            type: 'hidden',
            name: name,
            value: {
                filename: options.filename || 'chart',
                type: options.type,
                width: options.width,
                svg: svg
            }[name]
        }, null, form);
    });

    //submit the form to our servlet.
    form.submit();
    // clean up
    form.parentNode.removeChild(form);
}


