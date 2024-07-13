package Lesson7.core;

import Lesson7.properties.SelenideConfig;
import Lesson7.utils.SelenideLogListener;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

// 1. Create a Maven project (Java) using the following libraries:
abstract public class BaseTest {
    @BeforeAll

//    protected WebDriver driver;

    public static void setUp() {
        SelenideConfig.setup();
        SelenideLogger.addListener("SeleniumTests.SelenideLogListener", new SelenideLogListener());
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
//        Configuration.driverManagerEnabled = true; // deprecated
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;

    }

        @BeforeEach
         public void init(){
            setUp();
            System.out.println("Запускаемся");
        }

    @AfterEach
    public void tearDown(){
        Selenide.closeWebDriver();
    }
}
