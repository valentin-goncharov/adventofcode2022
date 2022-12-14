package io.github.valentingoncharov.adventincode2022.day10

import java.io.File
import java.util.Scanner

private const val MIN_THRESHOLD = 20
private const val MAX_THRESHOLD = 220
private const val THRESHOLD_STEP = 40

fun main() {

    val scanner = Scanner(File("inputs/cathode_ray_instructions.txt"))

    var strength = 0
    var register = 1
    var cycle = 1
    var nextThreshold = MIN_THRESHOLD

    while(scanner.hasNextLine() && nextThreshold <= MAX_THRESHOLD) {
        when (val instruction = scanner.nextLine()) {
            "noop" -> cycle++
            else -> {
                val value = instruction.split(" ")[1].toInt()
                if (cycle + 2 > nextThreshold) {
                    strength += nextThreshold * register
                    nextThreshold += THRESHOLD_STEP
                }
                cycle+=2
                register += value
            }
        }
    }

    println("----------------")
    println(strength)
}
