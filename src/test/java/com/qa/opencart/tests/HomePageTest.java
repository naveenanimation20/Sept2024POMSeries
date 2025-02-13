package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.constants.AppError;

public class HomePageTest extends BaseTest {

	@BeforeClass
	public void homePageSetup() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void homePageTitleTest() {
		Assert.assertEquals(homePage.getHomePageTitle(), AppConstants.HOME_PAGE_TITLE, AppError.TITLE_NOT_FOUND_ERROR);
	}

	@Test
	public void homePageURLTest() {
		Assert.assertTrue(homePage.getHomePageURL().contains(AppConstants.HOME_PAGE_URL_FRACTION), AppError.URL_NOT_FOUND_ERROR);
	}

	@Test
	public void logoutLinkExistTest() {
		Assert.assertTrue(homePage.isLogoutLinkExist(), AppError.ELEMENT_NOT_FOUND_ERROR);
	}

	@Test
	public void headersTest() {
		List<String> actualHeaders = homePage.getHeadersList();
		System.out.println("home page headers: ==>" + actualHeaders);
		
	}
	
	
	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] {
			{"macbook", 4},
			{"imac", 1},
			{"samsung", 2},
			{"canon", 1},
			{"airtel", 0}
		};
	}
	

	@Test(dataProvider = "getSearchData")
	public void searchTest(String searchKey, int resultCount) {
		searchResultsPage = homePage.doSearch(searchKey);
		Assert.assertEquals(searchResultsPage.getProductResultsCount(), resultCount);
	}
	
	
	@Test(description = "checking logo on home page", enabled = true)
	public void logoTest() {
		Assert.assertTrue(commonsPage.isLogoDisplayed(), AppError.LOGO_NOT_FOUND_ERROR);
	}
	
	@DataProvider
	public Object[][] getFooterData() {
		return new Object[][] {
			{"About Us"},
			{"Contact Us"},
			{"Specials"},
			{"Order History"}
		};
	}
	
	@Test(dataProvider = "getFooterData", description = "checking important footer links on home page", enabled = true)
	public void footerTest(String footerLink) {
		Assert.assertTrue(commonsPage.checkFooterLink(footerLink));
	}
	
	

}
