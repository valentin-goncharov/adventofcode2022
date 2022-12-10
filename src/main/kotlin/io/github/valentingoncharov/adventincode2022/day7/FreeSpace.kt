package io.github.valentingoncharov.adventincode2022.day7

import java.io.File
import java.util.Scanner

fun main() {
    val scanner = Scanner(File("inputs/device_dirs.txt"))

    val dirs = mutableListOf<Directory>()

    val root = readDirectories(scanner, dirs)

    root.printTree()
    println("--------------------------")

    val totalDisk = 70000000
    val updateSize = 30000000

    val freeSize = totalDisk - root.size
    val needSize = updateSize - freeSize

    dirs.filter { it.size >= needSize }.minBy { it.size }.let { println(it) }
}
