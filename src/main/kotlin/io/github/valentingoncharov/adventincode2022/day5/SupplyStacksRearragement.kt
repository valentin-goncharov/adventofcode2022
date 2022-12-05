package io.github.valentingoncharov.adventincode2022.day5

import java.io.File
import java.util.Scanner

fun main() {
    val crates = """(((\[[A-Z]\])|\s{3})\s?)?+""".toRegex()
    val cratesBottom = """(\s*\d+)+""".toRegex()
    val movements = """move (\d+) from (\d+) to (\d+)""".toRegex()
    val scanner = Scanner(File("/Users/valentingoncharov/Work/adventofcode2022/src/main/resources/supply_stacks.txt"))

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
    val cratesStack = mutableListOf<ArrayDeque<Char>>()
    while(scanner.hasNextLine()) {
        val line = scanner.nextLine()
        if (line.isEmpty() || cratesBottom.matches(line)){
            if(line.isEmpty()) {
                printStack(cratesStack)
                println("--------------")
                println("Start movements")
                println("")
            }
            continue
        }
        if (movements.matches(line)) {
            println(line)
            val (count, from, to) =  movements.find(line)!!.destructured
            repeat(count.toInt()) {
                cratesStack[to.toInt()-1].addLast(cratesStack[from.toInt()-1].removeLast())
            }
            printStack(cratesStack)
            println("--------------")
        } else {
            var i = 0
            var result = crates.find(line)
            while(result?.next() != null) {
                if (i == cratesStack.size) {
                    cratesStack.add(ArrayDeque())
                }
                val value = result.value
                if(value.isNotBlank()){
                    cratesStack[i].addFirst(value[1])
                }
                i++
                result = result.next()
            }
        }
    }

    println(cratesStack.map { it.last() }.joinToString(""))
}

fun printStack(stack: List<ArrayDeque<Char>>) {

    var level =  stack.maxBy { it.size }.size

    while(level > 0) {
        val builder = StringBuilder()
        for(q in stack) {
            if (q.size < level) {
                builder.append("    ")
            } else {
                builder.append("[").append(q[level-1]).append("] ")
            }
        }
        println(builder.toString())
        level--
    }
    val builder = StringBuilder()
    for (i in 1 .. stack.size) {
        builder.append(" $i ").append(" ")
    }
    println(builder.toString())
}
