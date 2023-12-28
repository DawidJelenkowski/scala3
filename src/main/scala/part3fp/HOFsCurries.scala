package part3fp

object HOFsCurries extends App {
  
  // higher order function (HOF)
  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null

  // example map, flatmap, filter

  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))

  // function that applies a function n times over a value x
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(x))) = nTimes(f, 2, f(x)) = f(f(f(x)))
  // nTimes(f, n, x) = f(f(...f(x))) = nTimes(f, n-1, f(x))

  val plusOne = (x: Int) => x + 1
  println(nTimes(plusOne, 10, 1))

  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n - 1)(f(x))
  
  val plus10 = nTimesBetter(plusOne, 10) // prints 11
  println(plus10(1)) // prints 11

  // curried functions
  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  val add3 = superAdder(3) // it looks like this: y => 3 + y
  println(add3(10)) // prints 13
  println(superAdder(3)(10)) // prints 13

  // go backwards with curried functions
  def curriedFormatter(c: String)(x: Double): String = c.format(x) // ("%4.2f").format(Math.PI)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f") // this is (c:String) 
  // and look like this curriedFormatter("%4.2f")(Math.PI) Math.PI is converted to a String

  println(standardFormat(Math.PI)) // prints 3.14 
  // this is (x:Double)

  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")
  println(preciseFormat(Math.PI)) // prints 3.14159265

  /* TASKS
    1. Expand MyList
      - foreach method A => Unit
        [1,2,3].foreach(x => println(x))
      - sort function ((A, A) => Int) => MyList
        [1,2,3].sort((x, y) => y - x) => [3,2,1]
      - zipWith (list, (A, A) => B) => MyList[B]
        [1,2,3].zipWith([4,5,6], x * y) => [1 * 4, 2 * 5, 3 * 6] = [4, 10, 18]
      - fold(start)(function) => a value
        [1,2,3].fold(0)(x + y) = 6
    2.  toCurry(f: (Int, Int) => Int) => (Int => Int => Int)
        fromCurry(f: (Int => Int => Int)) => (Int, Int) => Int
    3.  compose(f,g) => x => f(g(x))
        andThen(f,g) => x => g(f(x))
  */

  def toCurry(f: (Int, Int) => Int): (Int => Int => Int) =
    x => y => f(x, y)


  /* EXPLANATION
  1. toCurry is a higher-order function that takes another function f as a parameter. 
  The function f is a function that takes two Int parameters and returns an Int.

  2. toCurry returns a curried version of f. 
  A curried function is a function that takes multiple arguments one at a time, rather than all at once. 
  In this case, the curried version of f is a function that takes an Int and returns another function that takes an Int and returns an Int.

  3. The body of toCurry is x => y => f(x, y). 
  This is a lambda function that takes an Int x and returns another lambda function y => f(x, y). 
  This second lambda function takes an Int y and applies f to x and y.
  */


  // EXAMPLES
  val add = (x: Int, y: Int) => x + y
  val curriedAdd = toCurry(add)
  // val curriedAdd: Int => Int => Int = toCurry((x: Int, y: Int) => x + y)
  val addFive = curriedAdd(5)
  println(addFive(10)) // prints 15
  // println(curriedAdd(5)(10)) // prints 15


  def fromCurry(f: (Int => Int => Int)): (Int, Int) => Int =
    (x, y) => f(x)(y)


  /* EXPLANATION
  1. fromCurry is a higher-order function that takes a curried function f as a parameter. 
  The function f is a function that takes an Int and returns another function that takes an Int and returns an Int.

  2. fromCurry returns an uncurried version of f. 
  An uncurried function is a function that takes all its arguments at once. 
  In this case, the uncurried version of f is a function that takes two Int parameters and returns an Int.

  3. The body of fromCurry is (x, y) => f(x)(y). 
  This is a lambda function that takes two Int parameters x and y and applies f to x and y. 
  */

  // EXAMPLES
  val curriedAddd = (x: Int) => (y: Int) => x + y
  val addd= fromCurry(curriedAddd)
  println(addd(5, 10))


  def compose[A,B,T](f: A => B, g: T => A): T => B =
    x => f(g(x))


  /* EXPLANATION
  1. compose is a higher-order function that takes two functions as parameters: f and g. 
  The function f is a function that takes a parameter of type A and returns a value of type B. 
  The function g is a function that takes a parameter of type T and returns a value of type A.

  2. compose returns a new function that takes a parameter of type T and returns a value of type B. 
  This is achieved by first applying g to the input x (which should be of type T), and then applying f to the result.
  
  Function composition is a fundamental concept in functional programming. 
  The compose function allows you to create a new function by "chaining" two existing functions together. 
  The output of the first function (g) is used as the input to the second function (f).
  */

  // EXAMPLES
  val multiplyBy2 = (x: Int) => x * 2
  val addd3 = (x: Int) => x + 3
  val add3AndThenMultiplyBy2 = compose(multiplyBy2, addd3)
  println(add3AndThenMultiplyBy2(4)) // prints 14

  
  def andThen[A,B,C](f: A => B, g: B => C): A => C =
    x => g(f(x))


  // EXPLANATION
  // The same thing as compose but the other way around, first the f function is evauated, then g.
  // EXAMPLES
  val multiplyBy2AndThenAdd3 = andThen(multiplyBy2, addd3)
  println(multiplyBy2AndThenAdd3(4)) // prints 11

  
  
  // MORE EXAMPLES
  def superAdder2: (Int => Int => Int) = toCurry(_ + _)
  def add4 = superAdder2(4)
  println(add4(17))

  val simpleAdder = fromCurry(superAdder)
  println(simpleAdder(4,17))

  val add2 = (x: Int) => x + 2
  val times3 = (x: Int) => x * 3

  val composed = compose(add2, times3)
  val ordered = andThen(add2,times3)

  println(composed(4))
  println(ordered(4))
}