package stepdef



import java.io.File

import io.cucumber.scala.{EN, ScalaDsl}
import com.typesafe.scalalogging.LazyLogging
import io.cucumber.java.{After, Before}
import io.cucumber.scala.Scenario
import pages.BasePage
import util.{ScreenshotUtility, SingletonDriver}
import io.cucumber.scala.Scenario
import org.apache.commons.io.FileUtils
import org.openqa.selenium.{OutputType, TakesScreenshot, WebDriver}

class BaseSteps extends ScalaDsl with EN with LazyLogging with BasePage  {

  // hooks in Cucumber is the code block which can have optional definition in step definition file (with each scenario) by using the annotation @Before and @After.

  Before {
    def initialise() {
      logger.info("Start running the test")
    }
    initialise()
  }


  private def takeScreenshot(scenario: Scenario, s: String, dr: WebDriver with TakesScreenshot): Unit = {

    val name = scenario.getName

    if (!new java.io.File(s"./target/screenshots/$name$s.png").exists) {
      dr.manage().window().maximize()
      val scr = dr.getScreenshotAs(OutputType.FILE)
      FileUtils.copyFile(scr, new File(s"./target/screenshots/$name$s.png"))
//      val byteFile = dr.getScreenshotAs(OutputType.BYTES)
//      scenario.attach(byteFile, "image/png","")
    }
  }

  After { scenario: Scenario =>
    def teardown(result: Scenario) {

      //implement a screen capture method
      if (result.isFailed) {
        logger.info("fail")
        SingletonDriver.getInstance() match {
          case a: TakesScreenshot =>
        takeScreenshot (scenario, "-page-on-failure",a )
        println (s"Failure Page : Failing")
        }

      } else
        logger.info("pass")
        quit()
    }

    teardown(scenario)
  }




}
