package part2oop

object AnonymousClasses extends App {
  trait Animal {
    def eat: Unit
  }

  // anonymous class
  val funnyAnimal = new Animal {
    override def eat: Unit = println("ahah")
  }

  /*
    equivalent with

    class AnonymousClasses$$anon$1 extends Animal {
      override def eat: Unit = println("ahah")
    }
    val funnyAnimal = new AnonymousClasses$$anon$1
   */

  println(funnyAnimal.getClass) // part2oop.AnonymousClasses$$anon$1

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name, how can I help?")
  }

  // anonymous classes work for abstract and non-abstract types
  val jim = new Person("Jim") {
    override def sayHi: Unit = println(
      s"Hi, my name is Jim, how can I be of service?"
    )
  }

}
