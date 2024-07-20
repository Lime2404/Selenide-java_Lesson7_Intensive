package Lesson7.pageObjects;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$x;

public class ProgressBarPage {
    private final WebElement startButton = $x("//button[@class=\"mt-3 btn btn-primary\"]");

    private final WebElement ariaValue = $x("//div[@class='progress-bar bg-success']");

    public ProgressBarPage(String url) {
        Selenide.open(url);
    }

    public void clickStartButton() {
        startButton.click();
    }

    public WebElement getStartButton() {
        return startButton;
    }

    public String checkAreaValue() {
        return ariaValue.getAttribute("aria-valuenow");
    }
}
