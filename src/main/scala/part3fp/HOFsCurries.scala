package part3fp

object HOFsCurries extends App {
  
  // higher order function (HOF)
  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null

  // example map, flatmap, filter

  // function that applies a function n times over a value x
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(x))) = nTimes(f, 2, f(x)) = f(f(f(x)))
  // nTimes(f, n, x) = f(f(...f(x))) = nTimes(f, n-1, f(x))

  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))

  val plusOne = (x: Int) => x + 1
  println(nTimes(plusOne, 10, 1))

  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n - 1)(f(x))
  
  val plus10 = nTimesBetter(plusOne, 10) // 11
  println(plus10(1)) // 11

  // curried functions
  val sperAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  val add3 = sperAdder(3) // y => 3 + y
  println(add3(10)) // 13
  println(sperAdder(3)(10)) // 13

  // go backwards with curried functions
  def curriedFormatter(c: String)(x: Double): String = c.format(x) // ("%4.2f").format(Math.PI)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f") // this is (c:String) 
  // and look like this curriedFormatter("%4.2f")(Math.PI) Math.PI is converted to a String

  println(standardFormat(Math.PI)) // 3.14 
  // this is (x:Double)

  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")
  println(preciseFormat(Math.PI)) // 3.14159265
}
