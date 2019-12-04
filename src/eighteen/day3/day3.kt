package src.eighteen.day3

import java.io.File

fun main() {
    val inputLines = File("input/2018/day3.txt").readLines()

    var positions = mutableMapOf<Pair<Int, Int>, Int>()

    val inputRegex = """#(\d+) @ (\d+),(\d+): (\d+)x(\d+)""".toRegex()

    inputLines.forEach {
        val (index, startX, startY, width, height) = inputRegex.find(it)!!.destructured

        for (i in startX.toInt() until startX.toInt() + width.toInt()) {
            for (j in startY.toInt() until startY.toInt() + height.toInt()) {
                positions[Pair(i,j)] = positions.getOrDefault(Pair(i,j), 0) + 1
            }
        }
    }

    println(positions.filterValues { it > 1 }.count())
}