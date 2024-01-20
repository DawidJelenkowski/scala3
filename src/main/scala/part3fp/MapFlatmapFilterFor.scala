package part3fp

object MapFlatmapFilterFor extends App{
  
  val list = List(1,2,3)
  // println(list)
  // print(list.head)
  // print(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  // print all combinations between two lists
  val numbers = List(1,2,3,4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white")
  // List("a1", "a2", ... "d4")

  // if you have to do 2 loops use flatMap and map
  // the "" converts the result to a string

  //val combinations = numbers.flatMap(n => chars.map(c => "" + c + n))

  // the result of map is a list of strings
  // flatMap flattens them into a single list
  
  // tripple loop
  // "iterations"
  val combinations = numbers.filter(_ % 2 == 0).flatMap(n => chars.flatMap(c => colors.map(color => "" + c + n + "-" + color)))
  println(combinations)

  // foreach
  list.foreach(println)

  // for-comprehensions
  val forCombinations = for {
    n <- numbers if n % 2 == 0 // filters for numbers only divisible by 2
    c <- chars
    color <- colors
  } yield "" + c + n + "-" + color
  println(forCombinations)
  
  for {
    n <- numbers
  } println(n)

  // syntax overload
  println(list.map ( x =>
    x * 2
  ))

  /* explanation
  [1,2,3].map(n * 2)
    = new Cons(2, [2,3].map(n * 2))
    = new Cons(2, new Cons(4, [3].map(n * 2)))
    = new Cons(2, new Cons(4, new Cons(6, Empty.map(n * 2))))
    = new Cons(2, new Cons(4, new Cons(6, Empty))))
  */

  /* tasks
    1. MyList supports for comprehensions?
      map(f: A => B) => MyList[B]
      filter(p: A => Boolean) => MyList[A]
      flatMap(f: A => MyList[B]) => MyList[B]
    2. A small collection of at most ONE element - Maybe[+T]
      - map, flatMap, filter
    */
  
  
}