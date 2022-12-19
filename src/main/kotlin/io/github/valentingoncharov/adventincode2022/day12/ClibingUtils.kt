package io.github.valentingoncharov.adventincode2022.day12

import java.io.File

data class Point(val row: Int, val column: Int, val distance: Int = 0)

fun File.readHillMaps(): Triple<Point, Point, Array<Array<Int>>> {
    var start: Point = Point(0, 0)
    var target: Point = Point(0, 0)
    val map = this.readLines()
        .mapIndexed { i, r ->
            r.toCharArray().mapIndexed { j, c ->
                val char = when (c) {
                    'S' -> {
                        start = Point(i, j)
                        'a'
                    }
                    'E' -> {
                        target = Point(i, j)
                        'z'
                    }
                    else -> c
                }
                char - 'a'
            }.toTypedArray()
        }.toTypedArray()

    return Triple(start, target, map)
}

fun findShortestDistance(start: Point, target: Point, hillMap: Array<Array<Int>>): Int {
    val queue = ArrayDeque<Point>()
    queue.addLast(start)
    val visited =  Array(hillMap.size) { Array(hillMap[0].size){false} }
    visited[start.row][start.column] = true

    var distance = 0
    while (queue.isNotEmpty()) {
        val node = queue.removeFirst()
        if (node.row == target.row && node.column == target.column) {
            distance = node.distance
            break
        }

        //up
        if (canMove(node.row - 1, node.column, node, hillMap, visited)) {
            queue.addLast(Point(node.row - 1, node.column, node.distance + 1))
            visited[node.row - 1][node.column] = true
        }

        //down
        if (canMove(node.row + 1, node.column, node, hillMap, visited)) {
            queue.addLast(Point(node.row + 1, node.column, node.distance + 1))
            visited[node.row + 1][node.column] = true
        }

        //left
        if (canMove(node.row, node.column - 1 , node, hillMap, visited)) {
            queue.addLast(Point(node.row, node.column - 1, node.distance + 1))
            visited[node.row][node.column - 1] = true
        }

        //right
        if (canMove(node.row, node.column + 1 , node, hillMap, visited)) {
            queue.addLast(Point(node.row, node.column + 1, node.distance + 1))
            visited[node.row][node.column + 1] = true
        }
    }
    return distance
}

private fun canMove(row: Int, colunm: Int, point: Point, map: Array<Array<Int>>, visited: Array<Array<Boolean>>): Boolean {
    return (
            row > -1 && row < map.size &&
                    colunm > -1 && colunm < map[0].size &&
                    map[row][colunm] - map[point.row][point.column] < 2 &&
                    !visited[row][colunm]
            )
}

private fun printMap(map: Array<Array<Int>>){
    map.forEach { println(it.joinToString("\t")) }
}
