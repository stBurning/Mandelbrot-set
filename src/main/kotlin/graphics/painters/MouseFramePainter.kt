package graphics.painters

import java.awt.Color
import java.awt.Graphics
import java.awt.Point
import java.lang.Integer.min
import kotlin.math.abs

class MouseFramePainter(private var g: Graphics) {

    var start: Point? = null
    var current: Point? = null
        set(value) {
            paint()
            field = value
            paint()
        }
    var isPressed = false
        set(value) {
            field = value
            if (value) {

                current = null
                start = null
            }
        }

    private fun paint() {
        if (isPressed && start != null && current != null) {
            start?.let { s ->
                current?.let { c ->
                    g.setXORMode(Color.WHITE)
                    g.color = Color.BLACK
                    g.drawRect(
                        min(s.x, c.x), min(s.y, c.y),
                        abs(s.x - c.x),
                        abs(s.y - c.y)
                    )
                    g.setPaintMode()
                }
            }
        }
    }
}