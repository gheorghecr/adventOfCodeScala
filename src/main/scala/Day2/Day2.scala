package Day2

object Day2 extends App {
    
    def readInput(filename: String): List[String] = {
        val source = scala.io.Source.fromFile(filename)
        val lines = source.getLines().toList.map(_.toString())
        source.close()
        lines
    }

    def calculatePositionPart1(input: List[String], horizontalPosition: Int, verticalPosition: Int ): Int = {
        if (input.isEmpty) horizontalPosition * verticalPosition
        else {
            input.head.split(" ") match {
                case Array("forward", x) => calculatePositionPart1(input.tail, horizontalPosition + x.toInt, verticalPosition)
                case Array("down", x) => calculatePositionPart1(input.tail, horizontalPosition, verticalPosition + x.toInt)
                case Array("up", x) => calculatePositionPart1(input.tail, horizontalPosition, verticalPosition - x.toInt)
            }
        }
    }

    def calculatePositionPart2(input: List[String], horizontalPosition: Int, verticalPosition: Int, aim: Int ): Int = {
        if (input.isEmpty) horizontalPosition * verticalPosition
        else {
            input.head.split(" ") match {
                case Array("forward", x) => calculatePositionPart2(input.tail, horizontalPosition + x.toInt, verticalPosition + (x.toInt * aim), aim)
                case Array("down", x) => calculatePositionPart2(input.tail, horizontalPosition, verticalPosition, aim + x.toInt)
                case Array("up", x) => calculatePositionPart2(input.tail, horizontalPosition, verticalPosition , aim - x.toInt)
            }
        }
    }

    val input: List[String] = readInput("src/main/scala/Day2/input.txt")
    val inputExample: List[String] = readInput("src/main/scala/Day2/input_example.txt")

    println(calculatePositionPart1(inputExample, 0, 0))
    println(calculatePositionPart1(input, 0, 0))

    println(calculatePositionPart2(inputExample, 0, 0, 0))
    println(calculatePositionPart2(input, 0, 0, 0))
}
