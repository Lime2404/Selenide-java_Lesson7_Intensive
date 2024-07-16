package Lesson7.SelenideTests;

import Lesson7.core.BaseTest;
import Lesson7.pageObjects.*;
import Lesson7.utils.ScrollPage;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.HashMap;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class DemoqaTest extends BaseTest {
    private final static String BASE_URL = "https://demoqa.com/";

//    7. Output all actions to the console using the Log4j library
    private static final Logger logger = LogManager.getLogger(DemoqaTest.class);


// 2.Develop smoke autotests for the main page

    /**
     * Assert that all element can be found on the main page
     */
    @Test
    @Tag("Test1")
    public void assertMainPageElements() {
        String[] expectedElements = {"Elements", "Forms", "Alerts, Frame & Windows", "Widgets", "Interactions", "Book Store Application"};
        MainPage mainPage = new MainPage(BASE_URL);
        String names = mainPage.returnCard();
        System.out.println(names);
        String[] actualList = names.split("\n+");
        Assertions.assertArrayEquals(expectedElements, actualList);
        logger.info("Assert returns 200");
    }

    /**
     * Assert that the first element from the collection consist of the expected entities list
     */
    @Test
    @Tag("Test2")
    public void verifyElementPageEntities() {
        String[] expectedList = {"Text Box", "Check Box", "Radio Button", "Web Tables", "Buttons", "Links", "Broken Links - Images", "Upload and Download", "Dynamic Properties"};
        MainPage mainPage2 = new MainPage(BASE_URL);
        ScrollPage.scrollPageDown();
        mainPage2.clicOnElements();

        ElementsPage sideBarElements = new ElementsPage();
        String[] actualList = sideBarElements.getElementsList().first().getText().split("\n+");
//        System.out.println("Expected :" + Arrays.stream(expectedList).toList());
//        System.out.println("Actual :" + Arrays.stream(actualList).toList());
        Assertions.assertArrayEquals(expectedList, actualList);
        String expectedName = "Buttons";
        String names = sideBarElements.getElements().getText();
        boolean contains = names.contains(expectedName);
        Assertions.assertTrue(contains);
        logger.info("Assert returns 200");
    }

// 3. Using selenium webdriver to develop a test that handles the Progress Bar
//element (waiting for it to complete)

    /**
     * Assert that progress bar is complete
     */

    @Test
    @Tag("Test3")
    public void getStartButtonStatus() {
        ProgressBarPage progressBar = new ProgressBarPage(BASE_URL + "progress-bar");
        ScrollPage.scrollPageDown();
        progressBar.clickStartButton();
        while (progressBar.getStartButton().getText().equals("Stop")) {
            System.out.println("Do not press " + progressBar.getStartButton().getText());
        }

        if (progressBar.getStartButton().getText().equals("Reset")) {
            System.out.println("The status of the button turned to = " + progressBar.getStartButton().getText() + ", progress bar load is 100%");
        }
        logger.info("The status of the button turned to = " + progressBar.getStartButton().getText() + ", progress bar load is 100%");
    }

// 4. Develop a test that selects values from the list //[Red, Green, Purple, Indigo] on the page https://demoqa.com/autocomplete
//     "Type multiple color names". Important, 2 different values should //be selected at each run (use the Random function).
    /*
     * Select color randomly
     */
    @Test
    @Tag("Test4")
    public void pickColorFromList() {
        ColorCompletionPage colorCompletionPage = new ColorCompletionPage(BASE_URL + "auto-complete");
        String[] colors = {"Red", "Green", "Purple", "Indigo"};

        ScrollPage.scrollPageDown();
        colorCompletionPage.randomizer(colors);
        logger.info("Two random color sets have been selected");
        Selenide.sleep(10000L);
    }

//    @Test
    @org.junit.jupiter.api.Test
    @Tag("Test5")
    void fillFormTest() {

        String
                baseUrl = "https://demoqa.com/automation-practice-form",
                firstName = "Ivan",
                lastName = "Ivanov",
                email = "Ivan.Ivanov@mail.ru",
                genderRadioPick = "Male",
                mobilePhone = "7123123456",
                birthdayYear = "1987",
                birthdayMonth = "April",
                subject1 = "Physics",
                subject2 = "Commerce",
                hobbiesSport = "Sports",
                currentAddress = "sample address",
                fileName = "img/Solveva.png",
                state = "NCR",
                city = "Delhi";

        Selenide.open(baseUrl);
        SelenideElement hideAdsButton = $(byId("close-add"));
        if (hideAdsButton.isDisplayed()) {
            hideAdsButton.click();
        }

        $(byId("firstName")).sendKeys(firstName);
        $(byId("lastName")).sendKeys(lastName);
        $(byId("userEmail")).sendKeys(email);
        ScrollPage.scrollPageDown();
        $(byText(genderRadioPick)).click();
        $(byId("userNumber")).sendKeys(mobilePhone);
        ScrollPage.scrollPageDown();

        $(byId("dateOfBirthInput")).click();
        $(byCssSelector(".react-datepicker__year-select")).selectOptionByValue(birthdayYear);
        $(byCssSelector(".react-datepicker__month-select")).selectOption(birthdayMonth);
        $(byCssSelector(".react-datepicker__day.react-datepicker__day--014")).click();

        $(byId("subjectsInput")).sendKeys("P");
        ScrollPage.scrollPageDown();
        $(byText(subject1)).click();
        $(byId("subjectsInput")).sendKeys(subject2);
        $(byId("subjectsInput")).pressEnter();


        $(byText(hobbiesSport)).click();

        $(byId("uploadPicture")).uploadFromClasspath(fileName);

        $(byId("currentAddress")).sendKeys(currentAddress);
        ScrollPage.scrollPageDown();

        $(byId("state")).scrollTo().click();
        $(byText(state)).click();
        $(byId("city")).click();
        $(byId("stateCity-wrapper")).$(byText(city)).click();
        $(byId("submit")).click();

        HashMap<String, String> formSubmit = new HashMap<>() {{
            put("Student Name", firstName + " " + lastName);
            put("Email", email);
            put("Gender", genderRadioPick);
            put("Mobile", mobilePhone);
            put("Date of Birth", "14 April,1987");
            put("Subjects", subject1 + ", " + subject2);
            put("Hobbies", hobbiesSport);
            put("Picture", Paths.get(fileName).getFileName().toString());
            put("Address", currentAddress);
            put("State and City", state + " " + city);
        }};
        logger.info("The form is fully filled");
        Selenide.sleep(10000L);
    }
}
