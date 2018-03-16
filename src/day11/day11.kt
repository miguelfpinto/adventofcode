package day11

import java.io.File
import kotlin.math.abs
import kotlin.math.max

fun main(args : Array<String>) {
    val input = File("day11input.txt").readText().split(",")

    // Using cube coordinates from  https://www.redblobgames.com/grids/hexagons/
    val finalPosition = input.fold( Hexagon(0,0,0) ) { currentPosition, direction -> moveToNextHexagon(currentPosition, direction) }

    println("final position $finalPosition ")
    println("final distance to origin ${distanceToOrigin(finalPosition)}")

    var currentPosition = Hexagon(0,0,0)
    var maxDistance = 0

    input.forEach{
        currentPosition = moveToNextHexagon(currentPosition, it)
        maxDistance = max(maxDistance, distanceToOrigin(currentPosition))
    }
    println("max distance to origin $maxDistance")
}

fun distanceToOrigin(hex: Hexagon) : Int {
    return (abs(hex.x) + abs(hex.y) + abs(hex.z)) / 2
}

fun moveToNextHexagon(position: Hexagon, direction: String) : Hexagon {
    return when (direction) {
        "nw" -> { Hexagon(position.x - 1, position.y + 1,  position.z) }
        "n" -> { Hexagon(position.x, position.y + 1,  position.z - 1) }
        "ne" -> { Hexagon(position.x + 1, position.y,  position.z - 1) }
        "se" -> { Hexagon(position.x + 1, position.y - 1,  position.z) }
        "s" -> { Hexagon(position.x, position.y -1 ,  position.z + 1) }
        "sw" -> { Hexagon(position.x - 1, position.y,  position.z + 1) }
        else -> Hexagon(0,0,0)
    }
}

data class Hexagon(val x: Int, val y: Int, val z: Int)