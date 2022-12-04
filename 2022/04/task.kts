import java.io.File

fun readFileLineByLineUsingBufferedReader(filename: String): List<String> {
    return File(filename).bufferedReader().readLines()
}

val input = readFileLineByLineUsingBufferedReader("2022\\04\\input.txt")

var solutions = 0
var solutions2 = 0
var pairs = mutableListOf<Array<Int>>()
input.forEach { line -> 
    val l = line.split(",", "-").map() { it.toInt() } 
    pairs.add(arrayOf(l[0], l[1], l[2], l[3]))
}
pairs.forEach { numbers ->
    
    if (numbers[0] <= numbers[2] && numbers[1] >= numbers[3]) {
        solutions++
    }
    else if (numbers[0] >= numbers[2] && numbers[1] <= numbers[3]) {
        solutions++
    }
    
    // if only one overlaps
    if (numbers[0] <= numbers[2] && numbers[1] >= numbers[2]){
        solutions2++
    }
    else if (numbers[0] <= numbers[3] && numbers[1] >= numbers[3]){
        solutions2++
    }
    else if (numbers[0] >= numbers[2] && numbers[1] <= numbers[3]){
        solutions2++
    }
    else if (numbers[0] >= numbers[3] && numbers[1] <= numbers[2]){
        solutions2++
    }

}

println("Task 1: $solutions")

println("Task 2: $solutions2")