import kotlin.math.roundToInt

fun main() {
    fun joinToInt(bits: List<Int>): Int = bits.fold(0) { acc, curr -> acc * 2 + curr }

    fun Int.bitAt(index: Int) = if (this and 1.shl(index) != 0) 1 else 0

    fun gamma(bits: List<Int>, width: Int): Int {
        val countOfOnesInEachBitPosition = bits.fold(List(width) { 0 }) { a, c ->
            a.mapIndexed { index, i -> i + c.bitAt(index) }
        }.reversed()
        val mostCommonBits = countOfOnesInEachBitPosition.map { (it.toDouble() / bits.size).roundToInt() }
        return joinToInt(mostCommonBits)
    }

    fun part1(input: List<String>): Int {
        val width = input.first().length
        val mask = 1.shl(width) - 1
        val numbers = input.map { it.toInt(2) }
        val γ = gamma(numbers, width).and(mask)
        val ε = γ.inv().and(mask)
        println("γ = $γ, ε = $ε")
        return γ * ε
    }

    fun mostCommon(a: Int, b: Int) = a > b
    fun leastCommon(a: Int, b: Int) = a <= b

    fun keepOne(bar: List<Int>, width: Int, picker: (Int, Int) -> Boolean): List<Int> {
        if (bar.size == 1)
            return bar

        val (zeros, ones) = bar.partition { it.bitAt(width - 1) == 0 }
        val next = if (picker(zeros.size, ones.size)) zeros else ones

        return keepOne(next, width - 1, picker)
    }

    fun part2(input: List<String>): Int {
        val width = input.first().length
        val numbers = input.map { it.toInt(2) }
        val ratingO2 = keepOne(numbers, width, ::mostCommon).first()
        val ratingCO2 = keepOne(numbers, width, ::leastCommon).first()
        println("Rating O₂ = $ratingO2, CO₂ = $ratingCO2")
        return ratingO2 * ratingCO2
    }

    val testInput = readTestInput(3)
    val input = readInput(3)

    checkEquals(part1(testInput), 198)
    println(part1(input))

    checkEquals(part2(testInput), 230)
    println(part2(input))
}
