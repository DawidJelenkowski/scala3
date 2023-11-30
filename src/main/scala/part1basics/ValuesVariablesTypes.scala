package part1basics

object ValuesVariablesTypes extends App {

  val x: Int = 42
  println(x)

  // VALS ARE IMMUTABLE

  // COMPILER can infer types

  // this is legal but not encouraged
  val aString: String = "hello"; val anotherString = "goodbye"
  val aChar: Char = 'a'
  val anInt: Int = x
  // Short is represented in 2 bytes instead of 4
  val aShort: Short = 4613
  // Long is represented in 8 bytes
  val aLong: Long = 464654655454L
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.14

  // variables
  var aVariable: Int = 4
  aVariable = 5 // side effects
}
