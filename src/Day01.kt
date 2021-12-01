fun main() {
    fun part1(input: List<String>): Int {
        val depths = input.map { it.toLong() }
        return depths
            .zip(depths.drop(1))
            .fold(0) { acc, t ->
                acc + if (t.first < t.second) 1 else 0
            }
    }

    // fun part2(input: List<String>): Int {
    //     return input.size
    // }

    check(part1(readInput("Day01_puzzle1_input1")) == 7)
    println(part1(readInput("Day01_puzzle1_input2")))

    // val input = readInput("Day01")
    // println(part1(input))
    // println(part2(input))
}
