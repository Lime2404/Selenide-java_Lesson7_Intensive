package Lesson7.pageObjects;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Random;

import static com.codeborne.selenide.Selenide.$;

public class ColorCompletionPage {

    SelenideElement firstPickedColor = $(By.xpath("//div[@class='css-1rhbuit-multiValue auto-complete__multi-value'][1]"));
    SelenideElement secondPickedColor = $(By.xpath("//div[@class='css-1rhbuit-multiValue auto-complete__multi-value'][2]"));

    public ColorCompletionPage(String url) {
        Selenide.open(url);
    }

    public void randomizer(String[] colors) {

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
    public WebElement firstColorSelected(){
        return firstPickedColor;
    }

    public WebElement secondColorSelected(){
        return firstPickedColor;
    }
}
