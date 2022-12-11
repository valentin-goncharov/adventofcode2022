package io.github.valentingoncharov.adventincode2022.day8

import java.io.File

fun main() {
    val treeMap = readTreeMap(File("inputs/tree_map.txt"))

    val visibleTrees = mutableSetOf<Pair<Int, Int>>()
    val length = treeMap[0].size
    val height = treeMap.size

    for ((i, row) in treeMap.withIndex()) {
        var leftIndex = 0
        var rightIndex = length - 1
        var leftMax = -1
        var rightMax = -1
        var leftMaxIndex = 0
        var rightMaxIndex = length - 1

        while (leftIndex < rightIndex) {
            if (row[leftIndex] > leftMax) {
                visibleTrees.add(i to leftIndex)
                leftMax = row[leftIndex]
                leftMaxIndex = leftIndex
            }
            if (row[rightIndex] > rightMax) {
                visibleTrees.add(i to rightIndex)
                rightMax = row[rightIndex]
                rightMaxIndex = rightIndex
            }
            leftIndex++
            rightIndex--
        }

        if (leftMax > rightMax) {
            while (rightIndex > leftMaxIndex) {
                if (row[rightIndex] > rightMax) {
                    visibleTrees.add(i to rightIndex)
                    rightMax = row[rightIndex]
                }
                rightIndex--
            }
        } else if (rightMax > leftMax) {
            while (leftIndex < rightMaxIndex) {
                if (row[leftIndex] > leftMax) {
                    visibleTrees.add(i to leftIndex)
                    leftMax = row[leftIndex]
                }
                leftIndex++
            }
        }
    }


    for (j in 0 until  length) {
        var topIndex = 0
        var bottomIndex = height - 1

        var topMax = -1
        var bottomMax = -1

        var topMaxIndex = 0
        var bottomMaxIndex = height - 1

        while (topIndex < bottomIndex) {
            if(treeMap[topIndex][j] > topMax) {
                visibleTrees.add(topIndex to j)
                topMax = treeMap[topIndex][j]
                topMaxIndex = topIndex
            }
            if(treeMap[bottomIndex][j] > bottomMax) {
                visibleTrees.add(bottomIndex to j)
                bottomMax = treeMap[bottomIndex][j]
                bottomMaxIndex = bottomIndex
            }
            topIndex++
            bottomIndex--
        }

        if (topMax > bottomMax) {
            while (bottomIndex > topMaxIndex) {
                if (treeMap[bottomIndex][j] > bottomMax) {
                    visibleTrees.add(bottomIndex to j)
                    bottomMax = treeMap[bottomIndex][j]
                }
                bottomIndex--
            }
        } else if (bottomMax > topMax) {
            while (topIndex < bottomMaxIndex) {
                if (treeMap[topIndex][j] > topMax) {
                    visibleTrees.add(topIndex to j)
                    topMax = treeMap[topIndex][j]
                }
                topIndex++
            }
        }
    }

    println(visibleTrees.size)
}
