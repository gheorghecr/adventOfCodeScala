package Day1
import scala.io.Source

object Day1 extends App {
  
    def readInput(filename: String): Seq[Int] = {
        val source = scala.io.Source.fromFile(filename)
        val lines: Seq[Int] = source.getLines().toSeq.map(_.toInt)
        source.close()
        lines
    }

    def calculateIncreasesPart1(input: Seq[Int]): Int = {
        var prev = -1
        var total = 0
        input.foreach {value =>

            if (prev < 0) {
                prev = value
            } 
            
            if (prev < value) {
                total += 1
            }

            prev = value
        }
        total
    }

    val input: Seq[Int] = readInput("src/main/scala/Day1/input.txt")
    val inputExample: Seq[Int] = readInput("src/main/scala/Day1/input_example.txt")

    val totalNumberOfIncreases: Int = calculateIncreasesPart1(input = input)
    println(totalNumberOfIncreases)

    val totalNumberOfIncreasesExample: Int = calculateIncreasesPart1(input = inputExample)
    println(totalNumberOfIncreasesExample)
    
}
