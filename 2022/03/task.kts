import java.io.File

fun readFileLineByLineUsingBufferedReader(filename: String): List<String> {
    return File(filename).bufferedReader().readLines()
}

val input = readFileLineByLineUsingBufferedReader("2022\\03\\input.txt")


fun splitAndFindSameChar(line: String): Char {
    val length = line.length
    val x: String = line.substring(0, length/2)
    val y: String = line.substring(length/2, length)
    x.forEach { char ->
        y.forEach { char2 ->
            if (char == char2) {
                return char
            }
        }
    }
    return ' '
}

fun findSameChars(firstLine: String, secondLine: String): String {
    var result = ""
    firstLine.forEach { char ->
        secondLine.forEach { char2 ->
            if (char == char2) {
                result += char
            }
        }
    }
    return result
}

fun getPriority(char: Char): Int {
    if (char.isLowerCase()) {
        return char.toInt() - 'a'.toInt() + 1
    } else {
        return char.toInt() - 'A'.toInt() + 27
        
    }
}

// task 1
var sum = 0
input.forEach { line ->
    val char = splitAndFindSameChar(line)
    if (char != ' ') {
        sum += getPriority(char)
    }
    else {
        println(line)
    }
}
println("Task 1: $sum")


// task 2
sum = 0
var iteration = 0
var firstLine = ""
var secondLine = ""
var thirdLine = ""
input.forEach { line ->
    if (iteration == 0) {
        firstLine = line
        iteration++
    }
    else if (iteration == 1) {
        secondLine = line
        iteration++
    }
    else if (iteration == 2) {
        thirdLine = line
        iteration = 0
        val possibleChars = findSameChars(firstLine, secondLine)
        val result = findSameChars(possibleChars, thirdLine)
        sum += getPriority(result[0])
    }
}
println("Task 2: $sum")