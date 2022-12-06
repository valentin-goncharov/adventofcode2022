package io.github.valentingoncharov.adventincode2022.day5

import printStack
import readStacks
import java.io.File
import java.util.Scanner

fun main() {

    val movements = """move (\d+) from (\d+) to (\d+)""".toRegex()
    val scanner = Scanner(File("/Users/valentingoncharov/Work/adventofcode2022/src/main/resources/supply_stacks.txt"))

    val cratesStack = readStacks(scanner)

    while (scanner.hasNextLine()) {
        val line = scanner.nextLine()
        if (movements.matches(line)) {
            println(line)
            val (count, from, to) =  movements.find(line)!!.destructured
            cratesStack[to.toInt()-1].addAll(cratesStack[from.toInt()-1].takeLast(count.toInt()))
            repeat(count.toInt()) {
                cratesStack[from.toInt()-1].removeLast()
            }
            printStack(cratesStack)
            println("--------------")
        }
    }

    println(cratesStack.map { it.last() }.joinToString(""))
}
