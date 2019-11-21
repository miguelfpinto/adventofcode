package day7

import java.io.File

fun main(args: Array<String>) {
    val input = File("input/2017/day7.txt").readLines()
    var possibleBottoms: MutableList<String> = mutableListOf()
    var notBottoms: MutableList<String> = mutableListOf()
    val rowRegex = """\w+""".toRegex()

    input.forEach{
        val groups = rowRegex.findAll(it).toList().map { it.value }

        val programName = groups[0]
        possibleBottoms.add(programName)

        groups.drop(2).forEach { notBottoms.add(it) }
    }
    possibleBottoms.removeAll(notBottoms)
    println(possibleBottoms)
}
