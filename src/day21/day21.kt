package day21

import java.io.File
import kotlin.collections.HashMap

fun main(args : Array<String>) {

    // .#.
    // ..#
    // ###

    var currentMatrix = Array2D<String>(3, 3){ x, y -> "-" }

    currentMatrix[0,0] = "."; currentMatrix[0,1] = "#"; currentMatrix[0,2] = "."
    currentMatrix[1,0] = "."; currentMatrix[1,1] = "."; currentMatrix[1,2] = "#"
    currentMatrix[2,0] = "#"; currentMatrix[2,1] = "#"; currentMatrix[2,2] = "#"

    val rules = parseInput()
    currentMatrix.print()

    currentMatrix = rules.getValue(currentMatrix)

    currentMatrix.print()

    repeat(17) {
        var smallSize = if (currentMatrix.array.size % 2 == 0) 2 else 3

        var split = splitInSmallerSquares(currentMatrix, smallSize)
        currentMatrix = expandAndMerge(split, rules)
    }

    // print count of #
    println(currentMatrix.countHashtags())
}

fun splitInSmallerSquares(original: Array2D<String>, smallSize: Int) : Array2D<Array2D<String>> {
    var outerMatrix = Array2D<Array2D<String>>(original.array.size / smallSize, original.array.size / smallSize){ x, y -> Array2D() }
    var x = 0
    var y = 0
    var outerX = 0
    var outerY = 0
    while( x < original.array.size) {
        while( y < original.array.size) {

            var smallMatrix = Array2D<String>(smallSize, smallSize){ x, y -> "-" }

            for( i in 0 until  smallSize) {
                for( j in 0 until smallSize) {
                    smallMatrix[i,j] = original[x + i,  y + j]
                }
            }


            outerMatrix[outerX,outerY] = smallMatrix
            y += smallSize
            outerY++
        }
        x += smallSize
        outerX++
        y=0
        outerY=0

    }

    return outerMatrix
}

fun expandAndMerge(original: Array2D<Array2D<String>>, rules: HashMap<Array2D<String>, Array2D<String>>) : Array2D<String> {

    var finalSize : Int
    var smallMatrixSize: Int

    if (original[0,0].array.size % 2 == 0) {
        finalSize = 3 * original.array.size
        smallMatrixSize = 2
    } else {
        finalSize = 4 * original.array.size
        smallMatrixSize = 3
    }

    var finalMatrix = Array2D<String>(finalSize, finalSize){ x, y -> "-" }

    var cornerX = 0
    var cornerY = 0
    for( i in 0 until  original.array.size) {
        for( j in 0 until original.array.size) {
            val newSmallBlock = rules.getValue(original[i,j])

            for( w in 0 until  newSmallBlock.array.size) {
                for (z in 0 until newSmallBlock.array.size) {
                    val posX = cornerX + w
                    val posY = cornerY + z
                    finalMatrix[posX,posY] = newSmallBlock[w,z]
                }
            }
            cornerY += smallMatrixSize + 1
        }
        cornerY = 0
        cornerX += smallMatrixSize + 1
    }

    return finalMatrix
}

fun parseInput() : HashMap<Array2D<String>, Array2D<String>> {
    val input = File("day21input.txt").readLines()
    val rules = HashMap<Array2D<String>, Array2D<String>>()

    input.forEach{
        val groups = it.split(" => ")

        val rightMatrix = getArray2DFromString(groups[1])

        rules[ getArray2DFromString(groups[0]) ] = rightMatrix

        rules[ getArray2DFromString(groups[0]).flipHorizontal() ] = rightMatrix

        rules[ getArray2DFromString(groups[0]).flipVertical() ] = rightMatrix

        rules[ getArray2DFromString(groups[0]).rotateLeft() ] = rightMatrix
        rules[ getArray2DFromString(groups[0]).rotateLeft().flipHorizontal() ] = rightMatrix
        rules[ getArray2DFromString(groups[0]).rotateLeft().flipVertical() ] = rightMatrix

        rules[ getArray2DFromString(groups[0]).rotateLeft().rotateLeft() ] = rightMatrix
        rules[ getArray2DFromString(groups[0]).rotateLeft().rotateLeft().flipVertical() ] = rightMatrix
        rules[ getArray2DFromString(groups[0]).rotateLeft().rotateLeft().flipHorizontal() ] = rightMatrix

        rules[ getArray2DFromString(groups[0]).rotateLeft().rotateLeft().rotateLeft() ] = rightMatrix
        rules[ getArray2DFromString(groups[0]).rotateLeft().rotateLeft().rotateLeft().flipHorizontal() ] = rightMatrix
        rules[ getArray2DFromString(groups[0]).rotateLeft().rotateLeft().rotateLeft().flipVertical() ] = rightMatrix
    }

    return rules
}

fun getArray2DFromString(line: String ) : Array2D<String> {
    val lines = line.split("/")
    val size = lines.size

    val array2D = Array2D( size, size) { x, y -> "-" }
    var currentLine = 0
    var currentColumn = 0
    lines.forEach {
        it.forEach {
            array2D[currentLine,currentColumn] = it.toString()
            currentColumn = (currentColumn + 1) % size
        }
        currentLine++
    }

    return array2D
}

class Array2D<T> (val xSize: Int, val ySize: Int, val array: Array<Array<T>>) {

    companion object {

        inline operator fun <reified T> invoke() = Array2D(0, 0, Array(0, { emptyArray<T>() }))

        inline operator fun <reified T> invoke(xWidth: Int, yWidth: Int, operator: (Int, Int) -> (T)): Array2D<T> {
            val array = Array(xWidth, {
                val x = it
                Array(yWidth, {operator(x, it)})})
            return Array2D(xWidth, yWidth, array)
        }
    }

    operator fun get(x: Int, y: Int): T {
        return array[x][y]
    }

    operator fun set(x: Int, y: Int, t: T) {
        array[x][y] = t
    }

    override fun toString(): String {
        var result = ""
        for( x in 0 until xSize) {
            for( y in 0 until ySize) {
                result += array[x][y]
            }
            result+="/"
        }

        return result
    }

    fun flipHorizontal() : Array2D<T> {
        var i = 0
        var k = array.size - 1
        while (i < k) {
            val x = array[i]
            array[i] = array[k]
            array[k] = x
            ++i
            --k
        }
        return this
    }

    fun flipVertical() : Array2D<T> {

        for (j in 0 until array[0].size / 2) {
            for (i in 0 until array.size) {
                val x = array[i][j]
                array[i][j] = array[i][array[0].size - 1 - j]
                array[i][array[0].size - 1 - j] = x
            }
        }
        return this
    }

    fun rotateLeft() : Array2D<T> {
        return this.transpose().flipHorizontal()
    }

    private fun transpose() : Array2D<T> {
        for( i in 0 until xSize) {
            for( j in i until ySize) {
                val temp = array[i][j]
                array[i][j] = array[j][i]
                array[j][i] = temp
            }
        }
        return this
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Array2D<*>) return false
        if (xSize != other.xSize) return false
        if (ySize != other.ySize) return false


        for (j in 0 until array[0].size) {
            for (i in 0 until array.size) {
                if (array[i][j] != other.array[i][j])
                    return false
            }
        }
        return true
    }

    override fun hashCode(): Int {
        var result = xSize
        result = 31 * result + ySize
        for (j in 0 until array[0].size) {
            for (i in 0 until array.size) {
                result = 31 * result + array[i][j]!!.hashCode()
            }
        }
        return result
    }

    fun countHashtags(): Int {
        var result = 0
        for( i in 0 until xSize) {
            for( j in 0 until ySize) {
                if (array[i][j]!!.equals("#") )
                    result++
            }
        }
        return result
    }

    fun print() {
        for( i in 0 until xSize) {
            for( j in 0 until ySize) {
                print("${array[i][j]}")
            }
            println()
        }
    }
}