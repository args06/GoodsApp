package com.example.goodsapp.presentation.reusable

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.example.goodsapp.R
import com.example.goodsapp.databinding.ViewStatusCardBinding
import com.google.android.material.card.MaterialCardView

class GoodsStatusCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    private val binding: ViewStatusCardBinding = ViewStatusCardBinding.bind(
        LayoutInflater.from(context).inflate(R.layout.view_status_card, this, true)
    )

    fun setData(title: String, count: Int) {
        binding.tvTitle.text = title
        binding.tvCount.text = count.toString()
    }
}