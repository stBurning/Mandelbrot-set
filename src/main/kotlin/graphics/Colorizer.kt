package graphics

import java.awt.Color
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin

fun colorScheme(x: Float): Color {
    val r = abs(cos(sin(x*1234 + 200)))
    val g = abs(sin(10 + x.pow(5))).toFloat()
    val b = abs(cos(7 - x.pow(16)))
    return Color(r, g, b)
}