package eighteen.day5

import java.io.File
import java.lang.Integer.min

fun main() {
    val input = File("input/2018/day5.txt").readText()

    var c = 'A'
    var minUnitsAfterRemovingChar = input.length
    while (c <= 'Z') {
        val unitsWithoutThisChar = reactString(input.replace(c.toString(), "", true)).length

        minUnitsAfterRemovingChar = min(unitsWithoutThisChar, minUnitsAfterRemovingChar)

        ++c
    }
    println("$minUnitsAfterRemovingChar units")
}

private fun reactString(text: String): String {
    var reactedText = text
    var i = 0
    do {
        if (isInverseCase(reactedText[i], reactedText[i + 1])) {
            reactedText = reactedText.removeRange(i, i + 2)
            i = maxOf(i - 1, 0)
        } else {
            i++
        }
    } while (i < reactedText.length - 1)
    return reactedText
}

private fun isInverseCase(first: Char, second:Char) : Boolean {
    return (first.isUpperCase() && first.toLowerCase() == second || first.isLowerCase() && first.toUpperCase() == second )
}
