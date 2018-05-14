package rajshree;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestCases {
	public boolean verifyTitle(String actual, String Expected) {
		if (actual.equals(Expected))
			return true;
		else
			return false;
	}

	/* CASE- 1. Both User name and Password are entered correctly. */
	public String login(String username, String Password, WebDriver driver, String id1, String id2, String id3) {
		try {
			driver.findElement(By.id(id1)).clear();
			driver.findElement(By.id(id1)).sendKeys(username);
			driver.findElement(By.id(id2)).clear();
			driver.findElement(By.id(id2)).sendKeys(Password);
			driver.findElement(By.id(id3)).click();
			Thread.sleep(800);
			String currentURL = driver.getCurrentUrl();
			boolean check = checkURL(currentURL, "selectpay");
			System.out.println(currentURL);
			if (check == true) {
				// System.out.println("ok");
				return "selectpay";
			} else {
				// System.out.println("ok1");
				return "alert";
			}

		} catch (Throwable e) {
			// System.out.println("Emaild not found: " + e.getMessage());
			return e.getMessage();
		}
	}

	public boolean checkURL(String currentURL, String URLmatch) {
		if (currentURL.contains(URLmatch)) {
			return true;
		}
		return false;
	}

	public static void takeSnapShot(WebDriver webdriver, String fileWithPath, WebElement ele) throws Exception {
		// TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
		webdriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		webdriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		File screenshot = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.FILE);
		BufferedImage fullImg = ImageIO.read(screenshot);
		// File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		// File DestFile = new File(fileWithPath);
		// FileUtils.copyFile(SrcFile, DestFile);

		Point point = ele.getLocation();
		int eleWidth = ele.getSize().getWidth();
		int eleHeight = ele.getSize().getHeight();

		BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
		ImageIO.write(eleScreenshot, "png", screenshot);
		
		File screenshotLocation = new File(fileWithPath);
		FileUtils.copyFile(screenshot, screenshotLocation);
	}
	
	public StringBuffer getNumber(String str)
	{
		StringBuffer alpha = new StringBuffer(), 
		num = new StringBuffer(), special = new StringBuffer();
		
		for (int i=0; i<str.length(); i++)
		{
			if (Character.isDigit(str.charAt(i)))
				num.append(str.charAt(i));
			else if(Character.isAlphabetic(str.charAt(i)))
				alpha.append(str.charAt(i));
			else
				special.append(str.charAt(i));
		}
	
		//System.out.println(alpha);
		//System.out.println(num);
		//System.out.println(special);
		return num;
	}

}
