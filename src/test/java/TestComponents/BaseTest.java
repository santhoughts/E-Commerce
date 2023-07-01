package TestComponents;

import PageObjects.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class BaseTest {

        public WebDriver driver = new ChromeDriver();
        public LandingPage landingPage;

    // for open the chrome driver and maximize it
    public void initializeDriver()
    {
        WebDriverManager.chromedriver().setup();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }


    // Read the json data from the file
    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
    {
        // Convert Json data to String

        String jsonContent =  FileUtils.readFileToString
                (new File(filePath));

        // String to HashMap
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String , String>> data =  mapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String , String>>>() {
                });
        return data;
    }

    // Action to open the Chrome browser & hit the url
    @BeforeTest(alwaysRun = true) // Run always before the test
    public LandingPage LaunchApplication()
    {
        initializeDriver();
        landingPage= new LandingPage(driver);
        landingPage.goTo();
        return landingPage;

    }

    // After the all test execute also run the close method to close the browser
    @AfterTest( alwaysRun = true)
    public void tearDown()
    {
        driver.close();
    }

    // TakeScreenshot

    public File GetScreenshot(String testCaseName ) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//reports" +
                testCaseName + ".png");
        FileUtils.copyFile(source , file);
        return file;
    }
}


