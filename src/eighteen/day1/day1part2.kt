package src.eighteen.day1

import java.io.File

fun main() {
    val allNumbers = File("input/2018/day1.txt").readLines().map { it.toInt() }

    var total = 0

    val frequencies = mutableSetOf(0)

    var freqFound = false
    while (!freqFound) {
        for (line in allNumbers) {
            total += line

            if (frequencies.contains(total)) {
                println(total)
                freqFound = true
                break
            } else {
                frequencies.add(total)
            }
        }
    }
}