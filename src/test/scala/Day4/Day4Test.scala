package Day4

import org.scalatest.funsuite.AnyFunSuite

class Day4Test extends AnyFunSuite {
    test("with  input - part 1") {
        val (input: String, board: Array[Array[Array[String]]]) = Day4.readInput("src/main/scala/Day4/input.txt")
        assert(Day4.playBingoPart1(board, input.split(",").toList ) == 33348)
    }

    test("with example input - part 1") {
        val (input: String, board: Array[Array[Array[String]]]) = Day4.readInput("src/main/scala/Day4/input_example.txt")
        assert(Day4.playBingoPart1(board, input.split(",").toList ) == 4512)
    }
}
