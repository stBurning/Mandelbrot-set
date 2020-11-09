package math

import kotlin.math.absoluteValue
import kotlin.math.atan
import kotlin.math.sqrt

/** Класс реализующий комплексное число (x + iy)
 *  и операции между комплексными числами
 * @author Устинов Константин
 * @param x вещественная часть числа
 * @param y мнимая часть числа
 * **/
class Complex(private var x: Double, private var y: Double) {
    /** Конструктор по умолчанию инициализирует нулевое число
     * @see Complex - параметризованый конструктор*/
    constructor() : this(0.0, 0.0)

    /** Модуль комплексного числа
     * @see mod2 - квадрат модуля комплексного числа
     * @see arg - аргумент комплесного числа*/
    fun mod() = sqrt(x * x + y * y)


    /** Квадрат модуля комплексного числа
     * @see mod - молуль комплексного числа
     * @see arg - аргумент комплесного числа*/
    fun mod2() = x * x + y * y

    /** Аргумент комплексного числа
     * @see mod - модуль комплесного числа*/
    fun arg() = atan(y / x)

    /** Оператор суммы комплексных чисел
     * @param c слагаемое комплексное число*/
    operator fun plus(c: Complex) =
        Complex(x + c.x, y + c.y)

    /** Оператор разности комплексных чисел
     * @param c вычитаемое комплексное число*/
    operator fun minus(c: Complex) =
        Complex(x - c.x, y - c.y)

    /** Оператор умножения комплексных чисел
     * @param c комплексный множитель*/
    operator fun times(c: Complex) =
        Complex(x * c.x - y * c.y, x * c.y + y * c.x)

    /** Оператор деления комплексных чисел
     * @param c комплексный делитель*/
    operator fun div(c: Complex): Complex {
        return Complex(
            (x * c.x + y * c.y) / c.mod2(),
            (y * c.x - x * c.y) / c.mod2()
        )
    }
    /** Оператор суммы комплексных чисел
     * @param c слагаемое комплексное число*/
    operator fun plusAssign(c: Complex) {
        this.x += c.x
        this.y += c.y
    }
    /** Оператор разности комплексных чисел
     * @param c вычитаемое комплексное число*/
    operator fun minusAssign(c: Complex) {
        this.x -= c.x
        this.y -= c.y
    }
    /** Оператор умножения комплексных чисел
     * @param c комплексный множитель*/
    operator fun timesAssign(c: Complex) {
        val u = x * c.x - y * c.y
        val v = x * c.y + y * c.x
        this.x = u; this.y = v
    }
    /** Оператор возведения ком
     * плексных чисел в целую степень
     * @param p степень, в которую необходимо возвести число*/
    infix fun powAssign(p: Int) {
        if (p == 0){
            x = 1.0; y = 0.0
            return
        }
        var r = Complex(1.0, 0.0)
        repeat(p.absoluteValue) { r.timesAssign(this) }
        if (p < 0) {
            r = Complex(1.0, 0.0) / r
        }
        this.x = r.x
        this.y = r.y
    }

    /** Оператор возведения комплексных чисел в целую степень
     * @param p степень, в которую необходимо возвести число
     * @return данное число в степени p*/
    infix fun pow(p: Int): Complex {
        if (p == 0){
            return Complex(1.0, 0.0)
        }
        var r = Complex(1.0, 0.0)
        repeat(p.absoluteValue) { r.timesAssign(this) }
        if (p < 0) {
            r = Complex(1.0, 0.0) / r
        }
        return r
    }


}