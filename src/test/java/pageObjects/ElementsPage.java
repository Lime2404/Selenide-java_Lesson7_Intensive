package pageObjects;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.WebElement;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ElementsPage {

//    private final WebElement message = $x("//div[@id='Ad.Plus-970x250-1']/../text()");
    private final ElementsCollection elementsList = $$x("//ul[@class='menu-list']");

    public ElementsCollection getElementsList() {
        return elementsList;
    }

    public WebElement getElements(){
        return elementsList.first();
    }
}