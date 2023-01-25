package com.bignerdranch.android.patientsjournal

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View


private val paint: Paint = Paint()

//Класс для рисования на еловеке
class DrawClass(context: Context): View(context) {
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val w: Float
        val h: Float
        val cx: Float
        val cy: Float
        val radius: Float
        w = width.toFloat()
        h = height.toFloat()
        cx = w / 2
        cy = h / 2

        radius = if (w > h) {
            h / 4
        } else {
            w / 4
        }

        val paint = Paint()
        paint.color = Color.GREEN // установим зелёный цвет

        paint.style = Paint.Style.FILL

        canvas!!.drawCircle(cx, cy, radius, paint)
    }
}