import java.io.File

fun readFileLineByLineUsingBufferedReader(filename: String): List<String> {
    return File(filename).bufferedReader().readLines()
}

val input = readFileLineByLineUsingBufferedReader("2022\\10\\input.txt")

var x = 1

var cycle = 0

var sum = 0

val numberList = mutableListOf<Int>(20, 60, 100, 140, 180, 220)

input.forEach { line ->
    val lineSplit = line.split(" ")

    if (lineSplit[0] == "noop") {
        cycle += 1
        if (cycle in numberList) {
            sum += cycle * x
        }
    }
    else if (lineSplit[0] == "addx") {
        cycle += 1
        if (cycle in numberList) {
            sum += cycle * x
        }
        cycle += 1
        if (cycle in numberList) {
            sum += cycle * x
        }
        x += lineSplit[1].toInt()
    }
}
    
println("task 1: ${sum}")

x = 1

cycle = 0

sum = 0

var answer = "\n"

input.forEach { line ->
    val lineSplit = line.split(" ")
    

    if (lineSplit[0] == "noop") {
        drawChar(cycle, x)
        cycle += 1
    }
    else if (lineSplit[0] == "addx") {
        drawChar(cycle, x)
        cycle += 1
        drawChar(cycle, x)
        cycle += 1
        x += lineSplit[1].toInt()
    }
}

fun drawChar(cycle: Int, x: Int) {
    if (cycle % 5 == 0) {
        answer += "  "
    }
    val position = cycle % 40
    if (position == 0) {
        answer += "\n"
    }
    if (position == (x-1) || position == (x) || position == (x+1)) {
        answer += "#"
    }
    else {
        answer += "."
    }
}

println("task 2: $cycle")
println("task 2: $answer")