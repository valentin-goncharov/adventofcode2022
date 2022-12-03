package io.github.valentingoncharov.adventincode2022.day3

import java.io.File

fun main() {

    val priorities = (('a'..'z').mapIndexed { index, c -> c to index + 1 } +
                     ('A'..'Z').mapIndexed { index, c -> c to index + 27 }).toMap()

    val scores = File("/Users/valentingoncharov/Work/adventofcode2022/src/main/resources/rucksacks.txt")
        .readLines()
        .flatMap{
            val first = it.substring(0, it.length/2)
            val second = it.substring(it.length/2)

            val diff = first.asIterable().toSet() intersect  second.asIterable().toSet()
            diff.map { char -> priorities[char] }
        }
        .filterNotNull()
        .sum()

    println(scores)
}
