package seventeen.day17part2

fun main(args : Array<String>) {

    val inputSteps = 316

    var positionAfter: Int

    positionAfter = 0
    var valueAtOne = 1
    for (i in 1..50000000) {
        positionAfter = ((positionAfter + inputSteps) % i) + 1
        if (positionAfter == 1)
            valueAtOne = i
    }

    println("$valueAtOne")
}