package seventeen.day4

import java.io.File

fun main(args: Array<String>) {
    val lineList = File("input/2017/day4.txt").readLines()

    val count = lineList.stream()
        .filter { isValidPassphrase(it) }
        .count()

    println(count)
}

fun isValidPassphrase(passphrase: String): Boolean {
    println("Checking ${passphrase}")
    val words = passphrase.split(" ")
    val uniques = words.distinct()
    return words.size == uniques.size
}