package part1basics

object CBNvsCBV extends App {

  def calledByValue(x: Long): Unit = {
    println("by value: " + x)
    println("by value: " + x)
  }

  def calledByName(x: => Long): Unit = { // => is evaluated every time it is used
    println("by name: " + x)
    println("by name: " + x)
  }

  calledByValue(System.nanoTime())
  // System.nanoTime() is evaluated before the function is called
  calledByName(System.nanoTime())
  // System.nanoTime() is evaluated every time it is used

  def infinite(): Int = 1 + infinite() // infinite loop
  def printFirst(x: Int, y: => Int) = println(x)

  printFirst(infinite(), 34) // infinite loop
  printFirst(34, infinite())
  // only 34 is printed, infinite() is never evaluated
}
