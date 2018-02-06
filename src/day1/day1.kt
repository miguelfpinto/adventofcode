package day1

import java.io.File

fun main(args: Array<String>) {
    val captcha = File("day1input.txt").readText()

    var sum = 0

    (0 until captcha.length-1).forEach { i ->
        if (captcha[i] == captcha[i+1])
            sum += Integer.valueOf(""+captcha[i])
    }

    if (captcha[captcha.length-1] == captcha[0])
        sum += Integer.valueOf(""+captcha[0])
    println(sum)
}