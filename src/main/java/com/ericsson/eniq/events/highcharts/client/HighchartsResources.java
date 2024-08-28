package com.ericsson.eniq.events.highcharts.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.HeadElement;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.user.client.Window;

/**
 * 
 * @author ekurshi
 * @since 2013
 *
 */
public class HighchartsResources {

    private static boolean resourcesInitialized;

    public static void injectResources() {
        GWT.runAsync(new RunAsyncCallback() {

            @Override
            public void onSuccess() {
                if (!resourcesInitialized) {
                    resourcesInitialized = true;
                    final HighchartsJSResourceBundle bundle = GWT.create(HighchartsJSResourceBundle.class);
                    inject(bundle.jqueryJS().getText());
                    inject(bundle.highchartsJS().getText());
                    inject(bundle.exportJS().getText());
                    inject(bundle.highchartUtilsJS().getText());
                }
            }

            @Override
            public void onFailure(final Throwable reason) {
                Window.alert("Code splitting failed");

            }
        });
    }

    private static HeadElement head = getHead();

    public static void inject(final String javascript) {
        final ScriptElement element = createScriptElement();
        element.setText(javascript);
        head.appendChild(element);
    }

    private static ScriptElement createScriptElement() {
        final ScriptElement script = Document.get().createScriptElement();
        script.setAttribute("language", "javascript");
        return script;
    }

    private static HeadElement getHead() {
        if (head == null) {
            final Element element = Document.get().getElementsByTagName("head").getItem(0);
            assert element != null : "HTML Head element required";
            head = HeadElement.as(element);
        }
        return head;
    }
}
