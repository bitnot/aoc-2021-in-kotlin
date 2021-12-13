fun main() {

    fun part1(input: List<Int>): Int = input
        .zipWithNext()
        .count { (current, next) -> next > current }

    fun part2(input: List<Int>): Int = input
        .zip(input.drop(2))
        .count { (current, next) -> next > current }

    val testInput = readTestInput(1).map { it.toInt() }
    val input = readInput(1).map { it.toInt() }

    checkEquals(part1(testInput), 7)
    println(part1(input))

    checkEquals(part2(testInput), 5)
    println(part2(input))
}
