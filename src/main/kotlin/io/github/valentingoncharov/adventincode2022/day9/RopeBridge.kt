package io.github.valentingoncharov.adventincode2022.day9

import java.io.File
import java.util.Scanner
import kotlin.math.abs

private fun moveHorizontal(head: Point, tail: Point, route: MutableSet<Point>, steps: Int): Pair<Point, Point> {
    var newHead = head
    var newTail = tail
    val step = sign(steps)

    for (i in abs(steps) downTo 1) {
        newHead = Point(newHead.label ,newHead.x, newHead.y + step)
        if (abs(newHead.y - newTail.y) > 1) {
            if (newHead.x != newTail.x) {
                newTail = Point(newTail.label,newTail.x + sign(newHead.x - newTail.x), newTail.y)
            }
            newTail = Point(newTail.label,newTail.x, newTail.y + step)
            route.add(newTail)
        }
//        printBoard(listOf(newHead, newTail))
    }

    return newHead to newTail
}

private fun moveVertical(head: Point, tail: Point, route: MutableSet<Point>, steps: Int): Pair<Point, Point> {
    var newHead = head
    var newTail = tail
    val step = sign(steps)
    for (i in abs(steps) downTo 1) {
        newHead = Point(newHead.label,newHead.x + step, newHead.y)
        if (abs(newHead.x - newTail.x) > 1) {
            if (newHead.y != newTail.y) {
                newTail = Point(newTail.label,newTail.x, newTail.y + sign(newHead.y - newTail.y))
            }
            newTail = Point(newTail.label,newTail.x + step, newTail.y)
            route.add(newTail)
        }
//        printBoard(listOf(newHead, newTail))
    }

    return newHead to newTail
}

fun main() {
    val scanner = Scanner(File("inputs/rope_head_moves.txt"))

    val tailRoute = mutableSetOf<Point>()

    var head = Point("H")
    var tail = Point("T")
    tailRoute.add(tail)

    printBoard(listOf(head, tail))
    while (scanner.hasNextLine()) {
        val (move, steps) = scanner.nextLine().split(" ")
        println("==== $move $steps====")
        val (newHead, newTail) = when (move) {
            "U" -> moveVertical(head, tail, tailRoute,  -1 * steps.toInt())
            "R" -> moveHorizontal(head, tail, tailRoute, steps.toInt())
            "D" -> moveVertical(head, tail, tailRoute, steps.toInt())
            "L" -> moveHorizontal(head, tail, tailRoute,-1 * steps.toInt())
            else -> error("OOPS")
        }

        head = newHead
        tail = newTail
    }
    println("----------------")
    println(tailRoute.size)
}

