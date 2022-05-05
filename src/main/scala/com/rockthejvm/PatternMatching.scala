package com.rockthejvm

object PatternMatching extends App {

  //pattern matching is equivalent of switch expression
  val score = 100
  val scoreDesc = score match {
    case 0 => "duck"
    case 50 => "half-century"
    case 100 => "century"
    case _ => "random" //If Pattern matching does not match anything,
    // it will throw MatchError, so we should always add the default case
    //pattern matching will try all cases in sequence, so always add the default condition as the last case
  }
  println(scoreDesc)
  // notice that pattern matching is an expression


  //pattern matching on a case class structure (case class decomposition)
  case class Person (
    name: String,
    age: Int
  )
  val rocky = Person("Rocky", 30)     //===Person.apply("Rocky", 30)

  val personIntro = rocky match {
    case Person(n, a) => s"My name is $n and I am $a years old."
    //case Person(n, a) => Person.tupled(s"$n Bhai", 30)    //Person("Rocky Bhai", 30)
    case _ => "Object does not conform to the structure of Person case class."
  }
  println(personIntro)

  //Pattern matching to deconstruct tuples
  val aTuple = ("Harry Styles", "singing")
  val personPassion = aTuple match {
    case (name, passion) => s"$name's passion is $passion."
    case _ => "Could not deconstruct the input tuple."
  }
  println(personPassion)

  //pattern matching to decompose lists
  val aList = List(1,2,3)
  val listDesc = aList match {
    case List(_, 2, _) => "List contains 2 in the second position."
    case _ => "List cannot be described."
  }
  println(listDesc)

}
