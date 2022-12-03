import java.io.File

fun readFileLineByLineUsingBufferedReader(filename: String): List<String> {
    return File(filename).bufferedReader().readLines()
}

fun doMatch(opponentAction: String, myAction: String): Int {
    if ((opponentAction[0].toInt() - 'A'.toInt()) == (myAction[0].toInt() - 'X'.toInt())) {
        return 3 + actionToScore(myAction)
    }
    if (opponentAction == "A" && myAction == "Y") {
        return 6 + actionToScore(myAction)   
    }
    if (opponentAction == "B" && myAction == "Z") {
        return 6 + actionToScore(myAction)   
    }
    if (opponentAction == "C" && myAction == "X") {
        return 6 + actionToScore(myAction)   
    }
    return 0 + actionToScore(myAction)

}


fun actionToScore(action: String): Int {
    return (action[0].toInt() - 'X'.toInt()) + 1
}

fun chooseAction(opponentAction: String, outcome: String): String {
    return when (outcome) {
        "X" -> chooseLosingAction(opponentAction)
        "Y" -> chooseDrawAction(opponentAction)
        "Z" -> chooseWinningAction(opponentAction)
        else -> "X"
    }
}

fun chooseLosingAction(opponentAction: String): String {
    return when (opponentAction) {
        "A" -> "Z"
        "B" -> "X"
        "C" -> "Y"
        else -> "X"
    }
}
fun chooseDrawAction(opponentAction: String): String {
    return when (opponentAction) {
        "A" -> "X"
        "B" -> "Y"
        "C" -> "Z"
        else -> "X"
    }
}
fun chooseWinningAction(opponentAction: String): String {
    return when (opponentAction) {
        "A" -> "Y"
        "B" -> "Z"
        "C" -> "X"
        else -> "X"
    }
}

val input = readFileLineByLineUsingBufferedReader("2022\\02\\input.txt")

var score = 0
input.forEach { line ->
    val actions = line.split(" ")
    val opponentAction = actions[0]
    val myAction = actions[1]
    score += doMatch(opponentAction, myAction)
}

println("Task 1: $score")

// task 2

score = 0
input.forEach {line -> 
    val actions = line.split(" ")
    val opponentAction = actions[0]
    val myAction = chooseAction(opponentAction, actions[1])
    score += doMatch(opponentAction, myAction)
    }

println("Task 2: $score")

