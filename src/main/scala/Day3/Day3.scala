package Day3

object Day3 extends App {
  
    def readInput(filename: String): List[String] = {
        val source = scala.io.Source.fromFile(filename)
        val lines = source.getLines().toList.map(_.toString())
        source.close()
        lines
    }

    def calculatePowerConsumptionPart1(listOfValues: List[String]): Int = {
        val gamaRateBinary = getGamaRate(listOfValues)
        val epsilonRateBinary = getEpsilonRate(listOfValues)

        Integer.parseInt(gamaRateBinary, 2) * Integer.parseInt(epsilonRateBinary, 2)
    }

    def calculateLifeSupportRatingPart2(listOfValues: List[String]): Int = {
        val oxygenGeneratorRating = getOxygenGeneratorRating(listOfValues)
        val co2ScrubberRating = getCO2ScrubberRating(listOfValues)

        Integer.parseInt(oxygenGeneratorRating, 2) * Integer.parseInt(co2ScrubberRating, 2)
    }

    // most common bit -- part 2
    def getOxygenGeneratorRating(values: List[String]): String = {
        def getOxygenGeneratorRatingHelper(values: List[String], index: Int): String = {
            if (values.length == 1){
                values(0)
            }
            else {
                val mostCommonBitForIndex = mostCommonBit(index, values)
                val newValues = values.filter(x => x(index) == mostCommonBitForIndex)
                getOxygenGeneratorRatingHelper(newValues, index + 1)
            }
        }

        val finalBinary: String = getOxygenGeneratorRatingHelper(values, 0)
        finalBinary
    }

    // least common bit -- part 2
    def getCO2ScrubberRating(values: List[String]): String = {
        def getCO2ScrubberRatingHelper(values: List[String], index: Int): String = {
            if (values.length == 1) {
                values(0)
            }
            else {
                val mostCommonBitForIndex = leastCommonBit(index, values)
                val newValues = values.filter(x => x(index) == mostCommonBitForIndex)
                getCO2ScrubberRatingHelper(newValues, index + 1)
            }
        }

        val finalBinary: String = getCO2ScrubberRatingHelper(values, 0)
        finalBinary
    }
    
    // most common bit -- part 1
    def getGamaRate(values: List[String]): String = {
        def gamaRateHelper(values: List[String],  commonsBit: String, index: Int): String = {
            if (index == values(0).length) {
                commonsBit
            }
            else {
                val mostCommonBitForIndex = mostCommonBit(index, values)
                val commonsBitNew: String = commonsBit +  mostCommonBitForIndex.toString()
                gamaRateHelper(values, commonsBitNew, index + 1)
            }
        }

        val commonBitsBinary: String = gamaRateHelper(values, "", 0)
        commonBitsBinary
    }

    // least common bit -- part 1
    def getEpsilonRate(values: List[String]): String = {
        def epsilonRateHelper(values: List[String],  commonsBit: String, index: Int): String = {
            if (index == values(0).length) {
                commonsBit
            }
            else {
                val mostCommonBitForIndex = leastCommonBit(index, values)
                val commonsBitNew: String = commonsBit +  mostCommonBitForIndex.toString()
                epsilonRateHelper(values, commonsBitNew, index + 1)
            }
        }

        val commonBitsBinary: String = epsilonRateHelper(values, "", 0)
        commonBitsBinary
    }

    def mostCommonBit(index: Int, values: List[String]): Char = {
        val numberOfOnes = values.count(x => x(index) == '1')
        val numberOfZeros = values.length - numberOfOnes
        if(numberOfOnes >= numberOfZeros) '1' else '0'
    }

    def leastCommonBit(index: Int, values: List[String]): Char = {
        val numberOfOnes = values.count(x => x(index) == '1')
        val numberOfZeros = values.length - numberOfOnes
        if (numberOfZeros <= numberOfOnes) '0' else '1'
    }

    val input: List[String] = readInput("src/main/scala/Day3/input.txt")
    val inputExample: List[String] = readInput("src/main/scala/Day3/input_example.txt")

    println(calculatePowerConsumptionPart1(inputExample))
    println(calculatePowerConsumptionPart1(input))

    println(calculateLifeSupportRatingPart2(inputExample))
    println(calculateLifeSupportRatingPart2(input))

}
