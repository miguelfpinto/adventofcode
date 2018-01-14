package day4

import java.io.File

fun main(args: Array<String>) {
    val lineList = File("day4input.txt").readLines()

    var count = lineList.stream()
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