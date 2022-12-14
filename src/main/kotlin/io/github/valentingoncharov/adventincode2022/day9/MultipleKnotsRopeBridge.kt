package io.github.valentingoncharov.adventincode2022.day9

import java.io.File
import java.util.Scanner
import kotlin.math.abs

private val board =  (-15 to 5) to (-14 to 11)

private fun moveHorizontal(head: RopeKnot, route: MutableSet<Point>, steps: Int) {

    for (i in abs(steps) downTo 1) {
        head.y += sign(steps)


        var currentHead = head
        var currentTail = head.tail

        val points = mutableListOf<Point>()
        points.add(currentHead.toPoint())

        while(currentTail != null) {
            if (abs(currentHead.y - currentTail.y) > 1) {
                if (currentHead.x != currentTail.x) {
                    currentTail.x += sign(currentHead.x - currentTail.x)
                }
                currentTail.y += sign(currentHead.y - currentTail.y)
            }
            if (abs(currentHead.x - currentTail.x) > 1) {
                if (currentHead.y != currentTail.y) {
                    currentTail.y += sign(currentHead.y - currentTail.y)
                }
                currentTail.x += sign(currentHead.x - currentTail.x)
            }
            currentHead = currentTail
            currentTail = currentHead.tail
            points.add(currentHead.toPoint())
        }
        route.add(currentHead.toPoint())
        printBoard(points, board)
    }
}

private fun moveVertical(head: RopeKnot, route: MutableSet<Point>, steps: Int) {
    for (i in abs(steps) downTo 1) {
        head.x += sign(steps)
        var currentHead = head
        var currentTail = head.tail

        val points = mutableListOf<Point>()
        points.add(currentHead.toPoint())

        while(currentTail != null) {
            if (abs(currentHead.x - currentTail.x) > 1) {
                if (currentHead.y != currentTail.y) {
                    currentTail.y += sign(currentHead.y - currentTail.y)
                }
                currentTail.x += sign(currentHead.x - currentTail.x)
            }
            if (abs(currentHead.y - currentTail.y) > 1) {
                if (currentHead.x != currentTail.x) {
                    currentTail.x += sign(currentHead.x - currentTail.x)
                }
                currentTail.y += sign(currentHead.y - currentTail.y)
            }
            currentHead = currentTail
            currentTail = currentHead.tail
            points.add(currentHead.toPoint())
        }
        route.add(currentHead.toPoint())
        printBoard(points, board, route)
    }
}

fun main() {

    val scanner = Scanner(File("inputs/rope_head_moves.txt"))

    val tailRoute = mutableSetOf<Point>()

    val tail = RopeKnot("9")
    val knot8 = RopeKnot("8", tail)
    val knot7 = RopeKnot("7", knot8)
    val knot6 = RopeKnot("6", knot7)
    val knot5 = RopeKnot("5", knot6)
    val knot4 = RopeKnot("4", knot5)
    val knot3 = RopeKnot("3", knot4)
    val knot2 = RopeKnot("2", knot3)
    val knot1 = RopeKnot("1", knot2)
    val head = RopeKnot("H", knot1)
    tailRoute.add(tail.toPoint())

    printBoard(listOf(head, knot1, knot2, knot3, knot4, knot5, knot6, knot7, knot8, tail).map{it.toPoint()}, board)
    while (scanner.hasNextLine()) {
        val (move, steps) = scanner.nextLine().split(" ")
        println("==== $move $steps====")
        when (move) {
            "U" -> moveVertical(head, tailRoute,  -1 * steps.toInt())
            "R" -> moveHorizontal(head, tailRoute, steps.toInt())
            "D" -> moveVertical(head, tailRoute, steps.toInt())
            "L" -> moveHorizontal(head, tailRoute,-1 * steps.toInt())
            else -> error("OOPS")
        }
    }
    println("----------------")
    println(tailRoute.size)
}
