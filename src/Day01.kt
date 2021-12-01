fun main() {
    fun countIncreasingDepths(depths: List<Long>) = depths
        .zip(depths.drop(1))
        .fold(0) { acc, t ->
            acc + if (t.first < t.second) 1 else 0
        }

    fun part1(input: List<String>): Int {
        val depths = input.map { it.toLong() }
        return countIncreasingDepths(depths)
    }

    fun slidingSums(nums: List<Long>, window: Int): List<Long> = nums.windowed(window).map { it.sum() }

    fun part2(input: List<String>): Int {
        val depths = input.map { it.toLong() }
        return countIncreasingDepths(slidingSums(depths, 3))
    }

    check(part1(readInput("Day01_puzzle1_input1")) == 7)
    println(part1(readInput("Day01_puzzle1_input2")))

    check(part2(readInput("Day01_puzzle1_input1")) == 5)
    println(part2(readInput("Day01_puzzle1_input2")))
}
