import kotlin.math.pow
import kotlin.math.roundToInt

fun main() {
    fun gamma(bits: List<IntArray>, width: Int): Int {
        val length = bits.size
        val sums = bits.fold(IntArray(width)) { a, c ->
            a.zip(c).map { (x, y) -> x + y }.toIntArray()
        }
        println(sums.joinToString())
        return sums.map { (it.toDouble() / length).roundToInt() }.fold(0) { acc, curr -> acc * 2 + curr }
    }

    fun part1(input: List<String>): Int {
        val width = input.first().length
        val mask = (2.toDouble().pow(width) - 1).toInt()
        val bits = input.map { it.map { c -> if (c.code == 49) 1 else 0 }.toIntArray() }
        val γ = gamma(bits, width).and(mask)
        val ε = γ.inv().and(mask)
        // println("mask=${mask} gamma=${γ}, epsilon=${ε}")
        return γ * ε
    }

    // fun part2(input: List<String>): Int {
    //     val bits = input.map { it.toInt(2) }
    //
    //     return gamma(bits) * epsilon(bits)
    // }

    check(part1(readInput("Day03_test_input")) == 198)
    println(part1(readInput("Day03_real_input")))
    //
    // check(part2(readInput("Day03_test_input")) == 900)
    // println(part2(readInput("Day03_real_input")))
}
/*

0 0 0
0 1
1 0
1 1

* */
