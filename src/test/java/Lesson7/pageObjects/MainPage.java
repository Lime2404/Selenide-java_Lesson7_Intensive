package Lesson7.pageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.JavascriptExecutor;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Главная страница сайта demoqa.com
 */

public class MainPage {

    private final ElementsCollection categoryCards = $$x("//div[@class='category-cards']");

    private final SelenideElement elementsButton = $x("//a[@href='https://ultimateqa.com/consulting/']");

    private final SelenideElement elements = $x("//div[@class='card mt-4 top-card'][1]");

    public MainPage(String url) {
        Selenide.open(url);
    }

    public void clicOnElements() {
        elements.click();
    }

    public static void scrollPageDown() {
        JavascriptExecutor js = (JavascriptExecutor) Selenide.webdriver().object();
        // Прокрутите на 1000 пикселей вниз
        js.executeScript("window.scrollBy(0, 1000);");
    }

//    public void clicOnElement() {
//        elementsButton.click(); // но этого этапа мало. Для начала нам надо откоыть браузер и открыт страницу
//    }

    public String returnCard() {
        return categoryCards.first().getText();
    }


//    public ElementsCollection getCategoryCards() {
//        return categoryCards;
//    }
//
//    public SelenideElement getElementsButton() {
//        return elementsButton;
//    }
//
//    public SelenideElement getElements() {
//        return elements;
//    }
}
