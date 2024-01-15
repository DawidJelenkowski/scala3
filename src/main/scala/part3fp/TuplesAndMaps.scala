package part3fp
import scala.collection.immutable.Map

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

  val phonebook = Map(("Jim", 555), "Daniel" -> 789, "JIM" -> 9000).withDefaultValue(-1) // if key is not found, return -1
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
  println(phonebook.mapValues(number => "0245-" + number).toMap) // Map(("Jim", "0245-555"), ("Daniel", "0245-789"))
  // conversions to other collections
  println(phonebook.toList) // List(("Jim", 555), ("Daniel", 789))
  println(List(("Daniel", 555)).toMap) // Map(("Daniel", 555))
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))
  // Map(("A", List("Angela")), ("J", List("James", "Jim")), ("M", List("Mary")), ("D", List("Daniel")), ("B", List("Bob")))


  /* 
    1. What  would happen if I had two original entries "Jim -> 555" and "JIM" -> 900?
    For example, if I would like to toLowerCase those entries, I would lose one of them.
    2. Overly simplified social network based on maps
        Person = String
        - add a person to the network
        - remove
        - friend (mutual)
        - unfriend
        
        - number of friends of a person
        - person with most friends
        - how many pepople have NO friends
        - if there is social connection between two people (direct or not)
        */
  
  // add a person to the network
  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = 
    network + (person -> Set()) 

  // add a friend
  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)
    
    // expands a set of friends
    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }

  // unfriend
  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)
    
    // replaces old pairing with a new one
    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }

  // remove a person
  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    def revmoveAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] = 
      if (friends.isEmpty) networkAcc
      else revmoveAux(friends.tail, unfriend(networkAcc, person, friends.head))
      // first unfriend person from the head of the friends set, then tail of the friends set
    
    val unfriended = revmoveAux(network(person), network)
    unfriended - person
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"), "Mary")
  println(network) // Map(("Bob", Set()), ("Mary", Set()))
  println(friend(network, "Bob", "Mary")) // Map(("Bob", Set("Mary")), ("Mary", Set("Bob")))
  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary")) // Map(("Bob", Set()), ("Mary", Set()))
  println(remove(friend(network, "Bob", "Mary"), "Bob")) // Map(("Mary", Set()))


  // Jim, Bob, Mary
  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Bob", "Mary")

  println(testNet)

  def nFriends(network: Map[String, Set[String]], person: String): Int =
    if (!network.contains(person)) 0 // if person is not in the network, return 0
    else network(person).size // otherwise, return the size of the set of friends

  println(nFriends(testNet, "Bob")) // 2

  def mostFriends(network: Map[String, Set[String]]): String =
    network.maxBy(pair => pair._2.size)._1 // ._1 is the key, ._2 is the value

  println(mostFriends(testNet)) // Bob

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
    network.filterKeys(k => network(k).isEmpty).size // filterKeys returns a map with only the keys that satisfy the predicate

  println(nPeopleWithNoFriends(testNet)) // 0

  def nPeopleWithNoFriends2(network: Map[String, Set[String]]): Int =
    network.count(pair => pair._2.isEmpty) // count returns the number of elements that satisfy the predicate
    network.count(_._2.isEmpty) // same as above

  println(nPeopleWithNoFriends2(testNet)) // 0

  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    // BFS
    @scala.annotation.tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false // if there are no discovered pepole, there is no connection
      else {
        val person = discoveredPeople.head // take the firs person from the discovered people set
        if (person == target) true // if the person is the target, there is a connection
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail) // if the person is already considered, skip it
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person)) // otherwise, add the person to the considered people set and add the person's friends to the discovered people set
      }
    }

    bfs(b, Set(), network(a) + a) // start with the person a and add it to the discovered people set
  }
  println(socialConnection(testNet, "Mary", "Jim")) // true
  println(socialConnection(network, "Mary", "Bob")) // true
}
