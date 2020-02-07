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
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

/**
 * @author [大前良介 (OHMAE Ryosuke)](mailto:ryo@mm2d.net)
 */
class CardAdapter(context: Context?) :
    RecyclerView.Adapter<CardAdapter.ViewHolder>() {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            layoutInflater.inflate(
                R.layout.list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.applyItem(position)
    }

    override fun getItemCount(): Int {
        return 36
    }

    interface OnItemClickListener {
        fun onClick(color: Int)
    }

    private var onItemClickListener: OnItemClickListener? = null
    fun setOnItemClickListener(l: OnItemClickListener?) {
        onItemClickListener = l
    }

    inner class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view), View.OnClickListener {
        private val imageView: ImageView = view.findViewById<View>(R.id.icon) as ImageView
        private var color = 0
        fun applyItem(position: Int) {
            val hsv = floatArrayOf(position * 10f, 1.0f, 1.0f)
            color = Color.HSVToColor(hsv)
            imageView.setColorFilter(color)
        }

        override fun onClick(v: View) {
            if (onItemClickListener != null) {
                onItemClickListener!!.onClick(color)
            }
        }

        init {
            view.setOnClickListener(this)
        }
    }

}
