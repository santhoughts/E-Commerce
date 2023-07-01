package PageObjects;

import ECommerce.AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderConfrirmMessage extends AbstractComponents {


    public OrderConfrirmMessage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver , this);
    }


    @FindBy(css = ".hero-primary")
    WebElement confirmationMessage;

    @FindBy(id = "toast-container")
    WebElement toast;

    public String VerifyTheConfirmationMessage()
    {
        waitOfWebElementToDisappear(toast);
        return confirmationMessage.getText();

    }
}
