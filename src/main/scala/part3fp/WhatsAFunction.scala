package part3fp

object WhatsAFunction extends App {
  // DREAM: use functions as first class elements
  // problem: oop
  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2)) // 4

  // function types = Function1[A, B]
  trait MyFunction[A, B] {
    def apply(element: A): B
  }

  val stringToIntConverter = new MyFunction[String, Int] {
    override def apply(element: String): Int = element.toInt
  }

  println(stringToIntConverter("3") + 4) // 7

  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  // Function types Function2[A, B, R] === (A, B) => R

  // ALL SCALA FUNCTIONS ARE OBJECTS

  /*
    1. a function which takes 2 strings and concatenates them
    2. transform the MyPredicate and MyTransformer into function types
    3. define a function which takes an int and returns another function which takes an int and returns an int
      - what's the type of this function
      - how to do it
   */

  val concatenator: (String, String) => String = new Function2[String, String, String] {
    override def apply(a: String, b: String): String = a + b
  }
  println(concatenator("Hello ", "Scala"))

  val superAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val adder3 = superAdder(3) // 3
  println(adder3(4)) // 7
  println(superAdder(3)(4)) // curried functiomn
}
