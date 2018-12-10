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

    "second star should return fgij" {
        val input = listOf(
            "abcde",
            "fghij",
            "klmno",
            "pqrst",
            "fguij",
            "axcye",
            "wvxyz"
        )
        day2SecondStar(input) shouldBe "fgij"
    }

    "second star should return omlvgdokxfncvqyersasjziup" {
        val input = Files.readAllLines(Paths.get("src/main/resources/day2.txt"))
        day2SecondStar(input) shouldBe "omlvgdokxfncvqyersasjziup"
    }
})