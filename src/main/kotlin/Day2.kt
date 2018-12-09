import java.nio.file.Files
import java.nio.file.Paths


fun main(args: Array<String>) {
    val input = Files.readAllLines(Paths.get("src/main/resources/day2.txt"))
    val result1 = day2FirstStar(input)
    println("Result first star = $result1")

//    val result2 = day2SecondStar(input)
//    println("Result second star = $result2")
}


fun day2FirstStar(input: List<String>): Int {
    return input
        .asSequence()
        .map(::countLetters)
        .reduce { a: LetterCounter, b: LetterCounter ->
            a + b
        }
        .let { it.twoLetterCount * it.threeLetterCounter }
}

private fun countLetters(id: String): LetterCounter {
    val chars = id.toCharArray()
    val countLetters = chars.groupBy { c -> c }
        .mapValues { it.value.size }
        .filter { entry -> entry.value == 2 || entry.value == 3 }
        .mapKeys { entry -> entry.value }
        .mapValues { 1 }

    return LetterCounter(countLetters.getOrDefault(2, 0), countLetters.getOrDefault(3, 0))
}

private data class LetterCounter(val twoLetterCount: Int, val threeLetterCounter: Int) {
    operator fun plus(counter: LetterCounter): LetterCounter {
        return LetterCounter(
            twoLetterCount + counter.twoLetterCount,
            threeLetterCounter + counter.threeLetterCounter
        )
    }
}

//fun day2SecondStar(input: List<Int>): Int {
//    val frequencyReached: MutableMap<Int, Int> = mutableMapOf()
//    var frequency: Int = 0
//
//    while (true) {
//        for (change in input) {
//            frequency += change
//            val reached = frequencyReached.getOrDefault(frequency, 0)
//            if (reached == 1) {
//                return frequency
//            } else {
//                frequencyReached[frequency] = reached + 1
//            }
//        }
//    }
//}