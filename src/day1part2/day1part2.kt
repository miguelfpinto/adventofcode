package day1part2

import java.io.File

fun main(args: Array<String>) {
    val captcha = File("day1input.txt").readText()

    var sum = 0

    (0 until captcha.length).forEach { i ->
        var pos = (i+(captcha.length/2))%captcha.length
        if (captcha[i] == captcha[pos])
            sum += Integer.valueOf(""+captcha[i])
    }

    println(sum)
}