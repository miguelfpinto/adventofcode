package day2part2

import java.io.File

fun main(args: Array<String>) {
    val spreadsheetLines = File("day2input.txt").readLines()
    
    spreadsheetLines.forEach {
        val sorted = it.split("\t").map { it.toInt() }.sortedDescending()
        println(sorted)
    }
}