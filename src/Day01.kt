fun main() {
    fun countIncreasingDepths(depths: List<Long>) = depths
        .zipWithNext()
        .count { (current, next) -> next > current }

    fun part1(input: List<String>): Int {
        val depths = input.map { it.toLong() }
        return countIncreasingDepths(depths)
    }

    fun part2(input: List<String>): Int {
        val depths = input.map { it.toLong() }
        return countIncreasingDepths(depths.windowed(3).map { it.sum() })
    }

    check(part1(readInput("Day01_test_input")) == 7)
    println(part1(readInput("Day01_real_input")))

    check(part2(readInput("Day01_test_input")) == 5)
    println(part2(readInput("Day01_real_input")))
}
