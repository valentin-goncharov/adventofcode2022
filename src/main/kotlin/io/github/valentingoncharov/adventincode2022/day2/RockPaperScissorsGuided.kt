package io.github.valentingoncharov.adventincode2022.day2

import java.io.File

/*

A - Rock (1)
B - Paper (2)
C - Scissors (3)

X - lose
Y - Draw
Z - Win

Loss - 0
Draw - 3
Win - 6

- Rock defeats Scissors
- Scissors defeats Paper
- Paper defeats Rock

 */
fun main() {
    val combinations = mapOf(
        "A X" to 3, // rock - scissors, loss
        "A Y" to 4, // rock - rock, draw
        "A Z" to 8, // rock - paper, win

        "B X" to 1, // paper - rock, loss
        "B Y" to 5, // paper - paper, draw
        "B Z" to 9, // paper - scissors, win

        "C X" to 2, // scissors - paper, loss
        "C Y" to 6, // scissors - scissors, draw
        "C Z" to 7, // scissors - rock, win
    )

    val scores = File("inputs/rock_paper_scissors.txt")
        .readLines()
        .map{ combinations[it] }
        .filterNotNull()
        .sum()

    println(scores)
}
