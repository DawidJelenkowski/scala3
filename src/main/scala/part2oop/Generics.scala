package part2oop

import part2oop.Inheritance.Animal

object Generics extends App {

  class MyList[+A] { // traits can also by type parametrized
    // use the type A
    def add[B >: A](element: B): MyList[B] = ??? // B is a supertype of A
    /*
      A = Cat
      B = Dog = Animal
     */
  }

  class MyMap[Key, Value]

  val listOfIntegers = new MyList[Int] // Int replaces A
  val listOfStrings = new MyList[String] // String replaces A

  // generic methods
  object MyList { // objects cannot be type parametrized
    def empty[A]: MyList[A] = ???
  }
  val emptyListOfIntegers = MyList.empty[Int]

  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1. yes, List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // CovariantList[Cat] extends CovariantList[Animal]
  // animalList.add(new Dog) ??? HARD QUESTION => we return a list of Animals

  // 2. NO = INVARIANCE
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]
  // InvariantList[Cat] does not extend InvariantList[Animal]

  // 3. Hell, no! CONTRAVARIANCE
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]
  // Trainer[Animal] extends Trainer[Cat]

  // bounded types
  class Cage[A <: Animal](animal: A)
  // class Cage only accepts type parametrized classes that are subtypes of Animal
  val cage = new Cage(new Dog)

  // expand MyList to be generic

}
