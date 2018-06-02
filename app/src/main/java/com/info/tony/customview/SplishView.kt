package com.info.tony.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class SplishView: View {

    lateinit var mPaint:Paint
    var des :Float = 1f
    var index:Int = 0
    var gap :Float= 10f

    var colorSplish = Color.LTGRAY
    var colorNormal = Color.GRAY

    var mWidth :Int = 0
    var mHeight :Int = 0
    var padding:Float = 0f
    var lastDrawTime : Long = 0;

    constructor(context: Context) :super(context){
        init()
    }
    constructor(context: Context,attributeSet: AttributeSet) :super(context,attributeSet){
        init()
    }

    constructor(context: Context,attributeSet: AttributeSet,defstyleAttr:Int) :super(context,attributeSet,defstyleAttr){
        init()
    }

    fun init() {
        des = resources.displayMetrics.density;
        mPaint = Paint();
        mPaint.isAntiAlias = true;
        mPaint.strokeWidth = des * 3
        mPaint.style = Paint.Style.STROKE
        gap = des * 12
        padding = des * 4
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = MeasureSpec.getSize(widthMeasureSpec)
        mHeight = MeasureSpec.getSize(heightMeasureSpec)
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var cur = System.currentTimeMillis()
        if(lastDrawTime - cur < 700) {
            return
        }
        lastDrawTime = cur
        if(index == 0) {
            mPaint.color = colorSplish
        } else {
            mPaint.color = colorNormal
        }
        var path = Path();
        path.moveTo((mWidth/2).toFloat() - gap,padding)
        path.lineTo(mWidth/2+mHeight/2-padding - gap, (mHeight/2).toFloat())
        path.lineTo(mWidth/2 - gap,mHeight-padding)
        canvas?.drawPath(path,mPaint)

        if(index == 1) {
            mPaint.color = colorSplish
        } else {
            mPaint.color = colorNormal
        }
        path.reset()
        path.moveTo((mWidth/2).toFloat(),padding)
        path.lineTo(mWidth/2+mHeight/2-padding, (mHeight/2).toFloat())
        path.lineTo((mWidth/2).toFloat(),mHeight-padding)
        canvas?.drawPath(path,mPaint)

        if(index == 2) {
            mPaint.color = colorSplish
        } else {
            mPaint.color = colorNormal
        }
        path.reset()
        path.moveTo((mWidth/2).toFloat() + gap,padding)
        path.lineTo(mWidth/2+mHeight/2-padding + gap, (mHeight/2).toFloat())
        path.lineTo(mWidth/2 + gap,mHeight-padding)
        canvas?.drawPath(path,mPaint)

        index = (index + 4)%3

        postInvalidateDelayed(800)

    }
}