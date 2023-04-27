package com.insider.driver;

import com.insider.helpers.ProjectConst;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Driver {

   public static WebDriver driver;

   @Before
   public void setUp() {

      System.setProperty(ProjectConst.WEBDRIVER_CHROME_DRIVER, ProjectConst.WEBDRIVER_CHROME_DRIVER_PATH);
      ChromeOptions chromeOptions = new ChromeOptions();
      chromeOptions.addArguments("--disable-notifications");
      chromeOptions.addArguments("--disable-gpu");
      chromeOptions.addArguments("--no-sandbox");
      chromeOptions.addArguments("disable-plugins");
      chromeOptions.addArguments("disable-popup-blocking");
      chromeOptions.addArguments("disable-translate");
      chromeOptions.addArguments("disable-extensions");
      chromeOptions.setExperimentalOption("w3c", false);

      driver = new ChromeDriver(chromeOptions);
      driver.manage().window().maximize();
      driver.get(ProjectConst.PLATFORM_URL);

   }

   @After
   public void tearDown() {

      if (driver != null) {
         driver.close();
         driver.quit();
      }

   }

}
