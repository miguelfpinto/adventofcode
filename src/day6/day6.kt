package day6

import java.io.File

fun main(args: Array<String>) {
    var blocks = File("day6input.txt").readText().split("\t").map { it.toInt() }.toTypedArray()

    var numberOfTries = 0
    var nextStep = blocks.clone()

    var results: MutableList<Array<Int>> = mutableListOf()
    var found = false;
    do {
        numberOfTries++;
        nextStep = spreadBlocks(nextStep.indexOf(nextStep.max()), nextStep.clone())

        if (containsArray(results, nextStep)){
            found = true
        } else {
            results.add(nextStep)
        }
    } while (!found)

    println("> ${numberOfTries}")
}

fun spreadBlocks (position: Int, blocks: Array<Int>) : Array<Int> {
    var numBlocksToSpread = blocks[position]
    var pos = position
    blocks[position] = 0
    for (i in 1..numBlocksToSpread) {
        pos = (pos + 1) % blocks.size
        blocks[pos]++;
    }
    return blocks
}

fun containsArray(list: List<Array<Int>>, newElement: Array<Int>) : Boolean {
    return list.stream()
            .filter {it contentEquals newElement }
            .count() > 0
}

