package Lesson7.pageObjects;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.Random;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ColorCompletionPage {

    private final static String BASE_URL = "https://demoqa.com/";
    public ColorCompletionPage(String url){
        Selenide.open(url);
    }

    public void randomizer( String[] colors) {

        Random random = new Random();
        int firstIndex = random.nextInt(colors.length);
        int secondIndex;
        do {
            secondIndex = random.nextInt(colors.length);
        } while (secondIndex == firstIndex);

        String firstColor = colors[firstIndex];
        String secondColor = colors[secondIndex];

        SelenideElement inputElement = $(By.xpath("//input[@id='autoCompleteMultipleInput']"));
        inputElement.setValue(firstColor).pressEnter();
        inputElement.setValue(secondColor).pressEnter();
    }
    public static void scrollPageDown() {
        JavascriptExecutor js = (JavascriptExecutor) Selenide.webdriver().object();
        // Прокрутите на 1000 пикселей вниз
        js.executeScript("window.scrollBy(0, 1200);");
    }
}
