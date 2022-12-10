package io.github.valentingoncharov.adventincode2022.day2

import java.io.File

/*

A, X - Rock (1)
B, Y - Paper (2)
C, Z - Scissors (3)

Loss - 0
Draw - 3
Win - 6

- Rock defeats Scissors
- Scissors defeats Paper
- Paper defeats Rock

 */
fun main() {
    val combinations = mapOf(
        "A X" to 4, // rock - rock, draw
        "A Y" to 8, // rock - paper, win
        "A Z" to 3, // rock - scissors, loss

        "B X" to 1, // paper - rock, loss
        "B Y" to 5, // paper - paper, draw
        "B Z" to 9, // paper - scissors, win

        "C X" to 7, // scissors - rock, win
        "C Y" to 2, // scissors - paper, loss
        "C Z" to 6, // scissors - scissors, draw
    )

    val scores = File("inputs/rock_paper_scissors.txt")
        .readLines()
        .map{ combinations[it] }
        .filterNotNull()
        .sum()

    println(scores)
}
