package Day4

import scala.annotation.tailrec

object Day4 extends App {
  
    def readInput(filename: String): (String, Array[Array[Array[String]]]) = {
        val source = scala.io.Source.fromFile(filename)
        val lines = source.getLines().toArray.map(_.toString())

        val inputs = lines(0)
        var boards = lines.tail

        // remove empty lists
        boards = boards.filter(x => x.length > 1)

        var filteredBoards: Array[Array[String]] = boards.map(x => x.trim.split("[  ,]+").toArray)
        
        // create a board (group by 5)
        def createBoardsList(listOfValues: Array[Array[String]], boards: Array[Array[Array[String]]]): Array[Array[Array[String]]] = {
            if (listOfValues.isEmpty) boards
            else {
                var newBoards = boards.appended(listOfValues.take(5))
                var newListOfValues = listOfValues.drop(5)
                createBoardsList(newListOfValues, newBoards)
            }
        }
        
        val filteredBoardsGrouped = createBoardsList(filteredBoards, Array())

        source.close()
        (inputs, filteredBoardsGrouped)
    }    
   
    def markDownDrawNumber(individualBoard: Array[Array[String]], drawNumber: String, rowIndex: Int, columnIndex: Int): Array[Array[String]] = {
        if (rowIndex == individualBoard.length) individualBoard
        
        else if (columnIndex == individualBoard(0).length) {
            // when row finishes
            markDownDrawNumber(individualBoard, drawNumber, rowIndex + 1, 0)
        }
        else {
            // check if number is equal to draw and mark it
            if (individualBoard(rowIndex)(columnIndex) == drawNumber) individualBoard(rowIndex)(columnIndex) = "X"
            
            markDownDrawNumber(individualBoard, drawNumber, rowIndex, columnIndex + 1) 
        }
    }
    
    // count number of points achieved on a board
    def countPoints(individualBoard: Array[Array[String]], rowIndex: Int, columnIndex: Int, acc: Int): Int = {
        if (rowIndex == individualBoard.length) acc
        
        else if (columnIndex == individualBoard(0).length) {
            // when row finishes
            countPoints(individualBoard, rowIndex + 1, 0, acc)
        }
        else {
            var new_acc = acc
            // check if number is equal to draw and mark it
            if (individualBoard(rowIndex)(columnIndex) != "X") {
                new_acc += individualBoard(rowIndex)(columnIndex).toInt
            }
            countPoints(individualBoard, rowIndex, columnIndex + 1, new_acc) 
        }
    }

    // verify if board is the winner
    def verifyWinner(individualBoard: Array[Array[String]]): Boolean = {
        // verify rows
        def verifyWinnerRowHelper(individualBoard: Array[Array[String]], rowIndex: Int): Boolean = {
            if (rowIndex == individualBoard(0).length) false
            else {
                val numberOfXes = individualBoard(rowIndex).filter(_.toString == "X").length
                if (numberOfXes == individualBoard.length) true
                else {
                    verifyWinnerRowHelper(individualBoard, rowIndex + 1)
                }
            }
        }

        def verifyWinnerColumnHelper(individualBoard: Array[Array[String]], columnIndex: Int): Boolean = {
            if (columnIndex == individualBoard(0).length) false
            else {
                var valuesPerColumn: Seq[String] = Seq()
                individualBoard.map{row =>
                    valuesPerColumn = valuesPerColumn ++ Seq(row(columnIndex).toString())
                }
                val numberOfXes =  valuesPerColumn.filter(_.toString == "X").length
                if (numberOfXes == individualBoard.length) true
                else {
                    verifyWinnerColumnHelper(individualBoard, columnIndex + 1)
                }
            }
        }
        verifyWinnerRowHelper(individualBoard, 0) || verifyWinnerColumnHelper(individualBoard, 0)
    }

    def playBingoPart1(boards: Array[Array[Array[String]]], draws: List[String]): Int = {
        if (draws.isEmpty) 0
        else {
            boards.map { board =>
                markDownDrawNumber(board, draws.head, 0, 0)
                // call verify winner here
                val result = verifyWinner(board)
                if (verifyWinner(board)) {
                    return countPoints(board, 0, 0, 0).toInt * draws.head.toInt
                }
            }
            playBingoPart1(boards, draws.tail)
        }
    }


    def playBingoLastBoardPart2(boards: Array[Array[Array[String]]], draws: List[String], winnerBoardsIndexList: List[Int], previousDraw: String): Int = {
        if (winnerBoardsIndexList.length == boards.length || draws.isEmpty) {
            return countPoints(boards(winnerBoardsIndexList.last), 0, 0, 0).toInt * previousDraw.toInt
        }
        else if (draws.isEmpty) 0
        else {
            boards.map { board =>
                if (!winnerBoardsIndexList.contains(boards.indexOf(board))) {
                    markDownDrawNumber(board, draws.head, 0, 0)
                }
            }
            boards.map { board =>
                val result = verifyWinner(board)
                if (verifyWinner(board) && !winnerBoardsIndexList.contains(boards.indexOf(board)) ) {
                    var newWinnerBoardsIndexList = winnerBoardsIndexList.appended(boards.indexOf(board)).distinct
                    return playBingoLastBoardPart2(boards, draws.tail, newWinnerBoardsIndexList, draws.head) 
                }
            }
            playBingoLastBoardPart2(boards, draws.tail, winnerBoardsIndexList, draws.head) 
        }
    }

    val (input: String, board: Array[Array[Array[String]]]) = readInput("src/main/scala/Day4/input.txt")
    val (inputExample: String, boardExample: Array[Array[Array[String]]]) = readInput("src/main/scala/Day4/input_example.txt")

    // val points = playBingoPart1(boardExample, inputExample.split(",").toList)
    // println(points + " POINTS")

    // val points2 = playBingoPart1(board, input.split(",").toList)
    // println(points2 + " POINTS2")

    val pointsPart2 = playBingoLastBoardPart2(boardExample, inputExample.split(",").toList, List(), "0")
    println(pointsPart2 + " POINTS - PART 2")

    // val points2Part2 = playBingoLastBoardPart2(board, input.split(",").toList, List(), "0")
    // println(points2Part2 + " POINTS2 - PART 2")

}
