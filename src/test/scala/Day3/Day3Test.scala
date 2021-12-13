package Day3

import org.scalatest.funsuite.AnyFunSuite

class Day3Test extends AnyFunSuite {
    test("with example input - part 1") {
        val input: List[String] = Day3.readInput("src/main/scala/Day3/input_example.txt")
        assert(Day3.calculatePowerConsumptionPart1(input) == 198)
    }

    test("with input - part 1") {
        val input: List[String] = Day3.readInput("src/main/scala/Day3/input.txt")
        assert(Day3.calculatePowerConsumptionPart1(input) == 2035764)
    }
}
