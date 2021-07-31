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
import android.os.Build
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
class AppWidget : AppWidgetProvider() {
    override fun onUpdate(context: Context, manager: AppWidgetManager, ids: IntArray) {
        ids.forEach {
            updateAppWidget(context, manager, it)
        }
    }

    override fun onEnabled(context: Context) {}
    override fun onDisabled(context: Context) {}

    companion object {
        private val FLAGS = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }

        private fun Intent.toActivityPendingIntent(context: Context): PendingIntent =
            PendingIntent.getActivity(context, 0, this, FLAGS)

        fun updateAppWidget(context: Context, manager: AppWidgetManager, id: Int) {
            val views = RemoteViews(context.packageName, R.layout.app_widget)
            Intent(context, TransparentActivity::class.java).let {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                views.setOnClickPendingIntent(R.id.icon1, it.toActivityPendingIntent(context))
            }
            Intent(context, WallpaperActivity::class.java).let {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                views.setOnClickPendingIntent(R.id.icon2, it.toActivityPendingIntent(context))
            }
            manager.updateAppWidget(id, views)
        }
    }
}
