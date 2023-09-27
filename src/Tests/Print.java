package Tests;

import PageObjects.BasePage;
import PageObjects.HomePage;
import org.junit.*;
import org.junit.runners.MethodSorters;




@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Print extends BaseTest {


    @Test
    public void test01getAdvertisement () {
        driver.get("https://www.betexplorer.com/#all");
        homePage.clickOniAcceptButtonAndShowAllCountries();
homePage.ClickOnAllCountries();


    }



}
