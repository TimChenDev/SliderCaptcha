package com.timchentw.slidercaptcha

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

/**
 *  @author Tim Chen
 *  @time   2021/3/18
 *  @desc
 */
object Utils {

    fun dp2px(context: Context, dip: Float): Int {
        val density = context.resources.displayMetrics.density
        return (dip * density).toInt()
    }


    /**
     * Base64字串轉圖片
     *
     * @param base64Code Base64編碼會前綴 `data:image/ *;base64, `
     *                   傳進來的 String必須移除此字串, 否則會導致解碼失敗
     * @param scaleRatio 如果有設定這個參數, bitmap 將會依比例放大或縮小
     * @return 原圖或依比例調整過的 bitmap
     */
    fun decoderBase64(base64Code: String, scaleRatio: Float? = null): Bitmap? {
        return if (base64Code.isEmpty()) {
            null
        } else try {
            val result = Base64.decode(base64Code, Base64.DEFAULT)
            if (scaleRatio != null) {
                val tmpBitmap = BitmapFactory.decodeByteArray(result, 0, result.size)
                Bitmap.createScaledBitmap(
                    tmpBitmap,
                    (tmpBitmap.width * scaleRatio).toInt(),
                    (tmpBitmap.height * scaleRatio).toInt(),
                    true
                )
            } else {
                BitmapFactory.decodeByteArray(result, 0, result.size)
            }
        } catch (e: Exception) {
            null
        }
    }
}