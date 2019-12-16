package eighteen.day5

import java.io.File

fun main() {
    val input = File("input/2018/day5.txt").readText()

    println("${reactString(input).length} units")
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
