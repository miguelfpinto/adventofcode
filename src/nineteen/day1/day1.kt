package src.nineteen.day1

import java.io.File

fun main() {
    val result = File("input/2019/day1.txt").readLines().map { ((it.toInt() / 3) - 2) }.sum()

    println (result)
}