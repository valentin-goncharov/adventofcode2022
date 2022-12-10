package io.github.valentingoncharov.adventincode2022.day4

import java.io.File

fun main() {

    val pattern="(\\d+)-(\\d+),(\\d+)-(\\d+)".toRegex()

    val scores = File("inputs/clean_assignments.txt")
        .readLines()
        .map{
            val(s1, e1, s2, e2) = pattern.find(it)!!.destructured
            val r1 = IntRange(s1.toInt(), e1.toInt())
            val r2 = IntRange(s2.toInt(), e2.toInt())
            if ((r1.first <= r2.first && r1.last() >= r2.last()) ||
                (r1.first >= r2.first && r1.last() <= r2.last())) {
                1
            } else {
                0
            }
        }
        .sum()

    println(scores)
}
