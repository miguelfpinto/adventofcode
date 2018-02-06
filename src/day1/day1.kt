package day1

import java.io.File

fun main(args: Array<String>) {
    val captcha = File("day1input.txt").readText().map { it.toInt() }.toTypedArray()

    var sum = 0

    for(i in 0 until captcha.size-1) {
        if (captcha[i] == captcha[i+1])
            sum += captcha[i]
    }
    if (captcha[captcha.size-1] == captcha[0])
        sum += captcha[0]
    println(sum)
}