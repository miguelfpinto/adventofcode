package day4part2

import java.io.File

fun main(args: Array<String>) {
    val lineList = File("day4input.txt").readLines()

    val count = lineList.stream()
        .filter { isValidPassphrase(it) }
        .count()

    println(count)
}

fun isValidPassphrase(passphrase: String): Boolean {
    println("Checking ${passphrase}")
    val words = passphrase.split(" ")
    val uniques = words.distinct()
    if (words.size != uniques.size) {
        return false
    }
    uniques.forEach {
        if (!findAnagrams(it, uniques).isEmpty())
            return false
    }
    return true
}

fun findAnagrams(word: String, allWords: Collection<String>) =
        allWords.filter { containSameChars(word, it.toLowerCase()) }
                .filterNot { it.equals(word, ignoreCase = true) }
                .toSet()

private fun containSameChars(original: String, candidate: String) =
        candidate.toLowerCase().toCharArray().sorted() == original.toLowerCase().toCharArray().sorted()