package com.rockthejvm

import scala.util.{Failure, Success, Try}

object Advanced extends App {

  //lazy evaluation - an expression is not evaluated until its first use
  lazy val aLazyValue = 21
  lazy val lazyValueWithSideEffect = {
    println("I am lazy!")
    44
  }
  // nothing is printed when we run the program till here
  val eagerValue = lazyValueWithSideEffect + 1
  println(eagerValue)

  /**
  //"pseudo-collections": Option, Try
  */
  def methodWhichReturnsNonNull(): String = "hello"
  def methodWhichReturnsNull(): Option[Int] = null
  val option1 = Option(methodWhichReturnsNonNull())      //Some("hello")
  val option2 = Option(methodWhichReturnsNull())        //None
  //option = "collection" with utmost one element - either Some(value) or None
  println(option1)    //Some(hello)
  println(option2)    //None

  val optionProcessing = option1 match {
    case Some(value) => s"Valid string: $value"
    case None => "nothing is obtained"
  }


  //Try - guards your program over methods which can throws exception
  def methodThrowsException(): String = throw new RuntimeException()
  val try1 = Try(methodWhichReturnsNonNull())
  val try2 = Try(methodThrowsException())
  // a try = "collection" with either a value if code went well, or if the exception i the coed threw one
  println(try1)   //Success(hello)
  println(try2)   //Failure(java.lang.RuntimeException)

  val tryProcessing = try1 match {
    case Success(value) => s"Valid value: $value"
    case Failure(ex) => s"exception: $ex"
  }

  /**
    * Evaluate something on another thread
    * asynchronous programming
    */

  /**
    * Implicits basics ***
    *
    */
  // use case 1: implicit arguments

  implicit val myImplicitInt: Int = 5
  def aMethodWithImplicitArgs(implicit a: Int) = a + 5
  println(aMethodWithImplicitArgs)    //aMethodWithImplicitArgs(myImplicitInt) = aMethodWithImplicitArgs(5) = 10

  //use case 2: implicit conversions
  implicit class MyRichInteger(n: Int) {
    def isEven(): Boolean = n%2==0
  }
  println(12.isEven())  //true
  println(11.isEven())  //false

  //use case 3: implicit conversion - implicit classes
  case class Person (name: String) {
    def greet = println(s"My name is $name")
  }
  implicit def fromStringToPerson (name: String) = Person(name)
  "Shiva".greet   // === fromStringToPerson("Shiva").greet
}
