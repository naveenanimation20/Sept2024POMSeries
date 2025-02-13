package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 1. By Locator: page objects: OR
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");

	// 2. Public page actions - methods (features)
	@Step("get Login Page Title")
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("login page title==>" + title);
		ChainTestListener.log("login page title==>" + title);
		return title;
	}

	@Step("getLoginPageURL")
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("login page url==>" + url);
		return url;
	}

	@Step("checking forgot pwd link is displayed...")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.doIsElementDisplayed(forgotPwdLink);
	}

	@Step("login with username: {0} and password: {1}")
	public HomePage doLogin(String username, String pwd) {
		System.out.println("App creds are: ==> " + username + " : " + pwd);
		eleUtil.waitForElementVisible(emailId, AppConstants.SHORT_TIME_OUT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new HomePage(driver);
	}

}
