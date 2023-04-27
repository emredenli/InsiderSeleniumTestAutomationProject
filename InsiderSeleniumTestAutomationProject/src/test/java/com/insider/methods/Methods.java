package com.insider.methods;

import com.insider.driver.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Methods {

    WebDriver driver;
    FluentWait<WebDriver> wait;

    JavascriptExecutor jsdriver;
    long pollingEveryValue;

    Logger logger = LogManager.getLogger(Methods.class);

    public Methods() {

        driver = Driver.driver;
        wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NoSuchElementException.class);
        jsdriver = (JavascriptExecutor) driver;

    }

    public WebElement findElement(By by) {

        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void click(By by) {
        waitByMilliSeconds(500);
        findElement(by).click();
        System.out.println("( " + by + " ) element is clicked.");
    }

    public void waitBySeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitByMilliSeconds(long milliSeconds){

        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isElementVisible(By by) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            logger.info("true");
            System.out.println("( " + by + " ) element is visible.");
            return true;
        } catch (Exception e) {
            logger.info("false" + e.getMessage());
            return false;
        }
    }

    public void scrollWithAction(By by) {
        Actions actions = new Actions(driver);
        actions.moveToElement(findElement(by)).build().perform();
    }


    public void scrollWithJavaScript(By by) {
        waitByMilliSeconds(500);
        jsdriver.executeScript("arguments[0].scrollIntoView();", findElement(by));
    }

    public void doesUrlEqual ( String url ) {
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = url;
        try {
            System.out.println("currentUrl  : " + currentUrl);
            System.out.println("expectedUrl : " + expectedUrl);
            Assert.assertEquals(currentUrl, expectedUrl);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        System.out.println("The incoming url is equal to the expected url.");
    }

    public void waitPageLoadComplete(FluentWait<WebDriver> fluentWait) {

        ExpectedCondition<Boolean> expectation = driver -> jsdriver
                .executeScript("return document.readyState;").toString().equals("complete");
        try {
            fluentWait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    public void waitPageLoadCompleteJs() {

        waitPageLoadComplete(setFluentWait(10));
    }

    public FluentWait<WebDriver> setFluentWait(long timeout){

        FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
        fluentWait.withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(pollingEveryValue))
                .ignoring(NoSuchElementException.class);
        return fluentWait;
    }

    public void scrollElement(WebElement element) {

        jsdriver.executeScript("arguments[0].scrollIntoView();", element);

    }

    public void mouseOver(WebElement webElement) {

        waitByMilliSeconds(500);
        String javaScript = "var evObj = document.createEvent('MouseEvents');" +
                "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
                "arguments[0].dispatchEvent(evObj);";


        ((JavascriptExecutor)driver).executeScript(javaScript, webElement);

    }

    public void switchTab(int tabNumber){

        driver.switchTo().window(listTabs().get(tabNumber));
    }

    public List<String> listTabs(){
        List<String> list = new ArrayList<String>();
        for (String window: driver.getWindowHandles()){
            list.add(window);
        }
        return list;
    }

    public void doesStartsWithUrl(String url) {

        boolean bln;
        String currentUrl = driver.getCurrentUrl();
        if ( currentUrl.startsWith(url) )
            bln = true;
        else
            bln = false;

        Assert.assertTrue("Starts with URL", bln);

    }

}
