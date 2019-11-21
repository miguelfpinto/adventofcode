package day19

import java.io.File

fun main(args: Array<String>) {
    val matrix = mutableListOf<String>()

    File("input/2017/day19.txt").useLines { lines -> lines.forEach { matrix.add(it) }}
    //matrix.forEach { println(">" + it) }

    var status = Status(getFirstPosition(matrix), Direction.DOWN)
    var lastChar: Char
    var numSteps = 0
    do {
        status = getNextPosition(status, matrix)
        lastChar = matrix[status.position.x][status.position.y]
        if(lastChar != '|' && lastChar != '-' && lastChar != '+')
            print(lastChar)
        numSteps++
    } while (lastChar != ' ')

    println(" > $numSteps")
}

fun getFirstPosition(matrix: MutableList<String>) : Position {
    return Position(0, matrix[0].indexOf("|"))
}


fun getNextPosition(status: Status, matrix:  MutableList<String>) : Status {
    if (matrix[status.position.x][status.position.y] == '+')
        return changeDirection(status, matrix)

    return when(status.direction) {
        Direction.DOWN -> Status(Position(status.position.x+1, status.position.y), status.direction)
        Direction.UP -> Status(Position(status.position.x-1, status.position.y), status.direction)
        Direction.LEFT -> Status(Position(status.position.x, status.position.y-1), status.direction)
        Direction.RIGHT -> Status(Position(status.position.x, status.position.y+1), status.direction)
    }
}

fun changeDirection(status: Status, matrix:  MutableList<String>) : Status {
    if (status.direction == Direction.DOWN || status.direction == Direction.UP ) {
        if (status.position.y - 1 >= 0 && matrix[status.position.x][status.position.y - 1] != ' ')
            return Status(Position(status.position.x, status.position.y - 1), Direction.LEFT)
        else
            return Status(Position(status.position.x, status.position.y + 1), Direction.RIGHT)
    } else if (status.position.y - 1 < matrix[status.position.x-1].length-1 && matrix[status.position.x-1][status.position.y] != ' ') {
        return Status(Position(status.position.x - 1, status.position.y), Direction.UP)
    }
    else {
        return Status(Position(status.position.x + 1, status.position.y), Direction.DOWN)
    }
}

data class Status(val position: Position, val direction: Direction)

enum class Direction {
    DOWN, UP, LEFT, RIGHT
}

data class Position (val x: Int, val y: Int)