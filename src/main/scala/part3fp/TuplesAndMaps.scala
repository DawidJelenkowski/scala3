package part3fp

object TuplesAndMaps extends App {
  
  // tuples = finite ordered "lists"
  // they are limited in size - 22 elements
  val aTuple = new Tuple2(2, "hello, Scala") // Tuple2[Int, String] = (Int, String)
  val aTuple2 = Tuple2(2, "hello, Scala") // Tuple2[Int, String] = (Int, String)
  val aTuple3 = (2, "hello, Scala") // Tuple2[Int, String] = (Int, String)

  println(aTuple._1) // 2 // ._1 is the first element of the tuple
  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap) // ("hello, Scala", 2)

  // Maps - keys -> values
  val aMap: Map[String, Int] = Map()

  val phonebook = Map(("Jim", 555), "Daniel" -> 789).withDefaultValue(-1) // if key is not found, return -1
  // a -> b is sugar for (a, b)
  println(phonebook)

  // map ops
  println(phonebook.contains("Jim")) // true
  println(phonebook("Jim")) // 555
  println(phonebook("Mary")) // -1

  // add a pairing
  val newPairing = "Mary" -> 678
  val newPhonebook = phonebook + newPairing // Map(("Mary", 678))
  println(newPhonebook)

  // functionals on maps
  // map, flatMap, filter
  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2)) // Map(("jim", 555), ("daniel", 789))

  // filterKeys
  println(phonebook.view.filterKeys(x => x.startsWith("J")).toMap) // Map(("Jim", 555))
  // mapValues
  println(phonebook.mapValues(number => "0245-" + number)) // Map(("Jim", "0245-555"), ("Daniel", "0245-789"))

  // conversions to other collections
  println(phonebook.toList) // List(("Jim", 555), ("Daniel", 789))
  println(List(("Daniel", 555)).toMap) // Map(("Daniel", 555))
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0))) // Map(("A", List("Angela")), ("J", List("James", "Jim")), ("M", List("Mary")), ("D", List("Daniel")), ("B", List("Bob")))
}
