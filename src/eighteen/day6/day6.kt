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

    var matrix = Array(rightDownCorner.first+1) {Array(rightDownCorner.second+1) { listOf(Pair(-1,-1)) } }

    val pairCount = mutableMapOf<Pair<Int,Int>, Int>()

    for (y in leftUpCorner.second..leftDownCorner.second) {
        for (x in leftUpCorner.first..rightUpCorner.first) {
            val closestPoints = getClosestPoints(x, y, points)
            matrix[x][y] = closestPoints

            if (closestPoints.size == 1) {
                val point = closestPoints[0]
                if (point.first>leftPoint.first && point.first < rightPoint.first && point.second > upPoint.second && point.second < downPoint.second) {
                    pairCount[closestPoints[0]] = pairCount.getOrDefault(closestPoints[0], 0) + 1
                }
            }
        }
    }
    println(pairCount.values.sortedBy { it })
    println(pairCount.values.max())
    //answer is the second max, not the first max. find the bug
}

private fun getClosestPoints(x: Int, y: Int, points: Set<Pair<Int, Int>>) : List<Pair<Int,Int>> {
    var minDistance = points.maxBy { it.first + it.second }!!.second
    var result = mutableListOf<Pair<Int,Int>>()

    points.forEach {
        val distance = calculateDistanceFromCoordinateToPoint(x, y, it)

        if (distance == minDistance) {
            result.add(it)
        } else if ( distance < minDistance) {
            minDistance = distance
            result.clear()
            result.add(it)
        }
    }

    return result
}

private fun calculateDistanceFromCoordinateToPoint(x: Int, y: Int, point: Pair<Int,Int>) : Int {
    return abs(x - point.first) + abs(y - point.second)
}
