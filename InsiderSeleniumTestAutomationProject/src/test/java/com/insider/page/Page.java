package com.insider.page;

import com.insider.driver.Driver;
import com.insider.methods.Methods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Page {

    Methods methods;
    WebDriver driver;

    public Page() {
        methods = new Methods();
        this.driver = Driver.driver;
    }

    public void approveCookies() {
        methods.waitPageLoadCompleteJs();
        methods.isElementVisible(By.cssSelector("a#wt-cli-accept-all-btn"));
        methods.click(By.cssSelector("a#wt-cli-accept-all-btn"));
        methods.waitByMilliSeconds(500);
    }

    public void checkHomePage( String url ) {

        methods.doesUrlEqual(url);

    }

    public void checkCareerPage() {

        methods.click(By.xpath("//span[text()='More']"));
        methods.click(By.xpath("//h5[text()='Careers']"));
        methods.doesUrlEqual("https://useinsider.com/careers/");
        methods.isElementVisible(By.xpath("//h3[contains(text(),'Locations ')]"));
        methods.isElementVisible(By.xpath("//a[contains(text(),'teams')]"));
        methods.isElementVisible(By.xpath("//h2[text()='Life at Insider']"));

    }

    public void filterQAJobs() {

        methods.scrollWithJavaScript(By.xpath("//a[text()='See all teams']"));
        methods.click(By.xpath("//a[text()='See all teams']"));

        methods.scrollWithJavaScript(By.xpath("//h3[text()='Quality Assurance']"));
        methods.click(By.xpath("//h3[text()='Quality Assurance']"));

        methods.click(By.xpath("//a[text()='See all QA jobs']"));

        methods.click(By.xpath("(//span[@class='select2-selection select2-selection--single'])[1]"));
        methods.click(By.xpath("//li[text()='Istanbul, Turkey']"));

        methods.isElementVisible(By.xpath("//span[@title='Quality Assurance']"));
        methods.waitByMilliSeconds(500);

    }

    public void checkQAAllJobs() {

        methods.waitByMilliSeconds(500);
        List<WebElement> items = driver.findElements(By.xpath("//div[@class='position-list-item-wrapper bg-light']"));

        for ( int i = 1 ; i <= items.size() ; i++ ) {
            String str = "(//div[@class='position-list-item-wrapper bg-light'])[" + ( i ) + "]";
            methods.scrollWithJavaScript(By.xpath("//h3[@class='mb-0']"));
            methods.mouseOver(driver.findElement(By.xpath(str)));

            String positionContainsStr = "(//p[@class='position-title font-weight-bold' and contains(text(),'Quality Assurance')])[" + ( i ) + "]";
            methods.isElementVisible(By.xpath(positionContainsStr));

            String departmentContainsStr = "(//span[@class='position-department text-large font-weight-600 text-primary' and contains(text(),'Quality Assurance')])[" + ( i ) + "]";
            methods.isElementVisible(By.xpath(departmentContainsStr));

            String locationContainsStr = "(//div[@class='position-location text-large' and contains(text(),'Istanbul, Turkey')])[" + ( i ) + "]";
            methods.isElementVisible(By.xpath(locationContainsStr));

            methods.mouseOver(driver.findElement(By.xpath(positionContainsStr)));
            String applyNowStr = "(//a[text()='Apply Now'])[" + ( i ) + "]";
            WebElement element = driver.findElement(By.xpath(applyNowStr));
            element.isDisplayed();

        }

    }

    public void checkLeverApplicationFormPage() {

        methods.waitBySeconds(1);
        WebElement element = driver.findElement(By.xpath("(//div[@class='position-list-item-wrapper bg-light'])[1]"));
        methods.mouseOver(element);
        methods.click(By.xpath("(//a[text()='Apply Now'])[1]"));
        methods.waitPageLoadCompleteJs();
        methods.switchTab(1);
        methods.doesStartsWithUrl("https://jobs.lever.co/");
        methods.isElementVisible(By.xpath("//a[text()='Apply for this job']"));

    }

}
