typealias Down = Int
typealias Forward = Int
typealias Aim = Int
typealias Position = Pair<Down, Forward>
typealias State = Pair<Aim, Position>
fun State.aim() = this.first
fun State.depth() = this.second.first
fun State.horizontal() = this.second.second

fun main() {

    fun parseDirection(s: String): Position {
        val parts = s.trim().split(" ")
        val value = parts[1].toInt()
        return when (parts[0]) {
            "forward" -> 0 to value
            "down" -> value to 0
            "up" -> -value to 0
            else -> throw IllegalArgumentException("Cannot parse $s")
        }
    }

    fun parseStates(s: String): State {
        val parts = s.trim().split(" ")
        val value = parts[1].toInt()
        return when (parts[0]) {
            "forward" -> 0 to (0 to value)
            "down" -> value to (0 to 0)
            "up" -> -value to (0 to 0)
            else -> throw IllegalArgumentException("Cannot parse $s")
        }
    }

    fun sumDirections(directions: List<Position>): Position =
        directions.fold(0 to 0) { acc: Position, t: Position ->
            (acc.first + t.first) to (acc.second + t.second)
        }

    fun sumStates(directions: List<State>): State =
        directions.fold(0 to (0 to 0)) { acc: State, curr: State ->
            val aim = acc.aim() + curr.aim()
            val depth = acc.depth() + aim * curr.horizontal()
            val horizontal = acc.horizontal() + curr.horizontal()
            aim to (depth to horizontal)
        }

    fun part1(input: List<String>): Int {
        val directions = input.map { parseDirection(it) }
        val result = sumDirections(directions)
        return result.first * result.second
    }

    fun part2(input: List<String>): Int {
        val states = input.map { parseStates(it) }
        val result = sumStates(states)
        return result.second.first * result.second.second
    }

    check(part1(readInput("Day02_puzzle1_input1")) == 150)
    println(part1(readInput("Day02_puzzle1_input2")))

    check(part2(readInput("Day02_puzzle1_input1")) == 900)
    println(part2(readInput("Day02_puzzle1_input2")))
}
