package com.timchentw.slidercaptcha

/**
 *  @author Tim Chen
 *  @time   2021/3/18
 *  @desc
 */
interface CaptchaListener {

    /**
     * 通知釋放事件被觸發
     * @param y 釋放當下的 y 座標
     */
    fun onRelease(y: Int)
}