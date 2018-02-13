package day10

import java.io.File

fun main(args : Array<String>) {
    var list = IntArray(256) { it }
    val lengths = File("day10input.txt").readText().split(",").map { it.toInt() }

    var skipSize = 0
    var currentPosition = 0

    lengths.forEach { length ->
        list = reverseSublist(list, currentPosition, length)
        currentPosition = (currentPosition + length + skipSize) % list.size
        skipSize += 1

        println(list.joinToString())
    }

    println("${list[0]} * ${list[1]} = ${list[0] * list[1]}")
}

fun reverseSublist(list: IntArray, start: Int, length: Int) : IntArray{
    var startPosition = start % list.size
    var endPosition = (startPosition + length - 1) % list.size
    repeat(length / 2) {
        swap(list, startPosition, endPosition)
        startPosition = startPosition.inc() % list.size
        endPosition = endPosition.dec().takeIf { it >= 0 } ?: list.size - 1
    }
    return list
}

fun swap(array: IntArray, a: Int, b: Int): IntArray {
    val tmp = array[a]
    array[a] = array[b]
    array[b] = tmp
    return array
}
