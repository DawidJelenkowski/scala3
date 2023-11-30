package part1basics

object Expressions extends App {
  val x = 1 + 2 // EXPRESSION
  println(x)

  println(2 + 3 * 4)
  // + - * / // math operators
  // & | ^ // bit wise expressions
  // << >> >>> (right shift with zero extension)

  println(1 == x)
  // == != > >= < <=  // relational operators

  println(!(1 == x))
  // ! && || // logical operators

  var aVariable = 2
  aVariable += 3 // also works with -= *= /= (side effects)
  print(aVariable)

  // Instructions (do something) vs Expressions (has value or a type, in scala everything computes a value)

  // If expression
  val aCondition = true
  val aConditionedValue = if (aCondition) 5 else 3 // IF EXPRESSION
  println(if (aCondition) 5 else 3)
  println(1 + 3)

  var i = 0
  val aWhile = while (i < 10) {
    print(i)
    i += 1
  }
  // while returns an Unit type
  // EVERYTHING in Scala is an Expression

  val aWeirdValue =
    (aVariable = 3) // Unit === void (it doesn't return anything meaningful)
  println(aWeirdValue)

  // side effects: println(), whiles, reassigining

  // Code blocks

  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (z > 2) "hello" else "goodbye"
  }
  // the return of this expression is the last line
  // the vals in aCodeBlock are not accessible, they are only in accessbile within aCodeBlock
}
