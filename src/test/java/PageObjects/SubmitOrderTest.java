package PageObjects;

import TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    @Test(dataProvider = "GetDataForSubmitOrder")
    public void OrderPlacedSuccessfully(HashMap<String , String>input)
    {
        LandingPage landingPage = LaunchApplication();
        ProductCataloguePage productCataloguePage = landingPage.LoginIntoApplication
                ((String)input.get("Email"),(String)input.get("Password"));
        productCataloguePage.AddTOCart((String)input.get("productName"));
        CartPage cartPage = productCataloguePage.GoToCartPage();
        CheckOutPage checkOutPage = cartPage.GoToCheckOut();
        checkOutPage.SelectCountryName((String)input.get("countryName"));
        OrderConfrirmMessage orderConfrirmMessage = checkOutPage.SubmitOrder();
        String orderCofirmationMessageForOrederPlaced = orderConfrirmMessage.VerifyTheConfirmationMessage();
        Assert.assertTrue(orderCofirmationMessageForOrederPlaced.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

    }

    @DataProvider
    public Object[][] GetDataForSubmitOrder() throws IOException {
          List<HashMap<String , String>> oredrData = getJsonDataToMap
                  (System.getProperty("user.dir") + "\\src\\test\\java\\Data\\LoginData.json");
          return new Object[][] {{oredrData.get(0)}};
    }
}
