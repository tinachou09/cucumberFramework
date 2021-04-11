package stepdef

import io.cucumber.java.StepDefinitionAnnotation
import io.cucumber.java.en.{Then, When}
import org.scalatest.Assertion
import pages.InventoryPage

@StepDefinitionAnnotation
class InventoryStep extends BaseSteps {

  @When("""The user click the '([^\"]*)' filter option$""")
  def clickFilterFunction(filterOption: String): Unit = {
    InventoryPage.clickFilterButton(filterOption)
  }

  @Then("""The name has been sorted in '(an|reverse)' alphabetical order$""")
  def theLetterInAlphabeticalOrder(order: String): Assertion = {
    InventoryPage.checkTheListInAlphabetically(order)
  }


    @Then("""The price has been sorted in '(low ot high|high to low)' order$""")
    def thePriceOrderedFrom(order:String) :Assertion=  {
     ???
      //      InventoryPage.
      }



}
