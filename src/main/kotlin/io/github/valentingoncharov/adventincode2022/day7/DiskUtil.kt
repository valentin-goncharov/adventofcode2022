package io.github.valentingoncharov.adventincode2022.day7

import java.util.Scanner

fun readDirectories(scanner:Scanner, dirs: MutableList<Directory>): Directory {
    var curDir = Directory("/")
    dirs.add(curDir)
    var curLevel = 0
    val stack = ArrayDeque<Directory>()

    while (scanner.hasNextLine()) {
        val line = scanner.nextLine()

        if(line == "$ cd /") {
            continue
        }

        val command = line.split(" ")
        when(command[0]) {
            "$" -> {
                if(command[1] == "ls") {
                    continue
                } else {
                    if (command[2] == "..") {
                        curLevel--
                        curDir = stack.removeFirst()
                    } else {
                        curLevel++
                        val newDir = Directory(command[2], curDir.path + "/" + command[2], curLevel)
                        curDir.subDirs.add(newDir)
                        dirs.add(newDir)
                        stack.addFirst(curDir)
                        curDir = newDir
                    }
                }
            }
            "dir" -> {
                continue
            }
            else -> {
                val size = command[0].toInt()
                val name = command[1]
                curDir.files.add(FileEntry(name, size))
            }
        }
    }

    curDir = stack.removeLast()
    curDir.size

    return curDir
}
