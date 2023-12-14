package part2oop

object Exceptions extends App {
  
  val x: String = null
  // println(x.length)
  // this ^^ will crash with a NPE

  // 1. throwing exceptions
  val aWeirdValue: String = throw new NullPointerException // return Nothing class

  // throwable classes extend the Throwable class.
  // Exception and Error are the major Throwable subtypes

  // 2. catching exceptions
  def getInt(withExceptions: Boolean): Int = 
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 42
}
