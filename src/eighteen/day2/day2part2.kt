package eighteen.day2

import java.io.File

fun main() {
    val allLines = File("input/2018/day2.txt").readLines()

    for (i in 0 until allLines.size-2) {

        for (j in i until allLines.size-1) {
            val differentChars = indexOfDifferentCharIfWordsAreDifferentJustByOneChar(allLines[i], allLines[j])

            if (differentChars != -1) {

                println("Different char in " + allLines[i] + " position $differentChars: " + allLines[i][differentChars])
                println("Different char in " + allLines[j] + " position $differentChars: " + allLines[j][differentChars])
                println("Common chars: " + allLines[i].removeRange(differentChars..differentChars))
            }
        }
    }
}

fun indexOfDifferentCharIfWordsAreDifferentJustByOneChar(word: String, word2: String) : Int {
    var differentChars = 0
    var differentCharIndex = -1

    for (i in word.indices) {
        if (word[i] != word2[i]) {
            differentChars++
            differentCharIndex = i
        }
    }

    return if (differentChars == 1) differentCharIndex else -1
}