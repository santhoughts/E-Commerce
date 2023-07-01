package PageObjects;

import TestComponents.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class LoginAddToCartTest extends BaseTest {



    @DataProvider
    public Object[][] getLoginData() throws IOException {
        List<HashMap<String , String>> loginData= getJsonDataToMap(System.getProperty
                ("user.dir") + "\\src\\test\\java\\Data\\LoginData.json");
        return new Object[][]{{loginData.get(0)}};
        //0,1,2,3 are the indexes of the json data where
        // we put the combination of different credentilas
    }

    @Test(dataProvider = "getLoginData" , dependsOnMethods = {"Login"} , groups = {"Smoke"})
    public void AddProductIntoCart(HashMap<String , String>input)
    {
        ProductCataloguePage productCataloguePage = new ProductCataloguePage(driver);
        productCataloguePage.AddTOCart((String)input.get("productName"));
    }

}
