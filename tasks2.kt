//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    
}

// 1
fun findmax(a: Int, b: Int): Int {
    return maxOf(a, b)
}

//2
fun divide(a: Double, b: Double): Double {
    if (b == 0.0) throw ArithmeticException("Деление на ноль недопустимо")
    return a / b
}

//3
fun convertToInt(str: String): Int {
    return str.toIntOrNull() ?: throw NumberFormatException("Невозможно преобразовать '$str' в число")
}


//4
fun validateAge(age: Int) {
    if (age < 0 || age > 150) throw IllegalArgumentException("Недопустимый возраст: $age")
}

//5
fun sqrt(number: Double): Double {
    if (number < 0) throw IllegalArgumentException("Отрицательное число недопустимо")
    return kotlin.math.sqrt(number)
}

//6
fun factorial(n: Int): Long {
    if (n < 0) throw IllegalArgumentException("Отрицательное число недопустимо")
    return if (n <= 1) 1
    else n * factorial(n - 1)
}

//7
fun checkForZeros(array: IntArray) {
    if (array.contains(0)) throw IllegalStateException("Массив содержит нули")
}

//8
fun power(base: Double, exponent: Int): Double {
    if (exponent < 0) throw IllegalArgumentException("Отрицательная степень недопустима")
    return Math.pow(base, exponent.toDouble())
}

//9
fun truncateString(str: String, length: Int): String {
    if (length > str.length) throw IllegalArgumentException("Длина обрезки больше длины строки")
    return str.substring(0, length)
}

//10
fun <T> findElement(array: Array<T>, element: T): Int {
    val index: Int = array.indexOf(element)
    if (index == -1) throw NoSuchElementException("Элемент не найден")
    return index
}

//11
fun toBinary(number: Int): String {
    if (number < 0) throw IllegalArgumentException("Отрицательное число недопустимо")
    return number.toString(2)
}

//12
fun checkDivisibility(a: Int, b: Int): Boolean {
    if (b == 0) throw ArithmeticException("Деление на ноль недопустимо")
    return a % b == 0
}

//13
fun <T> getElement(list: List<T>, index: Int): T {
    if (index !in list.indices) throw IndexOutOfBoundsException("Индекс за пределами списка")
    return list[index]
}

//14
class WeakPasswordException(message: String) : Exception(message)
fun validatePassword(password: String) {
    if (password.length < 8) throw WeakPasswordException("Пароль должен содержать минимум 8 символов")
}

//15
fun validateDate(dateStr: String) {
    try {
        java.time.LocalDate.parse(dateStr, java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy"))
    } catch (e: Exception) {
        throw java.time.format.DateTimeParseException("Неверный формат даты", dateStr, 0)
    }
}

//16
fun concatenateStrings(str1: String?, str2: String?): String {
    if (str1 == null || str2 == null) throw NullPointerException("Строки не могут быть null")
    return str1 + str2
}

//17
fun mod(a: Int, b: Int): Int {
    if (b == 0) throw ArithmeticException("Деление на ноль недопустимо")
    return a % b
}

//18
fun squareRoot(number: Double): Double {
    if (number < 0) throw IllegalArgumentException("Отрицательное число недопустимо")
    return kotlin.math.sqrt(number)
}

//19
fun celsiusToFahrenheit(celsius: Double): Double {
    if (celsius < -273.15) throw IllegalArgumentException("Температура ниже абсолютного нуля")
    return celsius * 9/5 + 32
}

//20
fun validateString(str: String?) {
    if (str == null || str.isEmpty()) throw IllegalArgumentException("Строка пустая или null")
}