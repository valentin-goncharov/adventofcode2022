package io.github.valentingoncharov.adventincode2022.day8

import java.io.File

fun readTreeMap(file: File): Array<IntArray> =
    file.readLines().map { it.toCharArray().map { char -> char.digitToInt() }.toIntArray() }.toTypedArray()
