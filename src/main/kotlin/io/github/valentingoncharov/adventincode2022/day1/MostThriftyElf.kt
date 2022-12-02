package io.github.valentingoncharov.adventincode2022.day1

import java.io.File
import java.util.Scanner

fun main() {
    val scanner = Scanner(File("/Users/valentingoncharov/Work/adventofcode2022/src/main/resources/elf_supplies.txt"))
    var max = 0
    var cur = 0

    while(scanner.hasNextLine()) {
        val line = scanner.nextLine()
        if (line.isNotBlank()) {
            cur += line.toInt()
        } else {
            if (cur > max) {
                max = cur
            }
            cur = 0
        }
    }

    if (cur > max) {
        max = cur
    }

    println(max)
}
