package com.timchentw.slidercaptcha

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SeekBar
import com.timchentw.slidercaptcha.Utils.decoderBase64

/**
 *  @author Tim Chen
 *  @time   2021/3/18
 *  @desc
 */
class SliderCaptcha : LinearLayout {

    private val base64 = "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"
    private val puzzleBase64 = "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"
    private lateinit var seekBar: CustomSeekBar
    private lateinit var imgVerify: VerifyImageView
    private lateinit var imgRefresh: ImageView

    var listener: CaptchaListener? = null

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(
        context: Context,
        attrs: AttributeSet
    ) : super(context, attrs, 0) {
        init(context)
    }

    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_slider_captcha, this, true)
        seekBar = view.findViewById(R.id.seek_bar)
        imgVerify = view.findViewById(R.id.img_verify)
        imgRefresh = view.findViewById(R.id.img_refresh)

        val bitmap = decoderBase64(base64)
        bitmap?.let{
            imgVerify.setImageBitmap(it)
            // 使 seekBar 的最大值與底圖的寬度同步
            seekBar.max = it.width
        }
        imgVerify.init(43, puzzleBase64)

//        decoderBase64(puzzleBase64)?.let {
//            imgVerify.setImageBitmap(it)
//        }
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.d("234534534", "progress: $progress")
                imgVerify.move(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Todo 通知 imgCaptcha 結束拖動
                // Todo imgCaptcha 必須通知 SliderCaptcha 鬆開的位置嗎？ 或者這裡可以自行紀錄移動的位子


                seekBar?.let {
                    listener?.onRelease(it.progress)
                }

            }

        })
    }
}