import java.io.File

fun readFileLineByLineUsingBufferedReader(filename: String): List<String> {
    return File(filename).bufferedReader().readLines()
}

val inputlines = readFileLineByLineUsingBufferedReader("2022\\06\\input.txt")
val input = inputlines[0]

fun appendAndRemoveFirst(input: String, char: Char): String {
    return input.drop(1) + char
}

fun hasUniqueChars(input: String): Boolean {
    return input.toSet().size == input.length
}

var lastFourChars = input.substring(0,4)
var processed = 4;

var lastFourteenChars = input.substring(0,14)
var processed2 = 14;

while (!hasUniqueChars(lastFourChars) && processed < input.length) {
    processed++;
    lastFourChars = appendAndRemoveFirst(lastFourChars, input[processed-1])
}
while (!hasUniqueChars(lastFourteenChars) && processed2 < input.length) {
    processed2++;
    lastFourteenChars = appendAndRemoveFirst(lastFourteenChars, input[processed2-1])
}

println("task 1: $processed")
println("task 2: $processed2")