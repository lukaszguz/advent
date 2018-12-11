import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import java.nio.file.Files
import java.nio.file.Paths

class Day3Spec : StringSpec({
    val input: List<String> = listOf("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2")

    "parse line" {
        parseLine("#1 @ 469,741: 22x26") shouldBe Rectangle("1", 469, 741, 22, 26)
    }

    "first star test data" {
        firstStar(input) shouldBe 4
    }

    "first star" {
        val fileInput = Files.readAllLines(Paths.get("src/main/resources/day3.txt"))
        firstStar(fileInput) shouldBe 96569
    }
})

private fun firstStar(input: List<String>): Int {
    val images = Array(1000, init = { Array(1000, init = { 0 }) })
    val rectangles = input.map(::parseLine)
    var conflict = 0
    rectangles
        .forEach { rectangle ->
            rectangle.xRange.forEach { i ->
                rectangle.yRange.forEach { j ->
                    val element = images[i][j]
                    if (element == 0)
                        images[i][j] = 1
                    else if (element == 1) images[i][j] = 2
                }
            }


        }
    for (i in 0 until images.size) {
        for (j in 0 until images.size) {
            if (images[i][j] == 2) conflict += 1
        }
    }
    return conflict
}

private fun parseLine(line: String): Rectangle {
    val r = "#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)".toRegex()
    return r.find(line)!!.groupValues
        .let {
            Rectangle(
                id = it[1],
                x = it[2].toInt(),
                y = it[3].toInt(),
                width = it[4].toInt(),
                high = it[5].toInt()
            )
        }
}

private data class Rectangle(
    val id: String,
    val x: Int,
    val y: Int,
    val width: Int,
    val high: Int
) {
    val xRange = x until x + width
    val yRange = y until y + high
}