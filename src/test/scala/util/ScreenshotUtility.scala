package util

import java.io.File

import org.openqa.selenium.{JavascriptExecutor, OutputType, TakesScreenshot, WebDriver, WebDriverException}
import io.cucumber.scala.Scenario
import ru.yandex.qatools.ashot.shooting.{ShootingStrategies, ShootingStrategy}
import ru.yandex.qatools.ashot.{AShot, Screenshot}
import io.cucumber.scala.Scenario
import ru.yandex.qatools.ashot.util.ImageTool

import scala.reflect.internal.util.FileUtils


object ScreenshotUtility {
  //https://www.qaautomation.co.in/2018/11/full-page-screenshot-using-selenium-ashot.html

  def takesScreenshot(currentScenario: Scenario)(implicit wd: WebDriver with TakesScreenshot): Any ={
    try {
      //asinstance of => cast an object from one type to another
      //in this case, the webdriver is cast to an instance of take screenshot-Capture the screenshot and store it in the specified location.
      val screenshot = wd.asInstanceOf[TakesScreenshot].getScreenshotAs(OutputType.BYTES)
      val js: JavascriptExecutor = wd.asInstanceOf[JavascriptExecutor]
      //https://javascript.info/size-and-scroll-window
      //the root document element is document.documentElement, enclose all contnet, (scroll height, when the document full size, client height ->height of current window)
      val scrollBarExists: Boolean = (js.executeScript("return document.documentElement.scrollHeight>document.documentElement.clientHeight;")).asInstanceOf[Boolean]
      if (scrollBarExists) {
        //using Ashot library - weddriver screenshot utility -take screenshot of web-element on different platform eg mobile, web , decorates screenshot, comparison
        //Will scroll viewport while shooting
        val capture: Screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(wd)
        currentScenario.attach(ImageTool.toByteArray(capture), "image/png", "Image capture")
      } else {
        val screenshot: Array[Byte] = wd.asInstanceOf[TakesScreenshot].getScreenshotAs(OutputType.BYTES)
        currentScenario.attach(screenshot, "image/png", "Screenshot")
      }
      } catch {
        case somePlatformNotSupportScreenshot: WebDriverException =>
        System.err.printf(somePlatformNotSupportScreenshot.getMessage + "/n")
      }

    }

}
