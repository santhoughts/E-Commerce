package PageObjects;

import ECommerce.AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage extends AbstractComponents {

    public WebDriver driver;


    public RegistrationPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "firstName")
    WebElement FirstName;

    @FindBy(id = "lastName")
    WebElement LastName;

    @FindBy(id = "userEmail")
    WebElement Email;

    @FindBy(id = "userMobile")
    WebElement PhoneNumber;

    @FindBy(xpath = "//select[@class='custom-select ng-pristine ng-valid ng-touched']")
    WebElement Occupation;

    @FindBy(css = "input[value='Male']")
    WebElement Gender;

    @FindBy(id = "userPassword")
    WebElement Password;

    @FindBy(id = "confirmPassword")
    WebElement ConfirmPassword;

    @FindBy(css = ".col-md-1")
    WebElement CheckBox;

    @FindBy(css = "input[value='Register']")
    WebElement RegisterSubmit;

    @FindBy(css = ".toast-container")
    WebElement toastMessage;




    public ConfirmMessage RegistrationForm(String firstName, String lastName, String email,
                                           String mobileNumber, String password, String confirmPassword)
    {
        FirstName.sendKeys(firstName);
        LastName.sendKeys(lastName);
        Email.sendKeys(email);
        PhoneNumber.sendKeys(mobileNumber);
//        Select occupation= new Select(Occupation);
//        occupation.selectByVisibleText("Engineer");
        Gender.click();
        Password.sendKeys(password);
        ConfirmPassword.sendKeys(confirmPassword);
        CheckBox.click();
        RegisterSubmit.click();


        ConfirmMessage confirmMessage = new ConfirmMessage(driver);
        return confirmMessage;
    }

    public ConfirmMessage RegistrationFormWithoutRequiredField(String firstName, String lastName, String email,
                                           String mobileNumber, String password, String confirmPassword)
    {
        FirstName.sendKeys(firstName);
        LastName.sendKeys(lastName);
        Email.sendKeys(email);
        PhoneNumber.sendKeys(mobileNumber);
//        Select occupation= new Select(Occupation);
//        occupation.selectByVisibleText("Engineer");
        Password.sendKeys(password);
        ConfirmPassword.sendKeys(confirmPassword);
        CheckBox.click();
        RegisterSubmit.click();




        ConfirmMessage confirmMessage = new ConfirmMessage(driver);
        return confirmMessage;
    }

    public RegistrationPage RegisterSubmit()
    {
        RegisterSubmit.click();
        return new RegistrationPage(driver);

    }

    @FindBy(xpath = "//form/div[1]/div[1]/div/div")
    WebElement firstNameInputErrorMessage;

    @FindBy(xpath = "//form/div[2]/div[1]/div/div")
    WebElement emailInputErrorMessage;

    @FindBy(xpath = "//form/div[2]/div[2]/div/div")
    WebElement mobileNoInputErrorMessage;

    @FindBy(xpath = "//form/div[4]/div[1]/div/div")
    WebElement passwordInputErrorMessage;

    @FindBy(xpath = "//form/div[4]/div[2]/div/div")
    WebElement confirmPasswordInputErrorMessage;

    @FindBy(xpath = "//form/div[5]/div/div")
    WebElement checkBoxErrorMessage;

    @FindBy(xpath = "//form/div[4]/div[2]/div")
    WebElement confirmPassworNotMatchedErrorMessage;

  By toastMesssage1 = By.cssSelector(".toast-container");

    public String FirstNameFieldErrorMessage()
    {
         return firstNameInputErrorMessage.getText();
//
    }

    public String EmailFieldErrorMessage()
    {
       return emailInputErrorMessage.getText();
    }

    public String MobileNoFieldErrorMessage()
    {
        return mobileNoInputErrorMessage.getText();
    }

    public String PasswordFieldErrorMessage()
    {
        return passwordInputErrorMessage.getText();
    }

    public String ConfirmPasswordFieldErrorMessage()
    {
        return confirmPasswordInputErrorMessage.getText();
    }

    public String CheckBoxErrorMessage()
    {
        return checkBoxErrorMessage.getText();
    }

    public String PasswordNotMatchingErrorMEssage()
    {
        return confirmPassworNotMatchedErrorMessage.getText();
    }

    public String ToastMessageForAlredyExistingUser()
    {
        return toastMessage.getText();

    }


}
