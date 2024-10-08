package Lesson7.pageObjects;

import Lesson7.utils.CalendarComponent;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {

    CalendarComponent calendarComponent = new CalendarComponent();

    //locators
    private SelenideElement firstNameInput = $(byId("firstName")),
            lastNameInput = $(byId("lastName")),
            userEmailInput = $(byId("userEmail")),
            genderWrapper = $(byId("genterWrapper")),
            mobilePhoneInput = $(byId("userNumber")),
            calendar = $(byId("dateOfBirthInput")),
            subjectsInput = $(byId("subjectsInput")),
            pictureUploadButton = $(byId("uploadPicture")),
            currentAddressInput = $(byId("currentAddress")),
            stateDropDown = $(byId("state")),
            cityDropDown = $(byId("city")),
            cityDropDownWrapper = $(byId("stateCity-wrapper")),
            hideAdsButton = $(byId("close-fixedban")),
            submitButton = $(byId("submit"));

    private String relativeUrl = "/automation-practice-form";

    public RegistrationPage setFirstName(String firstName) {
        firstNameInput.sendKeys(firstName);
        return this;
    }

    public RegistrationPage setLastName(String lastName) {
        lastNameInput.sendKeys(lastName);
        return this;
    }

    public RegistrationPage setUserEmail(String mail) {
        userEmailInput.sendKeys(mail);
        return this;
    }

    public RegistrationPage chooseGenderInWrapper(String gender) {
        genderWrapper.$(byText(gender)).click();
        return this;
    }

    public RegistrationPage setMobilePhone(String mobilePhone) {
        mobilePhoneInput.sendKeys(mobilePhone);
        return this;
    }

    public RegistrationPage setBirthDate(String year, String month, String day) {
        calendar.click();
        calendarComponent.setDate(year, month, day);
        return this;
    }

    public RegistrationPage setSubjectBySendKeys(String subject) {
        subjectsInput.sendKeys(subject);
        subjectsInput.pressEnter();
        return this;
    }

    public RegistrationPage setHobby(String hobby) {
        $(byText(hobby)).click();
        return this;
    }

    public RegistrationPage uploadPicture(String fileName) {
        pictureUploadButton.uploadFromClasspath(fileName);
        return this;
    }

    public RegistrationPage setCurrentAddress(String currentAddress) {
        if (!(currentAddressInput.isDisplayed())) {
            hideAds();
        }
        currentAddressInput.sendKeys(currentAddress);
        return this;
    }

    public RegistrationPage selectStateFromDropDownList(String state) {
        if (!(stateDropDown.isDisplayed())) {
            hideAds();
        }
        stateDropDown.scrollTo().click();
        $(byText(state)).click();
        return this;
    }

    public RegistrationPage selectCityFromDropDownList(String city) {
        if (!(cityDropDown.isDisplayed())) {
            hideAds();
        }
        cityDropDown.click();
        cityDropDownWrapper.$(byText(city)).click();
        return this;
    }

    public void hideAds() {
        if (hideAdsButton.isDisplayed()) {
            hideAdsButton.click();
        }
    }

    public RegistrationPage clickSubmitButton() {
        submitButton.click();
        return this;
    }
}