package com.insider.test;

import com.insider.driver.Driver;
import com.insider.page.Page;
import org.junit.Test;

public class InsiderTest extends Driver {

    @Test
    public void exampleTest() {

        Page page = new Page();

        page.approveCookies();
        page.checkHomePage("https://useinsider.com/");
        page.checkCareerPage();
        page.filterQAJobs();
        page.checkQAAllJobs();
        page.checkLeverApplicationFormPage();

    }

}
