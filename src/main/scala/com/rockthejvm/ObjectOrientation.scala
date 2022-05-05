package com.rockthejvm

object ObjectOrientation extends App {
  // *** by extending App,
  // we are using the java equivalent public static main method already implemented in App class
  // which executed an object's body

  // class and instance
  class Animal {
    val age: Int = 0
    def eat() = println("I'm eating")
  }

  val anAnimal = new Animal

  //inheritance
  class Dog (val name: String) extends Animal //constructor definition

  val aDog = new Dog("Rocky")

  //constructor arguments are NOT fields: you need to put a val before the constructor argument
  aDog.name

  //subtype polymorphism
  val aDeclaredAnimal: Animal = new Dog("Agni")
  aDeclaredAnimal.eat()   //most derived method will be called at runtime

  //abstract class
  abstract class WalkingAnimal {
    val hasLegs = true  //by default public, can restrict by using private or protected
    def walk(): Unit
  }

  //"interface" = ultimate abstract type
  trait Carnivore {
    def eat (animal: Animal): Unit
  }

  trait Reptile {
    def walkAndSwim (animal: Animal): Unit
  }

  trait Philosopher {
    def ?!(thought: String): Unit     // a valid method name
  }
  //Scala offers SINGLE-CLASS INHERITANCE and MULTI-TRAIT INHERITANCE
  class Crocodile extends Animal with Carnivore with Reptile with Philosopher {
    override def eat(animal: Animal): Unit = {
      println("I can eat an animal!!")
    }

    override def walkAndSwim(animal: Animal): Unit = {
      println("I can live on the land and also swim in the water")
    }

    override def ?!(thought: String): Unit = println(s"I was thinking: $thought")
  }

  val aCroc = new Crocodile
  aCroc.eat()
  aCroc.eat(aDog)
  aCroc eat aDog    //infix notation: object method argument, only available for methods with one argument
  aCroc ?! "What if I could fly?"

  // operators in Scala are actually methods ***
  val basicMath = 1+2
  val anotherBasicMath = 1.+(2) //both are equivalent

  //anonymous classes ***
  val dinosaur = new Carnivore {
    override def eat(animal: Animal): Unit = println("I am a dinosaur and I can eat pretty much anything!")
  }
  /*
  The above anonymous class definition is equivalent to below code (this is what you tell the compiler):
  class Carnivore_anonymous_3242 extends Carnivore {
    override def eat(animal: Animal): Unit = println("I am a dinosaur and I can eat pretty much anything!")
   }

   val dinosaur = new Carnivore_anonymous_3242(aDog)
   */

  //singleton object
  object MySingleton {      // the only instance of the MySingleton type
    val mySpecialValue = 199
    def mySpecialMethod(): Int = 199
    def apply(x: Int): Int = x+1    //special apply method ***
  }
  MySingleton.mySpecialMethod()
  MySingleton.apply(198)
  MySingleton(198) //equivalent to the apply method call in the above statement
  //presence of apply functions in a class allows invoking instance of a class as function

  //companions - class/trait and singleton object with same names
  //companions can access each other's private fields/methods

  object Animal { //class Animal and object Animal are called companions
    // singleton Animal and instance of Animal class are different things
    val canLiveIndefinitely = false
  }

  val animalsCanLiveForever = Animal.canLiveIndefinitely //similar to accessing "static" fields/methods in Java

  /* ***
  case classes = lightweight data structures with some boilerplate
  when a case class is declared, teh compiler automatically generates
  - sensible equals and hash code
  - sensible and quick serialization
  - companion with apply
  - pattern matching
   */
  case class Person (name: String, age: Int)

  val bob = new Person("Bob", 30)
  val john = Person("John", 54) //no need of new keyword, as a companion with apply method is internally generated for case classes
  //Person.apply("John", 54)

  //exceptions
  try {
    val x: String = null
    val l = x.length
  } catch {
    case e: Exception =>
      println("some faulty error message")
  } finally {
    println("try catch block execution complete")
  }

  //generics
  abstract class MyList[T] {    //can be applied to traits
    def head: T
    def tail: MyList[T]
  }

  //using a generic with a concrete type
  val aList: List[Int] = List(1, 2, 3)  //List.apply(1,2,3)
  val first = aList.head
  val rest = aList.tail

  val aStringList = List("hello", "world")
  val firstString = aStringList.head

  // ***
  //  Point 1:  In Scala, we work with IMMUTABLE values/objects
  // any modification to an object should return a NEW object. This is beneficial for 2 reasons
  /**
    * Benefits
    * 1) works miracles in multithreaded/distributed env
    * 2) helps making sense of the code ("reasoning about")
    */
  val reversedList = aList.reverse  // returns a NEW list

  // ***
  // Point 2: Scala is closest to the Object Oriented ideal

}
