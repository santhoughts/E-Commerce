package PageObjects;

import ECommerce.AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponents {


    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".cartSection h3")
    List<WebElement> cartProducts;

    @FindBy(css = ".totalRow button")
    WebElement checkOut;

    // Verify the product Details
    public Boolean VerifyDisplayProduct(String productName)
    {
       Boolean match = cartProducts.stream().anyMatch
               (product->product.getText().equalsIgnoreCase(productName));
        return match;
    }

    public CheckOutPage GoToCheckOut()
    {
        checkOut.click();
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        return checkOutPage;
    }



}

