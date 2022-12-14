package io.github.valentingoncharov.adventincode2022.day9

fun sign(value: Int): Int = if (value < 0) -1 else 1

data class Point(val x: Int = 0, val y: Int = 0) {
    var label:String = ""
        private set

    constructor(label: String, x: Int = 0, y: Int = 0): this(x, y) {
        this.label = label
    }
}

data class RopeKnot(val label: String, val tail: RopeKnot? = null)  {
    var x: Int = 0
    var y: Int = 0
}

private val start = Point("s",0, 0)

fun RopeKnot.toPoint(): Point = Point(x,y)

fun printBoard(knots: List<Point>, board: Pair<Pair<Int, Int>, Pair<Int, Int>> = (-5 to 0) to (0 to 6)) {
    val head = knots.first()
    val tail = knots.last()
    println("head: $head; tail: $tail")
    println()

    for(i in board.first.first .. board.first.second) {
        for(j in board.second.first .. board.second.second) {
            when(val point = Point(i, j)) {
                in knots -> print(knots.first { it.x == point.x && it.y == point.y }.label)
                start -> print(start.label)
                else -> print(".")
            }
            print("${if (j<6) '\t' else '\n'}")
        }
    }
    println()
}
