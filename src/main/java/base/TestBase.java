package base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pages.BookStorePage;
import pages.LoginPage;
import pages.ProfilePage;

public class TestBase {
    protected LoginPage loginPage;
    protected ProfilePage profilePage;
    protected BookStorePage bookStorePage;

    public TestBase(){
        loginPage = new LoginPage();
        profilePage = new ProfilePage();
        bookStorePage = new BookStorePage();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(){
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 20000;
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(){
        if (WebDriverRunner.hasWebDriverStarted())
            WebDriverRunner.closeWebDriver();
    }
}
