package com.example.githubapp.customcomponent

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.githubapp.R
import com.example.githubapp.databinding.LayoutNoDataBinding

class NoDataScreen @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = LayoutNoDataBinding.inflate(LayoutInflater.from(context), this)

    init {
        orientation = VERTICAL
        showDividers = SHOW_DIVIDER_MIDDLE
        gravity = Gravity.CENTER
        dividerDrawable = ContextCompat.getDrawable(context, R.drawable.divider_margin_12)

        attrs?.let { attr ->
            val typedArray = context.obtainStyledAttributes(
                attr,
                R.styleable.NoDataScreen,
                0,
                0
            )

            setIcon(typedArray.getDrawable(R.styleable.NoDataScreen_icon))
            setMessage(typedArray.getString(R.styleable.NoDataScreen_message))
            typedArray.recycle()
        }

    }

    fun setIcon(icon: Drawable?) {
        binding.image.setImageDrawable(icon)
    }

    fun setMessage(desc: String?) {
        binding.message.isVisible = desc.isNullOrEmpty().not()
        binding.message.text = desc
    }

}