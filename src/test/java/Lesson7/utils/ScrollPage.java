package Lesson7.utils;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.JavascriptExecutor;

public class ScrollPage {
    public static void scrollPageDown() {
        JavascriptExecutor js = (JavascriptExecutor) Selenide.webdriver().object();
        js.executeScript("window.scrollBy(0, 1000);");
    }
}
