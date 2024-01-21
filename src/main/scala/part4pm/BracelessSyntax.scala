package part4pm

object BracelessSyntax {
  
  // if expressions
  val anIfExpression = if (2 > 3) "bigger" else "smaller"

  // java-style
  val anIfExpression_v2 = if (2 > 3) {
    "bigger"
  } else {
    "smaller"
  }

  // compact
  val anIfExpression_v3 = 
    if (2 > 3) "bigger" 
    else "smaller"

  // scala 3
  val anIfExpression_v4 = 
    if 2 > 3 then 
      "bigger" 
    else 
      "smaller"

  val anIfExpression_v5 = 
    if 2 > 3 then 
      val result = "bigger" 
    else
      val result = "smaller"
      result

  // scala 3 one-liner
  val anIfExpression_v6 = if 2 > 3 then "bigger" else "smaller"

  // for-comprehensions
  val aForComprehension = for {
    n <- List(1,2,3) 
    s <- List("black", "white")
  } yield s"$n$s"

  // scala 3
  val aForComprehension_v2 = 
    for 
      n <- List(1,2,3) 
      s <- List("black", "white")
    yield s"$n$s"


  // pattern matching
  val meaningOfLife = 42
  val aPatternMatching = meaningOfLife match {
    case 1 => "the one"
    case 2 => "double or nothing"
    case 3 => "third time is the charm"
    case _ => "something else"
  }

  // scala 3
  val aPatternMatching_v2 = meaningOfLife match 
    case 1 => "the one"
    case 2 => "double or nothing"
    case 3 => "third time is the charm"
    case _ => "something else"
  
  // methods without braces
  def computeMeaningOfLife(arg: Int): Int =
    val partialResult = 40
    partialResult + 2
  
  // class definitions with significant indentation (same for traits, objects enums etc)
  class Animal:
    def eat(): Unit =
      println("I'm eating")
    end eat
    
    def grow(): Unit =
      println("I'm growing")
    
  end Animal // for if, match, for, methods, classes, traits, enums, objects
  
  // anonymous classes
  val aSpecialAnimal = new Animal:
    override def eat(): Unit = println("I'm very special")
  
  // indentation = strictrly larger indentation
  // 3 spaces + 2 tabs > 2 spaces + 2 tabs
  // 3 spaces + 2 tabs > 3 spaces + 1 tab
  // 3 tabs + 2 spaces > 2 tabs + 3 spaces
  
  def main(args: Array[String]): Unit = {

  }
}
