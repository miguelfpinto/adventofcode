package src.nineteen.day1

import java.io.File

fun main(args: Array<String>) {
    val allNumbers = File("input/2019/day1.txt").readLines().map { it.toInt() }.toIntArray()

    println (allNumbers.fold(0) { sum, moduleMass -> sum + ((moduleMass / 3) - 2) })
}