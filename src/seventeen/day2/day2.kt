package seventeen.day2

import java.io.File

fun main(args: Array<String>) {
    val spreadsheetLines = File("input/2017/day2.txt").readLines()

    println(spreadsheetLines.map {
        it.split("\t").map { it.toInt() }.toIntArray()
    }.map { it.max()!! - it.min()!! }.sum())
}