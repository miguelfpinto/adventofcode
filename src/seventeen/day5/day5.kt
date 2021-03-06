package seventeen.day5

import java.io.File

fun main(args: Array<String>) {
    val instructions = File("input/2017/day5.txt").readLines().map { it.toInt() }.toIntArray()
    //var instructions : IntArray = intArrayOf(0, 3, 0, 1, -3)
    var currentPosition = 0
    var numberMoves = 0

    do {
        var nextMove = instructions[currentPosition]
        instructions[currentPosition]++
        currentPosition += nextMove
        numberMoves++
    } while (currentPosition >= 0 && currentPosition < instructions.size)
    println(numberMoves)
}