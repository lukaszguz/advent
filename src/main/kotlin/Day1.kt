import java.nio.file.Files
import java.nio.file.Paths


fun main(args: Array<String>) {
    val input = Files.readAllLines(Paths.get("src/main/resources/day1.txt")).map { it.toInt() }
    val result1 = day1FirstStar(input)
    println("Result first star = $result1")

    val result2 = day1SecondStar(input)
    println("Result second star = $result2")
}


fun day1FirstStar(input: List<Int>): Int {
    return input
        .stream()
        .reduce { a: Int, b: Int -> a + b }
        .get()
}

fun day1SecondStar(input: List<Int>): Int {
    val frequencyReached: MutableMap<Int, Int> = mutableMapOf()
    var frequency: Int = 0

    while (true) {
        for (change in input) {
            frequency += change
            val reached = frequencyReached.getOrDefault(frequency, 0)
            if (reached == 1) {
                return frequency
            } else {
                frequencyReached[frequency] = reached + 1
            }
        }
    }
}