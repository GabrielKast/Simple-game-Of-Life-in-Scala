package gameoflife

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GameOfLifeTestSuite extends FunSuite {

  test("A Cell has coordinates") {
    val cell:Cell = Cell(4, 3)
    assert(cell.x === 4)
    assert(cell.y === 3)
  }

  test("A Universe has cells") {
    val cells = List(Cell(1, 2), Cell(2, 2), Cell(3, 2))
    val universe :Universe = new Universe(4, 4, cells)
    assert(universe.cells.length === 3)
  }

  test ("Universe has a representation : toString"){
    val cells = List(Cell(2, 1), Cell(2, 2), Cell(2, 3))
    val universe :Universe = new Universe(3, 3, cells)
    assert(universe.toString ===". * .\n. * .\n. * .")
  }

  test("A Universe know the state of a cell - dead ") {
    val cells = List(Cell(1, 2), Cell(2, 2), Cell(3, 2))
    val universe :Universe = new Universe(4, 4, cells)
    assert(universe.isAlive(1, 1) === false)
  }

  test("A Universe know the state of a cell - alive ") {
    val cells = List(Cell(1, 2), Cell(2, 2), Cell(3, 2))
    val universe :Universe = new Universe(4, 4, cells)
    assert(universe.isAlive(2, 2) === true)
  }

  test("A Universe knows the neighbours Simple case.") {
    val cells = List(Cell(2, 1), Cell(2, 2), Cell(2, 3))
    val universe :Universe = new Universe(3, 3, cells)
    assert(universe.neighboursNumber(2, 2) === 2)
  }

  test("A Universe knows the neighbours of one cell.") {
    val cells = List(Cell(2, 1), Cell(2, 2), Cell(2, 3))
    val universe :Universe = new Universe(3, 3, cells)
    assert(universe.neighboursNumber(1, 1) === 2)
  }

  test ("Any live cell with fewer than two live neighbours dies, as if caused by under-population.") {
    val cells = List()
    val universe :Universe = new Universe(3, 3, cells)
    for (i<-1 to 3; j<-1 to 3) assert(universe.next(i, j) === false)    
  }

  test ("Any live cell with two or three live neighbours lives on to the next generation.") {
    val cells = List(Cell(2, 1), Cell(2, 2), Cell(2, 3))
    val universe :Universe = new Universe(3, 3, cells)
    assert(universe.next(2, 2) === true)
  }

  test ("Any dead cell two neighbours will die.") {
    val cells = List(Cell(2, 1), Cell(2, 2), Cell(2, 3))
    val universe :Universe = new Universe(3, 3, cells)
    assert(universe.next(1, 1) === false)
  }

  test ("Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.") {
    val cells = List(Cell(2, 1), Cell(2, 2), Cell(2, 3))
    val universe :Universe = new Universe(3, 3, cells)
    assert(universe.next(1, 2) === true)
  }

  test ("A Cell with three live neighbours will live - dead or alive.") {
    val cells = List(Cell(2, 1), Cell(2, 2), Cell(2, 3), Cell(1, 2))
    val universe :Universe = new Universe(3, 3, cells)
    assert(universe.next(1, 2) === true)
  }

  test ("Next generation test.") {
    val cells = List(Cell(2, 1), Cell(2, 2), Cell(2, 3))
    val universe :Universe = new Universe(3, 3, cells)
    val expectedCells = List(Cell(1, 2), Cell(2, 2), Cell(3, 2))
    val expected :Universe = new Universe(3, 3, expectedCells)
    assert(universe.nextUniverse === expected)
  }
}
