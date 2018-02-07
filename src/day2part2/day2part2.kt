package day2part2

import java.io.File

fun main(args: Array<String>) {
    var sum = 0
    File("day2input.txt").readLines().map {
        val sorted = it.split("\t").map { it.toInt() }.sortedDescending()
        sorted.forEach {
            sum += getSumWithDividend(it, sorted)
        }
    }
    println(sum)
}

fun getSumWithDividend(number: Int, otherNumbers: List<Int>) : Int {
    otherNumbers.forEach{
        if (it != number && number % it == 0) {
            return number / it
        }
    }
    return 0
}