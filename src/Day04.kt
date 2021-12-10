inline fun <reified T> transpose(xs: Array<Array<T>>): Array<Array<T>> {
    val cols = xs[0].size
    val rows = xs.size
    return Array(cols) { j ->
        Array(rows) { i ->
            xs[i][j]
        }
    }
}

class Board(private val numbers: Array<Array<Int>>) {
    private val transposed = transpose(numbers)
    private val unmarked = numbers.flatten().toSortedSet()
    fun mark(number: Int): Boolean = unmarked.remove(number)

    fun isBingo(): Boolean {
        fun Array<Array<Int>>.rowBingo() = this.any { row -> row.none { num -> unmarked.contains(num) } }
        return numbers.rowBingo() || transposed.rowBingo()
    }

    fun score(): Int = unmarked.sum()
}

fun main() {

    fun part1(input: List<String>): Int {
        val numbers = input.first().split(",").map { it.toInt() }
        val boards = input.drop(1)
            .windowed(6, 6)
            .map { sixLines ->
                Board(
                    sixLines.drop(1)
                        .map { line ->
                            line.trim().split("  ", " ")
                                .map { it.toInt() }
                                .toTypedArray()
                        }.toTypedArray()
                )
            }

        for (number in numbers) {
            for (board in boards) {
                if (board.mark(number) && board.isBingo())
                    return board.score() * number
            }
        }

        return 0
    }

    val testInput = readTestInput(4)
    val input = readInput(4)

    checkEquals(part1(testInput), 4512)
    println(part1(input))
    //
    // checkEquals(part2(testInput), 230)
    // println(part2(input))
}
