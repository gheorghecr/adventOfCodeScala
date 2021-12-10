package Day1

import org.scalatest.funsuite.AnyFunSuite

class Day1Tests extends AnyFunSuite {
  test("no increments") {
      assert(Day1.calculateIncreasesPart1(List(55, 45, 33)) == 0)
  }

  
}
