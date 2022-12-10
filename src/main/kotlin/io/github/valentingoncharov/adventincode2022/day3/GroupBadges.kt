package io.github.valentingoncharov.adventincode2022.day3

import java.io.File

fun main() {

    val priorities = (('a'..'z').mapIndexed { index, c -> c to index + 1 } +
            ('A'..'Z').mapIndexed { index, c -> c to index + 27 }).toMap()

    val scores = File("inputs/rucksacks.txt")
        .readLines()
        .chunked(3)
        .withIndex()
        .flatMap{
            val first = it.value[0]
            val second = it.value[1]
            val third = it.value[2]

            val diff = first.asIterable().toSet() intersect second.asIterable().toSet() intersect third.asIterable().toSet()
            println("${it.index}: $diff")
            diff.map { char -> priorities[char] }
        }
        .filterNotNull()
        .sum()

    println(scores)
}
