package io.github.valentingoncharov.adventincode2022.day11

import java.util.Scanner

fun plus(x: Int, y: Int): Int = x + y
fun mult(x: Int, y: Int): Int = x * y
fun sub(x: Int, y: Int): Int = x - y
fun div(x: Int, y: Int): Int = x / y

data class Monkey(
    val id: Int,
    val items: ArrayDeque<Int>,
    val test: Int,
    val positiveApplier: Int,
    val negativeApplier: Int,
    val operand1: String,
    val operand2: String,
    val operation: (Int, Int) -> Int,
    val operationString: String
) {
    var processCount: Int = 0

    override fun toString(): String {
        return "Monkey(id=$id, items=[${items.joinToString(", ")}], test=$test, positive=$positiveApplier, negative=$negativeApplier, operation=$operationString, processCount=$processCount)"
    }
}

fun readMonkeys(scanner: Scanner): List<Monkey> {
    val monkeys = mutableListOf<Monkey>()

    var i = 0
    while (scanner.hasNextLine()) {
        val line = scanner.nextLine()
        if (line.isEmpty()) {
            continue
        }
        val items = ArrayDeque(scanner.nextLine().substringAfter(": ").split(", ").map { it.toInt() })
        val operationString = scanner.nextLine().substringAfter("= ")
        val operands = operationString.split(' ')
        val test = scanner.nextLine().substringAfter("by ").toInt()
        val positive = scanner.nextLine().substringAfter("monkey ").toInt()
        val negative = scanner.nextLine().substringAfter("monkey ").toInt()

        val operation: (Int, Int) -> Int = when (operands[1]) {
            "+" -> {x: Int, y: Int -> x + y}
            "*" -> {x: Int, y: Int -> x * y}
            "-" -> {x: Int, y: Int -> x - y}
            "/" -> {x: Int, y: Int -> x / y}
            else -> error("Oops")
        }
        val monkey = Monkey(i, items, test, positive, negative, operands[0], operands[2], operation, operationString)
        println("${i++}: $monkey")
        monkeys.add(monkey)
    }
    return monkeys
}

inline fun <T, R : Comparable<R>> Iterable<T>.max2By(selector: (T) -> R): Pair<T,T> {
    val iterator = iterator()
    if (!iterator.hasNext()) error("Ooops 1")
    var max1Elem = iterator.next()
    if (!iterator.hasNext()) error("Ooops 2")
    var max2Elem = iterator.next()
    if (!iterator.hasNext()) return max1Elem to max2Elem
    var max1Value = selector(max1Elem)
    var max2Value = selector(max2Elem)
    do {
        val e = iterator.next()
        val v = selector(e)
        if (max1Value < v) {
            max2Elem = max1Elem
            max2Value = max1Value
            max1Elem = e
            max1Value = v
        } else if (max2Value < v) {
            max2Elem = e
            max2Value = v
        }
    } while (iterator.hasNext())
    return max1Elem to max2Elem
}
