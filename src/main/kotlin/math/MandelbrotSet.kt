package math

import kotlin.math.abs
import kotlin.math.max

class MandelbrotSet {

    /**
     * r^2
     * */
    private var r2: Double = 4.0

    //Количество итераций, в течение которых проверяется
    //принадлежность точки множеству
    private var maxIterates = 200
        set(value) {
            //Проверяем устанавливаемое значение на корректность
            field = max(200, abs(value))
        }

    /**
     * Метод определения принадлежности точки множеству Мандельброта
     * @param c точка комплексной плоскости
     * @return true, если точка принадлежит множеству (при заданном значении maxIter)
     * false - в противном случае */
    fun isInSet(c: Complex): Float {
        val z = Complex()
        for (i in 1..maxIterates) {
            z powAssign 2
            z += c
            if (z.mod2() > r2) return i.toFloat()/maxIterates.toFloat()
        }
        return 1F
    }
}
