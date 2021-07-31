/*
 * Copyright (c) 2017 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */
package net.mm2d.widgetdemo

import android.app.WallpaperManager
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.mm2d.widgetdemo.databinding.ActivityWallpaperBinding

class WallpaperActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWallpaperBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWallpaperBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = SYSTEM_UI_VISIBLE
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = CardAdapter(this)
        binding.recyclerView.adapter = adapter
        val manager = WallpaperManager.getInstance(this)
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            private var savedOffset = 0
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                savedOffset += dy
                if (recyclerView.childCount == 0) return
                val height = recyclerView.getChildAt(0).height * adapter.itemCount
                if (height == 0) return
                val offset = savedOffset / height.toFloat()
                manager.setWallpaperOffsets(recyclerView.windowToken, offset, offset)
            }
        })
        adapter.setOnItemClickListener {
            window.statusBarColor = it
            window.navigationBarColor = it
        }
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
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
