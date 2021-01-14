package stepdef

import pages.{BasePage, WebPage}



class loginStep extends BasePage {

   Given("^the user navigate to the page$"){

     driver.navigate().to(url)
  }

  And("^the user clicks the signIn button$"){


  }
}
