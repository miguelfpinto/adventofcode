package day11

import java.io.File
import kotlin.math.abs
import kotlin.math.max

fun main(args : Array<String>) {
    val input = File("day11input.txt").readText().split(",")

    // Using cube coordinates from  https://www.redblobgames.com/grids/hexagons/
    var x = 0
    var y = 0
    var z = 0
    var maxDistance = 0

    input.forEach{
        when (it) {
            "nw" -> { y++; x-- }
            "n" -> {z--;  y++}
            "ne" -> { x++; z-- }
            "se" -> {x++; y--}
            "s" -> {z++; y--}
            "sw" -> { x--; z++ }
        }
        maxDistance = max(maxDistance, distanceToOrigin(x,y,z))
    }

    println("final position ($x, $y, $z) ")
    println("max distance to origin $maxDistance")
    println("final distance to origin ${distanceToOrigin(x,y,z)}")
}

fun distanceToOrigin(x: Int, y: Int, z: Int) : Int {
    return (abs(x) + abs(y) + abs(z)) / 2
}
