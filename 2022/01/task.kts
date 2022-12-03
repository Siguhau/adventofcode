import java.io.File

fun readFileLineByLineUsingBufferedReader(filename: String): List<String> {
    return File(filename).bufferedReader().readLines()
}

val input = readFileLineByLineUsingBufferedReader("2022\\01\\input.txt")

var elfs =mutableListOf<Int>()

var caloriesSum = 0
input.forEach { line ->
    if (line.isEmpty()) {
        elfs.add(caloriesSum);
        caloriesSum = 0;
    }
    else {
        caloriesSum += line.trim().toInt();
    }
}

var maxCalories = elfs.sorted().last();

println("Task 1:")
println("Max calories: $maxCalories")

var topThree = elfs.sorted().takeLast(3);
var topThreeTotal = topThree.sum()

println("Task 2:")
println("Top three total: $topThreeTotal")