package graphics

import java.awt.Color
import kotlin.math.*

fun colorScheme(x: Float): Color {
    val r = abs(cos(PI.toFloat() - sin(x*1234 + 900)))
    val g = abs(sin(8+ x.pow(1 - cos(x/1900))))
    val b = abs(cos(-8 - x.pow(3)))
    return Color(r, g, b)
}