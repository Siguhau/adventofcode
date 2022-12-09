import java.io.File

fun readFileLineByLineUsingBufferedReader(filename: String): List<String> {
    return File(filename).bufferedReader().readLines()
}

val input = readFileLineByLineUsingBufferedReader("2022\\09\\input.txt")

class Knot() {
    var position = Pair(0, 0)
    var follows: Knot? = null
    val visited = mutableSetOf<Pair<Int, Int>>()

    fun move(direction: Char) {
        when (direction) {
            'U' -> position = Pair(position.first, position.second + 1)
            'D' -> position = Pair(position.first, position.second - 1)
            'R' -> position = Pair(position.first + 1, position.second)
            'L' -> position = Pair(position.first - 1, position.second)
        }
    }

    override fun toString() = "position: $position"

    fun addVisited() {
        visited.add(position)
    }

    fun euclideanDistance(): Double {
        if (follows == null) return 0.0
        val xDiff = follows!!.position.first - position.first
        val yDiff = follows!!.position.second - position.second
        return Math.sqrt((xDiff * xDiff + yDiff * yDiff).toDouble())
    }

    fun follow() {
        if (follows == null) return
        
        val xDiff = follows!!.position.first - position.first
        val yDiff = follows!!.position.second - position.second
        if (this.euclideanDistance() > 2) {
            val xDir = if (xDiff > 0) 'R' else 'L'
            val yDir = if (yDiff > 0) 'U' else 'D'
            move(xDir)
            move(yDir)
        }
        else if (this.euclideanDistance() > 1.5){
            if (Math.abs(xDiff) > Math.abs(yDiff)) {
                val xDir = if (xDiff > 0) 'R' else 'L'
                move(xDir)
            } else {
                val yDir = if (yDiff > 0) 'U' else 'D'
                move(yDir)
            }
        }
        addVisited()
    }
}


// Main program
val head = Knot()
val knots = mutableListOf<Knot>()

// create 9 knots
knots.add(Knot())
knots.first().follows = head
for (i in 1..8) {
    val knot = Knot()
    knot.follows = knots.last()
    knots.add(knot)
}

input.forEach { line ->
    val movement = line.split(" ")
    for (i in 0 until movement[1].toInt()) {
        head.move(movement[0][0])
        for (knot in knots) {
            knot.follow()
        }
    }
}

println("task 1: ${knots.first().visited.size}")
println("task 2: ${knots.last().visited.size}")
