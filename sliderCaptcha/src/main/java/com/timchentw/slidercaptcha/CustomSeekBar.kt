package com.timchentw.slidercaptcha

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatSeekBar
import android.util.AttributeSet
import android.util.Log
import com.timchentw.slidercaptcha.Utils.dp2px


/**
 *  @author Tim Chen
 *  @time   2021/3/18
 *  @desc
 */
class CustomSeekBar : AppCompatSeekBar {


    private lateinit var textPaint: Paint
    private lateinit var text: String

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(
        context: Context,
        attrs: AttributeSet
    ) : super(context, attrs, R.style.SeekBarStyle_New) {
        Log.d("1232342315", "go cons2")
        init(context, R.style.SeekBarStyle_New)
    }

    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        Log.d("1232342315", "go cons3z")
        init(context, defStyleAttr)
    }

    @SuppressLint("ResourceType")
    private fun init(context: Context, defStyleAttr: Int = 0) {
        val ta: TypedArray = context.obtainStyledAttributes(
            defStyleAttr, intArrayOf(
                android.R.attr.textSize,
                android.R.attr.text,
                android.R.attr.progressDrawable,
                android.R.attr.thumb
            )
        )

        val textSize = ta.getDimensionPixelSize(0, dp2px(context, 18f))
        // 從 style 拿出 text 設定, 或者使用 default string
        text = ta.getString(1) ?: context.resources.getString(R.string.please_slide)

        textPaint = Paint()
        textPaint.textAlign = Paint.Align.CENTER
        textPaint.textSize = textSize.toFloat()
        textPaint.isAntiAlias = true
        textPaint.color = Color.BLUE

        isEnabled = true

        val res1 = ta.getInt(3, R.drawable.thumb)
        val res2 = R.drawable.thumb
        val res3 = R.drawable.thumb2

        Log.d("1232342315", "res1: $res1")
        Log.d("1232342315", "res2: $res2")
        Log.d("1232342315", "res3: $res3")

        progressDrawable = ContextCompat.getDrawable(context, ta.getResourceId(2, R.drawable.progress))
        thumb = ContextCompat.getDrawable(context, ta.getResourceId(3, R.drawable.thumb))

        ta.recycle()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            // 實際字體在 Paint 中的詳細參數, 包含 top, bottom, ascent, descent, base
            val fontMetrics: Paint.FontMetrics = textPaint.fontMetrics
            // 基準線到 textPaint 上緣的距離
            val top = fontMetrics.top
            // 基準線到 textPaint 下緣的距離
            val bottom = fontMetrics.bottom
            val x = (width / 2).toFloat()
            val y = (height / 2).toFloat() - (top / 2) - (bottom / 2)

            drawText(text, x, y, textPaint)
        }
    }
}