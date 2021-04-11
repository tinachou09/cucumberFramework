package stepdef

import io.cucumber.java.StepDefinitionAnnotation
import io.cucumber.java.en.{And, Given, Then, When}
import pages.Inventorypage

@StepDefinitionAnnotation
class InventoryStep extends BaseSteps {

  @When("""The user click the '([^\"]*)' filter option$""")
  def clickFilterFunction(filterOption:String) =  {
    Inventorypage.clickFilterButton(filterOption)
  }

  @Then("""The name has been sorted in '(an|reverse)' alphabetical order$""")
  def theLetterInAlphabeticalOrder(order:String) =  {
    order match {
      case "an" => Inventorypage.checkTheListInAlphabetically
      case "reverse" => Inventorypage.checkTheListInReverseAlphabetically
    }
  }

}
