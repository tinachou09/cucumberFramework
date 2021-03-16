package stepdef

import io.cucumber.java.StepDefinitionAnnotation
import pages.BasePage
import io.cucumber.java.en.{And, Given, Then}
import org.scalatest.Assertion
import pages.Inventorypage


@StepDefinitionAnnotation
class loginStep extends BasePage {

  //the reason that need to import cucumber java.en is because  dependency mismatches (Cucumber - Content Assistance in Feature file not working)

  //a step definition : a java expression to link to Gherkin steps - it look for matching step to execute
  @Given("^the user navigate to the page$")
  def this_pre_condition = > {
    val url = "https://www.saucedemo.com/"
    goTo(url)
  }

  @And("log in the page via entering the username and password$")
  def logIn = > {
    //username
    val usernameFieid = id("user-name")
    val passwordField = id("password")

    def getUserNameValue: String = cssSelector(".login_credentials").webElement.getText

    //convert the paragraph by using a break line and , then it convert to array and then covert into a list
    val standardUsername: String = getUserNameValue.split("\n").toList.apply(1)
    usernameFieid.webElement.sendKeys(standardUsername)

    //password  -driver.findElement(By.className get replaced by cssSelector webbrowser
    def getPasswordValue: String = cssSelector("login_password").webElement.getText

    //convert the paragraph by using a break line and , then it convert to array and then covert into a list
    val password: String = getPasswordValue.split("\n").toList.apply(1)
    passwordField.webElement.sendKeys(password)

    clickOn("login-button")
  }

  //regular expression -known as regex
  @Then("""^It landed on the '([^\"]*)' page$""")
  def LandThepage(page: String): Assertion = {
    val landingpage: String = page match {
      //class --> new, otherwise page object dont need new
      case "Inventory" => new Inventorypage().url
      case _ => throw new Exception(s"Page [${page}] is not found")
    }

    currentUrl should be (landingpage)
  }


}
