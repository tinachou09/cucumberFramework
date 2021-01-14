package pages

import io.cucumber.scala.{EN, ScalaDsl}
import org.openqa.selenium.WebDriver
import org.scalatest.{Assertion, Matchers}
import org.scalatestplus.selenium.WebBrowser
import util.SingletonDriver


trait BasePage extends EN with ScalaDsl with WebBrowser with Assertion with Matchers {

  implicit val driver: WebDriver = SingletonDriver.getInstance()

  val url: String = ""
  val title: String = ""


}
