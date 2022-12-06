package io.github.valentingoncharov.adventincode2022.day6

import java.io.File

fun main() {

    val sequence = File("/Users/valentingoncharov/Work/adventofcode2022/src/main/resources/comm_sequence.txt")
        .readText()

    var point = 3
    while(!uniqueCharacters(sequence.slice(point-3..point))) {
        point++
    }

    println(point+1)
}

