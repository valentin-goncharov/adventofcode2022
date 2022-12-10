package io.github.valentingoncharov.adventincode2022.day5

import java.io.File
import java.util.Scanner

fun main() {
    val movements = """move (\d+) from (\d+) to (\d+)""".toRegex()
    val scanner = Scanner(File("inputs/supply_stacks.txt"))

    /*
    [V]         [T]         [J]
    [Q]         [M] [P]     [Q]     [J]
    [W] [B]     [N] [Q]     [C]     [T]
    [M] [C]     [F] [N]     [G] [W] [G]
    [B] [W] [J] [H] [L]     [R] [B] [C]
    [N] [R] [R] [W] [W] [W] [D] [N] [F]
    [Z] [Z] [Q] [S] [F] [P] [B] [Q] [L]
    [C] [H] [F] [Z] [G] [L] [V] [Z] [H]
     1   2   3   4   5   6   7   8   9
     */
    val cratesStack = readStacks(scanner)
    while(scanner.hasNextLine()) {
        val line = scanner.nextLine()
        if (movements.matches(line)) {
            println(line)
            val (count, from, to) =  movements.find(line)!!.destructured
            cratesStack[from.toInt()-1]
            repeat(count.toInt()) {
                cratesStack[to.toInt()-1].addLast(cratesStack[from.toInt()-1].removeLast())
            }
            printStack(cratesStack)
            println("--------------")
        }
    }

    println(cratesStack.map { it.last() }.joinToString(""))
}

