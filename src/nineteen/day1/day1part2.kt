package nineteen.day1

import java.io.File

fun main() {
    val result = File("input/2019/day1.txt").readLines().map { calculateModuleFuel(it.toInt()) }.sum()

    println(result)
}

fun calculateModuleFuel(mass: Int) : Int {
    val fuelForThisMass = ((mass / 3) - 2)
    return if (fuelForThisMass <= 0)
        0
    else
        fuelForThisMass + calculateModuleFuel(fuelForThisMass)
}