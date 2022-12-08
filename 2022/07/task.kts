import java.io.File

fun readFileLineByLineUsingBufferedReader(filename: String): List<String> {
    return File(filename).bufferedReader().readLines()
}

class AdventFile(size: Int, name: String) {
    val size = size
    val name = name
}

class AdventDirectory(name: String) {
    val name = name
    var parent: AdventDirectory? = null
    var children = mutableListOf<AdventDirectory>()
    var files = mutableListOf<AdventFile>()

    fun addChildren(child: AdventDirectory) {
        child.parent = this
        children.add(child)
    }
    fun addFile(size: Int, name: String) {
        files.add(AdventFile(size, name))
    }
    fun getSize(): Int {
        var size = 0
        files.forEach { size += it.size }
        children.forEach { size += it.getSize() }
        return size
    }
}

fun dfs(dir: AdventDirectory?): List<AdventDirectory> {
    var dirs = mutableListOf<AdventDirectory>()
    dir?.children?.forEach { it ->
        dirs.add(it)
        val childs = dfs(it) 
        dirs.addAll(childs)
    }
    return dirs
}



fun main() {
    val input = readFileLineByLineUsingBufferedReader("2022\\07\\input.txt")

    var currentDir : AdventDirectory? = null
    input.forEach { line -> 
        val tokens = line.split(" ")
        if (tokens.first() == "$") {
            // Command
            if (tokens[1] == "cd") {
                // Change directory
                if (tokens[2] == "..") {
                    // Go up one directory
                    currentDir = currentDir?.parent
                } else {
                    // Go down one directory
                    currentDir = currentDir?.children?.find { it.name == tokens[2] }
                }
            }
        }
        else if (tokens.first() == "dir") {
            // Directory
            if (currentDir == null) {
                currentDir = AdventDirectory("/")
            }
            currentDir?.addChildren(AdventDirectory(tokens[1]))

        } else {
            // File
            currentDir?.addFile(tokens[0].toInt(), tokens[1])
        }
    }
    while (currentDir?.name != "/" && currentDir != null) {
        currentDir = currentDir?.parent
    }
    val usedSpace: Int = currentDir?.getSize() ?: 0
   
   val freeSpace = 70000000 - usedSpace

    // Travers the currentDir tree with DFS
    var dirs = dfs(currentDir)

    var sum = 0
    var dirsOverSize = mutableListOf<AdventDirectory>()
    dirs.forEach { dir -> 
        val name = dir.name
        val size = dir.getSize()
        if (size <= 100000) {
            sum += size
        }
        if (size >= (30000000 - freeSpace)){
            dirsOverSize.add(dir)
        }
    }
    dirsOverSize.sortBy { it.getSize() }
    var dirToDelete = dirsOverSize.first()
    
    println("task 1: Sum of small directories: $sum")
    println("task 2: Smallest deletable directory: ${dirToDelete.name}") 
    println("task 2: Size of smallest deletable directory: ${dirsOverSize.first().getSize()}")
}

main()