package io.github.valentingoncharov.adventincode2022.day7

import java.io.File
import java.util.Scanner


fun main() {
    val scanner = Scanner(File("/Users/valentingoncharov/Work/adventofcode2022/src/main/resources/device_dirs.txt"))

    val dirs = mutableListOf<Directory>()

    val root = readDirectories(scanner, dirs)

    root.printTree()
    println("--------------------------")
    dirs.filter { it.size <= 100000 }.sumOf { it.size }.let { println(it) }
}
