package io.github.valentingoncharov.adventincode2022.day1

import java.io.File
import java.util.Scanner

fun main() {
    val scanner = Scanner(File("inputs/elf_supplies.txt"))
    val supplies = mutableListOf<Int>()
    var cur = 0

    while(scanner.hasNextLine()) {
        val line = scanner.nextLine()
        if (line.isNotBlank()) {
            cur += line.toInt()
        } else {
            supplies.add(cur)
            cur = 0
        }
    }

    if (cur > 0) {
        supplies.add(cur)
    }

    supplies.sortDescending()
    println(supplies.take(3).sum())
}
