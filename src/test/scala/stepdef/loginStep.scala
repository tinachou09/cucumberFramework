package stepdef

import io.cucumber.java.StepDefinitionAnnotation
import pages.BasePage
import io.cucumber.java.en.{And, Given}
import org.openqa.selenium.{By, WebElement}

@StepDefinitionAnnotation
class loginStep extends BasePage {

  //the reason that need to import cucumber java.en is because Cucumber - Content Assistance in Feature file not working

  @Given("^the user navigate to the page$")
  def this_pre_condition= > {
//    val url = ""
    driver.navigate().to(url)
  }

  @And("log in the page via entering the username and password$")
  def logIn = > {
    val usernameFieid = id("user-name")
    clickOn(usernameFieid)

    def getUserNameValue: String = driver.findElement(By.className("login_credentials")).getText

  }


}
