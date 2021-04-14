package com.szareckii.mynotes.ui.customviews

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import androidx.annotation.Dimension
import androidx.annotation.Dimension.DP
import com.szareckii.mynotes.common.dip
import com.szareckii.mynotes.common.getColorRes
import com.szareckii.mynotes.data.entity.Note

class ColorPickerView : LinearLayout {

    companion object {
        private const val PALETE_ANOMATION_DURATION = 150L
        private const val HEIGHT = "height"
        private const val SCALE = "scale"
        @Dimension(unit = DP) private const val COLOR_VIEW_PADDING = 8
    }

    var onColorClickListener: (color: Note.Color) -> Unit = { }

    val isOpen: Boolean
        get() = measuredHeight > 0

    private var desiredHeight = 0

    private val animator by lazy {
        ValueAnimator().apply {
            duration = PALETE_ANOMATION_DURATION
            addUpdateListener(updateListener)
        }
    }

    private val updateListener by lazy {
        ValueAnimator.AnimatorUpdateListener { animator ->
            layoutParams.apply { height = animator.getAnimatedValue(HEIGHT) as Int
            }.let { 
                layoutParams = it
            }


            val scaleFactor = animator.getAnimatedValue(SCALE) as Float
            for (i in 0  until childCount) {
                getChildAt(i).apply {
                    scaleX = scaleFactor
                    scaleY = scaleFactor
                    alpha = scaleFactor
                }
            }
        }
    }

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER

        Note.Color.values().forEach { color ->
            val view = ColorCircleView(context).apply {
                fillColorRes = color.getColorRes()
                tag = color
                dip(COLOR_VIEW_PADDING).let {
                    setPadding(it, it, it, it)
                }
                setOnClickListener { onColorClickListener(it.tag as Note.Color) }
            }

            addView(view)
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        layoutParams.apply {
            desiredHeight = height
            height = 0
        }.let {
            layoutParams = it
        }
    }

    fun open() {
        animator.cancel()
        animator.setValues(
                PropertyValuesHolder.ofInt(HEIGHT, measuredHeight, desiredHeight),
                PropertyValuesHolder.ofFloat(SCALE, getChildAt(0).scaleX, 1f)
        )
        animator.start()
    }

    fun close() {
        animator.cancel()
        animator.setValues(
                PropertyValuesHolder.ofInt(HEIGHT, measuredHeight, 0),
                PropertyValuesHolder.ofFloat(SCALE, getChildAt(0).scaleX, 0f)
        )
        animator.start()
    }



}