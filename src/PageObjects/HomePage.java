package PageObjects;


import org.openqa.selenium.*;

import java.util.List;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {super(driver);}

    By IAcceptButton = By.cssSelector("[id='onetrust-accept-btn-handler']");
    By ShowAllCountries = By.cssSelector("[class='button js-toggle-countrymenu closed']");
    By showAllResults = By.cssSelector(".wrap-section__header__link");

    public void clickOniAcceptButtonAndShowAllCountries(){
        scrollTobYElementAndClick(IAcceptButton);
        scrollTobYElementAndClick(ShowAllCountries);
    }
    public void ClickOnAllCountries() {
        List<WebElement> countries;
        countries = driver.findElements(By.cssSelector("[class='list-events list-events--secondary js-divlinks'] li"));
        System.out.println("Country:size " + countries.size());

        for (int i = 0; i < countries.size(); i++) {
            String country = countries.get(i).getText().toLowerCase();
            WebElement countryElement =countries.get(i);
            scrollToElementAndClick(countryElement);
            ClickonpremireLeage(country);
            scrollTobYElementAndClick(showAllResults);

        }
    }
    public void ClickonpremireLeage(String country) {
        By premeieleagy = By.cssSelector("[href=\"/football/"+country+"/premier-league-2022-2023/\"]");
scrollTobYElementAndClick(premeieleagy);

    }
    public void getAllText() {
        List<WebElement> Teams;
        Teams = driver.findElements(By.cssSelector("[class='list-events list-events--secondary js-divlinks'] li"));


    }












    }