package PageObjects;

import ECommerce.AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmMessage extends AbstractComponents {

    WebDriver driver;
    public ConfirmMessage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".headcolor")
    WebElement ConfirmationMessageForAccountCreation;


    public String getConfirmationMessage()
    {
         return ConfirmationMessageForAccountCreation.getText();

    }
}
