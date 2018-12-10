import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.math.abs

class Day10Spec : StringSpec({
    val input: List<String> = """position=< 9,  1> velocity=< 0,  2>
position=< 7,  0> velocity=<-1,  0>
position=< 3, -2> velocity=<-1,  1>
position=< 6, 10> velocity=<-2, -1>
position=< 2, -4> velocity=< 2,  2>
position=<-6, 10> velocity=< 2, -2>
position=< 1,  8> velocity=< 1, -1>
position=< 1,  7> velocity=< 1,  0>
position=<-3, 11> velocity=< 1, -2>
position=< 7,  6> velocity=<-1, -1>
position=<-2,  3> velocity=< 1,  0>
position=<-4,  3> velocity=< 2,  0>
position=<10, -3> velocity=<-1,  1>
position=< 5, 11> velocity=< 1, -2>
position=< 4,  7> velocity=< 0, -1>
position=< 8, -2> velocity=< 0,  1>
position=<15,  0> velocity=<-2,  0>
position=< 1,  6> velocity=< 1,  0>
position=< 8,  9> velocity=< 0, -1>
position=< 3,  3> velocity=<-1,  1>
position=< 0,  5> velocity=< 0, -1>
position=<-2,  2> velocity=< 2,  0>
position=< 5, -2> velocity=< 1,  2>
position=< 1,  4> velocity=< 2,  1>
position=<-2,  7> velocity=< 2, -2>
position=< 3,  6> velocity=<-1, -1>
position=< 5,  0> velocity=< 1,  0>
position=<-6,  0> velocity=< 2,  0>
position=< 5,  9> velocity=< 1, -2>
position=<14,  7> velocity=<-2,  0>
position=<-3,  6> velocity=< 2, -1>""".split("\n")

    "should good parse position" {
        val line = "position=< 7,  0> velocity=<-1,  0>"
        parsePoint(line) shouldBe Point(7, 0, -1, 0)
    }

    "input size should be 31 lines" {
        input.map { parsePoint(it) }.size shouldBe 31
    }

    "print HI" {
        searchText(input)
    }


    "println text" {
        val fileInput = Files.readAllLines(Paths.get("src/main/resources/day10.txt"))
        searchText(fileInput)
    }
})

class PointSpec : StringSpec({
    "correct move" {
        val point = Point(0, 0, 2, 2)

        point.move() shouldBe Point(2, 2, 2, 2)
    }
})

private fun searchText(input: List<String>) {
    var points = input.map { parsePoint(it) }
    val size = points.size
    var counterLoop = 0

    while (true) {
        val minX = points.minBy { it.pX }!!.pX
        val minY = points.minBy { it.pY }!!.pY
        val maxX = points.maxBy { it.pX }!!.pX
        val maxY = points.maxBy { it.pY }!!.pY

        val absX = abs(maxX) - abs(minX)
        val absY = abs(maxY) - abs(minY)

        if (absX < size && absY < size) {
            printImage(minY = minY, maxY = maxY, minX = minX, maxX = maxX, points = points)
            println("CL = $counterLoop")
        }

        counterLoop += 1
        points = points.map { it.move() }
    }
}

private fun printImage(minY: Int, maxY: Int, minX: Int, maxX: Int, points: List<Point>) {
    for (y in minY..maxY) {
        for (x in minX..maxX) {
            if (points.asSequence().firstOrNull { x == it.pX && y == it.pY } != null) {
                print("#")
            } else {
                print(".")
            }
        }
        println()
    }
}

private fun parsePoint(line: String): Point {
    val r = "-*\\d+".toRegex()
    return r.findAll(line).asSequence().map { it.value }.toList()
        .let { Point(it[0].toInt(), it[1].toInt(), it[2].toInt(), it[3].toInt()) }
}

data class Point(val pX: Int, val pY: Int, val vX: Int, val vY: Int) {
    fun move(): Point {
        return this.copy(pX = pX + vX, pY = pY + vY)
    }
}
