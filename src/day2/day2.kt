package day2

import java.io.File

fun main(args: Array<String>) {
    val spreadsheetLines = File("day2input.txt").readLines()

    println(spreadsheetLines.map {
        it.split("\t").map { it.toInt() }.toIntArray()
    }.map { it.max()!! - it.min()!! }.sum())
}