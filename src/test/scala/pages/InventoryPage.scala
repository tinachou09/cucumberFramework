package pages

import org.scalatest.Assertion

object InventoryPage extends BasePage {

  override val url = "https://www.saucedemo.com/inventory.html"

  def clickFilterButton(option: String): Unit = option match {
    case "Name (A to Z)" => clickOn(cssSelector("option[value='az']"))
    case "Name (Z to A)" => clickOn(cssSelector("option[value='za']"))
    case "Price (low to high)" => clickOn(cssSelector("option[value='lohi']"))
    case "Price (high to low)" => clickOn(cssSelector("option[value='hilo']"))
  }


  def checkTheListInAlphabetically(order: String): Assertion = {
    val theItemNameList = findAll(cssSelector(".inventory_item_name")).map(element => element.underlying.getText).toList
    println("original:" + theItemNameList)
    val expected = if (order == "an") {
      theItemNameList.sortWith(_ < _)
    } else theItemNameList.sortWith(_ > _)

    println("expected:" + expected)
    //This only check the content if using ==
    theItemNameList should equal(expected)
  }

  //  def checkThePriceOrder(order:String): Assertion ={
  //    val theItemNamePriceString: List[String] = findAll(cssSelector(".inventory_item_price")).map(element => element.underlying.getText).toList
  //    //convert the price from string to double
  //    val theItemNamePrice = theItemNamePriceString.map(element => element.c)
  //    println("original price:" + theItemNamePrice)
  //    val expected =
  //      if (order =="an") {
  //        theItemNamePrice.sortWith(_<_ )}
  //      else theItemNamePrice.sortWith(_>_)
  //
  //    println("expected price:" + expected)
  //    //This only check the content if using ==
  //    theItemNamePrice should equal(expected)
  //  }


}
