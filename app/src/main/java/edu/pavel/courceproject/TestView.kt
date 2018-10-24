package edu.pavel.courceproject

import android.content.Context
import android.view.View
import android.R.attr.y
import android.R.attr.x
import android.graphics.*


class TestView(context: Context) : View(context) {

      override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

         val x = 50f
         val y = 50f
         val sideLength = 200f

         val rectangle = RectF(x, y, sideLength, sideLength)
         val paint = Paint()
         paint.color = Color.GRAY

          val path = Path()
          path.addRect(rectangle, null)
          path.addCircle(100.0f, 100.0f, 50.0f, null)

         canvas?.drawColor(Color.BLUE)
         canvas?.drawPath(path, paint)

    }

}