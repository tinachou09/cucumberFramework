package pages

import io.cucumber.datatable.DataTable
import org.openqa.selenium.By
import util.ScenarioContext


object WebPage extends BasePage {

  def clickOnLoginButton()= id("login-button").webElement.click()

  val usernameField = id("user-name")
  val passwordField = id("password")
  def enterUsername(input: String) = usernameField.webElement.sendKeys(input)
  def enterPassword(input: String) = passwordField.webElement.sendKeys(input)

  def loginStepsTable(table: DataTable) = {
    val row = table.asMaps().iterator()

    while(row.hasNext){
      val map = row.next()
      ScenarioContext.set("username", enterUsername(map.get("username")))
      ScenarioContext.set("password", enterPassword(map.get("password")))

    }




  }

}
