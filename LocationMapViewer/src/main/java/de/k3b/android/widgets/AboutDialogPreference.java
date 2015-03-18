/*
 * Copyright (C) 2014 k3b
 * 
 * This file is part of de.k3b.android.toGoZip (https://github.com/k3b/ToGoZip/) .
 * 
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. 
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS 
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. 
 * 
 * You should have received a copy of the GNU General Public License along with 
 * this program. If not, see <http://www.gnu.org/licenses/>
 */
package de.k3b.android.widgets;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import de.k3b.android.GuiUtil;
import de.k3b.android.locationMapViewer.R;

/**
 * html/Webview about preference entry showing
 * R.string.about_content with placeholders
 * $versionName$ and
 * $about$ (R.string.about_content_about)<br/>
 * Created by k3b on 11.11.2014.
 */
public class AboutDialogPreference extends DialogPreference {
    private Context context;

    public AboutDialogPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDialogIcon(R.drawable.ic_launcher);
        setDialogTitle(R.string.about_summary);
        setDialogLayoutResource(R.layout.about_dialog);
        this.context = context;

    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);

        setAboutText(this.context, (WebView) view.findViewById(R.id.content));
    }

    private static WebView setAboutText(Context context, WebView wv) {
        String html = context.getResources().getString(R.string.about_content); // "<html><body>some <b>html</b> here</body></html>";

        final String versionName = GuiUtil.getAppVersionName(context);
        if (versionName != null) {
            html = html.replace("$versionName$", versionName);
        }

        html = html.replace("$about$",
                context.getText(R.string.about_content_about));

        wv.loadData(html, "text/html", "UTF-8");
        wv.setVerticalScrollBarEnabled(true);

        final WebSettings mWebSettings = wv.getSettings();
        mWebSettings.setBuiltInZoomControls(true);
        wv.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        wv.setScrollbarFadingEnabled(false);
        return wv;
    }

    public static Dialog createAboutDialog(Context context) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle(R.string.about_summary);
        alert.setIcon(R.drawable.ic_launcher);
        alert.setNeutralButton(R.string.cancel,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(
                            final DialogInterface paramDialogInterface,
                            final int paramInt) {
                        paramDialogInterface.cancel();

                    }
                }
        );

        final WebView wv =  setAboutText(context, new WebView(context));
        alert.setView(wv);

        return alert.create();
    }


}
