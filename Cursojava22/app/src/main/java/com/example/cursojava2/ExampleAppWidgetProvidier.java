package com.example.cursojava2;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RemoteViews;

public class ExampleAppWidgetProvidier extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId: appWidgetIds){
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent, 0);

            RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.example_widget);
            views.setOnClickPendingIntent(R.id.example_widget_button,pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}
