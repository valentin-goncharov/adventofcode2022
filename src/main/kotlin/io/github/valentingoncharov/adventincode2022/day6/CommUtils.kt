package io.github.valentingoncharov.adventincode2022.day6

fun uniqueCharacters(str: String): Boolean {
    // Assuming string can have characters a-z
    // this has 32 bits set to 0
    var checker = 0
    for (c in str) {
        val bitAtIndex = c - 'a'

        // if that bit is already set in checker,
        // return false
        if (checker and (1 shl bitAtIndex) > 0) {
            println("$str: false")
            return false
        }

        // otherwise update and continue by
        // setting that bit in the checker
        checker = checker or (1 shl bitAtIndex)
    }

    // no duplicates encountered, return true
    println("$str: true")
    return true
}
