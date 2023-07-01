package PageObjects;

import ECommerce.AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage  extends AbstractComponents {
    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver , this);
    }

    @FindBy(css = ".action__submit")
    WebElement placeOrder;

    @FindBy(css = "[placeholder='Select Country']")
    WebElement country;

    @FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
    WebElement selectCountry;

    By results = By.cssSelector(".ta-results");

    public void SelectCountryName(String countryName)
    {
        Actions a = new Actions(driver);
        a.sendKeys(country,countryName).build().perform();
        WaitForElementToAppear(results);
        selectCountry.click();
    }

    public OrderConfrirmMessage SubmitOrder()
    {
        PageScroll();
        placeOrder.click();
        OrderConfrirmMessage orderConfrirmMessage = new OrderConfrirmMessage(driver);
        return orderConfrirmMessage;
    }



}
