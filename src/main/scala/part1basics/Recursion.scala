package part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): Int =
    if (n <= 1) 1
    else {
      println(
        "Computing factorial of " + n + " - I first need factorial of " + (n - 1)
      )
      val result = n * factorial(n - 1)
      println("Computed factorial of " + n)

      result
    }
  println(factorial(10))
  // println(factorial(5000)) // StackOverflowError

  def anotherFactorial(n: Int): BigInt = {
    @tailrec // This annotation will throw an error if the function is not tail recursive
    def factHelper(x: Int, accumulator: BigInt): BigInt =
      if (x <= 1) accumulator
      else factHelper(x - 1, x * accumulator)
      // TAIL RECURSION = use recursive call as the LAST expression

    factHelper(n, 1)
  }

  println(anotherFactorial(5000))
  // stores the intermediate results in the accumulator

  // WHEN YOU NEED LOOPS, USE _TAIL_ RECURSION.

  /*
    anotherFactorial(10) = factHelper(10, 1)
    = factHelper(9, 10 * 1)
    = factHelper(8, 9 * 10 * 1)
    = factHelper(7, 8 * 9 * 10 * 1)
    = ...
    = factHelper(2, 3 * 4 * ... * 10 * 1)
    = factHelper(1, 2 * 3 * 4 * ... * 10 * 1)
    = 1 * 2 * 3 * 4 * ... * 10
   */

  /*
    1. Concatenate a string n times
    2. IsPrime function tail recursive
    3. Fibonacci function, tail recursive
   */

  @tailrec
  def concatenateTailrec(aString: String, n: Int, accumulator: String): String =
    if (n <= 0) accumulator
    else concatenateTailrec(aString, n - 1, aString + accumulator)

  println(concatenateTailrec("hello", 3, "")) // hellohellohello

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeTailrec(t: Int, isStillPrime: Boolean): Boolean =
      if (!isStillPrime) false
      else if (t <= 1) true
      else isPrimeTailrec(t - 1, n % t != 0 && isStillPrime)

    isPrimeTailrec(n / 2, true)
  }

  println(isPrime(2003)) // 1001  is not divisible by anything but 1 and itself
  println(isPrime(629)) // 17 * 37 which means 629 is divisible by 17 and 37

  def fibonacci(n: Int): Int = {
    @tailrec
    def fiboTailrec(i: Int, last: Int, nextToLast: Int): Int =
      if (i >= n) last
      else fiboTailrec(i + 1, last + nextToLast, last)

    if (n <= 2) 1
    else fiboTailrec(2, 1, 1)
  }

  println(fibonacci(8)) // 1 1 2 3 5 8 13 21
  /*
  fiboTailrec(2, 1, 1) -> fiboTailrec(3, 2, 1) -> fiboTailrec(4, 3, 2) ->
  fiboTailrec(5, 5, 3) -> fiboTailrec(6, 8, 5) -> fiboTailrec(7, 13, 8) ->
  fiboTailrec(8, 21, 13)
   */
}
