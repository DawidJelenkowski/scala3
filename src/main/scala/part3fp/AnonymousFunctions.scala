package part3fp

object AnonymousFunctions extends App {

  // default function
  val doubler = new Function1[Int, Int] {
    override def apply(x: Int) = x * 2
  }

  // anonymous function (LAMBDA) - syntactic sugar
  val ddoubler = (x: Int) => x * 2


  val dddoubler: Int => Int = x => x * 2

  // multiple params in a lambda
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  // no params
  val justDoSomething: () => Int = () => 3

  // calling methods
  println(justDoSomething) // function itself

  // calling lambda
  println(justDoSomething()) // call

  // curly braces with lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }


  val niceIncrementer: Int => Int = _ + 1 // eqivalent to x => x + 1

  val nniceAdder:(Int, Int) => Int = _ + _ // eqivalent to (a,b) => a + b
  
}
