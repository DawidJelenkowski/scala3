package part2oop

object AbstractDataType extends App {

  // abstract class
  abstract class Animal {
    val creatureType: String
    def eat: Unit

  }
  class Dog extends Animal {
    override val creatureType: String = "Canine"
    override def eat: Unit = print("crunch crunch")
  }

  // traits
  trait Carnivore {
    def eat(animal: Animal): Unit // can be inherited
    val preferredMeal: String = "fresh meat"
  }

  trait ColdBloded // You can extend a class with any number of trait classes
  class Crocodile extends Animal with Carnivore with ColdBloded {
    val creatureType: String = "croc"
    def eat: Unit = println("nomnomnom")
    def eat(animal: Animal): Unit =
      println(s"I'm a croc and I'm eating ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  // traits vs abstract classes
  // 1 - traits do not have constructor parameters
  // 2 - You can extend only with one class, and multiple traits
  // 3 - traits = behavior, abstract class = "thing"
}
