package com.rockthejvm

object Basics extends App {

  //defining a value
  val meaningOfLife: Int = 42

  val aString = "I love Scala"
  val aComposedString = "I" + " " + "love" + " " + "Scala"
  val anInterpolatedString = s"The meaning of life is $meaningOfLife"

  //expressions = structures that can be reduced to a value
  val anExpression = 2 + 3

  //if-expression
  val ifExpression = if (meaningOfLife > 43) 56 else 999

  val chainedIfExpression = {
    if (meaningOfLife > 43) 56
    else if (meaningOfLife < 0) -2
    else if (meaningOfLife > 999) 78
    else 0
  }

  //code blocks
  val aCodeBlock = {
    //definitions
    val aLocalValue = 67

    //value of block is the value of the last expression
    aLocalValue + 3
  }

  //defining functions
  def myFunction (x: Int, y: String): String = {
    y + " " + x
  }


  //recursive function
  def factorial (n: Int): Int = {
    if (n<=1) 1
    else n * factorial(n-1)

    //In Scala, try to think in terms of recursions and functions instead of loops, as iterations and var are heavily discouraged
  }

  // the Unit type = no meaningful value (equivalent of void in other languages)
  val theUnit: Unit = ()


}
