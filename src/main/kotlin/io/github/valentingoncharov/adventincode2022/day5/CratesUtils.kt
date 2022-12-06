import java.util.Scanner

fun printStack(stack: List<ArrayDeque<Char>>) {

    var level =  stack.maxBy { it.size }.size

    while(level > 0) {
        val builder = StringBuilder()
        for(q in stack) {
            if (q.size < level) {
                builder.append("    ")
            } else {
                builder.append("[").append(q[level-1]).append("] ")
            }
        }
        println(builder.toString())
        level--
    }
    val builder = StringBuilder()
    for (i in 1 .. stack.size) {
        builder.append(" $i ").append(" ")
    }
    println(builder.toString())
}

private val crates = """(((\[[A-Z]\])|\s{3})\s?)?+""".toRegex()
private val cratesBottom = """(\s*\d+)+""".toRegex()
fun readStacks(scanner: Scanner): List<ArrayDeque<Char>> {
    val cratesStack = mutableListOf<ArrayDeque<Char>>()
    while(scanner.hasNextLine()) {
        val line = scanner.nextLine()
        when {
            line.isEmpty() -> {
                printStack(cratesStack)
                println("--------------")
                println("Start movements")
                println("")
                break
            }
            cratesBottom.matches(line) -> continue
            else -> {
                var i = 0
                var result = crates.find(line)
                while(result?.next() != null) {
                    if (i == cratesStack.size) {
                        cratesStack.add(ArrayDeque())
                    }
                    val value = result.value
                    if(value.isNotBlank()){
                        cratesStack[i].addFirst(value[1])
                    }
                    i++
                    result = result.next()
                }
            }
        }
    }
    return cratesStack
}
