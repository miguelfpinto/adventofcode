package src.nineteen.day1

import java.io.File

fun main(args: Array<String>) {
    val allNumbers = File("input/2019/day1.txt").readLines().map { it.toInt() }.toIntArray()

    println (allNumbers.fold(0) { sum, moduleMass -> sum + calculateModuleFuel(moduleMass) })
}

fun calculateModuleFuel(mass: Int) : Int {
    if (mass <= 0)
        return 0

    val fuelForThisMass = ((mass / 3) - 2)

    if (fuelForThisMass <= 0)
        return 0

    return fuelForThisMass + calculateModuleFuel(fuelForThisMass)
}