package eighteen.day2

import java.io.File

fun main() {
    val allLines = File("input/2018/day2.txt").readLines()

    val wordsWithThreeLetters = allLines.stream().filter{word -> containsNEqualsLetters(word, 3)}.count()
    val wordsWithTwoLetters = allLines.stream().filter{word -> containsNEqualsLetters(word, 2)}.count()

    println (wordsWithThreeLetters * wordsWithTwoLetters)
}

fun containsNEqualsLetters(word: String, n: Int) : Boolean {

    val charsMap = mutableMapOf<Char, Int>()

    word.forEach{
        charsMap[it] = charsMap.getOrDefault(it, 0) + 1
    }

    return charsMap.containsValue(n)
}