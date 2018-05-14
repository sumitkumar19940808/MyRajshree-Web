package rajshree;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class OnlineGameTestCases {
	public WebDriver driver;
	 @BeforeSuite(alwaysRun = true)
	  public void beforeSuite() {
		 System.setProperty("webdriver.chrome.driver",
					"/home/software-179/workspace/plugins/plugins/chromedriver");
		  driver = new ChromeDriver();
		  driver.navigate().to("http://@www.gmail.com");
		  driver.manage().window().maximize();
		 // driver.manage().deleteAllCookies();
		  
		  driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		  driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
	  }	
 
  public void verifyImagelogo() {
//	  WebElement ImageFile = driver.findElement(By.xpath("//a[contains(@id,'weblogo')]"));
//	  Boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
//	    if (!ImagePresent)
//	    {
	    	
	         //System.out.println("Image not displayed.");
//	    }
//	    else
//	    {
//	        System.out.println("Image displayed.");
//	    }
	    
  }
}
