/*
 * Copyright (c) 2017 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */
package net.mm2d.widgetdemo

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
class AppWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(
                context,
                appWidgetManager,
                appWidgetId
            )
        }
    }

    override fun onEnabled(context: Context) {}
    override fun onDisabled(context: Context) {}

    companion object {
        fun updateAppWidget(
            context: Context, appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            val views = RemoteViews(context.packageName,
                R.layout.app_widget
            )
            val intent1 = Intent(context, TransparentActivity::class.java)
            intent1.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            views.setOnClickPendingIntent(
                R.id.icon1,
                PendingIntent.getActivity(context, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT)
            )
            val intent2 = Intent(context, WallpaperActivity::class.java)
            intent2.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            views.setOnClickPendingIntent(
                R.id.icon2,
                PendingIntent.getActivity(context, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT)
            )
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
