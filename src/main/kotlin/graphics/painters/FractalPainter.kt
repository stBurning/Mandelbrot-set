package graphics.painters

import math.Complex
import util.ConvertData
import util.Converter
import java.awt.Color
import java.awt.Graphics
import java.awt.image.BufferedImage
import kotlin.concurrent.thread


class FractalPainter(private val plane: ConvertData) : Painter {

    var fractalTest: ((Complex) -> Float)? = null

    var getColor: ((Float) -> Color)? = null

    /**
     * Рисование фрактала
     * @param g графический контекст для рисования
     */
    override fun draw(g: Graphics?, Width: Int, Height: Int) {
        if (fractalTest == null || g == null) return
        val availableThreadsCount = Runtime.getRuntime().availableProcessors()
        val dx = plane.width / availableThreadsCount
        List(availableThreadsCount) {
            val b = it * dx
            val e = (it + 1) * dx +
                    if (it == availableThreadsCount - 1) plane.width % availableThreadsCount else 0
            val image = BufferedImage(e - b + 1, plane.height, BufferedImage.TYPE_INT_RGB)
            val bg = image.graphics
            Pair(thread {
                for (i in 0..e - b) {
                    for (j in 0..plane.height) {
                        fillPixel(bg, i, j, i + b, j)
                    }
                }
            }, image)
        }.forEachIndexed { index, pair ->
            pair.first.join()
            synchronized(g) {
                g.drawImage(pair.second, index * dx, 0, null)
            }
        }
    }

    private fun fillPixel(g: Graphics, i: Int, j: Int, posX: Int, posY: Int) {
        val r = fractalTest?.invoke(
            Complex(
                Converter.xScr2Crt(posX, plane),
                Converter.yScr2Crt(posY, plane)
            )
        ) ?: return
        if (getColor != null) {
            g.color = getColor!!.invoke(r)
        }
        g.fillRect(i, j, 1, 1)
    }

    override fun update(Width: Int, Height: Int) {
        plane.width = Width; plane.height = Height
    }
}