package seventeen.day25

import java.io.File

fun main(args : Array<String>) {
    val input = File("input/2017/day25.txt").readLines()
    //from: https://todd.ginsberg.com/post/advent-of-code/2017/day25/
    val machine = parseInput(input)

    print(machine.run())

}

class MachineState(val ifZeroAction: Action, val ifOneAction: Action)

class Action(val write: Boolean, val move: Int, val nextState: Char)


class TuringMachine(private val states: Map<Char, MachineState>, private val steps: Int, startState: Char) {
        private val positionsMarkedAsOne = mutableSetOf<Int>()
        private var state = states.getValue(startState)
        private var cursor = 0

        fun run(): Int {
            repeat(steps) {
                execute()
            }
            return positionsMarkedAsOne.size
        }

        private fun execute() {
            if (cursor in positionsMarkedAsOne) {
                handleAction(state.ifOneAction)
            } else {
                handleAction(state.ifZeroAction)
            }
        }

        private fun handleAction(action: Action) {
            if (action.write)
                positionsMarkedAsOne.add(cursor)
            else
                positionsMarkedAsOne.remove(cursor)

            cursor += action.move

            state = states.getValue(action.nextState)
        }
    }

private fun parseInput(input: List<String>): TuringMachine {
    val initialState = input.first()[15]
    val steps = input[1].split(" ")[5].toInt()

    val stateMap = input
            .filter { it.isNotBlank() }
            .drop(2)
            .map { it.split(" ").last().dropLast(1) }
            .chunked(9)
            .map {
                it[0].first() to MachineState(
                        Action(it[2] == "1", if (it[3] == "right") 1 else -1, it[4].first()),
                        Action(it[6] == "1", if (it[7] == "right") 1 else -1, it[8].first())
                )
            }.toMap()
    return TuringMachine(stateMap, steps, initialState)
}
