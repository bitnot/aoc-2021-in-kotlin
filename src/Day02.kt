data class Position(val vertical: Int, val horizontal: Int) {
    operator fun plus(other: Position): Position {
        return Position(vertical + other.vertical, horizontal + other.horizontal)
    }

    fun distance(): Int = vertical * horizontal

    companion object {
        val Initial = Position(0, 0)

        fun parse(s: String): Position {
            val (command, v) = s.trim().split(" ")
            val value = v.toInt()
            return when (command) {
                "forward" -> Position(0, value)
                "down" -> Position(value, 0)
                "up" -> Position(-value, 0)
                else -> throw IllegalArgumentException("Cannot parse $s")
            }
        }
    }
}

fun List<Position>.sum(): Position =
    this.fold(Position.Initial) { acc: Position, t: Position -> acc + t }

data class State(val aim: Int, val position: Position) {
    operator fun plus(other: State): State {
        val newAim = aim + other.aim
        val vertical = position.vertical + newAim * other.position.horizontal
        val horizontal = position.horizontal + other.position.horizontal
        return State(newAim, Position(vertical, horizontal))
    }

    companion object {
        val Initial = State(0, Position(0, 0))
        fun parse(s: String): State {
            val (command, v) = s.trim().split(" ")
            val value = v.toInt()
            return when (command) {
                "forward" -> State(0, Position(0, value))
                "down" -> State(value, Position(0, 0))
                "up" -> State(-value, Position(0, 0))
                else -> throw IllegalArgumentException("Cannot parse $s")
            }
        }
    }
}

fun List<State>.sum(): State =
    this.fold(State.Initial) { acc: State, t: State -> acc + t }

fun main() {
    fun part1(input: List<String>): Int = input.map { Position.parse(it) }.sum().distance()

    fun part2(input: List<String>): Int = input.map { State.parse(it) }.sum().position.distance()

    check(part1(readInput("Day02_test_input")) == 150)
    println(part1(readInput("Day02_real_input")))

    check(part2(readInput("Day02_test_input")) == 900)
    println(part2(readInput("Day02_real_input")))
}
