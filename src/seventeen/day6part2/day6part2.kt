package src.seventeen.day6part2

import java.io.File

fun main(args: Array<String>) {
    val blocks = File("input/2017/day6.txt").readText().split("\t").map { it.toInt() }.toTypedArray()

    var numberOfTries = 0

    var nextStep = blocks.clone()
    var results: MutableList<Array<Int>> = mutableListOf()
    var found = false;

    do {
        numberOfTries++
        nextStep = spreadBlocks(nextStep.indexOf(nextStep.max()), nextStep.clone())
        if (containsArray(results, nextStep)){
            found = true
            println("1st appearance: ${numberOfTries}")
            println("2nd appearance: " + loopUntilFoundAgain(nextStep))
        }
        results.add(nextStep)
    } while (!found)
}

fun loopUntilFoundAgain(blocks: Array<Int>) : Int {
    var step = blocks
    var numberOfTries = 0
    do {
        numberOfTries++
        step = spreadBlocks(step.indexOf(step.max()), step.clone())
    } while(!(step contentEquals blocks) )

    return numberOfTries
}

fun spreadBlocks (position: Int, blocks: Array<Int>) : Array<Int> {
    val numBlocksToSpread = blocks[position]
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