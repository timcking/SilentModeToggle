package com.timothyking.silentmodetoggle;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

public class AppWidget extends AppWidgetProvider {
    @Override
    public void onReceive(Context ctxt, Intent intent) {
        if (intent.getAction()==null) {
		    // Do Something
        } else {
		    super.onReceive(ctxt, intent);
        }	
    }
        
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
	    // Do Something
	}
}
