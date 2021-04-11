package pages

import org.scalatest.Assertion

object Inventorypage extends BasePage {

  override val url = "https://www.saucedemo.com/inventory.html"

  def clickFilterButton(option: String) = option match {
      case "Name (A to Z)" => clickOn(cssSelector("option[value='az']"))
      case "Name (Z to A)" => clickOn(cssSelector("option[value='za']"))
      case "Price (low to high)" => clickOn(cssSelector("option[value='lohi']"))
      case "Price (high to low)" => clickOn(cssSelector("option[value='hilo']"))
  }


  def checkTheListInAlphabetically: Assertion ={
    val theItemNameList = findAll(cssSelector(".inventory_item_name")).map(element => element.underlying.getText).toList
    println("original:" + theItemNameList)
    val expected = theItemNameList.sortWith(_<_ )
    println("expected:" + expected)
    //This only check the content if using ==
    theItemNameList should equal(expected)
  }

  def checkTheListInReverseAlphabetically: Assertion ={
    val theItemNameList = findAll(cssSelector(".inventory_item_name")).map(element => element.underlying.getText).toList
    println("original:" + theItemNameList)
    val expected = theItemNameList.sortWith(_>_)
    println("expected:" + expected)
    theItemNameList should equal(expected)
  }

}
