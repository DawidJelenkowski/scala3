package part2oop

object Objects {

  // SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static")
  object Person { // type + its only instance
    // "static"/"class" - level functionality
    val N_EYES = 2
    def canFly: Boolean = false

    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }
  class Person(val name: String) {
    // instance-level functionality
  }
  // COMPANIONS
  // object Person and class Person are companions

  def main(args: Array[String]): Unit = {
    // println(Person.N_EYES) // 2
    // println(Person.canFly) // false

    // Scala object = SINGLETON INSTANCE
    val mary = new Person("Mary")
    val john = new Person("John")

    val person1 = Person
    val person2 = Person
    // println(person1 == person2) // true

    val bobbie = Person(mary, john) // it is apply method
  }
  // Scala Applications = Scala object with
  // def main(args: Array[String]): Unit
}
