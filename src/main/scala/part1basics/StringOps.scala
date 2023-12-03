package part1basics

object StringOps extends App {

  val str: String = "Hello, I am learning Scala"

  println(str.charAt(2)) // 0 index based
  println(str.substring(7, 11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello")) // returns boolean
  println(str.replace(" ", "-"))
  println(str.toLowerCase())
  println(str.length)

  val aNumberString = "45"
  val aNumber = aNumberString.toInt
  println('a' +: aNumberString :+ 'z') // +: prepends, :+ appends
  print(str.reverse)
  println(str.take(2)) // pops the first 2 characters

  // Scala-specific: String interpolators

  // S-interpolators

  val name = "Dawid"
  val age = 25
  val greeting = s"Hello, my name is $name and I am $age years old"
  // python f like string interpolation
  println(greeting)

  val anotherGreeting =
    s"Hello, my name is $name and I will be turning ${age + 1} years old"

  println(anotherGreeting)

  // F-interpolators

  val speed = 1.2f
  val myth = f"$name%s can eat $speed%2.2f burgers per minute"
  // f interpolator allows for type checking
  // $ expands the variable
  // %s is a string
  // %2.2f is a float with at least 2 characters total, and 2 decimal precision
  // %2.2f also checks for type correctness, %3d would throw type error
  println(myth)

  // raw-interpolator

  println(raw"This is a \n newline") // prints the \n
  val escaped = "This is a \n newline"
  print(raw"$escaped") // prints the newline
}
