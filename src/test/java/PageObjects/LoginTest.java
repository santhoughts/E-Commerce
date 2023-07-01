package PageObjects;

import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "getLoginData" , groups = {"Smoke"})

    public void Login(HashMap<String , String>input)
    {
        LandingPage landingPage=LaunchApplication();
        landingPage.LoginIntoApplication((String)input.get("Email"), (String)input.get("Password"));

    }

    // DataProvider for login
    @DataProvider
    public Object[][] getLoginData() throws IOException {
      List<HashMap<String , String>>  loginData= getJsonDataToMap(System.getProperty
              ("user.dir") + "\\src\\test\\java\\Data\\LoginData.json");
      return new Object[][]{{loginData.get(0)}};
      //0,1,2,3 are the indexes of the json data where
        // we put the combination of different credentilas
    }

    @Test(dataProvider = "getLoginData1")
    public void LoginErrorValidations(HashMap<String , String>input)
    {
        LandingPage landingPage = LaunchApplication();
       landingPage.LoginIntoApplication((String)input.get("Email"),(String)input.get("Password"));
        String loginErrorMessage = landingPage.getLoginErrorMessage();
        Assert.assertTrue(loginErrorMessage.equalsIgnoreCase("Incorrect email  password."));
    }

    @DataProvider
    public Object[][] getLoginData1() throws IOException {
        List<HashMap<String, String>> loginData1 = getJsonDataToMap(System.getProperty
                ("user.dir") + "\\src\\test\\java\\Data\\LoginData.json");
        return new Object[][]{{loginData1.get(1)}, {loginData1.get(2)}, {loginData1.get(3)}};


    }
}
