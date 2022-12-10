package io.github.valentingoncharov.adventincode2022.day7

data class FileEntry(val name: String, val size: Int) {
    override fun toString(): String {
        return "$name (file, size=$size)"
    }
}
class Directory(val name: String, val path: String = "", val level: Int = 0) {

    val subDirs = mutableListOf<Directory>()
    val files = mutableListOf<FileEntry>()

    val size: Int by lazy {
        files.sumOf{ it.size } +subDirs.sumOf { it.size }
    }

    override fun toString(): String {
        return "$name (dir, size=$size, level = $level, path = $path)"
    }

    fun printTree() {
        val prefix = "\\".padStart(level + 1, '\t')
        println("$prefix $this")
        for (f in files) {
            val prefix = "-".padStart(level + 2, '\t')
            println("$prefix $f")
        }
        for (s in subDirs) {
            s.printTree()
        }
    }
}
