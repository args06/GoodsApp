package com.example.goodsapp.presentation.reusable

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.withStyledAttributes
import com.example.goodsapp.R
import com.example.goodsapp.databinding.ViewGoodsEdittextBinding

class GoodsEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: ViewGoodsEdittextBinding
    private val etInput: EditText
    private val iconStart: ImageView
    private val iconEnd: ImageView
    private val errorTextView: TextView

    var text: String
        get() = etInput.text.toString()
        set(value) {
            etInput.setText(value)
        }

    private var onTextChangeListener: ((String) -> Unit)? = null

    init {
        // Inflate the layout
        binding = ViewGoodsEdittextBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )

        // Find views
        etInput = binding.etInput
        iconStart = binding.iconStart
        iconEnd = binding.iconEnd
        errorTextView = binding.errorTextView

        // Process attributes
        context.withStyledAttributes(
            attrs,
            R.styleable.GoodsEditText,
            defStyleAttr,
            0
        ) {
            try {
                // Hint
                val hint = getString(R.styleable.GoodsEditText_customHint)
                etInput.hint = hint ?: ""

                // Input type
                val inputType = getInt(
                    R.styleable.GoodsEditText_inputType,
                    InputType.TYPE_CLASS_TEXT
                )
                etInput.inputType = inputType

                // Password toggle
                val isPassword = getBoolean(R.styleable.GoodsEditText_isPassword, false)
                if (isPassword) {
                    setupPasswordToggle()
                }

                // Start icon
                val startIconDrawable = getResourceId(R.styleable.GoodsEditText_startIcon, -1)
                if (startIconDrawable != -1) {
                    iconStart.setImageResource(startIconDrawable)
                    iconStart.visibility = View.VISIBLE
                }

                // Error message
                val errorMessage = getString(R.styleable.GoodsEditText_errorMessage)
                if (!errorMessage.isNullOrEmpty()) {
                    showError(errorMessage)
                }
            } catch (e: Exception) {
                Log.e("GoodsEditText", "Error processing attributes", e)
            }
        }

        // Setup text change listener
        setupTextChangeListener()
    }

    private fun setupTextChangeListener() {
        etInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                onTextChangeListener?.invoke(s?.toString() ?: "")
                hideError()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun setupPasswordToggle() {
        // Always make the end icon visible when it's a password input
        iconEnd.visibility = View.VISIBLE

        // Set the initial eye icon
        iconEnd.setImageResource(R.drawable.ic_eye_close)

        // Track password visibility state
        var isPasswordVisible = false

        iconEnd.setOnClickListener {
            isPasswordVisible = !isPasswordVisible

            if (isPasswordVisible) {
                // Show password
                etInput.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                iconEnd.setImageResource(R.drawable.ic_eye_open)
            } else {
                // Hide password
                etInput.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                iconEnd.setImageResource(R.drawable.ic_eye_close)
            }

            // Move cursor to the end
            etInput.setSelection(etInput.text.length)
        }
    }

    fun setOnTextChangeListener(listener: (String) -> Unit) {
        onTextChangeListener = listener
    }

    fun showError(message: String?) {
        errorTextView.text = message
        errorTextView.visibility = if (message.isNullOrEmpty()) View.GONE else View.VISIBLE

        // Change background to error state
        etInput.setBackgroundResource(R.drawable.bg_rounded_input_error)
    }

    fun hideError() {
        errorTextView.visibility = View.GONE
        etInput.setBackgroundResource(R.drawable.bg_rounded_input)
    }

    fun setStartIcon(@DrawableRes iconRes: Int) {
        iconStart.setImageResource(iconRes)
        iconStart.visibility = View.VISIBLE
    }

    fun clearText() {
        etInput.text.clear()
    }

    fun setInputType(inputType: Int) {
        etInput.inputType = inputType
    }
}