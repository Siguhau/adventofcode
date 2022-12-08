import java.io.File

fun readFileLineByLineUsingBufferedReader(filename: String): List<String> {
    return File(filename).bufferedReader().readLines()
}

val input = readFileLineByLineUsingBufferedReader("2022\\08\\input.txt")

fun createMatrix(input: List<String>): List<List<Int>> {
    val matrix = mutableListOf<List<Int>>()
    input.forEach { line ->
        val row = mutableListOf<Int>()
        line.forEach { char ->
            row.add(char.toString().toInt())
        }
        matrix.add(row)
    }
    return matrix
}

fun visibleToLeft(matrix: List<List<Int>>, row: Int, col: Int): Boolean {
    var height = matrix[row][col]
    for (k in col - 1 downTo 0) {
        if (matrix[row][k] >= height) {
            return false
        }
    }
    return true
}

fun visibleToRight(matrix: List<List<Int>>, row: Int, col: Int): Boolean {
    var height = matrix[row][col]
    for (k in col + 1 until matrix[row].size) {
        if (matrix[row][k] >= height) {
            return false
        }
    }
    return true
}

fun visibleToTop(matrix: List<List<Int>>, row: Int, col: Int): Boolean {
    var height = matrix[row][col]
    for (k in row - 1 downTo 0) {
        if (matrix[k][col] >= height) {
            return false
        }
    }
    return true
}

fun visibleToBottom(matrix: List<List<Int>>, row: Int, col: Int): Boolean {
    var height = matrix[row][col]
    for (k in row +1 until matrix.size) {
        if (matrix[k][col] >= height) {
            return false
        }
    }
    return true
}

fun visibleToAnyDirection(matrix: List<List<Int>>, row: Int, col: Int): Boolean {
    return visibleToLeft(matrix, row, col) || visibleToRight(matrix, row, col) || visibleToTop(matrix, row, col) || visibleToBottom(matrix, row, col)
}

fun treesVisibleLeft(matrix: List<List<Int>>, row: Int, col: Int): Int {
    var trees = 0
    var height = matrix[row][col]
    for (k in col - 1 downTo 0) {
        if (matrix[row][k] >= height) {
            return trees + 1 
        }
        trees += 1
    }
    return trees
}

fun treesVisibleRight(matrix: List<List<Int>>, row: Int, col: Int): Int {
    var trees = 0
    var height = matrix[row][col]
    for (k in col + 1 until matrix[row].size) {
        if (matrix[row][k] >= height) {
            return trees + 1 
        }
        trees += 1
    }
    return trees
}

fun treesVisibleTop(matrix: List<List<Int>>, row: Int, col: Int): Int {
    var trees = 0
    var height = matrix[row][col]
    for (k in row - 1 downTo 0) {
        if (matrix[k][col] >= height) {
            return trees + 1 
        }
        trees += 1
    }
    return trees
}

fun treesVisibleBottom(matrix: List<List<Int>>, row: Int, col: Int): Int {
    var trees = 0
    var height = matrix[row][col]
    for (k in row +1 until matrix.size) {
        if (matrix[k][col] >= height) {
            return trees + 1 
        }
        trees += 1
    }
    return trees
}

fun treesVisibleAnyDirection(matrix: List<List<Int>>, row: Int, col: Int): Int {
    return treesVisibleLeft(matrix, row, col) * treesVisibleRight(matrix, row, col) * treesVisibleTop(matrix, row, col) * treesVisibleBottom(matrix, row, col)
}

var matrix = createMatrix(input)

var sumVisible = 0
var visibleTrees = mutableListOf<Int>()



for (row in 0 until matrix.size) {
    for (col in 0 until matrix[row].size) {
        if (row == 0 || col == 0 || row == matrix.size - 1 || col == matrix[row].size - 1) {
            sumVisible += 1
        }
        else if (visibleToAnyDirection(matrix, row, col)) {
            sumVisible += 1
        }
        visibleTrees.add(treesVisibleAnyDirection(matrix, row, col))
    }
}
println("task 1: $sumVisible")

println("task 2: ${visibleTrees.maxOrNull()}")