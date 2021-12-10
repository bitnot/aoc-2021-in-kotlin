import kotlin.math.pow
import kotlin.math.roundToInt

fun main() {
    fun gamma(bits: List<IntArray>, width: Int): Int {
        val length = bits.size
        val sums = bits.fold(IntArray(width)) { a, c ->
            a.zip(c).map { (x, y) -> x + y }.toIntArray()
        }
        // println(sums.joinToString())
        return sums.map { (it.toDouble() / length).roundToInt() }.fold(0) { acc, curr -> acc * 2 + curr }
    }

    fun part1(input: List<String>): Int {
        val width = input.first().length
        val mask = (2.toDouble().pow(width) - 1).toInt()
        val bits = input.map { it.map { c -> if (c.code == 49) 1 else 0 }.toIntArray() }
        val γ = gamma(bits, width).and(mask)
        val ε = γ.inv().and(mask)
        return γ * ε
    }

    fun mostCommon(a: Int, b: Int) = a > b
    fun leastCommon(a: Int, b: Int) = a <= b

    fun keepOne(bar: List<Int>, bitIndex: Int, picker: (Int, Int) -> Boolean): List<Int> {
        if (bar.size == 1)
            return bar

        val (zeros, ones) = bar.partition { it.and(1.shl(bitIndex)) == 0 }
        val next = if (picker(zeros.size, ones.size)) zeros else ones

        // println("${bitIndex}: out of ${zeros.size} zeros and ${ones.size} ones picked ${next.size}: ${
        //     next.joinToString { Integer.toBinaryString(it).padStart(5, '0') }
        // }"
        // )

        return keepOne(next, bitIndex - 1, picker)
    }

    fun part2(input: List<String>): Int {
        val width = input.first().length
        val mask = (2.toDouble().pow(width) - 1).toInt()
        val bits = input.map { it.toInt(2) }
        val ratingO2 = keepOne(bits, width - 1, ::mostCommon).first().and(mask)
        val ratingCO2 = keepOne(bits, width - 1, ::leastCommon).first().and(mask)
        println("Rating O₂ = $ratingO2, CO₂ = $ratingCO2")
        return ratingO2 * ratingCO2
    }

    checkEquals(part1(readInput("Day03_test_input")), 198)
    println(part1(readInput("Day03_real_input")))

    checkEquals(
        part2(readInput("Day03_test_input")), 230
    )
    println(part2(readInput("Day03_real_input")))
}
