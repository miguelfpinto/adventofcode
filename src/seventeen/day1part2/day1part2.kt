package day1part2

import java.io.File

fun main(args: Array<String>) {
    val captcha = File("input/2017/day1.txt").readText()

    var sum = 0

    (0 until captcha.length).forEach { i ->
        var pos = (i+(captcha.length/2))%captcha.length
        if (captcha[i] == captcha[pos])
            sum += captcha[i].toString().toInt()
    }

    println(sum)
}