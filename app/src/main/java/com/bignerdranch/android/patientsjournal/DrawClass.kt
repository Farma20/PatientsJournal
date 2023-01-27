package com.bignerdranch.android.patientsjournal

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

private const val TAG = "DrawClassView"

//Класс для рисования на еловеке
class DrawClass(context: Context, attrs:AttributeSet): View(context, attrs) {

    private var currentBox: Box? = null
    private val boxen = mutableListOf<Box>()
    private val boxPaint = Paint().apply {
        color = 0x22ff0000.toInt()
    }
    private val backgroundPaint = Paint().apply {
        color = 0xfff8efe0.toInt()
    }

    //Отрисовка объектов
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawPaint(backgroundPaint)
        boxen.forEach{box->
            canvas.drawRect(box.left, box.top, box.right, box.bottom, boxPaint)
        }
    }

    //обрабатываем события касаний
    override fun onTouchEvent(event: MotionEvent): Boolean {
        //достаем координаты касания
        val current = PointF(event.x, event.y)
        var action = ""

        when(event.action){
            MotionEvent.ACTION_DOWN->{
                action = "ACTION_DOWN"

                //сбросить состояние объекта
                currentBox = Box(current).also {
                    boxen.add(it)
                }
            }
            MotionEvent.ACTION_MOVE->{
                action = "ACTION_MOVE"

                updateCurrentBox(current)
            }
            MotionEvent.ACTION_UP->{
                action = "ACTION_UP"

                updateCurrentBox(current)
                currentBox = null
            }
            MotionEvent.ACTION_CANCEL ->{
                action = "ACTION_CANCEL"

                currentBox = null
            }
        }

        Log.i(TAG, "$action at x=${current.x}, y=${current.y}")

        return true
    }

    private fun updateCurrentBox(current: PointF){
        currentBox?.let {
            it.end = current
            invalidate()
        }
    }
}

class Box(val start: PointF){
    var end: PointF = start

    val left: Float
        get() = Math.min(start.x, end.x)

    val right: Float
        get() = Math.max(start.x, end.x)

    val top: Float
        get() = Math.min(start.y, end.y)

    val bottom: Float
        get() = Math.max(start.y, end.y)

}