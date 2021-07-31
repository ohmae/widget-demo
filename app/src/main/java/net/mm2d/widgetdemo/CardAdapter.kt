/*
 * Copyright (c) 2017 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */
package net.mm2d.widgetdemo

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.mm2d.widgetdemo.databinding.ListItemBinding

/**
 * @author [大前良介 (OHMAE Ryosuke)](mailto:ryo@mm2d.net)
 */
class CardAdapter(context: Context) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ListItemBinding.inflate(layoutInflater, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.applyItem(position)
    }

    override fun getItemCount(): Int = 36

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        onItemClickListener = listener
    }

    fun interface OnItemClickListener {
        fun onClick(color: Int)
    }

    inner class ViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private var color = 0

        fun applyItem(position: Int) {
            binding.root.setOnClickListener(this)
            color = Color.HSVToColor(floatArrayOf(position * 10f, 1.0f, 1.0f))
            binding.icon.setColorFilter(color)
        }

        override fun onClick(v: View) {
            onItemClickListener?.onClick(color)
        }
    }
}
