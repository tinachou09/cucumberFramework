package pages

import org.openqa.selenium.By

object WebPage extends BasePage {



  private val signInButton = driver.findElement(By.xpath("//*[@id=\"cms-body-content\"]/nav/div[1]/a/span")
  )

  def clickOnSignIn()= clickOn(signInButton)
}
