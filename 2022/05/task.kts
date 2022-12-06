import java.io.File

fun readFileLineByLineUsingBufferedReader(filename: String): List<String> {
    return File(filename).bufferedReader().readLines()
}

val input = readFileLineByLineUsingBufferedReader("2022\\05\\input.txt")

var stackinput = ArrayDeque<String>()
var refinedInputStacks = mutableListOf<ArrayDeque<Char>>()

var linei = input[0]
var lineNr = 0;
while (!linei.contains('1')) {
    stackinput.add(linei)
    lineNr++
    linei = input[lineNr]
}
while (stackinput.isNotEmpty()) {
    val line = stackinput.removeLast()
    var stackNr = 0
    for (i in 1 until line.length step 4) {
        if (line[i] != ' ') {
            if (refinedInputStacks.size <= stackNr) {
                refinedInputStacks.add(ArrayDeque())
            }
            refinedInputStacks[stackNr].add(line[i])
        }
        stackNr++
    }
}

var task1Stack = mutableListOf<ArrayDeque<Char>>()
var task2Stack = mutableListOf<ArrayDeque<Char>>()
// deep copys
for (stack in refinedInputStacks) {
    task1Stack.add(ArrayDeque(stack))
    task2Stack.add(ArrayDeque(stack))
}

lineNr++
lineNr++

while (lineNr < input.size) {
    val line = input[lineNr].split(" ")
    val numberOfCrates = line[1].toInt()
    val fromStackNr = line[3].toInt() -1
    val toStackNr = line[5].toInt() -1 
    // Task 1
    for (i in 0 until numberOfCrates) {
        task1Stack[toStackNr].add(task1Stack[fromStackNr].removeLast())
    }
    // Task 2
    val tempStack = ArrayDeque<Char>()
    for (i in 0 until numberOfCrates) {
        tempStack.add(task2Stack[fromStackNr].removeLast())
    }
    for (i in 0 until numberOfCrates) {
        task2Stack[toStackNr].add(tempStack.removeLast())
    }

    lineNr++
}


var answer1 = ""
for (stack in task1Stack) {
    answer1 += stack.last()
}
var answer2 = ""
for (stack in task2Stack) {
    answer2 += stack.last()
}

println("task 1: $answer1")
println("task 2: $answer2")