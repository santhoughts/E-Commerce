package PageObjects;

import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class AddToCartTest extends BaseTest {

    @Test(dataProvider = "getData")
    public void AddProductIntoCart(HashMap<String , String>input)
    {
        LandingPage landingPage = LaunchApplication();
       ProductCataloguePage productCataloguePage = landingPage.LoginIntoApplication(
               (String)input.get("Email") , (String)input.get("Password"));
       productCataloguePage.AddTOCart((String)input.get("productName"));
    }


    @Test(dataProvider = "getData")
    public void ClickOnBuynowButton(HashMap<String , String>input)
    {
        LandingPage landingPage = LaunchApplication();
       ProductCataloguePage productCataloguePage = landingPage.LoginIntoApplication
               ((String)input.get("Email") , (String)input.get("Password"));
       productCataloguePage.AddTOCart((String)input.get("productName"));
      CartPage cartPage = productCataloguePage.GoToCartPage();
    Boolean match = cartPage.VerifyDisplayProduct((String)input.get("productName"));
        Assert.assertTrue(match);
     CheckOutPage checkOutPage= cartPage.GoToCheckOut();

    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String , String>> data =getJsonDataToMap(System.getProperty("user.dir")
                + "\\src\\test\\java\\Data\\LoginData.json");
        return new Object[][] {{data.get(0)}};
    }
}
