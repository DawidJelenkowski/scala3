package part2oop

object Inheritance extends App {

  // single class inheritance, you can extend only one class at a time
  // final class Animal { prevents from inheriting
  // seal class Animal { prevents from modifying in the other files
  class Animal {
    def eat = println("nomnom") // every child can use it
    // private def eat = println("nomnom") // only superclass can use it
    // protected def eat = println("nomnom") // only subclasses can use it
    // final def eat = println("nomnom") // prevents from everriding method
    val creatureType = "wild"
  }

  class Cat extends Animal { // Animal is a superclass
    def crunch = {
      eat
      // println("crunch crunch")
    }
  }
  val cat = new Cat
  // cat.crunch // prints protected eat and "crunch crunch"

  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  } // using "this(name, 0)"" you may call "Person(name)" instead of "Person(name, age)"
  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  // overrriding // supplying different iplementation of a classes

  // 1 overriding directly in the class
  class Dog(override val creatureType: String) extends Animal {
    // override val creatureType = "domestic"
    override def eat = {
      super.eat // inherits eat from animal
      println("crunch, crunch")
    }
  }

  // 2 overriding inside the class using class parameter
  // class Dog(dogType: String) extends Animal {
  //   override val creatureType = dogType
  // }

  val dog = new Dog("K9")
  // dog.eat // overrided "crunch, crunch"
  // println(dog.creatureType) // overrided "domestic"

  // type substitution (broad: polymorphism)
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat // if not protected, inherits method

  // overloading supplying multiple methods with different signatures
  // but with the same name in the same class

  // super

  // prevents overrides
  // 1 - use final on member
  // 2 - use final on the entire class
  // 3 - seal the class = extend classes in THIS FILE,
  // prevent extension in other files.
}
