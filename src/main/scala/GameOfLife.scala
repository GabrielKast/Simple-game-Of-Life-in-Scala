package gameoflife

case class Cell (x :Int, y :Int)

case class Universe (sizeX :Int, sizeY :Int, cells :Seq[Cell]) {
  def isAlive(x :Int, y :Int) :Boolean = cells contains Cell(x, y)
  
  def neighboursNumber(x :Int, y :Int) :Int = {
    val all = for {i <- x-1 to x+1
		   j <- y-1 to y+1
		   if ((i!=x || j!=y) 
		       && (i>0 && i<=sizeX) && (j>0 && j<=sizeY)
		       && isAlive(i, j))
		 } yield 1
    all.sum
  }

  def next(x :Int, y :Int) :Boolean= neighboursNumber(x, y) match {
    case n if n<2 || n>3 => false
    case 2 if (!isAlive(x, y))=> false
    case _ => true
  }

  def nextUniverse :Universe = {
    val nextCells = for { i <- 1 to sizeX
			 j <- 1 to sizeY
			 if (next(i, j) == true)
		       } yield Cell(i, j)
    Universe(sizeX, sizeY, nextCells)
  }

  override def toString = {
    def drawCell(x :Int, y :Int) :String= if (isAlive(x, y)) "*" else "."
    def line(y :Int) :String = 
      List.range(1, sizeX + 1).map(x => drawCell(x, y)) mkString " "
    List.range(1, sizeY + 1).map(line) mkString "\n"
  }
}
