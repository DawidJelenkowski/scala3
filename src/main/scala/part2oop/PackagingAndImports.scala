package part2oop

import playground.{Cinderella => Princess, PrinceCharming}
// import playground._ // import everything from playground
import java.util.Date
import java.sql.{Date => SqlDate}

// package object
object PackagingAndImports extends App {
  
  // package members are accessible by their simple name
  val writer = new Writer("John", "RockTheJVM", 2023)
  
  // import the package
  // val princess = new Princess
  
  // packages are in hierarchy
  // matching folder structure.

  // package object
  sayHello
  println(SPEED_OF_LIGHT)


  val prince = new PrinceCharming

  // use Fully Qualified name
  val date = new Date
  // val sqlDate = new java.sgl.Date(2023, 1, 1)
  // 2. use aliasing
  val sqlDate = new SqlDate(2023, 1, 1)

  // default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???
  // imports

}
