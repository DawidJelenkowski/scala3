package part2oop

import part2oop.OOBasics

object OOBasics extends App {

  // val person = new Person("John", 26)
  // println(person.age)
  // person.greet("Dawid")
  // person.greet()
  val author = new Writer("Charles", "Dickens", 1812)
  val imposter = new Writer("Charles", "Dickens", 1812)
  val novel = new Novel("Great Expectations", 1861, author)

  // println(novel.authorAge)
  // print(novel.isWrittenBy(author))

  val counter = new Counter
  counter.inc.print
  counter.inc.inc.inc.print
  counter.inc(10).print
}

// constructor
class Person(name: String, val age: Int) {
  // body
  val x = 2

  // println(1 + 3)

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

/* Novel and a Writer
Writer: first name, surname, year
  - method fullname

Novel: name, year of release, author
  - authorAge
  - isWrittenBy(author)
  - copy (new year of release) = new instance of Novel
 */

class Writer(firstName: String, surname: String, val year: Int) {
  def fullname: String = firstName + " " + surname
}

class Novel(name: String, year: Int, author: Writer) {
  def authorAge: Int = year - author.year
  def isWrittenBy(author: Writer) = author == this.author
  def copy(newYear: Int): Novel = new Novel(name, newYear, author)
}
/*
  Counter class
    - receives an int value
    - method current count
    - method to increment/decrement => new Counter
    - overload inc/dec to receive an amount
 */

class Counter(val count: Int = 0) {
  def inc = {
    println("inceremnting")
    new Counter(count + 1) // immutability
  }

  def dec = {
    println("decrementing")
    new Counter(count - 1)
  }

  def inc(n: Int): Counter = {
    if (n <= 0) this
    else inc.inc(n - 1)
  }

  def dec(n: Int): Counter = {
    if (n <= 0) this
    else dec.dec(n - 1)
  }

  def print = println(count)
}
