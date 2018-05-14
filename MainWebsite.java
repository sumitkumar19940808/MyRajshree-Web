package rajshree;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class MainWebsite extends TestCases {
	public WebDriver driver;
	String URL="http://172.20.5.6/myplay/accept";
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest logger;
	SoftAssert softAssertion = new SoftAssert();
	String lotteryName;
	String lotteryNameOnly;
	int entryBalance;
	String mrp;
	String number;

	Scanner sc = new Scanner(System.in);

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		System.setProperty("webdriver.chrome.driver", "/home/software-179/workspace/plugins/plugins/chromedriver");
		driver = new ChromeDriver();
		driver.navigate().to(URL);
		driver.manage().window().maximize();
		// driver.manage().deleteAllCookies();

		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

	}

	@BeforeTest
	public void startReport() {

		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/MyRajshree.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", URL);
		extent.setSystemInfo("Environment", "Automation Test");
		extent.setSystemInfo("Tester Name", "Sumit Kumar");

		htmlReporter.config().setDocumentTitle("QA MyRajshree Web");
		htmlReporter.config().setReportName("Rajshree Cash Card");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
	}

	@Test(priority = 1)
	public void checkTitle() {
		String actualTitle = driver.getTitle();
		String expectedTitle = "MyRajShree";
		logger = extent.createTest("Check For Title");
		boolean check = verifyTitle(actualTitle, expectedTitle);
		Assert.assertTrue("Title Mismatch", check);
		Reporter.log("Title is correct :" + expectedTitle, true);
		logger.pass("Title is correct :" + expectedTitle);
	}

	@Test(priority = 2)
	public void termandcondition() {
		driver.findElement(By.id("acceptcheck")).click();
		driver.findElement(By.id("proceedbtn")).click();
	}

	@Test(priority = 3)
	public void loginOperator() throws InterruptedException {
		logger = extent.createTest("Check For Login input field");
		String invalid[] = { "9013779490", "sdla1234" };
		String valid[] = { "7678304468", "sdla1234" };
		String id[] = { "inputphone", "inputpassword", "login" };
		// System.out.println("ok1");
		String incorrect = login(invalid[0], invalid[1], driver, id[0], id[1], id[2]);
		// System.out.println("ok2");
		// System.out.println(driver.manage().getCookies());
		if (incorrect.equals("selectpay")) {
			logger.fail("Logged in success with invalid input " + invalid[0] + "," + invalid[1]);
//			System.out.println(driver.manage().getCookies());
		} else if (incorrect.equals("alert")) {
			System.out.println("ok-1");
			String alertM = driver.findElement(By.id("errormsg")).getText();
			Thread.sleep(200);
			driver.findElement(By.id("close")).click();
			logger.pass("Not Log IN message is :" + alertM + " " + invalid[0] + "," + invalid[1]);
		} else {
			logger.fail(incorrect);
			System.out.println("ok-0");
		}
		Thread.sleep(200);
		String correctInput = login(valid[0], valid[1], driver, id[0], id[1], id[2]);
		if (correctInput.equals("selectpay")) {
			logger.pass("Logged in success " + valid[0] + "," + valid[1]);
//			System.out.println(driver.manage().getCookies());
			// System.out.println("ok1");
		} else if (correctInput.equals("alert")) {
			// driver.switchTo().activeElement();
			// System.out.println("ok2");
			String alertM = driver.findElement(By.id("errormsg")).getText();
			// System.out.println("ok3");
			Thread.sleep(200);
			driver.findElement(By.id("close")).click();
			logger.fail("Not Log IN message is :" + alertM + " with valid input " + valid[0] + "," + valid[1]);
		} else {
			logger.fail(correctInput);
			// System.out.println("ok4");
		}

		// System.out.println("ok-1");
	}

	@Test(priority = 4)
	public void selectPayment() {
		System.out.println("ok0");
		driver.findElement(By.id("operator")).click();
		System.out.println("ok1");
	}

	@Test(priority = 5, dependsOnMethods = { "selectPayment" })
	public void selectWallet() throws InterruptedException {
		logger = extent.createTest("Selection of Wallet");
		// driver.findElement(By.className("walletoptions")).;
		// System.out.println(tag);
		// String url =driver.getCurrentUrl();
		/// html/body/div[1]/div[2]/div[1]/div/h3
		/// html/body/div[1]/div[2]/div[3]/div
		String xpathwallet = "/html/body/div[1]/div[2]/div[1]/div";
		String h1 = driver.findElement(By.xpath(xpathwallet + "/h3")).getText();
		String s = "";
		String custname = null;
		for (int i = 1; i <= 4; i++) {
			s = s + driver.findElement(By.xpath(xpathwallet + "/p[" + i + "]")).getText();
			if (i == 2) {
				custname = driver.findElement(By.xpath(xpathwallet + "/p[" + i + "]")).getText();
				// System.out.println(custname);
			} else if (i == 4) {
				String bal = driver.findElement(By.xpath(xpathwallet + "/p[" + i + "]")).getText();
				Pattern lastIntPattern = Pattern.compile("[^0-9]+([0-9]+)$");
				Matcher matcher = lastIntPattern.matcher(bal);
				if (matcher.find()) {
					String someNumberStr = matcher.group(1);
					entryBalance = Integer.parseInt(someNumberStr);
				}
				// System.out.println(entryBalance);
			}
		}
		WebElement wb = driver.findElement(By.xpath(xpathwallet));
		wb.click();
		Thread.sleep(800);
		String url1 = driver.getCurrentUrl();
		boolean check = checkURL(url1, "selectgame");
		if (check == true) {/// html/body/div[1]/div[2]/div[1]/div/h3
			logger.pass("wallet selected sucessfully " + h1 + " " + s);
			/// html/body/div[1]/div[2]/div[1]/div/p[1]
		} else if (check == false) {
			logger.fail("wallet selection failure ");
		}
		try {
			String bal = driver.findElement(By.xpath("//a[@id='refreshbalance']")).getText();
			String test = Integer.toString(entryBalance);
			// softAssertion.
			if (bal.contains(test)) {
				logger.createNode("checking available balance is correct or not on selectgame screen")
						.pass("balance is correct on this screen as : " + bal);
				// logger.log("balance is correct on this screen as : "+bal );
				// logger.log(Status.PASS, MarkupHelper.createLabel("balance is
				// correct on this screen as : "+bal , ExtentColor.GREEN) );
			}
		} catch (Exception e) {
			logger.createNode("checking available balance is correct or not on selectgame screen")
					.fail("balance is not similiar as entry balance is : ₹ " + entryBalance);
			// logger.log(Status.FAIL,MarkupHelper.createLabel("balance is not
			// similiar as entry balance is : ₹ " +entryBalance
			// ,ExtentColor.RED));
		}
		logger.createNode("checking customer name is correct or not on selectgame screen")
				.fail("customer name is not showing on this screen as : " + custname);
		// logger.log(Status.FAIL,MarkupHelper.createLabel("customer name is not
		// showing on this screen as : " + custname , ExtentColor.RED) );
		/// html/body/div[1]/div[2]/div[1]/div
		/// html/body/div[1]/div[2]/div[2]/div
		/// html/body/div[1]/div[2]/div[3]/div
	}

	@Test(priority = 6, dependsOnMethods = { "selectWallet" })
	public void selectGame() throws InterruptedException {
		logger = extent.createTest("Selection of Game");
		// entryBalance =
		// driver.findElement(By.xpath("//a[@id='refreshbalance']")).getText();
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div")).click();
		Thread.sleep(800);
		String currentURL = driver.getCurrentUrl();
		boolean check = checkURL(currentURL, "online");
		if (check == true) {
			logger.pass("Game is selected successfully");
		} else if (check == false) {
			logger.fail("There is a problem in game selection");
		}
		// System.out.println(currentURL);

	}
	// @Test(priority=4,dependsOnMethods={"selectGame"})
	// public void verifyImagelogo(){//*[@id="weblogo"]
	// WebElement ImageFile =
	// driver.findElement(By.xpath("//a[contains(@id,'weblogo')]"));//img[contains(@id,'Test
	// Image')]
	// Boolean ImagePresent = (Boolean)
	// ((JavascriptExecutor)driver).executeScript("return arguments[0].complete
	// && typeof arguments[0].naturalWidth != \"undefined\" &&
	// arguments[0].naturalWidth > 0", ImageFile);
	// if (!ImagePresent)
	// {
	// System.out.println("Image not displayed.");
	// }
	// else
	// {
	// System.out.println("Image displayed.");
	// }
	//
	// }
	@Test(priority = 7)
	public void selectLP() throws InterruptedException{
		logger = extent.createTest("Select Game Screen on Online Game");
		driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul/li[2]/a")).click();
		Thread.sleep(100);
		driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul/li[2]/ul/li[2]")).click();
		logger.pass("Lucky Number Selected");
	}

	@Test(priority = 8)
	public void checklotterynameonLP() throws InterruptedException {
		logger = extent.createTest("check for lottery name on Lucky Number");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		lotteryName = driver.findElement(By.xpath("//th[@id='scheme']")).getText();
		lotteryNameOnly = driver.findElement(By.xpath("//span[@id='lotname']")).getText();
		System.out.println("L "+lotteryName);
		mrp = driver.findElement(By.xpath("//span[@id='mrp']")).getText();
		Thread.sleep(200);
		//driver.findElement(By.xpath("//button[@name='luckyNo']")).click();
		Thread.sleep(200);
		String lotteyNameOnLP = null;
		try {
			lotteyNameOnLP = driver.findElement(By.xpath("//label[@id='lbl_number_LucNo']")).getText();
			// Assert.assertEquals(lotteryName, lotteyNameOnLP);
			softAssertion.assertEquals(lotteyNameOnLP,lotteryName);
			logger.pass("lottery name is correct as :" + lotteyNameOnLP);
		} catch (Exception e) {
			System.out.println(e);
			logger.fail("lottery name is not correct on lp screen is : " + lotteyNameOnLP + "and on game screen is : "
					+ lotteryName);
		}
		softAssertion.assertAll();
	}

	@Test(priority = 9)
	public void luckynumber() throws Exception {
		// Thread.sleep(200);
		logger = extent
				.createTest("check ticket purchase on lucky number and balance of this wallet is : " + entryBalance);
		String digit = driver.findElement(By.xpath("//span[@id='display_lotdigit']")).getText();
		System.out.println("Enter the " + digit + " digit number for Lucky number option");
		
		try {
			number = sc.nextLine();
			// System.out.println(number);
			driver.findElement(By.xpath("//input[@id='number_LucNo']")).sendKeys(number);
		} catch (Exception e) {
			// System.out.println("Please enter the numbers only");
			logger.fail("either number you have not entered or lucky number field is not showing");
		}
		System.out.println("Enter the QTY for Lucky number option");
		String qty = null;
		try {
			qty = sc.nextLine();
			driver.findElement(By.xpath("//input[@id='qty_LucNo']")).sendKeys(qty);
		} catch (Exception e) {
			// System.out.println("either number you have not entered or qty
			// field is not showing");
			logger.fail("either number you have not entered or qty field is not showing");
		}
		driver.findElement(By.xpath("//button[@id='submitLuckyNo']")).click();
		 System.out.println(mrp);
		System.out.println(entryBalance);
		if (entryBalance <= 1) {
			// System.out.println("in if");
			try {
				String alerttext = driver.findElement(By.xpath("//*[@id='onlineerrordiv']/div/b")).getText();
				// System.out.println(alerttext);
				Thread.sleep(200);
				driver.findElement(By.xpath("/html/body/div[13]/div[1]/button")).click();
				softAssertion.assertEquals("ERROR! You don't have enough balance. Please topup your account.",alerttext);
				logger.pass("Ticket is not saled , Input is : NUMBER = " + number + " , QTY :" + qty);
				// driver.findElement(By.xpath("/html/body/div[13]/div[1]/button")).click();
			} catch (Exception e) {
				// System.out.println("ol");
				logger.fail("Ticket is purchased when wallet amount is less than to ticket prize");
			}
		} else {
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			WebElement myelement = driver.findElement(By.id("confirm"));
			Thread.sleep(1000);
			myelement.click();
			// System.out.println("okay");
			try {
				Thread.sleep(1000);
				String saleString = driver.findElement(By.xpath("/html/body/div[7]/div[1]/span")).getText();
				// System.out.println(saleString);
				if (saleString.equals("Sale Ticket")) {
					driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					WebElement ticketid = driver.findElement(By.id("dialogBuyTicket"));
					takeSnapShot(driver, "/home/software-179/workspace/MyRajshreeWeb/test-output/test.png", ticketid);
					logger.pass("Ticket successfull purchased");
					logger.addScreenCaptureFromPath("/home/software-179/workspace/MyRajshreeWeb/test-output/test.png");
					//driver.findElement(By.xpath("/html/body/div[7]/div[1]/button")).click();
				}
			} catch (Exception e) {
				logger.fail("There is something wroung");
				e.printStackTrace();
			}
			// System.out.println(s);	
		}
	}

	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			// logger.log(Status.FAIL, "Test Case Failed is "+result.getName());
			// MarkupHelper is used to display the output in different colors
			logger.log(Status.FAIL,
					MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			logger.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
		} else if (result.getStatus() == ITestResult.SKIP) {
			// logger.log(Status.SKIP, "Test Case Skipped is
			// "+result.getName());
			logger.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
		}
	}

	@AfterTest
	public void endReport() {
		extent.flush();
	}
	// @AfterSuite
	// public void afterSuite() {
	// driver.close();
	// }
}
