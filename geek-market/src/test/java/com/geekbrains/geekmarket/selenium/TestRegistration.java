package com.geekbrains.geekmarket.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class TestRegistration {
    WebDriver webDriver;

    @BeforeMethod
    private void connect() {
        System.setProperty("webdriver.gecko.driver", "src/geckodriver");
        webDriver = new FirefoxDriver();
        webDriver.get("http://localhost:8189/geekmarket/register/");
    }

    @DataProvider(name = "notNullCheck")
    private Object[][] notNullCheck() {
        return new Object[][]{
                //not null
                {"", "abc", "abc", "abc", "abc", "abc@abc.abc", "188888888"},
                {"abc", "", "abc", "abc", "abc", "abc@abc.abc", "188888888"},
                {"abc", "abc", "", "abc", "abc", "abc@abc.abc", "188888888"},
                {"abc", "abc", "abc", "", "abc", "abc@abc.abc", "188888888"},
                {"abc", "abc", "abc", "abc", "", "abc@abc.abc", "188888888"},
                {"abc", "abc", "abc", "abc", "abc", "", "188888888"},
                {"abc", "abc", "abc", "abc", "abc", "abc@abc.abc", ""},
        };
    }

    @DataProvider(name = "passwordCheck")
    private Object[][] passwordCheck() {
        return new Object[][]{
                //Same password
                {"abc", "abc", "abc1", "abc", "abc", "abc@abc.abc", "188888888"},
                {"abc", "abc1", "abc", "abc", "abc", "abc@abc.abc", "188888888"},
        };
    }

    @DataProvider(name = "emailCheck")
    private Object[][] emailCheck() {
        return new Object[][]{
                //Same password
                {"abc", "abc", "abc", "abc", "abc", "abc@abc", "188888888"},
                {"abc", "abc", "abc", "abc", "abc", "abc@.abc", "188888888"},
                {"abc", "abc", "abc", "abc", "abc", "@abc.abc", "188888888"},
                {"abc", "abc", "abc", "abc", "abc", "abcabc.abc", "188888888"},
                {"abc", "abc", "abc", "abc", "abc", "abcabc", "188888888"},
                {"abc", "abc", "abc", "abc", "abc", "a", "188888888"},
        };
    }

    @Test(dataProvider = "notNullCheck")
    public void notNullCheck
            (
                    String username,
                    String password,
                    String matchingPassword,
                    String firstName,
                    String lastName,
                    String email,
                    String phone
            ) {
        fillFields(username, password, matchingPassword, firstName, lastName, email, phone);
        webDriver.findElement(By.className("btn-primary")).click();
        List<WebElement> list = webDriver.findElements(By.className("error"));
        Assert.assertEquals(1, list.size());
    }

    @Test(dataProvider = "passwordCheck")
    public void passwordCheck
            (
                    String username,
                    String password,
                    String matchingPassword,
                    String firstName,
                    String lastName,
                    String email,
                    String phone
            ) {
        fillFields(username, password, matchingPassword, firstName, lastName, email, phone);
        webDriver.findElement(By.className("btn-primary")).click();
        List<WebElement> list = webDriver.findElements(By.className("error"));
        Assert.assertEquals(1, list.size());
    }

    @Test(dataProvider = "emailCheck")
    public void emailCheck
            (
                    String username,
                    String password,
                    String matchingPassword,
                    String firstName,
                    String lastName,
                    String email,
                    String phone
            ) {
        fillFields(username, password, matchingPassword, firstName, lastName, email, phone);
        webDriver.findElement(By.className("btn-primary")).click();
        List<WebElement> list = webDriver.findElements(By.className("error"));
        Assert.assertEquals(1, list.size());
    }

    private void fillFields
            (
                    String username,
                    String password,
                    String matchingPassword,
                    String firstName,
                    String lastName,
                    String email,
                    String phone
            ) {
        WebElement element = webDriver.findElement(By.cssSelector("#userName"));
        element.click();
        element.clear();
        element.sendKeys(username);
        element = webDriver.findElement(By.cssSelector("#password"));
        element.click();
        element.clear();
        element.sendKeys(password);
        element = webDriver.findElement(By.cssSelector("#matchingPassword"));
        element.click();
        element.clear();
        element.sendKeys(matchingPassword);
        element = webDriver.findElement(By.cssSelector("#firstName"));
        element.click();
        element.clear();
        element.sendKeys(firstName);
        element = webDriver.findElement(By.cssSelector("#lastName"));
        element.click();
        element.clear();
        element.sendKeys(lastName);
        element = webDriver.findElement(By.cssSelector("#email"));
        element.click();
        element.clear();
        element.sendKeys(email);
        element = webDriver.findElement(By.cssSelector("#phone"));
        element.click();
        element.clear();
        element.sendKeys(phone);
    }

    @AfterMethod
    private void disconnect() {
        webDriver.close();
    }
}
