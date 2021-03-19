package stepdef



import com.typesafe.scalalogging.LazyLogging
import io.cucumber.scala.{EN, ScalaDsl, Scenario}
import org.openqa.selenium.{JavascriptExecutor, TakesScreenshot}
import pages.BasePage
import util.SingletonDriver
import util.ScreenshotUtility._

class BaseSteps extends ScalaDsl with EN with LazyLogging with BasePage  {

  // hooks in Cucumber is the code block which can have optional definition in step definition file (with each scenario) by using the annotation @Before and @After.

  Before {
    def initialise() {
      logger.info("Start running the test")
      driver.manage().window().maximize()
    }
    initialise()
  }


//  private def takeScreenshot(scenario: Scenario, s: String, dr: WebDriver with TakesScreenshot): Unit = {
//z
//    val name = scenario.getName
//
//    if (!new java.io.File(s"./target/screenshots/$name$s.png").exists) {
//      dr.manage().window().maximize()
//      val scr = dr.getScreenshotAs(OutputType.FILE)
//      FileUtils.copyFile(scr, new File(s"./target/screenshots/$name$s.png"))
////      val byteFile = dr.getScreenshotAs(OutputType.BYTES)
////      scenario.attach(byteFile, "image/png","")
//    }
//  }

  After { scenario: Scenario =>
    def teardown(result: Scenario) {

      //implement a screen capture method
      if (result.isFailed) {
        logger.info("fail")
        //mixing type
        SingletonDriver.getInstance() match {
          case adriver: TakesScreenshot with JavascriptExecutor =>
          takesScreenshot(scenario)(adriver)
        }

      } else
        logger.info("pass")
        quit()
    }

    teardown(scenario)
  }




}
