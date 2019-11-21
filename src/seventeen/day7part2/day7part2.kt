package day7part2

import java.io.File
import kotlin.math.absoluteValue

fun main(args: Array<String>) {
    val input = File("input/2017/day7.txt").readLines()
    var possibleBottoms: MutableList<String> = mutableListOf()
    var notBottoms: MutableList<String> = mutableListOf()

    var allPrograms = mutableMapOf<String, Program>()
    val parentChildPairs = mutableSetOf<Pair<Program, String>>()
    val rowRegex = """\w+""".toRegex()

    input.forEach{
        val groups = rowRegex.findAll(it).toList().map { it.value }

        val programName = groups[0]
        possibleBottoms.add(programName)

        val prog = Program(programName, groups[1].toInt())
        allPrograms.put(prog.name, prog)

        groups.drop(2).forEach {
            notBottoms.add(it)
            parentChildPairs.add(Pair(prog, it))
        }

    }

    parentChildPairs.forEach { (program, subProgram) ->
        with(allPrograms.getValue(subProgram)) {
            program.subPrograms.add(this)
        }
    }
    possibleBottoms.removeAll(notBottoms)
    println(findImbalance(allPrograms.get(possibleBottoms.first())!!))
}

tailrec fun findImbalance(root: Program, imbalance: Int? = null): Int {

    return if (imbalance != null && root.childrenAreBalanced()) {
        root.weight - imbalance
    } else {
        val subProgramsByWeight = root.subPrograms.groupBy { it.getTotalWeight() }
        val badTowerRoot = subProgramsByWeight.minBy { it.value.size }?.value?.first()!!
        findImbalance(badTowerRoot, imbalance ?: subProgramsByWeight.keys.reduce { a, b -> a - b }.absoluteValue)
    }
}

data class Program (val name: String, val weight: Int, var subPrograms: MutableList<Program> = mutableListOf()) {

    fun childrenAreBalanced(): Boolean {
        return subPrograms.map { it.getTotalWeight() }.distinct().size == 1
    }


    fun getTotalWeight() : Int {
        return weight + subPrograms.sumBy { it.getTotalWeight() }
    }
}