fun isValid(romanNumber: String): Boolean {
    if (romanNumber.length !in 1..15) throw IllegalArgumentException("length must be greater than 0 and shorter than 16")
    var lastDigit = 0
    var countOfDigit = 0
    for (i in romanNumber) {
        if (!inSupportRomanDigit(i)) return false

        val curDigit = getArabic(i)!!
        if (curDigit > lastDigit)
            if (countOfDigit >= 1) return false
        if (curDigit == lastDigit) countOfDigit++
        else countOfDigit = 0

        lastDigit = curDigit
    }
    return true
}
