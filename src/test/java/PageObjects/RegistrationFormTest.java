package PageObjects;

import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class RegistrationFormTest extends BaseTest {


    @Test( dataProvider = "getData") // getData by using the HashMap
    public void CompleteRegistration(HashMap<String , String> input)
    {
        LandingPage landingPage=LaunchApplication();
        RegistrationPage registrationPage=landingPage.openRegisterForm();
        ConfirmMessage confirmMessage=registrationPage.RegistrationForm((String)input.get("firstName") ,(String)input.get("lastName") ,
                (String)input.get("email") ,(String)input.get("mobileNo"),
                (String)input.get("password"),(String)input.get("confirmPassword"));
        String confirmationMessage = confirmMessage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Account Created Successfully"));
        //tearDown();

    }

    /*@DataProvider
    public Object[][] getData()
    {
        HashMap<String,String> map = new HashMap<>();
        map.put("firstName" , "Sanjeev");
        map.put("lastName" , "Yadav");
        map.put("email" , "sdfg@gmail.com");
        map.put("mobileNo" , "7878789090");
        map.put("password" , "Amit@123");
        map.put("confirmPassword" ,"Amit@123");

        HashMap<String , String> map1 = new HashMap<>();
        map1.put("firstName" , "Gaurav");
        map1.put("lastName" , "Yadav");
        map1.put("email" , "fg@gmail.com");
        map1.put("mobileNo" , "7870089090");
        map1.put("password" , "Amit@123");
        map1.put("confirmPassword" ,"Amit@123");

        return new Object[][] {{map},{map1}};
    }*/

    @Test
    public void InputErrorMessageForEachField()
    {
        LandingPage landingPage=LaunchApplication();
        RegistrationPage registrationPage=landingPage.openRegisterForm();
        registrationPage.RegisterSubmit();
        String firstNameWarningMessage = registrationPage.FirstNameFieldErrorMessage();
        Assert.assertTrue(firstNameWarningMessage.equalsIgnoreCase("*First Name is required"));
        String emailWarningMessage = registrationPage.EmailFieldErrorMessage();
        Assert.assertTrue(emailWarningMessage.equalsIgnoreCase("*Email is required"));
        String mobileNoWarningMessage = registrationPage.MobileNoFieldErrorMessage();
        Assert.assertTrue(mobileNoWarningMessage.equalsIgnoreCase("*Phone Number is required"));
        String passwordWarningMessage = registrationPage.PasswordFieldErrorMessage();
        Assert.assertTrue(passwordWarningMessage.equalsIgnoreCase("*Password is required"));
        String confirmPasswordWarningMessage = registrationPage.ConfirmPasswordFieldErrorMessage();
        Assert.assertTrue(confirmPasswordWarningMessage.equalsIgnoreCase("Confirm Password is required"));
        String checkBoxWarningMessage = registrationPage.CheckBoxErrorMessage();
        Assert.assertTrue(checkBoxWarningMessage.equalsIgnoreCase("*Please check above checkbox"));

    }



    // getThe data from the json file (dataProvider)
    @Test
    public  void RegisterationWithoutEnterValueInTheRequiredField()
    {
        LandingPage landingPage=LaunchApplication();
        RegistrationPage registrationPage=landingPage.openRegisterForm();
        ConfirmMessage confirmMessage=registrationPage.RegistrationFormWithoutRequiredField("Sanjeev","Yadav",
                "sanjpp12300@aksinteractive.com", "8066262084",
                "Sanjeev@123","Sanjeev@123");
        String confirmationMessage = confirmMessage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Account Created Successfully"));

    }

    @DataProvider
    public Object[][] getData() throws IOException {
      List<HashMap<String , String>> data = getJsonDataToMap
              (System.getProperty("user.dir")+"//src//test//java//Data//SignUpData.json");
        return new Object[][] {{data.get(0)},{data.get(1)}};
    }


    @Test(dataProvider = "getData")
    public void ErrorMessageForPasswordNotMatchWithConfirmPassword(HashMap<String , String>input)
    {
        LandingPage landingPage=LaunchApplication();
        RegistrationPage registrationPage=landingPage.openRegisterForm();
        registrationPage.RegistrationForm((String)input.get("firstName") ,(String)input.get("lastName") ,
                (String)input.get("email") ,(String)input.get("mobileNo"),
                (String)input.get("password"),(String)input.get("confirmPassword"));
       String passwordMatchError = registrationPage.PasswordNotMatchingErrorMEssage();
       Assert.assertTrue(passwordMatchError.equalsIgnoreCase
               ("Password and Confirm Password must match with each other."));

    }

    @Test(dataProvider = "getData")
    public void VerifyTheWarningMessageforAlreadyAddedUser(HashMap<String , String>input)
    {
        LandingPage landingPage=LaunchApplication();
        RegistrationPage registrationPage=landingPage.openRegisterForm();
        registrationPage.RegistrationForm((String)input.get("firstName") ,(String)input.get("lastName") ,
                (String)input.get("email") ,(String)input.get("mobileNo"),
                (String)input.get("password"),(String)input.get("confirmPassword"));
      String warnnigMessage = registrationPage.ToastMessageForAlredyExistingUser();
        System.out.println(warnnigMessage);
        Assert.assertTrue(warnnigMessage.equalsIgnoreCase("User already exisits with this Email Id!"));


    }







}
