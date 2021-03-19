package com.timchentw.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.timchentw.slidercaptcha.CaptchaListener
import com.timchentw.slidercaptcha.SliderCaptcha

/**
 *  @author Tim Chen
 *  @time   2021/3/18
 *  @desc
 */
class MainActivity : AppCompatActivity() {

//    private val sliderCaptcha: SliderCaptcha

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sliderCaptcha: SliderCaptcha = findViewById(R.id.slider_captcha)
        sliderCaptcha.listener = object: CaptchaListener{
            override fun onRelease(y: Int) {
                Log.d("1231231234235", "MainActivity 收到 onRelease, y: $y")
            }

        }
    }
}