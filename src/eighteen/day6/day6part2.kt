package eighteen.day6

import java.io.File
import kotlin.math.abs

fun main() {

    val inputRegex = """(\d+), (\d+)""".toRegex()
    var points = mutableSetOf<Pair<Int,Int>>()

    File("input/2018/day6.txt").readLines().map {
        val (x, y) = inputRegex.find(it)!!.destructured
        points.add( Pair(x.toInt(),y.toInt()) )
    }

    val leftPoint = points.minBy { it.first}
    val rightPoint = points.maxBy { it.first}
    val upPoint = points.minBy { it.second}
    val downPoint = points.maxBy { it.second}

    val leftUpCorner = Pair(leftPoint!!.first, upPoint!!.second)
    val leftDownCorner = Pair(leftPoint!!.first, downPoint!!.second)
    val rightUpCorner = Pair(rightPoint!!.first, upPoint!!.second)
    val rightDownCorner = Pair(rightPoint!!.first, downPoint!!.second)

    println ("""
        $leftUpCorner       $rightUpCorner

        $leftDownCorner       $rightDownCorner
    """)

    var output = 0
    for (y in leftUpCorner.second..leftDownCorner.second) {
        for (x in leftUpCorner.first..rightUpCorner.first) {
            val totalDistances = points.sumBy {
                calculateDistanceFromCoordinateToPoint(x,y,it)
            }

            if (totalDistances < 10000) {
                output++
            }
        }
    }
    println(output)
}

private fun calculateDistanceFromCoordinateToPoint(x: Int, y: Int, point: Pair<Int,Int>) : Int {
    return abs(x - point.first) + abs(y - point.second)
}
