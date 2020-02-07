/*
 * Copyright (c) 2017 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */
package net.mm2d.widgetdemo

import android.app.WallpaperManager
import android.graphics.Color
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WallpaperActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallpaper)
        window.decorView.systemUiVisibility = SYSTEM_UI_VISIBLE
        val recyclerView =
            findViewById<View>(R.id.recycler_view) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = CardAdapter(this)
        recyclerView.adapter = adapter
        val manager = WallpaperManager.getInstance(this)
        recyclerView.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            private var savedOffset = 0
            override fun onScrolled(
                recyclerView: RecyclerView,
                dx: Int,
                dy: Int
            ) {
                savedOffset += dy
                if (recyclerView.childCount == 0) {
                    return
                }
                val height = recyclerView.getChildAt(0).height * adapter.itemCount
                if (height == 0) {
                    return
                }
                val offset = savedOffset / height.toFloat()
                manager.setWallpaperOffsets(recyclerView.windowToken, offset, offset)
            }
        })
        if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
            adapter.setOnItemClickListener(object : CardAdapter.OnItemClickListener {
                override fun onClick(color: Int) {
                    window.statusBarColor = color
                    window.navigationBarColor = color
                }
            })
            window.statusBarColor = Color.TRANSPARENT
            window.navigationBarColor = Color.TRANSPARENT
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, 0)
    }

    companion object {
        private const val SYSTEM_UI_VISIBLE =
            (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
    }
}
