package ECommerce.AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponents {
         public WebDriver driver;

        // Here we create a local object for driver class to use throughout the class
        public AbstractComponents(WebDriver driver)
        {
            this.driver = driver;
            PageFactory.initElements(driver,this);
        }


        // Wait for element to appear
        public void WaitForElementToAppear(By FindBy)
        {
            WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
        }


        // Wait for element to appear by WebElement
         public void WaitOfWebElementToAppear(WebElement FindBy)
         {
            WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(FindBy));
         }

         // Wait for Element to Disappear
        public void WaitOfWebElementToDisappear(WebElement FindBy)
        {
            WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
            wait.until(ExpectedConditions.invisibilityOf(FindBy));
        }



    }

