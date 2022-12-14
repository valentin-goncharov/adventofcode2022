package io.github.valentingoncharov.adventincode2022.day9

typealias Board = Pair<Pair<Int, Int>, Pair<Int, Int>>

fun sign(value: Int): Int = if (value < 0) -1 else 1

data class Point(val x: Int = 0, val y: Int = 0) {
    var label:String = ""
        private set

    constructor(label: String, x: Int = 0, y: Int = 0): this(x, y) {
        this.label = label
    }

    override fun toString(): String {
        return "Point(label=$label, x=$x, y=$y)"
    }
}

data class RopeKnot(val label: String, val tail: RopeKnot? = null)  {
    var x: Int = 0
    var y: Int = 0

    override fun toString(): String {
        return "RopeKnot(label= $label, x=$x, y=$y)"
    }
}

private val start = Point("s",0, 0)

fun RopeKnot.toPoint(): Point = Point(label, x, y)

fun printBoard(knots: List<Point>, board: Board = (-5 to 0) to (0 to 6), route: Set<Point> = emptySet()) {
    val head = knots.first()
    val tail = knots.last()
    println("head: $head; tail: $tail")
    println()

    for(i in board.first.first .. board.first.second) {
        for(j in board.second.first .. board.second.second) {
            when(val point = Point(i, j)) {
                in knots -> print(knots.first { it.x == point.x && it.y == point.y }.label)
                in route -> print("#")
                start -> print(start.label)
                else -> print(".")
            }
            print("${if (j<board.second.second) ' ' else '\n'}")
        }
    }
    println()
}
