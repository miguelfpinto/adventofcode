package src.eighteen.day1

import java.io.File

fun main() {
    val sumAllNumbers = File("input/2018/day1.txt").readLines().map { it.toInt() }.sum()

    println (sumAllNumbers)
}