package io.github.valentingoncharov.adventincode2022.day4

import java.io.File

fun main() {

    val pattern="(\\d+)-(\\d+),(\\d+)-(\\d+)".toRegex()

    val scores = File("/Users/valentingoncharov/Work/adventofcode2022/src/main/resources/clean_assignments.txt")
        .readLines()
        .map{
            val(s1, e1, s2, e2) = pattern.find(it)!!.destructured
            val r1 = IntRange(s1.toInt(), e1.toInt())
            val r2 = IntRange(s2.toInt(), e2.toInt())
            r1 intersect r2
        }
        .filterNot { it.isEmpty() }
        .count()

    println(scores)
}
