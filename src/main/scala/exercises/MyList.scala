package exercises

import scala.compiletime.ops.boolean

abstract class MyList[+A] {
  /*
    head = first element of the list
    tail = remainder of the list
    isEmpty = is this list empty
    add(int) => new list with this element added
    toString => a string representation of the list
   */
  // abstract methods, because they don't implement anything
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  // polymorphic call
  override def toString: String = "[" + printElements + "]"

  // higher order functions
  def map[B](transformer: A => B): MyList[B]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]

  // concatenation
  def ++[B >: A](list: MyList[B]): MyList[B]

  // hofs
  def foreach(f: A => Unit): Unit
  def sort(compare: (A, A) => Int): MyList[A] // if the first is less than second it will return a negative value
  def zipWith[B, C](list: MyList[B], zip:(A, B) => C): MyList[C] // takes my list of type B
  // zips list of type A and B into type C
  def fold[B](start: B)(operator: (B, A) => B): B
}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
  def printElements: String = ""

  def map[B](transformer: Nothing => B): MyList[B] = Empty
  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty
  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  // hofs
  def foreach(f: Nothing => Unit): Unit = () // The parenthesis is an Unit value
  def sort(compare: (Nothing, Nothing) => Int) = Empty
  def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] =
    if (!list.isEmpty) throw new RuntimeException("List do not have the same length")
    else Empty
  def fold[B](start: B)(operator: (B, Nothing) => B): B = start
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)
  def printElements: String =
    if (t.isEmpty) "" + h
    else s"$h ${t.printElements}"

  
  def filter(predicate: A => Boolean): MyList[A] = 
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
    // if head passes the filter it will be included in the result

  
  def map[B](transformer: A => B): MyList[B] =
    new Cons(transformer(h), t.map(transformer))


  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)
  /* explanation
    [1,2] ++ [3,4,5]
    = new Cons(1, [2] ++ [3,4,5])
    = new Cons(1, new Cons(2, Empty ++ [3,4,5]))
    = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5)))))
   */

  def flatMap[B](transformer: A => MyList[B]): MyList[B] = 
    transformer(h) ++ t.flatMap(transformer)

  /* explanation
    [1,2].flatMap(n => [n, n+1])
    = [1,2] ++ [2].flatMap(n => [n, n+1])
    = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1])
    = [1,2] ++ [2,3] ++ Empty
    = [1,2,2,3]
  */

  def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  def sort(compare: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] = 
      if (sortedList.isEmpty) new Cons(x, Empty) // if sortedList is empty it wil return the last element
      else if (compare(x, sortedList.head) <= 0) new Cons(x, sortedList) // instead of x <= 0 the compare is used
      // if x is smaller than already the smallest element of the list =, then it becomes the new smallest element
      else new Cons(sortedList.head, insert(x, sortedList.tail))
      // otherwise the head is the new smalles element
    val sortedTail = t.sort(compare)
    insert(h, sortedTail) // insertion of a value in an already sorted list
  }

  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] = 
    if (list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else new Cons(zip(h, list.head), t.zipWith(list.tail, zip))
  
  def fold[B](start: B)(operator: (B, A) => B): B =
    t.fold(operator(start, h))(operator)
  /* explanation
    [1,2,3].fold(0) (+) =
    = [2,3].fold(1) (+) =
    = [3].fold(3) (+) =
    = [].fold(6) (+) =
    = 6
  */
}

object ListTest extends App {
  val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val clonelistOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val anotherListOfIntegers: MyList[Int] = new Cons(4, new Cons(5, Empty))
  val listOfStrings: MyList[String] =
    new Cons("Hello", new Cons("Scala", Empty))

  // val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
  // println(list.tail.head) // 2
  // println(list.add(4).head) // 4
  // println(list.isEmpty) // false

  // println(list.toString) // [1 2 3]

  // println(listOfIntegers.toString) // [1 2 3]
  // println(listOfStrings.toString) // [Hello Scala]

  // anonymous class
  println(listOfIntegers.map(elem => elem * 2).toString) // [2 4 6]
  // println(listOfIntegers.map(_ * 2).toString) // [2 4 6]
  
  /* explanation
  [1,2,3].map(n * 2)
    = new Cons(2, [2,3].map(n * 2))
    = new Cons(2, new Cons(4, [3].map(n * 2)))
    = new Cons(2, new Cons(4, new Cons(6, Empty.map(n * 2))))
    = new Cons(2, new Cons(4, new Cons(6, Empty))))
  */

  println(listOfIntegers.filter(elem => elem % 2 == 0).toString) // [2]
  // println(listOfIntegers.filter(_ % 2 == 0).toString) // [2]

  /* explanation
  [1,2,3].filter(n % 2 == 0)
    = [2,3].filter(n % 2 == 0)
    = new Cons(2, [3].filter(n % 2 == 0))
    = new Cons(2, Empty.filter(n % 2 == 0))
    = new Cons(2, Empty)
  */

  println((listOfIntegers ++ anotherListOfIntegers).toString) // [1 2 3 4 5]
  println(listOfIntegers.flatMap(elem => Cons(elem, new Cons(elem + 1, Empty))).toString) // [1 2 2 3 3 4]

  println(clonelistOfIntegers == listOfIntegers) // true

  listOfIntegers.foreach(println) // 1 2 3
  println(listOfIntegers.sort((x, y) => y - x)) // [3 2 1]
  println(anotherListOfIntegers.zipWith[String, String](listOfStrings, _ + "-" + _)) // [4-Hello 5-Scala]

  println(listOfIntegers.fold(0)(_ + _)) // 6

  // for comprehensions
  val combinations = for {
    n <- listOfIntegers
    string <- listOfStrings
  } yield n + "-" + string
  println(combinations) // [1-Hello 1-Scala 2-Hello 2-Scala 3-Hello 3-Scala]
}

/* tasks
  1. Generic trait MyPredicate[-T] with a little method test(T) => Boolean
  2. Generic trait MyTransformer[-A, B] with a method transform(A) => B
  3. MyList:
    - map(transformer) => MyList
    - filter(predicate) => MyList
    - flatMap(transformer from A to MyList[B]) => MyList[B]

    class EvenPredicate extends MyPredicate[Int]
    class StringToIntTransformer extends MyTransformer[String, Int]

    [1,2,3].map(n * 2) = [2,4,6]
    [1,2,3,4].filter(n % 2) = [2,4]
    [1,2,3].flatMap(n => [n, n+1]) => [1,2,2,3,3,4]
 */
