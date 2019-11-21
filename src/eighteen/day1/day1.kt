package eighteen.day1

import java.io.File

fun main(args: Array<String>) {
    val allNumbers = File("input/2018/day1.txt").readLines().map { it.toInt() }.toIntArray()

    println (allNumbers.fold(0) { sum, element -> sum + element })
}