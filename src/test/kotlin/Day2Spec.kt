import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import java.nio.file.Files
import java.nio.file.Paths

class Day2Spec : StringSpec({

    "first star" {
        val input = listOf("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab")
        day2FirstStar(input) shouldBe 12
    }

    "first star result" {
        val input = Files.readAllLines(Paths.get("src/main/resources/day2.txt"))
        day2FirstStar(input) shouldBe 7105
    }

//    "second star should return 10" {
//        val input = listOf(+3, +3, +4, -2, -4)
//        day1SecondStar(input) shouldBe 10
//    }
//
//    "second star should return 5" {
//        val input = listOf(-6, +3, +8, +5, -6)
//        day1SecondStar(input) shouldBe 5
//    }
//    "second star should return 14" {
//        val input = listOf(+7, +7, -2, -7, -4)
//        day1SecondStar(input) shouldBe 14
//    }
})