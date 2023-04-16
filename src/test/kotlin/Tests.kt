import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Tests {
    companion object {
        @JvmStatic
        fun validTestsLatinArg(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("I", 1),
                Arguments.of("III", 3),
                Arguments.of("IIII", 4),
                Arguments.of("IV", 4),
                Arguments.of("VII", 7),
                Arguments.of("LXXX", 80),
                Arguments.of("CMXLIII", 943),
                Arguments.of("CDLCIX", 459),
                Arguments.of("i", 1),
                Arguments.of("iii", 3),
                Arguments.of("iiii", 4),
                Arguments.of("iv", 4),
                Arguments.of("vii", 7),
                Arguments.of("lxxx", 80),
                Arguments.of("cmxliii", 943),
                Arguments.of("cdlcix", 459),
                Arguments.of("CmXlIiI", 943),
                Arguments.of("iXiiiiii", 15),
                Arguments.of("i".repeat(15), 15)
            )
        }

        @JvmStatic
        fun validTestsUnicodeArg(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("Ⅰ", 1),
                Arguments.of("Ⅱ", 2),
                Arguments.of("ⅢⅠ", 4),
                Arguments.of("Ⅳ", 4),
                Arguments.of("Ⅴ", 5),
                Arguments.of("Ⅵ", 6),
                Arguments.of("Ⅶ", 7),
                Arguments.of("Ⅷ", 8),
                Arguments.of("Ⅸ", 9),
                Arguments.of("Ⅹ", 10),
                Arguments.of("Ⅺ", 11),
                Arguments.of("Ⅻ", 12),
                Arguments.of("ⅬⅩⅩⅩ", 80),
                Arguments.of("ⅭⅯⅩⅬⅢ", 943),
                Arguments.of("ⅭⅮⅬⅭⅨ", 459),
                Arguments.of("ⅰ", 1),
                Arguments.of("ⅱ", 2),
                Arguments.of("ⅲⅰ", 4),
                Arguments.of("ⅳ", 4),
                Arguments.of("ⅴ", 5),
                Arguments.of("ⅵ", 6),
                Arguments.of("ⅶ", 7),
                Arguments.of("ⅷ", 8),
                Arguments.of("ⅸ", 9),
                Arguments.of("ⅹ", 10),
                Arguments.of("ⅺ", 11),
                Arguments.of("ⅻ", 12),
                Arguments.of("ⅼⅹⅹⅹ", 80),
                Arguments.of("ⅽⅿⅹⅼⅲ", 943),
                Arguments.of("ⅽⅾⅼⅽⅸ", 459),
                Arguments.of("ⅽⅯⅹⅬⅲ", 943),
                Arguments.of("Ⅻ".repeat(15), 12 * 15)
            )
        }

        @JvmStatic
        fun validTestsOthersArg(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("ↈↇↂↂↁↀ", 176000), Arguments.of("Φ⊗⊕Θↆↅ", 1356)
            )
        }

        @JvmStatic
        fun validTestsMixedArg(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("ↈↇↁↀCMXLⅲ", 156943), Arguments.of("ↈↇↁↀΦMXLiↅ", 158045)
            )
        }
    }

    @ParameterizedTest
    @MethodSource("validTestsLatinArg", "validTestsUnicodeArg", "validTestsOthersArg", "validTestsMixedArg")
    @DisplayName("valid tests")
    fun validTests(romanNumber: String, arabicNumber: Int) {
        assertAll({ assertTrue(isValid(romanNumber)) }, { assertEquals(arabicNumber, convert(romanNumber)) })
    }

    @ParameterizedTest
    //here are characters that aren't supported or aren't Roman numerals
    @ValueSource(
        strings = ["\u0000", "IↃ", "LHD", "LhD", "\n", "\u2159", "\u2189", "qwertyuop", "asdfghjk", "zcbn", "a", "iiiix", "iivii"]
    )
    @DisplayName("isValid return false tests")
    fun nonValidTests(romanNumber: String) {
        assertFalse(isValid(romanNumber))
    }


    @ParameterizedTest
    //here are strings with length 0 and 16
    @ValueSource(strings = ["", "iiiiiiiiiiiiiiii", "iiiiiiiiiiiiiiik", "iiiiikiiiiiiiiii"])
    @DisplayName("isValid throws")
    fun breakTests(romanNumber: String) {
        assertThrows<IllegalArgumentException>(
            "length must be greater than 0 and shorter than 16"
        ) { isValid(romanNumber) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "qwertyuop"])
    @DisplayName("converter throws")
    fun converterThrows(romanNumber: String) {
        assertThrows<IllegalArgumentException>("non-Valid Roman number") { convert(romanNumber) }
    }
}
