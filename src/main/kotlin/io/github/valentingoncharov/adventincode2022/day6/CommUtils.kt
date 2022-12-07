package io.github.valentingoncharov.adventincode2022.day6

fun uniqueCharacters(str: String): Boolean {
    var checker = 0
    for (c in str) {
        val bitAtIndex = c - 'a'

        if (checker and (1 shl bitAtIndex) > 0) {
            return false
        }

        checker = checker or (1 shl bitAtIndex)
    }

    return true
}
