package suites.trendyol.ui;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.UITestCase;

public class UITest extends UITestCase {


	
	@BeforeClass
	public void setUp() {
		Reporter.log("Set Up completed \n", true);
	}
	
	@AfterClass
	public void tearDown() {
		 driver.close();
	}

	@BeforeMethod
	public void beforeTest(Method method) {
		Reporter.log("Test Started= " + method.getName(), true);
	}

	@AfterMethod
	public void afterTest(Method method) {
		Reporter.log("Test Completed= " + method.getName() + "\n", true);
	}

	@Test(priority=0)
	public void login() {
		try {
		 driver.get("https://www.trendyol.com/");
		 //popup close btn
		 WebElement popupCloseBtn = driver.findElement(By.xpath("/html/body/div[9]/div/div/a"));
		 popupCloseBtn.click();
		 Thread.sleep(5000);
		 //trendyol icon
		 WebElement trendyolIcon=driver.findElement(By.id("logo"));
		 
		 assertEquals(trendyolIcon.isDisplayed(), true, "Trendyol iconu gözükmedi");
		 
		 
		 //giris yap btn
		 WebElement girisYapBtn = driver.findElement(By.xpath("//*[@id=\"accountNavigationRoot\"]/div/ul/li[1]/div[1]/i"));
		 girisYapBtn.click();
		 Thread.sleep(5000);
		 //email alaný
		 WebElement email = driver.findElement(By.id("email"));	 
		 assertEquals(email.isDisplayed(), true, "email text alaný gözükmedi");
		 
		 //login bilgileri girilir ve login ol butn týklanýr.
		 email.sendKeys("asiemerter@gmail.com");
		 driver.findElement(By.id("password")).sendKeys("Amerter123");
		 driver.findElement(By.id("loginSubmit")).click();
		 Thread.sleep(5000);
		 
		 //logged in container
		 WebElement loggedInContainer=driver.findElement(By.id("logged-in-container"));
		 
		 assertEquals(loggedInContainer.isDisplayed(), true, "Logged in container iconu gozukmedi");


		 
		} catch (Exception e) {
			Reporter.log(e.getStackTrace().toString(), true);
		}
	}
	
	@Test(dependsOnMethods="login")
	public void boutiqueImageTest() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;   
		
		long menuCount = (Long) js.executeScript("return document.getElementById(\"main-menu\").children[0].childElementCount");
		
		for (int i = 0; i < menuCount; i++) {
			
			int menuNo=i+1;
			WebElement menuItem=waitAndGetElement(By.xpath("//*[@id=\"item"+menuNo+"\"]/a"));
			menuItem.click();
			
			 //trendyol icon
			 WebElement trendyolIcon=driver.findElement(By.id("logo"));
		     assertEquals(trendyolIcon.isDisplayed(), true, "Trendyol iconu gözükmedi");
		     
		     
		     //Get All bigBoutiqueImages WebElements
		    @SuppressWarnings("unchecked")
			List<WebElement> bigBoutiqueImages = (List<WebElement>) js.executeScript("return document.getElementsByClassName(\"bigBoutiqueImage\")");
		    System.out.println(bigBoutiqueImages.size() + " adet big butik image bulundu");
		    for (int j = 0; j < bigBoutiqueImages.size(); j++) {
			     Object result = ((JavascriptExecutor) driver).executeScript(
			    		   "return arguments[0].complete && "+
			    		   "typeof arguments[0].naturalWidth != \"undefined\" && "+
			    		   "arguments[0].naturalWidth > 0", bigBoutiqueImages.get(j));

			    		    boolean loaded = false;
			    		    if (result instanceof Boolean) {
			    		      loaded = (Boolean) result;
			    		      System.out.println(loaded);
			    		      System.out.println((j+1)+ ". Big pic is loaded");
			    		    }
			}
		    
		    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250000)");
		    Thread.sleep(5000);
		    
		     //Get All smallBoutiqueImages WebElements
		    long littleBoutiqueImageSize = (Long) js.executeScript("return document.getElementsByClassName(\"littleBoutiqueImage\").length");
		    System.out.println(littleBoutiqueImageSize + " adet little butik image bulundu");
		    for (int k = 0; k < littleBoutiqueImageSize; k++) {
		    	
				int imgNo=i+1;
				WebElement littleImage=waitAndGetElement(By.xpath("//*[@id=\"littleCampaigns\"]/div["+imgNo+"]/div[1]/a/img"));
		    	
			     Object result = ((JavascriptExecutor) driver).executeScript(
			    		   "return arguments[0].complete && "+
			    		   "typeof arguments[0].naturalWidth != \"undefined\" && "+
			    		   "arguments[0].naturalWidth > 0", littleImage);

			    		    boolean loaded = false;
			    		    if (result instanceof Boolean) {
			    		      loaded = (Boolean) result;
			    		      System.out.println(loaded);
			    		      System.out.println((k+1)+ ". Little pic is loaded");
			    		    }
			}
			}
		     
	}
	
	
	@Test(dependsOnMethods="login")
	public void productImageTest() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;   
		
		
			//SüperMarket
			WebElement menuItem=waitAndGetElement(By.xpath("//*[@id=\"item9\"]/a"));
			menuItem.click();
			
			 //trendyol icon
			 WebElement trendyolIcon=driver.findElement(By.id("logo"));
		     assertEquals(trendyolIcon.isDisplayed(), true, "Trendyol iconu gözükmedi");
		     
			//product
			WebElement product=waitAndGetElement(By.xpath("//*[@id=\"dynamic-boutiques\"]/div/div/div[2]/div[1]/a"));
			product.click();
			
		    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250000)");
		    Thread.sleep(5000);
		    
		     //Get All product images 
		    long productImageSize = (Long) js.executeScript("return document.getElementsByClassName(\"prc-picture\").length");
		    System.out.println(productImageSize + " adet little butik image bulundu");
		    for (int i = 0; i < productImageSize; i++) {
		    	
				int imgNo=i+1;
				WebElement littleImage=waitAndGetElement(By.xpath("//*[@id=\"root\"]/div/ul/li["+imgNo+"]/div/a/div[3]/div/div/img"));
		    	
			     Object result = ((JavascriptExecutor) driver).executeScript(
			    		   "return arguments[0].complete && "+
			    		   "typeof arguments[0].naturalWidth != \"undefined\" && "+
			    		   "arguments[0].naturalWidth > 0", littleImage);

			    		    boolean loaded = false;
			    		    if (result instanceof Boolean) {
			    		      loaded = (Boolean) result;
			    		      System.out.println(loaded);
			    		      System.out.println((i+1)+ ". Product pic is loaded");
			    		    }
			}	     
	}
	
	
	@Test(dependsOnMethods="login")
	public void getProductToBasket() throws InterruptedException {
	    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-250000)");
	    Thread.sleep(5000);
			//SüperMarket
			WebElement menuItem=waitAndGetElement(By.xpath("//*[@id=\"item9\"]/a"));
			menuItem.click();
			
			 //trendyol icon
			 WebElement trendyolIcon=driver.findElement(By.id("logo"));
		     assertEquals(trendyolIcon.isDisplayed(), true, "Trendyol iconu gözükmedi");
		     
			//Boutique
			WebElement boutique=waitAndGetElement(By.xpath("//*[@id=\"dynamic-boutiques\"]/div/div/div[2]/div[1]/a"));
			boutique.click();
			
			//product
			WebElement product=waitAndGetElement(By.xpath("//*[@id=\"root\"]/div/ul/li[1]/div/a/div[3]/div/div/img"));
			product.click();
			Thread.sleep(5000);
			
			//add to basket
			WebElement addBtn=waitAndGetElement(By.id("addToBasketButton"));
			addBtn.click();
			Thread.sleep(5000);
			
			//go to basket
			WebElement basketBtn=waitAndGetElement(By.id("myBasketListItem"));
			basketBtn.click();
			Thread.sleep(5000);
			
			//product
			WebElement basketProductText=waitAndGetElement(By.id("basketContent"));
			assertTrue(basketProductText.getText().contains("Sepetim (1 Ürün)"));
			
			    
	}

}
