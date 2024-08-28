package com.ericsson.eniq.events.highcharts.client.options;

import com.ericsson.eniq.events.highcharts.client.RawStringType;

/**
 * 
 * @author ekurshi
 * @since 2012
 *
 */
public class NavigationOptions extends BaseOptions {

    public NavigationOptions() {
        super("navigation");
        setDefaults();
    }

    private void setDefaults() {
        final ButtonOptions buttonOptions = new ButtonOptions("buttonOptions");

        buttonOptions.setBorderColor("#9A9999");
        buttonOptions.setBorderWidth(1);
        buttonOptions.setHoverBorderColor("#B0B0B0");
        buttonOptions.setBackgroundColor(new RawStringType(
                "{linearGradient: [0, 0, 0, 20],stops: [[0.2, '#ECECEC'],[0.8, '#A3A3A2']]}"));
        setButtonOptions(buttonOptions);

        final CSSStyleOptions menuStyles = new CSSStyleOptions();
        menuStyles.setProperty("border", "1px solid #9A9999");
        menuStyles.setProperty("background", "#FFFFFF");
        menuStyles.setProperty("borderRadius", "5px");
        setMenuStyle(menuStyles);

        final CSSStyleOptions menuItemStyles = new CSSStyleOptions();
        menuItemStyles.setProperty("padding", "3px 13px");
        menuItemStyles.setProperty("background", "none");
        menuItemStyles.setProperty("color", "#333333");
        menuItemStyles.setProperty("margin", "1px");
        menuItemStyles.setProperty("fontSize", "12px");
        menuItemStyles.setProperty("fontFamily", "arial");
        setMenuItemStyle(menuItemStyles);

        final CSSStyleOptions menuItemHoverStyle = new CSSStyleOptions();
        menuItemHoverStyle.setProperty("color", "#333333");
        menuItemHoverStyle.setProperty("background", "#E6F7FF");
        menuItemHoverStyle.setProperty("borderRadius", "0px");
        setMenuItemHoverStyle(menuItemHoverStyle);
    }

    public void setButtonOptions(final ButtonOptions buttonOptions) {
        properties.put(buttonOptions.getOptionsObjectId(), buttonOptions);
    }

    public void setMenuItemStyle(final CSSStyleOptions styleObject) {
        properties.put("menuItemStyle", styleObject);
    }

    public void setMenuStyle(final CSSStyleOptions styleObject) {
        properties.put("menuStyle", styleObject);
    }

    public void setMenuItemHoverStyle(final CSSStyleOptions styleObject) {
        properties.put("menuItemHoverStyle", styleObject);
    }
}
