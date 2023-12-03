package part2oop

object OOBasics extends App {

  val person = new Person("John", 26)
  println(person.age)
  person.greet("Dawid")
  person.greet()
}

// constructor
class Person(name: String, val age: Int) {
  // body
  val x = 2

  println(1 + 3)

  // method
  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")
  // this is equivalent to python's self

  // overloading is legal
  def greet(): Unit = println(s"Hi, I am $name")
  // compiler will complain if you have two methods
  // with the same name and same number of parameters

  // multiple constructors
  def this(name: String) = this(name, 0)
  def this() = this("John Doe")
  // this is useless, but it's possible
}

// class parameters are NOT FIELDS unless you add val or varsa
