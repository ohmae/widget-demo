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
import net.mm2d.widgetdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.icon1.setOnClickListener {
            startActivity(
                Intent(this@MainActivity, TransparentActivity::class.java),
                makeScaleUpAnimationBundle(it)
            )
            finish()
        }
        binding.icon2.setOnClickListener {
            startActivity(
                Intent(this@MainActivity, WallpaperActivity::class.java),
                makeScaleUpAnimationBundle(it)
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
