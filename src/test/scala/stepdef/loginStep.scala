package stepdef

import io.cucumber.datatable.DataTable
import io.cucumber.java.StepDefinitionAnnotation
import pages.{BasePage, Inventorypage, WebPage}
import io.cucumber.java.en.{And, Given, Then}
import io.cucumber.messages.Messages.GherkinDocument.Feature.Background
import org.openqa.selenium.WebElement
import org.scalatest.Assertion


@StepDefinitionAnnotation
class loginStep extends  BaseSteps {

  //the reason that need to import cucumber java.en is because  dependency mismatches (Cucumber - Content Assistance in Feature file not working)

  //a step definition : a java expression to link to Gherkin steps - it look for matching step to execute
  @Given("^the user navigate to the page$")
  def this_pre_condition = > {
    val url = "https://www.saucedemo.com/"
    goTo(url)
  }

  @And("^log in the page via entering the username and password$")
  def logIn = > {
    //username
    val usernameFieid = id("user-name")
    val passwordField = id("password")

    def getUserNameValue: String = cssSelector(".login_credentials").webElement.getText

    //convert the paragraph by using a break line and , then it convert to array and then covert into a list
    val standardUsername: String = getUserNameValue.split("\n").toList.apply(1)
    usernameFieid.webElement.sendKeys(standardUsername)

    //password  -driver.findElement(By.className get replaced by cssSelector webbrowser
    def getPasswordValue: String = cssSelector(".login_password").webElement.getText

    //convert the paragraph by using a break line and , then it convert to array and then covert into a list
    val password: String = getPasswordValue.split("\n").toList.apply(1)
    passwordField.webElement.sendKeys(password)

    clickOn("login-button")
  }

  //regular expression -known as regex
  @Then("""^It landed on the '([^\"]*)' page$""")
  def LandThePage(page: String): Assertion = {
    val landingpage: String = page match {
      //class --> new, otherwise page object dont need new
      case "Inventory" => Inventorypage.url
      case _ => throw new Exception(s"Page [${page}] is not found")
    }

    currentUrl should be(landingpage)
  }

  @And("""^the user adds '([^\"]*)' to the shopping cart$""")
  def addTheItemToTheBasket(product: String): Unit = {
    val list: List[String] = findAll(cssSelector(".inventory_item_name")).map(_.underlying.getText).toList
    //fold -translate to single result- transformation
    val listIndex: Int = list.zipWithIndex.foldLeft(0)((itemIdx, item) =>
      // access element in tuple
      //      product match {
      //        case item._1 => item._2
      //        case _ => itemIdx
      //      }
      if (product == item._1) item._2 else itemIdx
    )

//    val button: CssSelectorQuery = cssSelector(s"#inventory_container > div > div:nth-child(${listIndex + 1}) > div.pricebar > button")
    val button: CssSelectorQuery = cssSelector(s"div.inventory_item:nth-child(${listIndex + 1}) button")
    clickOn(button)
  }

  @Then("""^the user go to basket and check the '([^\"]*)' to the shopping cart$""")
   def checkTheItemIntheBasket(product: String): Assertion = {
    val basketItemId = id("shopping_cart_container")
    clickOn(basketItemId)

    val cartItem = cssSelector("div.inventory_item_name").webElement.getText
    product should be(cartItem)
  }



  @And("^log in the page via entering the following details$")
   def loginMethod(table: DataTable) ={

    WebPage.loginStepsTable(table)



  }

}
