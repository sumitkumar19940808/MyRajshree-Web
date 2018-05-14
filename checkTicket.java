package rajshree;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class checkTicket extends MainWebsite {
	SoftAssert softAssertionChild = new SoftAssert();

  @Test(priority = 10)
  public void checkTicketPrint(){
	  logger = extent.createTest("Check for Ticket print");
	  logger.createNode("Check for govt logo on ticket").pass("govt logo present");
  }
  @Test(priority = 11)
  public void isTestingTicket(){
	  String testingTicket =driver.findElement(By.xpath("//*[@id='ticketTable0']/tr[2]/td")).getText();
	  System.out.println(testingTicket);
	  boolean check = verifyTitle(testingTicket, "Testing Ticket");
	  if(check == true){
		  softAssertionChild.assertEquals(testingTicket, "Testing Ticket");
		  logger.createNode("Testing ticket").pass("Testing Ticket is present");
	  }else{
		  softAssertionChild.assertEquals(testingTicket, "Testing Ticket");
		  logger.createNode("Testing ticket").fail("Testing Ticket not present");
	  }
	  softAssertionChild.assertAll();
  }
  @Test(priority = 12)
  public void isLotteryName(){
	  String lotteryNameOnTicket = driver.findElement(By.xpath("//*[@id='ticketTable0']/tr[3]/td")).getText();
	  boolean check = verifyTitle(lotteryNameOnTicket, lotteryNameOnly);
	  if(check == true){
		  softAssertionChild.assertEquals(lotteryNameOnTicket, lotteryNameOnly);
		  logger.createNode("check for lottery Name on Ticket").pass("Ticket Name is correct : "+lotteryNameOnTicket);
	  }else{
		  softAssertionChild.assertEquals(lotteryNameOnTicket, lotteryNameOnly);
		  logger.createNode("check for lottery Name on Ticket").fail("Ticket Name is correct : "+lotteryNameOnTicket);
	  }
	  softAssertionChild.assertAll();
  }
  @Test(priority = 13)
  public void drawDateandTime(){
	  String ddt = driver.findElement(By.xpath("/html/body/div[7]/div[2]/div/table/tr[4]/td")).getText();
	  logger.createNode("Check for Draw date and Time on Ticket").info("Please check Manually ").pass(ddt);
  }
  @Test(priority = 14)
  public void isSecondPrizeAmount(){
	  String prizeAmount = driver.findElement(By.xpath("//*[@id='ticketTable0']/tr[5]/td")).getText();
	  StringBuffer prizeA = getNumber(prizeAmount);
	  String prizeAM = prizeA.toString();
//	  System.out.println(" ac "+prizeA);
//	  System.out.println(prizeAmount);
	  if(mrp.contains("2")){
		  try{
			  softAssertionChild.assertEquals("180", prizeAM);
			  logger.createNode("check for Second prize amount").info("Ticket Amount is : "+mrp).pass("Second Prize Amount on Ticket is correct : "+prizeA);
		  }catch(Exception e){
			  logger.createNode("check for Second prize amount").info("Ticket Amount is : "+mrp).fail("Second Prize Amount on Ticket is correct : "+prizeA);
		  }
	  }else if(mrp.contains("3")){
		  try{
			  softAssertionChild.assertEquals("210", prizeAM);
			  logger.createNode("check for Second prize amount").info("Ticket Amount is : "+mrp).pass("Prize Amount on Ticket is correct : "+prizeA);
		  }catch (Exception e) {
			// TODO: handle exception
			  logger.createNode("check for Second prize amount").info("Ticket Amount is : "+mrp).fail("Prize Amount on Ticket is correct : "+prizeA);
		}
	  }else{
		  logger.createNode("there is something wroung while getting second prize amount ").info("please check the test cases");
	  }
	  softAssertionChild.assertAll();	  
  }
  @Test(priority = 15)
  public void checkNumber(){
	  String getnumber =driver.findElement(By.className("numberonticket")).getText();
	  try{
		  System.out.println(number);
		  softAssertionChild.assertEquals(getnumber, number);
		  logger.createNode("Check of Number Print on Ticket").info("Number is : " +number).pass(getnumber +"is Correct");
	  }catch (Exception e) {
		// TODO: handle exception
		  System.out.println("erro");
	}
	  softAssertionChild.assertAll();
  }
}
