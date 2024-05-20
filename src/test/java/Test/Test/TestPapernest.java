package Test.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

import org.junit.Assert;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import io.github.bonigarcia.wdm.WebDriverManager;

public class TestPapernest {

	final static String ancienlogement = "113 Rue Lecourbe 75015 Paris"; 
	final static String nouveaulogement = "157 Boulevard Macdonald 75019 Paris";
	final static String  street_number  = "4";
	final static String address_street  = "111114";
	final static String   address_city  = "paris 75013" ; 
	final static String first_name  = "Aymen";
	final static String last_name  = "BENAMOR";
	final static String phone_number = "0600000000" ;

	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		
		WebDriverManager.chromedriver().setup();
		 
		 WebDriver driver = new ChromeDriver();	
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		String url= "https://app.papernest.com/onboarding?anonymous&anonymousId=test&id_text=1&destination=newspaper";
		driver.get(url);
		driver.manage().window().maximize();
		
		
		 
	    
		driver.findElement(By.xpath("//*[@id=\"popin-poste-classic\"]")).click();
		driver.findElement(By.id("poste-subscription.begin_date")).click();
		driver.findElement(By.xpath("//*[@id=\"mat-datepicker-0\"]/div/mat-month-view/table/tbody/tr[6]/td[5]/button/span[1]")).click();
		 driver.findElement(By.id("old_housing.address")).sendKeys(ancienlogement);
		 driver.findElement(By.xpath("//*[@id=\"animation-container\"]/div[2]/div/ppn-question-layout/div[2]/ppn-autocomplete-adapter/ds-address-v2/div/ds-autocomplete-address/div/ul/li\r\n"
		 		)).click();


		 driver.findElement(By.id("housing.address")).sendKeys(nouveaulogement);
		 driver.findElement(By.xpath("//*[@id=\"animation-container\"]/div[3]/div/ppn-question-layout/div[2]/ppn-autocomplete-adapter/ds-address-v2/div/ds-autocomplete-address/div/ul/li")).click();
		 driver.findElement(By.xpath("//*[@id=\"sct_content\"]/ppn-flow/app-dynamic-flow/div/div[2]/div[1]/ppn-cookie-banner/div/button[1]")).click();

		 
		 
		 driver.findElement(By.id("housing.address.street_number")).sendKeys(street_number);
		 driver.findElement(By.id("housing.address.street")).clear();
		 driver.findElement(By.id("housing.address.street")).sendKeys(address_street);
		 driver.findElement(By.id("housing.address.city")).sendKeys(address_city);
		 
		 
		 driver.findElement(By.xpath("//*[@id=\"animation-container\"]/div[5]/div/ppn-question-layout/div[2]/ppn-autocomplete-adapter/ds-address-v2/div/ds-autocomplete-city/div/ul/li[1]")).click();
		 WebElement btn_next = driver.findElement(By.id("button_next")); 
		 btn_next.click();
		 driver.findElement(By.id("offer_poste_6"))
		 .click(); 
		
		 String user_email = "user" + UUID.randomUUID().toString() + "test@papernest.com";
		 WebElement User = driver.findElement(By.id("user.email"));
		            User.sendKeys(user_email);
		            
		 WebElement phone = driver.findElement(By.id("user.phone_number"));
		 phone.sendKeys(phone_number);
		 
		   WebElement civility = driver.findElement(By.id("user.civility-mister"));
		            civility.click();
		 
		   WebElement prenom = driver.findElement(By.id("user.first_name"));
				 prenom.sendKeys(first_name);
		   WebElement nom = driver.findElement(By.id("user.last_name"));
				    nom.sendKeys(last_name);
				    
		
			
			
			
		 
		   //WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5)); 		 
			//WebElement Suivant1;
			//Suivant1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button_next")));
			//Suivant1.click();
			Thread.sleep(3000);	    
     		WebElement Suivant1 = driver.findElement(By.id("button_next"));
		    JavascriptExecutor executor = (JavascriptExecutor)driver;
		    executor.executeScript("arguments[0].click();", Suivant1);
		
		     
			
				 
			
			
			driver.findElement(By.id("poste-subscription.confirmation_code_destination-post_office")).click();

			Thread.sleep(3000);
			WebElement submit1 = driver.findElement(By.id("button_next"));
		    JavascriptExecutor executor1 = (JavascriptExecutor)driver;
		    executor1.executeScript("arguments[0].click();", submit1);
		    
		    Thread.sleep(3000);
			driver.findElement(By.id("button_next_summary")).click();
			
			
			 
			Thread.sleep(3000);
			String expect_message = "Il ne vous reste plus qu'à payer la redirection"; 
			WebElement message = driver.findElement(By.xpath("//*[@id=\"undefined\"]/h1"));
			String actuel_message = message.getText();
			Assert.assertEquals(expect_message,actuel_message);
			
			
			
			TakesScreenshot screenshot= (TakesScreenshot) driver;
	        File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);
            File destinationFile = new File("Test\\src\\test\\screen\\screen\\screenshot.png");
	        FileUtils.copyFile(screenshotFile, destinationFile);
		
		  
	
		
	   
	        driver.quit();
	   
		
		 
	}

	}
