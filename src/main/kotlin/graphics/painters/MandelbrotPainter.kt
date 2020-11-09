package graphics.painters

import math.Complex
import util.ConvertData
import util.Converter
import java.awt.Color
import java.awt.Graphics


class MandelbrotPainter(private val plane: ConvertData) : Painter {

        var fractalTest : ((Complex)->Boolean)? = null

        /**
         * Рисование фрактала
         * @param g графический контекст для рисования
         */
        override fun draw(g: Graphics?, Width: Int, Height: Int) {
            if (fractalTest==null || g==null) return
            for (i in 0..plane.width){
                for (j in 0..plane.height){
                    val r = fractalTest?.invoke(
                        Complex(
                            Converter.xScr2Crt(i, plane),
                            Converter.yScr2Crt(j, plane)
                        )
                    ) ?: return
                    g.color = if (r) Color.BLACK else Color.WHITE
                    g.fillRect(i, j, 1, 1)
                }
            }
        }

    override fun update(Width: Int, Height: Int) {

    }
}