package part3fp

object Sequences extends App{
  
  // Seq
  val aSequence = Seq(1,2,3,4) 
  println(aSequence) // List(1,2,3,4)
  println(aSequence.reverse) // List(4,3,2,1)
  println(aSequence(2)) // 3
  println(aSequence ++ Seq(5,6,7)) // List(1,2,3,4,5,6,7)
  println(aSequence.sorted) // List(1,2,3,4)

  // Ranges
  val aRange: Seq[Int] = 1 to 10
  aRange.foreach(println) // 1 2 3 4 5 6 7 8 9 10

  (1 to 10).foreach(x => println("Hello")) // Hello 10 times

  // Lists
  val aList = List(1,2,3)
  val prepended = 42 :: aList // List(42,1,2,3)
  println(prepended)
  val prepended2 = 42 +: aList :+ 89 // List(42,1,2,3,89)
  println(prepended2)

  val apples5 = List.fill(5)("apple") // Repeats apple 5 times
  println(apples5) // List(apple,apple,apple,apple,apple)
  println(aList.mkString("-|-")) // 1-|-2-|-3

  // Arrays
  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[Int](3)
  println(threeElements)
  threeElements.foreach(println) // 0 0 0

  val threeElementss = Array.ofDim[String](3)
  threeElementss.foreach(println) // null null null

  // Mutation
  numbers(2) = 0 // Syntax sugar for numbers.update(2,0)
  println(numbers.mkString(" ")) // 1 2 0 4

  // Arrays and Seq
  val numbersSeq: Seq[Int] = numbers // Implicit conversion
  println(numbersSeq) // WrappedArray(1,2,0,4)

  // Vectors
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector) // Vector(1,2,3)


  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new scala.util.Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt)
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // pros: keeps reference to tail
  // cons: updating an element in the middle takes long
  println(getWriteTime(numbersList)) // 1.5E7

  // pros: depth of the tree is small
  // cons: needs to replace an entire 32-element chunk
  println(getWriteTime(numbersVector)) // 1.0E7
}