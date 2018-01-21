package day7

import java.io.File

fun main(args: Array<String>) {
    val input = File("day7input.txt").readLines()
    var possibleBottoms: MutableList<String> = mutableListOf()
    var notBottoms: MutableList<String> = mutableListOf()

    input.forEach{
        val programInfo = it.split(" -> ")
        val programName = programInfo.get(0).split(" ").get(0)
        possibleBottoms.add(programName)

        if (programInfo.size == 2) {
            val programsBelow = programInfo.get(1).split(", ")
            programsBelow.forEach { notBottoms.add(it) }
        }
    }
    possibleBottoms.removeAll(notBottoms)
    println(possibleBottoms)
}
