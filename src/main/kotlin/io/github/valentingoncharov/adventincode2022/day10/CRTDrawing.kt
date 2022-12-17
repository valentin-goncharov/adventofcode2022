package io.github.valentingoncharov.adventincode2022.day10

import java.io.File
import java.util.Scanner

fun main() {

    val scanner = Scanner(File("inputs/cathode_ray_instructions.txt"))

    var register = 1
    var duration = 0
    var value = 0

    for (i in 0 until 6) {
        val builder = StringBuilder()
        for (j in 0 until 40) {
            if (duration == 0 && scanner.hasNextLine()) {
                register += value
                when (val instruction = scanner.nextLine()) {
                    "noop" -> {
                        duration = 1
                        value = 0
                    }
                    else -> {
                        duration = 2
                        value = instruction.split(" ")[1].toInt()
                    }
                }
            }
            if (duration > 0) {
                builder.append( if (register in j-1 .. j+1) '#' else '.')
                duration--
            } else {
                builder.append('.')
            }
        }
        println(builder.toString())
    }

    println("----------------")
}
