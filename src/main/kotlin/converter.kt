fun convert(romanNumber: String): Int {
    if (!isValid(romanNumber))
        throw IllegalArgumentException("non-Valid Roman number")

    var number = 0
    var lastDigit = 0
    for (i in romanNumber) {
        val curDigit = getArabic(i)!!
        if (curDigit > lastDigit) {
            number -= lastDigit
        }
        else  {
            number += lastDigit
        }
        lastDigit = curDigit
    }
    return number + lastDigit
}
