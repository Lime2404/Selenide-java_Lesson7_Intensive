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

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class DemoqaTest extends BaseTest {
    private final static String BASE_URL = "https://demoqa.com/";

// 7. Output all actions to the console using the Log4j library
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
        System.out.println("Below is the list of elements "+ names);
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
        MainPage mainPage = new MainPage(BASE_URL);
        ScrollPage.scrollPageDown();
        mainPage.clicOnElements();

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
//            System.out.println("Do not press " + progressBar.getStartButton().getText());
        }

        if (progressBar.getStartButton().getText().equals("Reset")) {
            System.out.println("The status of the button turned to = " + progressBar.getStartButton().getText() + ", progress bar load is 100%");
        }
        Assertions.assertTrue(progressBar.checkAreaValue().equals("100"));
        logger.info("The status of the button turned to = " + progressBar.getStartButton().getText() + ", progress bar load is 100%");
    }

// 4. Develop a test that selects values from the list //[Red, Green, Purple, Indigo] on the page https://demoqa.com/autocomplete
//     "Type multiple color names". Important, 2 different values should //be selected at each run (use the Random function).
    /**
     * Select color randomly
     */
    @Test
    @Tag("Test4")
    public void pickColorFromList() {
        ColorCompletionPage colorCompletionPage = new ColorCompletionPage(BASE_URL + "auto-complete");
        String[] colors = {"Red", "Green", "Purple", "Indigo", "White", "Yellow"};

        ScrollPage.scrollPageDown();
        colorCompletionPage.randomizer(colors);
        Assertions.assertEquals(colorCompletionPage.getFirstColorPlaceholder(), colorCompletionPage.firstColorSelected());
        Assertions.assertEquals(colorCompletionPage.getSecondColorPlaceholder(), colorCompletionPage.secondColorSelected());
        logger.info("Two random color sets have been selected");
    }

//  5. Using selenium webdriver to develop an autotest that fills out a form on the page
    /**
     * Fill in the form
     */
    @org.junit.jupiter.api.Test
    @Tag("Test5")
    void fillFormTest() {

        String
                baseUrl = "https://demoqa.com/automation-practice-form#google_vignette",
                firstName = "Ivan",
                lastName = "Ivanov",
                email = "Ivan.Ivanov@mail.ru",
                genderRadioPick = "Male",
                mobilePhone = "7123123456",
                birthdayYear = "1987",
                birthdayMonth = "April",
                birthdayDate = "24",
                subject1 = "Physics",
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

        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.setFirstName(firstName);
        registrationPage.setLastName(lastName);
        registrationPage.setUserEmail(email);
        ScrollPage.scrollPageDown();
        registrationPage.chooseGenderInWrapper(genderRadioPick);
        registrationPage.setMobilePhone(mobilePhone);
        ScrollPage.scrollPageDown();
        registrationPage.setBirthDate(birthdayYear, birthdayMonth, birthdayDate);
        registrationPage.setSubjectBySendKeys(subject1);
        ScrollPage.scrollPageDown();
        registrationPage.setHobby(hobbiesSport);
        registrationPage.uploadPicture(fileName);
        registrationPage.setCurrentAddress(currentAddress);
        ScrollPage.scrollPageDown();
        registrationPage.selectStateFromDropDownList(state);
        registrationPage.selectCityFromDropDownList(city);
        ScrollPage.scrollPageDown();
        registrationPage.clickSubmitButton();

        Assertions.assertTrue($(byCssSelector(".table-responsive")).isDisplayed());
        logger.info("The form is fully filled");
    }
}
