package Day2

import org.scalatest.funsuite.AnyFunSuite

class Day2Test extends AnyFunSuite {
  test("with example input - part 1") {
    val input: List[String] = Day2.readInput("src/main/scala/Day2/input_example.txt")
    assert(Day2.calculatePositionPart1(input, 0, 0) == 150)
  }

  test("with input - part 1") {
    val input: List[String] = Day2.readInput("src/main/scala/Day2/input.txt")
    assert(Day2.calculatePositionPart1(input, 0, 0) == 1990000)
  }

  test("with example input - part 2") {
    val input: List[String] = Day2.readInput("src/main/scala/Day2/input_example.txt")
    assert(Day2.calculatePositionPart2(input, 0, 0, 0) == 900)
  }

  test("with input - part 2") {
    val input: List[String] = Day2.readInput("src/main/scala/Day2/input.txt")
    assert(Day2.calculatePositionPart2(input, 0, 0, 0) == 1975421260)
  }
}
