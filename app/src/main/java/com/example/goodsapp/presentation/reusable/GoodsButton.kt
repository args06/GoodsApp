package com.example.goodsapp.presentation.reusable

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.goodsapp.R
import com.example.goodsapp.databinding.ViewGoodsButtonBinding

class GoodsButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    // Inflate the layout
    private val binding: ViewGoodsButtonBinding = ViewGoodsButtonBinding.inflate(
        LayoutInflater.from(context),
        this
    )

    var text: String
        get() = binding.btnMain.text.toString()
        set(value) {
            binding.btnMain.text = value
        }

    init {

        // Process custom attributes
        context.obtainStyledAttributes(
            attrs,
            R.styleable.GoodsButton,
            defStyleAttr,
            0
        ).apply {
            try {
                // Button text
                val buttonText = getString(R.styleable.GoodsButton_buttonText)
                binding.btnMain.text = buttonText ?: "Button"
            } finally {
                recycle()
            }
        }
    }

    override fun setOnClickListener(listener: OnClickListener?) {
        binding.btnMain.setOnClickListener(listener)
    }
}