package seventeen.day12

import java.io.File

fun main(args : Array<String>) {
    val input = File("input/2017/day12.txt").readLines()

    val allPrograms = input.map(::toProgram).toMap()
    println(allPrograms)

    val result = seenFromNode(0, allPrograms).size

    println(result)


}

private fun seenFromNode(from: Int, allPrograms: Map<Int,Set<Int>>, alreadyChecked: MutableSet<Int> = mutableSetOf()): Set<Int> {
    if(from !in alreadyChecked) {
        alreadyChecked.add(from)
        allPrograms.getValue(from).forEach { seenFromNode(it, allPrograms, alreadyChecked) }
    }
    return alreadyChecked
}

private fun toProgram(inputLine: String) : Pair<Int, Set<Int>> {
    val programInfo =  inputLine.replace("\\s".toRegex(), "").split("<->")
    val connections = programInfo[1].split(",").map { it.toInt() }
    return programInfo.first().toInt() to connections.toSet()
}