package com.selenium.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasicScript1 {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        // driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        driver.get("https://admin-demo.nopcommerce.com/login");

        String title = driver.getTitle();
        System.out.println("Page Title: " + title);

        WebElement emailBox = driver.findElement(By.id("Email"));
        emailBox.clear();
        emailBox.sendKeys("admin@yourstore.com");

        WebElement passwordBox = driver.findElement(By.id("Password"));
        passwordBox.clear();
        passwordBox.sendKeys("admin");

        WebElement loginButton = driver
                .findElement(By.xpath("//*[@id=\"main\"]/div/section/div/div[2]/div[1]/div/form/div[3]/button"));
        System.out.println("Login Button Text: " + loginButton.getText());
        loginButton.click();
        // driver.findElement(By.cssSelector("button[type='submit']")).click();

        // driver.quit();
    }

}
