package util

import java.awt.image.BufferedImage
import java.io.{ByteArrayOutputStream, File, FileOutputStream}

import org.openqa.selenium.{JavascriptExecutor, OutputType, TakesScreenshot, WebDriver, WebDriverException}
import io.cucumber.scala.Scenario
import ru.yandex.qatools.ashot.shooting.{ShootingStrategies, ShootingStrategy}
import ru.yandex.qatools.ashot.{AShot, Screenshot}
import io.cucumber.scala.Scenario
import javax.imageio.ImageIO
import org.apache.commons.io.FileUtils
import ru.yandex.qatools.ashot.util.ImageTool



object ScreenshotUtility {


  def takesScreenshot(currentScenario: Scenario)(implicit wd: WebDriver with TakesScreenshot with JavascriptExecutor): Any ={
    try {
      val name = currentScenario.getName
      //asinstance of => cast an object from one type to another
      //in this case, the webdriver is cast to an instance of take screenshot-Capture the screenshot and store it in the specified location.
//      val screenshot = wd.asInstanceOf[TakesScreenshot].getScreenshotAs(OutputType.BYTES)
//      val js: JavascriptExecutor = wd.asInstanceOf[JavascriptExecutor]

      //https://javascript.info/size-and-scroll-window
      //https://www.toolsqa.com/selenium-webdriver/screenshot-in-selenium/
      //the root document element is document.documentElement, enclose all contnet, (scroll height, when the document full size, client height ->height of current window)
//      val scrollBarExists: Boolean = (wd.executeScript("return document.documentElement.scrollHeight>document.documentElement.clientHeight;")).asInstanceOf[Boolean]
//      if (scrollBarExists) {
        //using Ashot library - weddriver screenshot utility -take screenshot of web-element on different platform eg mobile, web , decorates screenshot, comparison
        //Will scroll viewport while shooting

        val capture: Screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(ShootingStrategies.scaling(2f),1000)).takeScreenshot(wd)

//        currentScenario.attach(ImageTool.toByteArray(capture), "image/png", "Image capture")
//        val captureFile = new File("image.png")
//        val imageFile = new File(s"./target/screenshots/$name.jpg")
//        val imageOutput = ImageIO.createImageOutputStream(new FileOutputStream(imageFile))
//        def writer = ImageIO.getImageWritersByFormatName("JPF").next()
//         writer.setOutput(imageOutput)
//         ImageIO.write()

        val boas = new ByteArrayOutputStream()
        println("screenshot")
        ImageIO.write(capture.getImage(), "PNG", new File(s"./target/screenshots/$name.png"))


//        FileUtils.copyFile(screen, new File(s"./target/screenshots/$name.png"))
//      } else {
//        val screenshot: Array[Byte] = wd.asInstanceOf[TakesScreenshot].getScreenshotAs(OutputType.BYTES)
//        currentScenario.attach(screenshot, "image/png", "Screenshot")
//        val screenshot: File = wd.getScreenshotAs(OutputType.FILE)
//        FileUtils.copyFile(screenshot, new File("target/screenshots"))
//      }
      } catch {
        case somePlatformNotSupportScreenshot: WebDriverException =>
        System.err.printf(somePlatformNotSupportScreenshot.getMessage + "/n")
      }

    }

}
