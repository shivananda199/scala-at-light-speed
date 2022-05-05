package com.rockthejvm

object FunctionalProgramming extends App {

  //Scala is OO
  class Person(name: String) {
    def apply(age: Int) = println(s"I am $age years old")
  }

  val bob = new Person("Bob")
  bob(43) //invoking bob as a function === bob.apply(43)

  /**
    * Scala runs on the JVM
    * Functional programming
    * - compose functions
    * - pass functions as args
    * - return functions as results
    *
    * *** Conclusion: FunctionX = Function1, Function2, ... Function22 (22 is the max no of arguments you can pass to a function)
    */

  val simpleIncrementer = new Function1[Int, Int] {
    override def apply(arg: Int): Int = arg + 1
  }

  simpleIncrementer.apply(54)
  simpleIncrementer(54) // === simpleIncrementer.apply(54)
  //defined a function!

  //ALL SCALA FUNCTIONS ARE INSTANCES OF THESE FUNCTION_X TYPES

  val stringConcatenator = new ((String, String) => String) {
    override def apply(arg1: String, arg2: String): String = arg1 + arg2
  }

  stringConcatenator("I love ", "Scala")

  //Syntax sugars
  val doubler1 = new ((Int) => Int) {
    override def apply(arg: Int): Int = 2 * arg
  }

  val doubler2: Int => Int = (a: Int) => 2 * a
  /*equivalent to much longer:
    val doubler: Function1[Int, Int] = new Function1[Int, Int] {
      override def apply (a:Int): Int = 2 * a
    }
    */

  // Higher-order functions (HOF): functions that take functions as arguments or return functions as results
  val aMappedList = List(1, 2, 3).map(x => x + 1)   //map is a HOF
  println(aMappedList)

  val aFlatMappedList = List(1, 2, 3).flatMap(a => List(a, 2 * a)) // flatMap is a HOF
  // a => List(a, 2 * a) - will return a list for each element in the list
  // and flatMap will concatenate each individual element list into a single list
  println(aFlatMappedList)

  val aFilteredList1 = List(1, 2, 3, 4, 5, 6).filter(a => a>=4)
  val aFilteredList2 = List(1, 2, 3, 4, 5, 6).filter(_ >= 4) //both are equivalent
  println(aFilteredList1)
  println(aFilteredList2)

  //all pairs between list(1, 2, 3) and list(a, b, c) ***
  val allPairs = List(1, 2, 3).flatMap(number => List('a', 'b', 'c').map(letter => s"$number-$letter"))
  println(allPairs)

  // for comprehensions
  // equivalent to the code above for allPairs
  val alternativeAllPairs = for {
    number <- List(1,2,3)
    letter <- List('a', 'b', 'c')
  } yield s"$number-$letter"
  println(alternativeAllPairs)

  /** ***
    * Collections - lists, sequences, vectors, ranges, sets, tuple, maps,
    */

  //Lists
  val aList = List(1,2,3,4,5)
  val firstElement = aList.head
  val rest = aList.tail
  val aPrependedList = 0::aList   //prepend list List(0,1,2,3,4,5)
  val anExtendedList = 0 +: aList :+ 6 //List(0,1,2,3,4,5,6)

  //Sequences
  val aSequence: Seq[Int] = Seq(1,2,3)  //Seq.apply(1,2,3)
  val accessedElement = aSequence(1)  //element at index 1 = 2

  //Vectors: fast Seq implementation, has exact methods as Seq and List
  val aVector = Vector(1,2,3,4,5)

  //Sets = no duplicates
  val aSet = Set(1,2,3,4,1,2,3)   //Set(1,2,3,4)
  val setHas5 = aSet.contains(5)  //false
  val anAddedSet = aSet + 5 //Set(1,2,3,4,5)
  val aRemovedSet = aSet - 3 //Set(1,2,4)

  //Ranges - this is useful for iterations
  val aRange = 1 to 500
  val twoByTwo = aRange.map(a => 2 * a).toList    //List(2,4,6,8,...,500)

  //Tuples = groups of value under the same value ***
  val aTuple = ("Joe", "John", 12323)

  //Maps
  val aPhoneBook: Map[String, Int] = Map(
    ("Rocky", 12345),
    "Ryan" -> 67789
  )

}
