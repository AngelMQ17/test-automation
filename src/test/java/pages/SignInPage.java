package pages;

import model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasePage {

    @FindBy(xpath = "//a[@href='#/login']")
    private WebElement loginLink;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement signInButton;

    @FindBy(xpath = "//div[contains(text(), 'Logged in successfully')]")
    private WebElement userLoggedInMessage;

    @FindBy(xpath = "//div[contains(@class,'close-button')]")
    private WebElement closePopupButton;

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public void signIn(User user) {
        loginLink.click();
        typeInto(emailInput, user.getEmail());
        typeInto(passwordInput, user.getPassword());
        signInButton.click();
    }

    public boolean checkUserLoggedIn() {
        return userLoggedInMessage.isDisplayed();
    }

    public void closePopUp() {
        closePopupButton.click();
    }
}
