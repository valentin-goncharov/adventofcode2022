package io.github.valentingoncharov.adventincode2022.day8

import java.io.File


fun calculateTop(i: Int, j: Int, treeMap: Array<IntArray>): Int {
    return if (i == 0) {
        0
    } else {
        var score = 1
        var topIndex = i - 1
        while(topIndex > 0 && treeMap[i][j] > treeMap[topIndex][j]) {
            score++
            topIndex--
        }
        score
    }
}

fun calculateRight(i: Int, j: Int, treeMap: Array<IntArray>): Int {
    return if (j == treeMap[i].size - 1) {
        0
    } else {
        var score = 1
        var rightIndex = j + 1
        while(rightIndex < treeMap[i].size-1 && treeMap[i][j] > treeMap[i][rightIndex]) {
            score++
            rightIndex++
        }
        score
    }
}

fun calculateBottom(i: Int, j: Int, treeMap: Array<IntArray>): Int {
    return if (i == treeMap.size - 1) {
        0
    } else {
        var score = 1
        var bottomIndex = i + 1
        while(bottomIndex < treeMap.size-1 && treeMap[i][j] > treeMap[bottomIndex][j]) {
            score++
            bottomIndex++
        }
        score
    }
}

fun calculateLeft(i: Int, j: Int, treeMap: Array<IntArray>): Int {
    return if (j == 0) {
        0
    } else {
        var score = 1
        var leftIndex = j - 1
        while(leftIndex > 0 && treeMap[i][j] > treeMap[i][leftIndex]) {
            score++
            leftIndex--
        }
        score
    }
}

fun main() {
    val treeMap = readTreeMap(File("inputs/tree_map.txt"))

    val length = treeMap[0].size
    val height = treeMap.size

    val scores = Array(height) { Array(length) { intArrayOf(-1,-1,-1,-1)} }

    var max = 0
    var iMax = 0
    var jMax = 0
    for (i in 0 until length) {
        for (j in 0 until height) {

            //top
            val top = calculateTop(i, j, treeMap)
            //right
            val right = calculateRight(i, j, treeMap)
            //bottom
            val bottom = calculateBottom(i, j, treeMap)
            //left
            val left = calculateLeft(i, j, treeMap)

            val score = top * right * bottom * left
            scores[i][j] = intArrayOf(top, right, bottom, left)
            if (score > max) {
                max = score
                iMax = i
                jMax = j
            }
        }
    }

    treeMap.forEach { println(it.joinToString("\t")) }
    println("------------------------")
    scores.map { it.map { arr -> "[${arr.joinToString(",")}]" } }.forEach { println(it.joinToString("\t\t")) }
    println("------------------------")
    scores.map { it.map { arr -> arr[0] * arr[1] * arr[2] * arr[3] } }.forEach { println(it.joinToString("\t\t")) }
    println("------------------------")
    println("$max: ($iMax, $jMax)")
}
