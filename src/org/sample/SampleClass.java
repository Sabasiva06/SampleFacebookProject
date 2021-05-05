package org.sample;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.RepaintManager;

import java.util.Set;

import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.seleniumhq.jetty9.server.HttpConnection;

public class SampleClass {

	public static void main(String[] args) throws IOException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\vels\\eclipse-workspace\\SampleProject\\driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
		List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		System.out.println(allLinks.size());
		
		int brokenCount=0;
		for (int i = 0; i < allLinks.size(); i++) {
			WebElement webElement = allLinks.get(i);
			String attribute = webElement.getAttribute("href");
			if (attribute != null) {

				URL url = new URL(attribute);
				URLConnection openConnection = url.openConnection();
				HttpURLConnection  connection = (HttpURLConnection) openConnection;
				int responseCode = connection.getResponseCode();
				if (responseCode!=200) {
					brokenCount++;
					System.out.println(attribute);
				}
				
			}
		}
		System.out.println("Total Broken Link Count :"+brokenCount);
	}
}
