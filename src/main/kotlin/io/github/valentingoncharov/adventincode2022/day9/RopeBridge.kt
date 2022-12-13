package io.github.valentingoncharov.adventincode2022.day9

import java.io.File
import java.util.Scanner
import kotlin.math.abs

data class Point(val x: Int = 0, val y: Int = 0)

fun moveHorizontal(head: Point, tail: Point, route: MutableSet<Point>, steps: Int): Pair<Point, Point> {
    var newHead = head
    var newTail = tail
    val step = sign(steps)

    for (i in abs(steps) downTo 1) {
        newHead = Point(newHead.x, newHead.y + step)
        if (abs(newHead.y - newTail.y) > 1) {
            if (newHead.x != newTail.x) {
                newTail = Point(newTail.x + sign(newHead.x - newTail.x), newTail.y)
            }
            newTail = Point(newTail.x, newTail.y + step)
            route.add(newTail)
        }
//        printBoard(newHead, newTail)
    }

    return newHead to newTail
}

fun moveVertical(head: Point, tail: Point, route: MutableSet<Point>, steps: Int): Pair<Point, Point> {
    var newHead = head
    var newTail = tail
    val step = sign(steps)

    for (i in abs(steps) downTo 1) {
        newHead = Point(newHead.x + step, newHead.y)
        if (abs(newHead.x - newTail.x) > 1) {
            if (newHead.y != newTail.y) {
                newTail = Point(newTail.x, newTail.y + sign(newHead.y - newTail.y))
            }
            newTail = Point(newTail.x + step, newTail.y)
            route.add(newTail)
        }
//        printBoard(newHead, newTail)
    }


    return newHead to newTail
}

fun main() {
    val scanner = Scanner(File("inputs/rope_head_moves.txt"))

    val tailRoute = mutableSetOf<Point>()

    var head = Point()
    var tail = Point()
    tailRoute.add(tail)

    printBoard(head, tail)
    while (scanner.hasNextLine()) {
        val (move, steps) = scanner.nextLine().split(" ")
//        println("==== $move $steps====")
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

fun sign(value: Int): Int = if (value < 0) -1 else 1

fun printBoard(head: Point, tail: Point) {
    println("head: $head; tail: $tail")
    println()
    val start = Point(0, 0)
    for(i in -5 .. 0) {
        for(j in 0 .. 6) {
            val point = Point(i, j)
            when(point) {
                head -> print("H")
                tail -> print("T")
                start -> print("s")
                else -> print(".")
            }
            print("${if (j<6) '\t' else '\n'}")
        }
    }
    println()
}
