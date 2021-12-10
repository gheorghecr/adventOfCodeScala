package Day1
import scala.io.Source
import scala.annotation.tailrec

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

    @tailrec
    def calculateIncreaseRecursive(input: Seq[Int], count: Int, stepper: Int): Int = {
        if (input.size <= stepper) count
        else {
            val tail = input.tail
            val firstTreeValues = input.take(stepper).sum
            val secondTreeValues = tail.take(stepper).sum
            calculateIncreaseRecursive(tail, if(firstTreeValues < secondTreeValues) count + 1 else count, stepper)
        } 
    }

    val input: Seq[Int] = readInput("src/main/scala/Day1/input.txt")
    val inputExample: Seq[Int] = readInput("src/main/scala/Day1/input_example.txt")

    val totalNumberOfIncreases: Int = calculateIncreasesPart1(input = input)
    println(totalNumberOfIncreases)

    val totalNumberOfIncreasesExample: Int = calculateIncreasesPart1(input = inputExample)
    println(totalNumberOfIncreasesExample)

    println(calculateIncreaseRecursive(inputExample, 0, 1))
    println(calculateIncreaseRecursive(input, 0, 1))
    
}
