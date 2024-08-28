package com.ericsson.eniq.events.highcharts.client.options;

import com.ericsson.eniq.events.highcharts.client.RawStringType;

/**
 * 
 * @author ekurshi
 * @since 2012
 *
 */
public class ExportButtonOptions extends ButtonOptions {

    public enum MenuItemType {
        DOWNLOAD_PNG, DOWNLOAD_JPEG, DOWNLOAD_PDF, DOWNLOAD_SVG
    };

    public ExportButtonOptions() {
        super("exportButton");
    }

    @Override
    protected void setDefaults() {
        super.setDefaults();
        setEnabled(true);
        setX(-10);
        setSymbol("url(./resources/icons/export.png)");
        setSymbolX(10);
        setSymbolY(8);

    }

    public void setClickHandler(final RawStringType handler) {
        properties.put("onclick", handler);
    }

    public void setX(final int x) {
        properties.put("x", x);
    }

    public void setEnabled(final boolean enabled) {
        properties.put("enabled", enabled);
    }

    /**
     * Allows the setting of menuItems for the drop down menu.
     * @param items
     */
    public void setMenuItems(final MenuItemType... items) {

        final StringBuilder exportMenu = new StringBuilder("[");

        //set all menuItems to null by default.
        String downloadPNG = "null";
        String downloadJPEG = "null";
        String downloadPDF = "null";
        String downloadSVG = "null";

        //activate each selected menuItem.
        for (final MenuItemType menuItemType : items) {
            switch (menuItemType) {
            case DOWNLOAD_PNG:
                downloadPNG = "";
                break;
            case DOWNLOAD_JPEG:
                downloadJPEG = "";
                break;
            case DOWNLOAD_PDF:
                downloadPDF = "";
                break;
            case DOWNLOAD_SVG:
                downloadSVG = "";
                break;
            }
        }

        //build the menu...
        exportMenu.append(downloadPNG);
        exportMenu.append(",");
        exportMenu.append(downloadJPEG);
        exportMenu.append(",");
        exportMenu.append(downloadPDF);
        exportMenu.append(",");
        exportMenu.append(downloadSVG);
        exportMenu.append("]");

        //add the menuItems property.
        properties.put("menuItems", new RawStringType(exportMenu.toString()));
    }
}
