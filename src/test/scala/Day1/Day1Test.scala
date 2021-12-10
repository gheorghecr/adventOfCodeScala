package Day1

import org.scalatest.funsuite.AnyFunSuite

class Day1Tests extends AnyFunSuite {
  test("no increments - part 1") {
      assert(Day1.calculateIncreasesPart1(List(55, 45, 33)) == 0)
  }

  test("one increment - part 1") {
    assert(Day1.calculateIncreasesPart1(List(55, 22, 66, 33)) == 1)
  }

  test("with example input - part 1") {
    val input: Seq[Int] = Day1.readInput("src/main/scala/Day1/input_example.txt")
    assert(Day1.calculateIncreasesPart1(input) == 7)
  }

  test("with input - part 1") {
    val input: Seq[Int] = Day1.readInput("src/main/scala/Day1/input.txt")
    assert(Day1.calculateIncreasesPart1(input) == 1553)
  }

  test("no increments - part 2") {
      assert(Day1.calculateIncreaseRecursive(List(55, 45, 33, 11, 12, 15, 5, 3, 7), 0, 3) == 0)
  }

  test("one increment - part 2") {
    assert(Day1.calculateIncreaseRecursive(List(10, 22, 66, 33), 0, 3) == 1)
  }

  test("with example input - part 2") {
    val input: Seq[Int] = Day1.readInput("src/main/scala/Day1/input_example.txt")
    assert(Day1.calculateIncreaseRecursive(input, 0, 3) == 5)
  }

  test("with input - part 2") {
    val input: Seq[Int] = Day1.readInput("src/main/scala/Day1/input.txt")
    assert(Day1.calculateIncreaseRecursive(input, 0, 3) == 1597)
  }

  
}
