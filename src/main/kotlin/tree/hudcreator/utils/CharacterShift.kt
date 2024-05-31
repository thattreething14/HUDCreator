package tree.hudcreator.utils

import kotlin.math.abs

enum class CharacterShift(val code: Char, val pixels: Int) {
    // -1 to -8 pixels
    NEGATIVE_1('\uF801', -1),
    NEGATIVE_2('\uF802', -2),
    NEGATIVE_3('\uF803', -3),
    NEGATIVE_4('\uF804', -4),
    NEGATIVE_5('\uF805', -5),
    NEGATIVE_6('\uF806', -6),
    NEGATIVE_7('\uF807', -7),
    NEGATIVE_8('\uF808', -8),

    // -16, -32, -64, -128, -256, -512, -1024 pixels
    NEGATIVE_16('\uF809', -16),
    NEGATIVE_32('\uF80A', -32),
    NEGATIVE_64('\uF80B', -64),
    NEGATIVE_128('\uF80C', -128),
    NEGATIVE_256('\uF80D', -256),
    NEGATIVE_512('\uF80E', -512),
    NEGATIVE_1024('\uF80F', -1024),

    // 1 to 8 pixels
    POSITIVE_1('\uF821', 1),
    POSITIVE_2('\uF822', 2),
    POSITIVE_3('\uF823', 3),
    POSITIVE_4('\uF824', 4),
    POSITIVE_5('\uF825', 5),
    POSITIVE_6('\uF826', 6),
    POSITIVE_7('\uF827', 7),
    POSITIVE_8('\uF828', 8),

    // 16, 32, 64, 128, 256, 512, 1024 pixels
    POSITIVE_16('\uF829', 16),
    POSITIVE_32('\uF82A', 32),
    POSITIVE_64('\uF82B', 64),
    POSITIVE_128('\uF82C', 128),
    POSITIVE_256('\uF82D', 256),
    POSITIVE_512('\uF82E', 512),
    POSITIVE_1024('\uF82F', 1024),

    // -max and max
    MAX_NEGATIVE('\uF800', Int.MIN_VALUE), // Use Int.MIN_VALUE for maximally negative
    MAX_POSITIVE('\uF820', Int.MAX_VALUE); // Use Int.MAX_VALUE for maximally positive

    companion object {
        fun fromCode(code: Char): CharacterShift? {
            return entries.find { it.code == code }
        }

        private val pixelValues = entries.sortedBy { it.pixels }

        fun shift(code: String, amount: Int): String {
            val result = StringBuilder()
            var remainingShift = amount

            while (remainingShift != 0) {
                val bestFit = pixelValues.filter {
                    if (remainingShift > 0) it.pixels in 1..remainingShift
                    else it.pixels in remainingShift..-1
                }.maxByOrNull { abs(it.pixels) }

                if (bestFit != null) {
                    result.append(bestFit.code)
                    remainingShift -= bestFit.pixels
                } else {
                    break // No suitable fit found, exit the loop
                }
            }

            // Append the original code after shifting
            result.append(code)

            return result.toString()
        }
    }
}