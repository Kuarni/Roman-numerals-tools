fun inSupportRomanDigit(romanDigit: Char): Boolean {
    if (romanDigit.code in "2160".toInt(16).."217F".toInt(16)) return true
    if (romanDigit in 'a'..'z') return romanDigit - ('a' - 'A') in LatinRomanNum
    val otherChars = LatinRomanNum.keys + OtherRomanNum.keys
    return romanDigit in otherChars
}

fun getArabic(romanDigit: Char): Int? {
    if (!inSupportRomanDigit(romanDigit))
        return null
    if (romanDigit.code in "2170".toInt(16).."217F".toInt(16))
        return UnicodeRomanNum[romanDigit - 16]
    if (romanDigit in 'a'..'z') return LatinRomanNum[romanDigit - ('a' - 'A')]
    return (LatinRomanNum + UnicodeRomanNum + OtherRomanNum)[romanDigit]
}

val LatinRomanNum = mapOf(
    Pair('I', 1),
    Pair('V', 5),
    Pair('X', 10),
    Pair('L', 50),
    Pair('C', 100),
    Pair('D', 500),
    Pair('M', 1000),
)

val UnicodeRomanNum = mapOf(
    Pair('Ⅰ', 1),
    Pair('Ⅱ', 2),
    Pair('Ⅲ', 3),
    Pair('Ⅳ', 4),
    Pair('Ⅴ', 5),
    Pair('Ⅵ', 6),
    Pair('Ⅶ', 7),
    Pair('Ⅷ', 8),
    Pair('Ⅸ', 9),
    Pair('Ⅹ', 10),
    Pair('Ⅺ', 11),
    Pair('Ⅻ', 12),
    Pair('Ⅼ', 50),
    Pair('Ⅽ', 100),
    Pair('Ⅾ', 500),
    Pair('Ⅿ', 1000)
)

val OtherRomanNum = mapOf(
    Pair('ↅ', 6),
    Pair('ↆ', 50),
    Pair('Θ', 100),
    Pair('⊕', 100),
    Pair('⊗', 100),
    Pair('Φ', 1000),
    Pair('ↀ', 1000),
    Pair('ↁ', 5000),
    Pair('ↂ', 10000),
    Pair('ↇ', 50000),
    Pair('ↈ', 100000)
)
