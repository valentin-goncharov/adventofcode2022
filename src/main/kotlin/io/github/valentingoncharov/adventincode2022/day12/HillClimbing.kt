package io.github.valentingoncharov.adventincode2022.day12

import java.io.File


fun main() {
    val (start, target, hillMap) = File("inputs/hill_map.txt").readHillMaps()

    val queue = ArrayDeque<Point>()
    queue.addLast(start)
    val visited =  Array(hillMap.size) { Array(hillMap[0].size){false} }
    visited[start.row][start.column] = true

    val distance = findShortestDistance(start, target, hillMap)

    println("--------------")
    println(distance)

}
