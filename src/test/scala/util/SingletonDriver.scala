package util

import org.openqa.selenium.chrome.{ChromeDriver, ChromeOptions}
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.{MutableCapabilities, WebDriver}

object SingletonDriver  {


  private var instanceOption: Option[WebDriver] = None

  def getInstance(customOption: Option[MutableCapabilities] = None) : WebDriver = {
    if (instanceOption.isDefined && instanceOption.get.asInstanceOf[RemoteWebDriver].getSessionId == null)
      instanceOption = None

    instanceOption getOrElse initialiseBrowser()
  }

  private def initialiseBrowser(): WebDriver = {
    val browser: Option[String] = Option(Configuration.browserType)
    val driver = createBrowser(browser)
    instanceOption = Some(driver)
    driver
  }

  def createBrowser(browserType: Option[String]): WebDriver =
    browserType match {
      case Some("chrome") => chromeInstance(chromeOptions())
      case None =>   chromeInstance(chromeOptions())
    }

  private def chromeInstance(option: ChromeOptions): WebDriver = new ChromeDriver(option)

  def chromeOptions(): ChromeOptions = {
    val defaultOptions = new ChromeOptions()
    defaultOptions.addArguments("start-maximized")
    defaultOptions
  }
}

