package io.github.valentingoncharov.adventincode2022.day11

import java.io.File
import java.util.Scanner

fun main() {
    val scanner = Scanner(File("inputs/monkeys_items.txt"))
    val monkeys = readMonkeys(scanner)

    for (i in 0 until 20) {
        for (monkey in monkeys) {
            with (monkey) {
                while (items.isNotEmpty()) {
                    val item = items.removeFirst()
                    val x = if (operand1 == "old") item else operand1.toInt()
                    val y = if (operand2 == "old") item else operand2.toInt()

                    val newValue = operation(x, y) / 3
                    if (newValue % test == 0) {
                        monkeys[positiveApplier].items.addLast(newValue)
                    } else {
                        monkeys[negativeApplier].items.addLast(newValue)
                    }
                    processCount++
                }
            }
        }
    }

    monkeys.forEach {println(it) }

    val max2 = monkeys.max2By { it.processCount }

    println("----------------")
    println(max2.first.processCount * max2.second.processCount)
}
