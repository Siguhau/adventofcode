import java.io.File

fun readFileLineByLineUsingBufferedReader(filename: String): List<String> {
    return File(filename).bufferedReader().readLines()
}

val input = readFileLineByLineUsingBufferedReader("2022\\09\\input.txt")

class Node() {
    var position = Pair(0, 0)
    var follows: Node? = null
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


val head = Node()
val tail1 = Node()
tail1.follows = head

val nodes = mutableListOf<Node>()
nodes.add(Node())
nodes.last().follows = head

// create 7 nodes
for (i in 1..7) {
    val node = Node()
    node.follows = nodes.last()
    nodes.add(node)
}
val tail2 = Node()
tail2.follows = nodes.last()

input.forEach { line ->
    val movement = line.split(" ")
    for (i in 0 until movement[1].toInt()) {
        head.move(movement[0][0])
        tail1.follow()
        for (node in nodes) {
            node.follow()
        }
        tail2.follow()
    }
}

println("task 1: ${tail1.visited.size}")
println("task 2: ${tail2.visited.size}")
