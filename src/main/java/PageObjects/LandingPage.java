package PageObjects;

import ECommerce.AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponents {

   public WebDriver driver;

    public LandingPage(WebDriver driver)
    {
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver,this); // use driver key in pageFactory methods
    }

    // Register Button Locator
    @FindBy(css = "a.btn1")
    WebElement register;

    @FindBy(id = "userEmail")
    WebElement Email;

    @FindBy(id = "userPassword")
    WebElement Password;

    @FindBy(id = "login")
    WebElement submit;

    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage;





    //Method to perform the click action
    public RegistrationPage openRegisterForm()
    {
        register.click();
        RegistrationPage registrationPage=new RegistrationPage(driver);
        return registrationPage;
    }

    public void LoginIntoApplication( String userEmail , String password)
    {
        Email.sendKeys(userEmail);
        Password.sendKeys(password);
        submit.click();
    }

    public String getLoginErrorMessage()
    {
        WaitOfWebElementToAppear(errorMessage);
        return errorMessage.getText();
    }


    public void goTo()
    {
        driver.get("https://rahulshettyacademy.com/client");
    }


}
