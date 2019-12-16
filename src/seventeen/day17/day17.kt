package seventeen.day17

fun main(args : Array<String>) {

    val inputSteps = 316
    val buffer = mutableListOf(0)

    var positionAfter = 0

    for (i in 1..2017) {
        positionAfter = ((positionAfter + inputSteps) % buffer.size) + 1
        buffer.add(positionAfter, i)
    }

    println("${buffer[ (positionAfter + 1) % buffer.size]}")
}