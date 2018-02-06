package day8

import java.io.File

fun main(args: Array<String>) {
    val input = File("day8input.txt").readLines()
    val registers = HashMap<String, Int>()
    var max = Int.MIN_VALUE
    var historicalMax = Int.MIN_VALUE

    input.forEach{
        val instructions = it.split(" ")
        val registerToUpdate = instructions[0]
        val operation = instructions[1]
        val value = instructions[2].toInt()
        val registerToCheck = instructions[4]
        val conditionToCheck = instructions[5]
        val valueToCompareWith = instructions[6].toInt()

        if (evaluate(registers.getOrDefault(registerToCheck, 0), conditionToCheck, valueToCompareWith)) {
            val newValue = doOperation(registers.getOrDefault(registerToUpdate, 0), operation, value)

            historicalMax = maxOf(historicalMax, newValue)

            registers.put(registerToUpdate, newValue)
        }
    }

    for ((reg, value) in registers) {
        println("Register $reg =  $value")
        max = maxOf(max, value)
    }

    println("HistoricalMax: $historicalMax Max: $max")
}

fun evaluate(valueA: Int, condition: String, valueB: Int) : Boolean {
    return when(condition) {
        "<" -> valueA < valueB
        "<=" -> valueA <= valueB
        ">" -> valueA > valueB
        ">=" -> valueA >= valueB
        "!=" -> valueA != valueB
        "==" -> valueA == valueB
        else -> false
    }
}

fun doOperation(valueA: Int, operation: String, valueB: Int) : Int {
    return when(operation) {
        "inc" -> valueA + valueB
        "dec" -> valueA - valueB
        else -> valueA
    }
}


