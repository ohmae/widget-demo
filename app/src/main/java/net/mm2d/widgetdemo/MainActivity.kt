/*
 * Copyright (c) 2017 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */
package net.mm2d.widgetdemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.icon1).setOnClickListener { v: View ->
            startActivity(
                Intent(this@MainActivity, TransparentActivity::class.java),
                makeScaleUpAnimationBundle(v)
            )
            finish()
        }
        findViewById<View>(R.id.icon2).setOnClickListener { v: View ->
            startActivity(
                Intent(this@MainActivity, WallpaperActivity::class.java),
                makeScaleUpAnimationBundle(v)
            )
            finish()
        }
    }

    companion object {
        private fun makeScaleUpAnimationBundle(v: View): Bundle? =
            ActivityOptionsCompat
                .makeScaleUpAnimation(v, 0, 0, v.width, v.height)
                .toBundle()
    }
}
