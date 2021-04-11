package pages

import org.apache.xerces.xs.StringList
//Converting list to map by adding index to the list
object scalaEx extends App {

  val numList = List(1,2,3)
  val stringList = List("hello", "String", "number")


   val x = numList.zip(stringList)

  val y = stringList.zip(numList).map {
    case(v, i) => (i, v)._1
  }
  val z = stringList.zip(numList).map {
    case(v, i) => (i, v)._2
  }

//  Converting List to map by merging two list
  val a = stringList.zip(numList).toMap

//  List((1,hello), (2,String))


  println(x)
  println(y)
//  println(z)
  println(a)
}
