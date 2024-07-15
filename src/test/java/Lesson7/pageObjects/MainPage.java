package Lesson7.pageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Главная страница сайта demoqa.com
 */

public class MainPage {

    private final ElementsCollection categoryCards = $$x("//div[@class='category-cards']");

    private final SelenideElement elements = $x("//div[@class='card mt-4 top-card'][1]");

    public MainPage(String url) {
        Selenide.open(url);
    }

    public void clicOnElements() {
        elements.click();
    }

    public String returnCard() {
        return categoryCards.first().getText();
    }
}
