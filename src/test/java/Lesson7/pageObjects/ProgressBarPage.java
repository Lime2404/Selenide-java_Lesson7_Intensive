package Lesson7.pageObjects;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$x;

public class ProgressBarPage {

    // заменить на селенид Элемента

//    private final WebElement progressBar =  $x("//div[@class=\"progress-bar bg-info\"]");
    private final WebElement progressBarElement =  $x("//div[@class=\"progress-bar bg-info\"]");
    private final WebElement progressBarSuccess =  $x("//div[@class=\"progress-bar bg-success\"]");
    private final WebElement startButton = $x("//button[@class=\"mt-3 btn btn-primary\"]");
    private final WebElement resetButton = $x("//button[@class=\"mt-3 btn btn-primary\"]");
////button[contains(@class, 'mt-3 btn btn-primary' ) and contains(@id, 'resetButton') and contains(@type, 'button')]

    public ProgressBarPage(String url){
        Selenide.open(url);
    }

    public void clickStartButton(){ startButton.click();}

    public String getAreaValue(){ return progressBarElement.getAttribute("aria-valuenow");}

    public String getAreaSuccessValue(){ return progressBarSuccess.getAttribute("aria-valuenow");}

    public String getAriaValueMax(){
        return progressBarElement.getAttribute("aria-valuemax");
    }

    public WebElement getStartButton(){ return startButton; }

    public WebElement getResetButton() {
        return resetButton;
    }

    public static void scrollPageDown() {
        JavascriptExecutor js = (JavascriptExecutor) Selenide.webdriver().object();
        // Прокрутите на 1000 пикселей вниз
        js.executeScript("window.scrollBy(0, 1000);");
    }

    public void clickResetButton(){
        progressBarElement.click();
    }



}
