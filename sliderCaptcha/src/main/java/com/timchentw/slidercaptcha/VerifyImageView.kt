package com.timchentw.slidercaptcha

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.widget.ImageView
import com.timchentw.slidercaptcha.Utils.decoderBase64


/**
 *  @author Tim Chen
 *  @time   2021/3/18
 *  @desc
 */
@SuppressLint("AppCompatCustomView")
class VerifyImageView : ImageView {

    private lateinit var position: Position
    private var puzzleBase64 = ""

    constructor(context: Context) : super(context) {
        init(0)
    }

    constructor(
        context: Context,
        attrs: AttributeSet
    ) : super(context, attrs, 0) {
        init(0)
    }

    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int
    ) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(0)
    }

    fun init(startY: Int, puzzle: String = "") {
        position = Position(0, startY)
        position.y = startY
        puzzleBase64 = puzzle
        invalidate()
    }

    fun move(x: Int) {
        position.x = x
        invalidate()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.apply {
            // 取得圖片被拉寬的比例
            // 原圖寬
            val originWidth = drawable.bounds.right
            // 放大後的寬度
            val newWidth = width.toFloat()
            // 計算出 y 座標需要放大的比例
            val ratio = (newWidth / originWidth)

            // 計算 fitCenter 時, y 需要再墊高多少
            // 計算 ImageView 高度
            val h1 = this@VerifyImageView.measuredHeight
            // 計算 實際圖片 高度
            val h2 = drawable.bounds.bottom * ratio
            // 取得拼圖塊區要墊高的 y 值
            val gap = (h1 - h2) / 2

            // 拼圖塊的 base64 轉 bitmap, 輸入 ratio 可同時將將 bitmap 進行尺寸調整
            decoderBase64(puzzleBase64, ratio)?.let {
                // 將拼圖塊畫上去 ImageView 時, 座標點也要依照比例調高
                drawBitmap(it, (position.x * ratio), (position.y * ratio) + gap, Paint())
            }
        }
    }

    private fun testPath(): Path {
        return Path().apply {
            lineTo(50f, 0f)
            lineTo(50f, 50f)
            lineTo(0f, 50f)
            close()
        }
    }

    private fun testPaint(): Paint {
        return Paint().apply {
            color = Color.RED
        }
    }
}