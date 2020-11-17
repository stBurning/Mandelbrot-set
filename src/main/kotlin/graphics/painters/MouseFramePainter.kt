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
            current = null
            field = value
            if (value) {
                current = null
                start = null
            }
        }

    init {
        g.apply {
            setXORMode(Color.WHITE)
            drawRect(-2, -2, 1, 1)
            setPaintMode()
        }
    }

    private fun paint() {
        if (isPressed && start != null && current != null) {
            start?.let { s ->
                current?.let { c ->
                    g.apply {
                        setXORMode(Color.WHITE)
                        color = Color.BLACK
                        drawRect(
                            min(s.x, c.x), min(s.y, c.y),
                            abs(s.x - c.x),
                            abs(s.y - c.y))
                        setPaintMode()
                    }
                }
            }
        }
    }
}