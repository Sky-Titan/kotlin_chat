package org.techtown.kotlinchat

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager
import java.io.PrintWriter
import java.io.StringWriter

class NonSwipeViewPager: ViewPager  {

    private var scrollEnabled : Boolean

    constructor(context: Context, attributeSet: AttributeSet) : super(context,attributeSet)
    {
        this.scrollEnabled = true;
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        try {
            if (this.scrollEnabled) { //				Log.i("INFO", "스크롤 중..");
                return super.onTouchEvent(ev)
            }
        } catch (e: Exception) {
            val sw = StringWriter()
            e.printStackTrace(PrintWriter(sw))
            val exceptionAsStrting = sw.toString()
            Log.e("INFO", exceptionAsStrting)
        }
        return false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {

        if(scrollEnabled)
            return super.onInterceptTouchEvent(ev)
        return false
    }

    fun setPagingEnabled(){
        scrollEnabled = true
    }
    fun setPagingDisabled()
    {
       scrollEnabled = false
    }

}